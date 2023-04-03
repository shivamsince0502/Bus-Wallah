package com.services;

import com.model.Bus;
import com.model.Location;
import com.model.Route;
import com.model.RoutePath;
import com.payload.BusRoutesPayload;
import com.payload.InterRidesPayload;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteServicesImpl implements RouteServices {
    @Autowired
    private SessionFactory sessionFactory;

    public static void dijkstra(int[][] graph, int start, List<List<Integer>> paths, int[] distances) {
        System.out.println("Dijkstra called");
        int n = graph.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
            distances[i] = Integer.MAX_VALUE;
            paths.add(new ArrayList<Integer>());
        }

        distances[start] = 0;
        paths.get(start).add(start);

        while (true) {
            int u = -1;
            int minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && distances[i] < minDistance) {
                    u = i;
                    minDistance = distances[i];
                }
            }

            if (u == -1) {
                break;
            }

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] > 0) {
                    int distanceThroughU = distances[u] + graph[u][v];
                    if (distanceThroughU < distances[v]) {
                        distances[v] = distanceThroughU;

                        List<List<Integer>> newPaths = new ArrayList<>();
                        for (List<Integer> path : paths) {
                            if (path.size() > 0 && path.get(path.size() - 1) == u) {
                                List<Integer> newPath = new ArrayList<>(path);
                                newPath.add(v);
                                newPaths.add(newPath);
                            }
                        }
                        paths.get(v).clear();
                        paths.get(v).addAll(newPaths.get(0));
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print("Shortest distance from " + start + " to " + i + " is " + distances[i] + ", path is ");
            for (int j = 0; j < paths.get(i).size(); j++) {
                System.out.print(paths.get(i).get(j));
                if (j < paths.get(i).size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }

    }

    public List<List<BusRoutesPayload>>  allBusFromStartToEnd(String start, String end) {
        System.out.println("allBusFromStartToEnd called");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Location> allLocations = session.createQuery("from Location", Location.class).list();
        HashMap<String, Integer> locMap = new HashMap<String, Integer>();
        HashMap<Integer, String> locMapIS = new HashMap<>();
        for (Location location : allLocations) {
            locMap.put(location.getLocationName(), location.getLocationId());
            locMapIS.put(location.getLocationId(), location.getLocationName());
        }
        Integer maxId = 1000;
        String sqlQuery = "SELECT MAX(from_id), MAX(to_id) FROM edge";
        Object[] result = (Object[]) session.createNativeQuery(sqlQuery).getSingleResult();
        Integer maxFromId = (Integer) result[0];
        Integer maxToId = (Integer) result[1];

        maxId = java.lang.Math.max(maxFromId, maxToId);
        maxId++;
        int[][] edges = new int[maxId][maxId];
        String sql = "select from_id, to_id, distance from edge";
        List<Object[]> edgesList = session.createNativeQuery(sql).getResultList();
        if (!edgesList.isEmpty()) {
            for (Object[] row : edgesList) {
                int f = Integer.parseInt(row[0].toString());
                int t = Integer.parseInt(row[1].toString());
                int d = Integer.parseInt(row[2].toString());
                edges[f][t] = d;
                edges[t][f] = d;
            }
        }
        List<List<Integer>> paths = new ArrayList<>();
        int[] distances = new int[maxId];
        dijkstra(edges, locMap.get(start), paths, distances);
        if (distances[locMap.get(end)] == Integer.MAX_VALUE) return null;
        List<Integer> startEndList = new ArrayList<>();
        startEndList.add(locMap.get(start)); startEndList.add(locMap.get(end));

        List<RoutePath> routePaths = session.createQuery("from RoutePath", RoutePath.class).list();
        HashMap<Integer, Integer> routeMap = new HashMap<>();
        HashMap<String, Integer> routePathMap = new HashMap<>();
        for (RoutePath routePath : routePaths) routeMap.put(routePath.getLocationId(), routePath.getRouteId());
        String rpsql = "SELECT r.route_id, GROUP_CONCAT(rp.location_id ORDER BY rp.seq_no ASC) AS sorted_location_ids " +
                "FROM route r " +
                "JOIN route_path rp ON r.route_id = rp.route_id " +
                "GROUP BY r.route_id ";
        SQLQuery rpquery = session.createSQLQuery(rpsql);
        List<Object[]> rpList = rpquery.list();
        for (Object[] row : rpList) {
            String routeP = row[1].toString();
            System.out.print("roteP : " + routeP);
            String[] routeArray = routeP.split(",");
            System.out.print("routeP arr : " + routeArray);
            int routeId = Integer.parseInt(row[0].toString());
            for (int i = 0; i < routeArray.length-1; i++) {
                String from = routeArray[i];
                String to = routeArray[i+1];
                System.out.println(" from : " + from + " to : "+to);
                routePathMap.put(from+to, routeId);
                routePathMap.put(to+from, routeId);
            }
        }
        Iterator<Map.Entry<String, Integer>> iterate1 = routePathMap.entrySet().iterator();
        System.out.print("Entries: ");
        while (iterate1.hasNext()) {
            System.out.print(iterate1.next());
            System.out.print(", ");
        }

        TreeMap<Integer, List<Integer>> allRoutes = new TreeMap<>();
        for (int i = 0; i < paths.size(); i++) {
            List<Integer> path = new ArrayList<>();
            int totDist = 0;

                for (int j = 0; j < paths.get(i).size(); j++) {
                    if(j < paths.get(i).size()-1)
                        totDist += edges[paths.get(i).get(j)][paths.get(i).get(j+1)];
                    path.add(paths.get(i).get(j));

                }
                System.out.println(totDist + " : " + path);

            if(path.contains(locMap.get(start)) && path.contains(locMap.get(end)) && totDist != 0) {
                System.out.print("This is going to be added " + totDist + " : " + path);
                allRoutes.put(totDist, path);
            }

        }
        System.out.println("all Routes : "+ allRoutes);
        List<List<BusRoutesPayload>> resultRoutes = new ArrayList<>();
        System.out.println(allRoutes);
        for (Map.Entry<Integer, List<Integer>> p : allRoutes.entrySet()){
            Integer currDist = p.getKey();
            List<Integer> shortestPath = p.getValue();
            System.out.print(currDist + " : ");
            System.out.println(shortestPath);
            List<BusRoutesPayload> busRoutesPayloadList = new ArrayList<>();
            List<Integer> rps = new ArrayList<>();
            for (int i = 0; i < shortestPath.size() - 1; i++) {
                String fromRoute = shortestPath.get(i).toString();
                String toRoute = shortestPath.get(i+1).toString();
                String from = locMapIS.get(Integer.parseInt(fromRoute));
                String to = locMapIS.get(Integer.parseInt(toRoute));

                System.out.println(from + " " + to);
                System.out.println(fromRoute + " " + toRoute);
                int rId = routePathMap.get(fromRoute+toRoute);
                if(rId == 0) routePathMap.get(toRoute+fromRoute);
                System.out.println(routePathMap.get(fromRoute+toRoute));
                if(rId <= 0) continue;
                Route route = session.get(Route.class, rId);
                System.out.print("curr : " + from+to);
                List<String> ploc = new ArrayList<String>();
                if (i == 0 || (busRoutesPayloadList.size() > 0 && rId != busRoutesPayloadList.get(busRoutesPayloadList.size() - 1).getRoute().getRouteId())) {
                    BusRoutesPayload busRoutesPayload = new BusRoutesPayload();
                    ploc.add(from); ploc.add(to);
                    busRoutesPayload.setRoute(route);
                    busRoutesPayload.setPathLocations(ploc);
                    busRoutesPayloadList.add(busRoutesPayload);
                    rps.add(rId);
                    System.out.println(" buspayload : " + busRoutesPayload);
                } else {
                    System.out.println(busRoutesPayloadList);
                    BusRoutesPayload bp = new BusRoutesPayload();
                    if(busRoutesPayloadList.size() > 0){
                        bp.setRoute(busRoutesPayloadList.get(busRoutesPayloadList.size() - 1).getRoute());
                        bp.setPathLocations(busRoutesPayloadList.get(busRoutesPayloadList.size() - 1).getPathLocations());
                    }
                    ploc = bp.getPathLocations();
                    ploc.add(to);
                    bp.setPathLocations(ploc);
                    System.out.println(" buspayload : " + bp);
                }
            }
            for (int i = 0; i < rps.size(); i++) System.out.print("\n busRoutesPayloadList :" + rps.get(i) + " ");
            List<Bus> busList = session.createQuery("from Bus", Bus.class).list();
            HashMap<Integer, Bus> busMap = new HashMap<>();
            for (Object row : busList) {
                Bus bus = (Bus) row;
                Bus newBus = new Bus(bus.getBusId(), bus.getBusName(), bus.getBusNumber(), true, bus.getRouteId(), bus.getNoOfSeats());
                busMap.put(bus.getRouteId(), newBus);
            }
            for (BusRoutesPayload busRoutesPayload : busRoutesPayloadList) {
                if (busRoutesPayload.getRoute() != null && busMap.get(busRoutesPayload.getRoute().getRouteId()) != null)
                    busRoutesPayload.setBus(busMap.get(busRoutesPayload.getRoute().getRouteId()));
                System.out.println(busRoutesPayload);
                List<String> currAllLocations = busRoutesPayload.getPathLocations();
                int totDistOfPath = 0;
                for(int i = 0; i < currAllLocations.size()-1; i++)
                    totDistOfPath += edges[locMap.get(currAllLocations.get(i))][locMap.get(currAllLocations.get(i+1))];

                busRoutesPayload.setTotalDistance(totDistOfPath);
            }
            resultRoutes.add(busRoutesPayloadList);
        }
        return resultRoutes;
    }



    @Override
    public List<List<BusRoutesPayload>> allBusFromStartToEndIntermediaryPoints(InterRidesPayload interRidesPayload) {
        System.out.println("allBusFromStartToEndIntermediaryPoints called");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String start = interRidesPayload.getStart();
        String end = interRidesPayload.getEnd();
        List<String> pa = interRidesPayload.getIntercities();
        int tm = pa.size();
        List<Location> allLocations = session.createQuery("from Location", Location.class).list();
        HashMap<String, Integer> locMap = new HashMap<String, Integer>();
        HashMap<Integer, String> locMapIS = new HashMap<>();
        for (Location location : allLocations) {
            locMap.put(location.getLocationName(), location.getLocationId());
            locMapIS.put(location.getLocationId(), location.getLocationName());
        }
        Integer maxId = 1000;
        String sqlQuery = "SELECT MAX(from_id), MAX(to_id) FROM edge";
        Object[] result = (Object[]) session.createNativeQuery(sqlQuery).getSingleResult();
        Integer maxFromId = (Integer) result[0];
        Integer maxToId = (Integer) result[1];

        maxId = java.lang.Math.max(maxFromId, maxToId);
        maxId++;
        int[][] edges = new int[maxId][maxId];
        String sql = "select from_id, to_id, distance from edge";
        List<Object[]> edgesList = session.createNativeQuery(sql).getResultList();
        if (!edgesList.isEmpty()) {
            for (Object[] row : edgesList) {
                int f = Integer.parseInt(row[0].toString());
                int t = Integer.parseInt(row[1].toString());
                int d = Integer.parseInt(row[2].toString());
                edges[f][t] = d;
                edges[t][f] = d;
            }
        }

        List<RoutePath> routePaths = session.createQuery("from RoutePath", RoutePath.class).list();
        HashMap<Integer, Integer> routeMap = new HashMap<>();
        HashMap<String, Integer> routePathMap = new HashMap<>();
        int[][] graph = new int[maxId][maxId];
        for (RoutePath routePath : routePaths) routeMap.put(routePath.getLocationId(), routePath.getRouteId());
        String rpsql = "SELECT r.route_id, GROUP_CONCAT(rp.location_id ORDER BY rp.seq_no ASC) AS sorted_location_ids " +
                "FROM route r " +
                "JOIN route_path rp ON r.route_id = rp.route_id " +
                "GROUP BY r.route_id ";
        SQLQuery rpquery = session.createSQLQuery(rpsql);
        List<Object[]> rpList = rpquery.list();
        for (Object[] row : rpList) {
            String routeP = row[1].toString();
            System.out.print("roteP : " + routeP);
            String[] routeArray = routeP.split(",");
            System.out.print("routeP arr : " + routeArray);
            int routeId = Integer.parseInt(row[0].toString());
            for (int i = 0; i < routeArray.length-1; i++) {
                String from = routeArray[i];
                String to = routeArray[i+1];
                System.out.println(" from : " + from + " to : "+to);
                routePathMap.put(from+to, routeId);
                routePathMap.put(to+from, routeId);
                int f = Integer.parseInt(from);
                int t = Integer.parseInt(to);
                int d = edges[f][t];
                graph[f][t] = d;
                graph[t][f] = d;
            }
        }
        Iterator<Map.Entry<String, Integer>> iterate1 = routePathMap.entrySet().iterator();
        System.out.print("Entries: ");
        while (iterate1.hasNext()) {
            System.out.print(iterate1.next());
            System.out.print(", ");
        }
        int[] interPath = new int[tm];
        List<Integer> ip = new ArrayList<>();
        ip.add(locMap.get(start));
        for(int i = 0; i < tm; i++){
            interPath[i] = locMap.get(pa.get(i));
            ip.add(interPath[i]);
        }
        ip.add(locMap.get(end));
        List<List<Integer>> paths = findPaths(graph, locMap.get(start), locMap.get(end));
        TreeMap<Integer, List<Integer>> allRoutes = new TreeMap<>();
        for (int i = 0; i < paths.size(); i++) {
            List<Integer> path = new ArrayList<>();
            int totDist = 0;

            for (int j = 0; j < paths.get(i).size(); j++) {
                if(j < paths.get(i).size()-1)
                    totDist += edges[paths.get(i).get(j)][paths.get(i).get(j+1)];
                path.add(paths.get(i).get(j));

            }
            System.out.println(totDist + " : " + path);

            if(path.size() > 1 && path.containsAll(ip) && totDist != 0) {
                System.out.print("This is going to be added " + totDist + " : " + path);
                allRoutes.put(totDist, path);
            }

        }
        List<List<BusRoutesPayload>> resultRoutes = new ArrayList<>();
        System.out.println(allRoutes);
        for (Map.Entry<Integer, List<Integer>> p : allRoutes.entrySet()){
            Integer currDist = p.getKey();
            List<Integer> shortestPath = p.getValue();
            List<BusRoutesPayload> routes = new ArrayList<>();
            List<Integer> rps = new ArrayList<>();
            for (int i = 0; i < shortestPath.size() - 1; i++) {
                String fromRoute = shortestPath.get(i).toString();
                String toRoute = shortestPath.get(i+1).toString();
                String from = locMapIS.get(Integer.parseInt(fromRoute));
                String to = locMapIS.get(Integer.parseInt(toRoute));

                System.out.println(from + " " + to);
                System.out.println(fromRoute + " " + toRoute);
                if(!routePathMap.containsKey(fromRoute+toRoute)) continue;
                int rId = routePathMap.get(fromRoute+toRoute);
                System.out.println(routePathMap.get(fromRoute+toRoute));
                if(rId <= 0) continue;
                Route route = session.get(Route.class, rId);
                System.out.print("curr : " + from+to);
                List<String> ploc = new ArrayList<String>();
                if (i == 0 || (routes.size() > 0 && rId != routes.get(routes.size() - 1).getRoute().getRouteId())) {
                    BusRoutesPayload busRoutesPayload = new BusRoutesPayload();
                    ploc.add(from); ploc.add(to);
                    busRoutesPayload.setRoute(route);
                    busRoutesPayload.setPathLocations(ploc);
                    routes.add(busRoutesPayload);
                    rps.add(rId);
                    System.out.println(" buspayload : " + busRoutesPayload);
                } else {
                    BusRoutesPayload bp = routes.get(routes.size() - 1);
                    ploc = bp.getPathLocations();
                    ploc.add(to);
                    bp.setPathLocations(ploc);
                    System.out.println(" buspayload : " + bp);
                }
            }
            for (int i = 0; i < rps.size(); i++) System.out.print("\n routes :" + rps.get(i) + " ");
            List<Bus> busList = session.createQuery("from Bus", Bus.class).list();
            HashMap<Integer, Bus> busMap = new HashMap<>();
            for (Object row : busList) {
                Bus bus = (Bus) row;
                Bus newBus = new Bus(bus.getBusId(), bus.getBusName(), bus.getBusNumber(), true, bus.getRouteId(), bus.getNoOfSeats());
                busMap.put(bus.getRouteId(), newBus);
            }
            for (BusRoutesPayload busRoutesPayload : routes) {
                if (busRoutesPayload.getRoute() != null && busMap.get(busRoutesPayload.getRoute().getRouteId()) != null)
                    busRoutesPayload.setBus(busMap.get(busRoutesPayload.getRoute().getRouteId()));
                List<String> currAllLocations = busRoutesPayload.getPathLocations();
                int totDistOfPath = 0;
                for(int i = 0; i < currAllLocations.size()-1; i++)
                    totDistOfPath += edges[locMap.get(currAllLocations.get(i))][locMap.get(currAllLocations.get(i+1))];

                busRoutesPayload.setTotalDistance(totDistOfPath);
                System.out.println(busRoutesPayload);
            }
            resultRoutes.add(routes);
        }
        return resultRoutes;
    }




    public static List<List<Integer>> findPaths(int[][] graph, int start, int end) {
        System.out.println("FindPaths called");
        int n = graph.length;
        List<List<Integer>> allPaths = new ArrayList<>();

        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(path -> getPathWeight(path, graph)));
        List<Integer> initialPath = new ArrayList<>();
        initialPath.add(start);
        queue.offer(initialPath);

        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int current = currentPath.get(currentPath.size() - 1);

            if (current == end) {
                allPaths.add(currentPath);
            }

            for (int i = 0; i < n; i++) {
                if (graph[current][i] > 0 && !currentPath.contains(i)) {
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(i);
                    queue.offer(newPath);
                }
            }
        }

        // Sort all paths in ascending order of their weights
        allPaths.sort(Comparator.comparingInt(path -> getPathWeight(path, graph)));

        for (int i = 0; i < allPaths.size(); i++) {
            for (int j = 0; j < allPaths.get(i).size(); j++) {
                System.out.print(allPaths.get(i).get(j));
                if (j < allPaths.get(i).size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }

        return allPaths;
    }

    private static int getPathWeight(List<Integer> path, int[][] graph) {
        int weight = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            weight += graph[path.get(i)][path.get(i + 1)];
        }
        return weight;
    }



    @Override
    public List<String> chooseRoute(String routeName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Location> allLocations = session.createQuery("from Location", Location.class).list();
        List<String> allLocationNames = new ArrayList<>();
        HashMap<String, Integer> locMap = new HashMap<String, Integer>();
        HashMap<Integer, String> locMapIS = new HashMap<>();
        for (Location location : allLocations) {
            locMap.put(location.getLocationName(), location.getLocationId());
            locMapIS.put(location.getLocationId(), location.getLocationName());
            allLocationNames.add(location.getLocationName());
        }
        String sql = "select * from route where route_name = '"+routeName+"'";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Route.class);
        Route route = (Route)query.uniqueResult();
        int routeId = route.getRouteId();
        String rpsql = "SELECT r.route_id, GROUP_CONCAT(rp.location_id ORDER BY rp.seq_no ASC) AS sorted_location_ids " +
                "FROM route r " +
                "JOIN route_path rp ON r.route_id = rp.route_id " +
                "Where r.route_id = "+routeId+" "+
                "GROUP BY r.route_id ";
        SQLQuery rpquery = session.createSQLQuery(rpsql);
        List<Object[]> resultList = rpquery.getResultList();

        if(resultList.size() == 0) return  allLocationNames;

        String routeP = resultList.get(0)[1].toString();
        System.out.print("roteP : " + routeP);
        String[] routeArray = routeP.split(",");
        System.out.print("routeP arr : " + routeArray);
        int lasLocId = Integer.parseInt(routeArray[routeArray.length-1]);
        String psql = "SELECT DISTINCT `from_id`, `to_id` " +
                "FROM edge " +
                "WHERE `from_id` = "+lasLocId+" OR `to_id` = "+lasLocId;
        SQLQuery query1 = session.createSQLQuery(psql);
        List<Object[]> query1ResultList = query1.getResultList();
        List<String> reqResult = new ArrayList<>();
        for(Object[] row : query1ResultList) {
            int from = Integer.parseInt(row[0].toString());
            int to = Integer.parseInt(row[1].toString());
            if(from != lasLocId) reqResult.add(locMapIS.get(from));
            if(to != lasLocId) reqResult.add(locMapIS.get(to));
        }
        return reqResult;
    }

    @Override
    public Route createRoute(Route route) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        route.setActive(true);
        session.save(route);
        transaction.commit();
        session.close();
        return route;
    }

    @Override
    public List<String> getAllRoutesWithName(String routeName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT route_name FROM route WHERE route_name LIKE '"+routeName+"%'";
        SQLQuery query = session.createSQLQuery(sql);
        List<String> routeNames = query.list();
        transaction.commit();
        session.close();
        return routeNames;
    }


}







