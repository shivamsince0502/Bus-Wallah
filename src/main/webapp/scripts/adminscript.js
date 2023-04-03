


const addbus = document.getElementById('addbus')
const connect = document.getElementById('connect')
const createroute = document.getElementById('createroutehref')
const addlocation = document.getElementById('addlocationshre')
const newroute = document.getElementById('addnewroutehref')
const addriver = document.getElementById('addnewdriverhref')
const showroutes = document.getElementById('showrouteshref')

const addbusdiv = document.getElementById('addbusoperation')
const addedgediv = document.getElementById('connectedge')
const createroutediv = document.getElementById('createroute')
const addlocationdiv = document.getElementById('addlocation')
const createnewroutediv = document.getElementById('createnewroute')
const adddriverdiv = document.getElementById('adddriverdiv')
const showroutesdiv = document.getElementById('showroutesdiv')

window.onload = firstfunction
async function firstfunction() {
    createroutediv.style.display = "none"
    addedgediv.style.display = "none"
    addbusdiv.style.display = "block"
    addbus.click()
    addlocationdiv.style.display = "none"
    createnewroutediv.style.display = "none"
    adddriverdiv.style.display = "none"
    showroutesdiv.style.display = "none"
}


showroutes.addEventListener('click', async (e) => {
    e.preventDefault()
    createroutediv.style.display = "none"
    addedgediv.style.display = "none"
    addbusdiv.style.display = "none"
    addlocationdiv.style.display = "none"
    createnewroutediv.style.display = "none"
    adddriverdiv.style.display = "none"
    showroutesdiv.style.display = "block"

    document.getElementById("head-tb-curr").innerHTML = "All Active Routes ";
    const showuprides = document.getElementById("select-ride")
    showuprides.innerHTML = ""
    var alluprides = document.createElement('table');
    alluprides.setAttribute('class', 'table-striped')
    alluprides.setAttribute('class', 'table-striped1')
    alluprides.setAttribute('class', 'table')
    alluprides.setAttribute('id', 'alluprides');
    showuprides.appendChild(alluprides);

    var upridetablehead = alluprides.insertRow(0);

    var tableHeadArray = new Array();
    tableHeadArray = ['Route Name', 'Start Location', 'End Location', 'Locations', 'Buses', 'Show Route'];
    for (var i = 0; i < tableHeadArray.length; i++) {
        var th = document.createElement('th');
        th.innerHTML = tableHeadArray[i];
        upridetablehead.appendChild(th);
    }
    console.log(upridetablehead)

    alluprides.setAttribute('cellpadding', '10px');

    let uprideurl = serverurl + '/routepath/allroutesdata'
    await fetch(uprideurl, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        }
    }).then((res) => res.json())
        .then(async (uprides) => {
            for (const item of uprides) {
                let routeName = item.routeName;
                let start = item.startLocation;
                let end = item.endLocation
                let nooflocations = item.noOfStations;
                let noofbus = item.noOfBuses;

                var tableDataArrayOfRide = new Array();
                let tr = alluprides.insertRow(-1)
                tableDataArrayOfRide = [routeName, start, end, nooflocations, noofbus];

                for (var i = 0; i < tableDataArrayOfRide.length; i++) {

                    var td = tr.insertCell(-1);
                    td.innerHTML = tableDataArrayOfRide[i];
                }

                var td = tr.insertCell(-1);

                // add a button
                var button = document.createElement('button');

                // set button attributes.
                button.setAttribute('type', 'button');
                button.setAttribute('class', 'btn btn-dark')

                button.innerHTML = 'Show Route';
                // set onclick event.
                button.setAttribute('onclick', 'showRoute(this)');
                td.appendChild(button);

            }
        })
})

async function showRoute(el) {
    var uTable = document.getElementById('alluprides');
    // uTable.deleteRow(el.parentNode.parentNode.rowIndex);
    let index = el.parentNode.parentNode.rowIndex;
    var oCells = uTable.rows.item(index).cells;
    let routeName = oCells[0].innerHTML;
    let furl = serverurl + '/routepath/wholepath/' + routeName
    let fd = await fetch(furl, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        }
    })
    let allpath = await fd.json()

    var allprevrides = document.createElement('table');
    allprevrides.setAttribute('class', 'table-striped')
    allprevrides.setAttribute('class', 'table')
    allprevrides.setAttribute('id', 'allprevrides');
    document.getElementById('showpath').innerHTML = ''
    document.getElementById('showpath').appendChild(allprevrides)
    var upridetablehead = allprevrides.insertRow(0);
    var tableHeadArray = new Array();
    tableHeadArray = ['S no', 'Location Name'];
    for (var i = 0; i < tableHeadArray.length; i++) {
        var th = document.createElement('th');
        th.innerHTML = tableHeadArray[i];
        upridetablehead.appendChild(th);
    }
    console.log(upridetablehead)

    allprevrides.setAttribute('cellpadding', '10px');
    let sno = 1;
    for (const item of allpath) {
        let location = item;
        var tableDataArrayOfRide = new Array();
        let tr = allprevrides.insertRow(-1)
        tableDataArrayOfRide = [sno, location];
        
        for (var i = 0; i < tableDataArrayOfRide.length; i++) {
            var td = tr.insertCell(-1);
            td.innerHTML = tableDataArrayOfRide[i];
        }
        sno++;
    }
    $("#routePath").modal('show');

}


addriver.addEventListener('click', async (e) => {
    e.preventDefault()
    createroutediv.style.display = "none"
    addedgediv.style.display = "none"
    addbusdiv.style.display = "none"
    addlocationdiv.style.display = "none"
    createnewroutediv.style.display = "none"
    showroutesdiv.style.display = "none"
    adddriverdiv.style.display = "block"
    let busselect = document.getElementById('busname')
    let allburl = serverurl + '/bus/allbus'
    let d = await fetch(allburl, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        }
    })
    let allbuses = await d.json();
    console.log(allbuses)
    busselect.innerHTML = ''
    let cname = "Select"
    let option = document.createElement("option")
    option.setAttribute('value', cname);
    let optionText = document.createTextNode(cname);
    option.appendChild(optionText);
    busselect.appendChild(option);
    allbuses.forEach((data) => {
        let carname = data.busName;
        // console.log(cityname)
        let option = document.createElement("option")
        option.setAttribute('value', carname);
        let optionText = document.createTextNode(carname);
        option.appendChild(optionText);
        busselect.appendChild(option);

    })

})




newroute.addEventListener('click', (e) => {
    e.preventDefault();
    createroutediv.style.display = "none"
    addedgediv.style.display = "none"
    addbusdiv.style.display = "none"
    addlocationdiv.style.display = "none"
    createnewroutediv.style.display = "block"
    adddriverdiv.style.display = "none"
    showroutesdiv.style.display = "none"
})

addbus.addEventListener('click', async (e) => {
    e.preventDefault()
    console.log('addbus function called')
    createroutediv.style.display = "none"
    addedgediv.style.display = "none"
    addbusdiv.style.display = "block"
    addlocationdiv.style.display = "none"
    createnewroutediv.style.display = "none"
    adddriverdiv.style.display = "none"
    showroutesdiv.style.display = "none"

    let addroutesel = document.getElementById('rnameselect')
    let fetallrouteurl = serverurl + '/routepath/allroutes'
    let rd = await fetch(fetallrouteurl, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json"
        }
    })
    let routedetails = await rd.json();
    console.log(routedetails)
    addroutesel.innerHTML = ''
    let cname = "Select"
    let option = document.createElement("option")
    option.setAttribute('value', cname);
    let optionText = document.createTextNode(cname);
    option.appendChild(optionText);
    addroutesel.appendChild(option);
    routedetails.forEach(data => {
        let carname = data
        // console.log(cityname)
        let option = document.createElement("option")
        option.setAttribute('value', carname);
        let optionText = document.createTextNode(carname);
        option.appendChild(optionText);
        addroutesel.appendChild(option);

    })
})

let addbusformaction = document.getElementById('addbusform')
addbusformaction.addEventListener('submit', async (e) => {
    e.preventDefault()
    if (document.getElementById('addbusname').style.border === "2px solid red") showToast('Bus Name already exists.', 'error')
    else if (document.getElementById('addbusnumber').style.border === "2px solid red") showToast('Bus Number already exists.', 'error')
    else {
        const formData = new FormData(addbusformaction);
        let formDataObject = Object.fromEntries(formData.entries());
        let formDataJsonString = JSON.stringify(formDataObject);
        console.log(formDataJsonString)
        let addburl = serverurl + '/bus/addbus';
        let res = await fetch(addburl, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
            },
            body: formDataJsonString
        })

        let addedbus = await res.json();
        console.log(addbus)
        if (res.ok) {
            showToast('Bus Added', 'success')
            document.getElementById('addbusnumber').value = ''
            document.getElementById('addbusname').value = ''
        } else {
            showToast('Error occured in adding bus data incorrect', 'error')
        }
    }

})


connect.addEventListener('click', (e) => {
    e.preventDefault();
    console.log('connect button clicked. ')
    createroutediv.style.display = "none"
    addedgediv.style.display = "block"
    addbusdiv.style.display = "none"
    addlocationdiv.style.display = "none"
    createnewroutediv.style.display = "none"
    showroutesdiv.style.display = "none"
    adddriverdiv.style.display = "none"
})



const searchBox1 = document.getElementById('from');
const resultsList1 = document.getElementById('from-list');

let debounceTimer;
async function searchItems1(searchTerm) {
    resultsList1.innerHTML = "";
    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(() => {
        let feturl = serverurl + 'location/getLocation/' + searchTerm;

        fetch(feturl, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            }
        })
            .then((res) => res.json())
            .then((matchedItems) => {
                console.log(matchedItems);
                resultsList1.innerHTML = ""
                if (matchedItems.length === 0) {
                    let pfrom = document.getElementById('fromlocid').innerHTML = 'No locations found try else'

                } else {
                    matchedItems.forEach(item => {
                        document.getElementById('fromlocid').innerHTML = ""
                        const li = document.createElement('option');
                        li.value = item;
                        li.textContent = li.value
                        resultsList1.appendChild(li);
                    });
                }
                resultsList1.style.display = "block"
            })
    }, 500)
}

searchBox1.addEventListener('input', event => {
    const searchTerm = event.target.value;
    if (searchTerm != "")
        searchItems1(searchTerm);
    else resultsList1.innerHTML = ""
});

resultsList1.addEventListener('click', event => {
    const clickedItem = event.target;
    searchBox1.value = clickedItem.textContent;
    resultsList1.innerHTML = ""
});

resultsList1.addEventListener('dragover', (e) => {
    let currow = e.target
    console.log(currow)
})



const searchBox2 = document.getElementById('to');
const resultsList2 = document.getElementById('to-list');

async function searchItems2(searchTerm) {
    resultsList2.innerHTML = "";
    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(() => {
        let feturl = serverurl + 'location/getLocation/' + searchTerm;

        fetch(feturl, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            }
        })
            .then((res) => res.json())
            .then((matchedItems) => {
                console.log(matchedItems);
                if (matchedItems.length === 0) {
                    let pfrom = document.getElementById('tolocid').innerHTML = 'No locations found try else'

                } else {
                    matchedItems.forEach(item => {
                        document.getElementById('tolocid').innerHTML = ""
                        let li = document.createElement('option');
                        li.value = item;
                        li.textContent = li.value
                        resultsList2.appendChild(li);
                    });
                }
                resultsList2.style.display = "block"
            })
    }, 500)
}

searchBox2.addEventListener('input', event => {
    const searchTerm = event.target.value;
    if (searchTerm != "")
        searchItems2(searchTerm);
    else resultsList2.innerHTML = ""
});

resultsList2.addEventListener('click', event => {
    const clickedItem = event.target;
    console.log(clickedItem)
    searchBox2.value = clickedItem.textContent;
    resultsList2.innerHTML = ""
});

resultsList2.addEventListener('dragover', (e) => {
    let currow = e.target
    console.log(currow)
})


const addlocations = document.getElementById('pointlocation');

addlocations.addEventListener('input', async (e) => {
    let searchTerm = e.target.value;
    if (searchTerm === "") searchTerm = "hello"
    debounceTimer = setTimeout(() => {
        let feturl = serverurl + '/location/getLocation/' + searchTerm;
        fetch(feturl, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            }
        })
            .then((res) => res.json())
            .then((matchedItems) => {
                for (let t = 0; t < matchedItems.length; t++) {
                    matchedItems[t] = matchedItems[t].toLowerCase();
                }
                let nsearchTerm = searchTerm.toLowerCase();
                if (matchedItems.includes(nsearchTerm)) {
                    console.log(searchTerm)
                    addlocations.style.border = "2px solid red"
                }
                else addlocations.style.border = "1px solid black"
            })
    }, 500)
});


const addbusnameinp = document.getElementById('addbusname');

addbusnameinp.addEventListener('input', async (e) => {
    let searchTerm = e.target.value;
    if (searchTerm === "") searchTerm = "hello"
    debounceTimer = setTimeout(() => {
        let feturl = serverurl + '/bus/allbuwithname/' + searchTerm;
        fetch(feturl, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            }
        })
            .then((res) => res.json())
            .then((matchedItems) => {
                console.log(matchedItems);
                if (matchedItems.length > 0) addbusnameinp.style.border = "2px solid red"
                else addbusnameinp.style.border = "1px solid black"
            })
    }, 500)
});

const addbusnumberinp = document.getElementById('addbusnumber');

addbusnumberinp.addEventListener('input', (e) => {
    let searchTerm = e.target.value;

    debounceTimer = setTimeout(() => {
        let feturl = serverurl + '/bus/allbuwithnumber/' + searchTerm;
        fetch(feturl, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            }
        })
            .then((res) => res.json())
            .then((matchedItems) => {
                console.log(matchedItems);
                if (matchedItems.length > 0) {
                    console.log('2px border : ' + addbusnumberinp)
                    addbusnumberinp.style.border = "2px solid red"
                }
                else {
                    addbusnumberinp.style.border = "1px solid black"
                }
            })
    }, 500)
});


let addroutesel = document.getElementById('allroutesselect')
createroute.addEventListener('click', async (e) => {
    e.preventDefault()

    addedgediv.style.display = "none"
    addbusdiv.style.display = "none"
    createroutediv.style.display = "block"
    addlocationdiv.style.display = "none"
    createnewroutediv.style.display = "none"
    adddriverdiv.style.display = "none"
    showroutesdiv.style.display = "none"
    let fetallrouteurl = serverurl + '/routepath/allroutes'
    let rd = await fetch(fetallrouteurl, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json"
        }
    })
    let routedetails = await rd.json();
    console.log(routedetails)
    routedetails.innerHTML = ''
    let cname = "Select"
    let option = document.createElement("option")
    option.setAttribute('value', cname);
    let optionText = document.createTextNode(cname);
    option.appendChild(optionText);
    addroutesel.appendChild(option);
    console.log(rd.headers.get('content-type'));
    routedetails.forEach(data => {
        let carname = data
        // console.log(cityname)
        let option = document.createElement("option")
        option.setAttribute('value', carname);
        let optionText = document.createTextNode(carname);
        option.appendChild(optionText);
        addroutesel.appendChild(option);
    })
})

let routepathselect = document.getElementById('routepaths')
addroutesel.addEventListener(`change`, async (e) => {
    document.getElementById('routeselp').innerHTML = ''
    routepathselect.innerHTML = ''
    document.getElementById('result-list5').innerHTML = ''
    const select = e.target;
    const value = select.value;
    const desc = select.options[select.selectedIndex].text;
    let fetallrouteurl = serverurl + '/route/chooseroute/' + desc
    let rd = await fetch(fetallrouteurl, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json"
        }
    })
    let routedetails = await rd.json();
    if (routedetails.length === 0) {
        document.getElementById('routeselp').innerHTML = 'No Location are in this route';
        showToast('no location connected with it.', 'error')
        showToast('Please create a route path', 'success')
    }
    console.log(routedetails)
    let cname = "Select"
    routedetails.innerHTML = ''
    let option = document.createElement("option")
    option.setAttribute('value', cname);
    let optionText = document.createTextNode(cname);
    option.appendChild(optionText);
    routepathselect.appendChild(option);
    routedetails.forEach(data => {
        let carname = data
        // console.log(cityname)
        let option = document.createElement("option")
        option.setAttribute('value', carname);
        let optionText = document.createTextNode(carname);
        option.appendChild(optionText);
        routepathselect.appendChild(option);
    })
});

var routepathsloc = [];
var resulpathloc = [];

routepathselect.addEventListener('change', async (e) => {
    e.preventDefault();
    resulpathloc = []
    const select = e.target;

    let value = select.value;
    if (value != routepathsloc[routepathsloc.length - 1] && value != "Select")
        routepathsloc.push(value)
    let belowresdiv = document.getElementById('result-list5')
    belowresdiv.innerHTML = ""
    let routepathtable = document.createElement('table')
    routepathtable.setAttribute('id', 'routepathtable');
    routepathtable.setAttribute('class', 'table table-hover')
    belowresdiv.appendChild(routepathtable)
    for (i = 0; i < routepathsloc.length; ++i) {
        if (i > 0 && routepathsloc[i - 1] == routepathsloc[i]) continue;
        if (routepathsloc[i] === "") continue;
        resulpathloc.push(routepathsloc[i])
        let loc = routepathsloc[i];
        var newtr = routepathtable.insertRow(-1);
        var sno = newtr.insertCell(-1)
        sno.innerHTML = i + 1
        var ntrdata = newtr.insertCell(-1)
        ntrdata.innerHTML = loc;

    }
    console.log(resulpathloc)
    const desc = select.options[select.selectedIndex].text;
    let routepathselect = document.getElementById('routepaths')
    let fdata = {}
    fdata.currLocation = desc
    fdata.prevLocations = resulpathloc
    let fdatastring = JSON.stringify(fdata)
    let fetallrouteurl = serverurl + '/location/nextlocation'
    let rd = await fetch(fetallrouteurl, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json"
        },
        body: fdatastring
    })
    let routedetails = await rd.json();
    console.log(routedetails)
    routepathselect.innerHTML = ""
    let cname = "Select"
    let option = document.createElement("option")
    option.setAttribute('value', cname);
    let optionText = document.createTextNode(cname);
    option.appendChild(optionText);
    routepathselect.appendChild(option);
    routedetails.forEach(data => {
        let carname = data
        // console.log(cityname)
        let option = document.createElement("option")
        option.setAttribute('value', carname);
        let optionText = document.createTextNode(carname);
        option.appendChild(optionText);
        routepathselect.appendChild(option);
    })

})


const routepathformdet = document.getElementById('routepathform')
routepathformdet.addEventListener('submit', async (e) => {
    e.preventDefault();
    if (resulpathloc.length != 0 && document.getElementById('allroutesselect').value != 'Select') {
        let createroutepathform = {}
        let routename = document.getElementById('allroutesselect').value
        createroutepathform.routeName = routename
        createroutepathform.cities = resulpathloc;
        let createroutepathjson = JSON.stringify(createroutepathform)
        let crpurl = serverurl + '/routepath/createroutepath';
        let crpurlres = await fetch(crpurl, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
            },
            body: createroutepathjson
        })
        let data = await crpurlres.json();
        console.log(data);
        if (data.routeName) {
            showToast('Route Path created', 'success')
            document.getElementById('routepathtable').innerHTML = ''
            resulpathloc = [];
            routepathsloc = [];
        } else showToast('Cannot create the route path', 'error')
    } else showToast('please create a route path first.', 'error');
})



function showToast(message, type) {
    console.log(message)
    var toast = document.createElement('div');
    toast.classList.add('toast');

    if (type === 'success') {
        toast.classList.add('toast-success');
    } else if (type === 'error') {
        toast.classList.add('toast-error');
    }

    toast.innerText = message;

    var toastContainer = document.getElementById('toast-container');
    toastContainer.appendChild(toast);

    setTimeout(function () {
        toast.remove();
    }, 3000);
}

let connectedgeform = document.getElementById('connectedgeform')
connectedgeform.addEventListener('submit', async (e) => {
    e.preventDefault()
    if (document.getElementById('fromlocid').innerHTML === "" && document.getElementById('tolocid').innerHTML === "") {
        console.log("if " + document.getElementById('from').innerHTML)
        console.log(document.getElementById('to').innerHTML)
        const formData = new FormData(connectedgeform);
        let formDataObject = Object.fromEntries(formData.entries());
        let formDataJsonString = JSON.stringify(formDataObject);
        let connecteurl = serverurl + '/edge/addedge'
        let res = await fetch(connecteurl, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
            },
            body: formDataJsonString
        })
        let resdata = await res.json()
        console.log(resdata)
        if (resdata.fromId) {
            showToast('Edge Created Sucessfully', 'success')
            document.getElementById('from').value = ''
            document.getElementById('to').value = ''
            document.getElementById('r1name').value = ''
        } else showToast('Edge not created.', 'error')
    } else {
        console.log("else" + document.getElementById('from').innerHTML)
        console.log(document.getElementById('to').innerHTML)
        showToast('Add correct locations.', 'error');
    }

})


var addlocationtable = document.createElement('table');
const listoflocationsdiv = document.getElementById('new-cities')
addlocation.addEventListener('click', (e) => {
    e.preventDefault()
    createroutediv.style.display = "none"
    addedgediv.style.display = "none"
    addbusdiv.style.display = "none"
    addlocationdiv.style.display = "block"
    createnewroutediv.style.display = "none"
    adddriverdiv.style.display = "none"
    showroutesdiv.style.display = "none"
    addlocationtable.setAttribute('id', 'addcitieslist');
    addlocationtable.setAttribute('class', 'table table-hover')

    listoflocationsdiv.appendChild(addlocationtable);
})


let intercities = [];
let addnewlocation = document.getElementById('add-inter')
let addcities = document.getElementById('new-cities')
let newcity = document.getElementById('pointlocation')
addnewlocation.addEventListener('click', (e) => {
    let interloc = newcity.value
    newcity.value = ""
    if (interloc === "") showToast('Please fill location name first', 'error')
    else if (addlocations.style.border === "2px solid red") {
        showToast('location already exists.', 'error')
    } else if ((intercities.includes(interloc))) {
        showToast('location name already in list.', 'error')
    } else intercities.push(interloc)

    document.getElementById('addcitieslist').innerHTML = ''
    for (let i = 0; i < intercities.length; i++) {
        let loc = intercities[i];
        var newtr = addlocationtable.insertRow(-1);
        var sno = newtr.insertCell(-1)
        sno.innerHTML = i + 1
        var ntrdata = newtr.insertCell(-1)
        ntrdata.innerHTML = loc;
        var ntricon = newtr.insertCell(-1);
        var button = document.createElement('button');

        button.setAttribute('type', 'button');
        button.setAttribute('class', 'btn btn-dark')
        button.innerHTML = 'Delete';
        button.setAttribute('onclick', 'deletelocation(this)');
        ntricon.appendChild(button);
    }

})

function deletelocation(el) {
    let uTable = document.getElementById('addcitieslist');
    let index = el.parentNode.parentNode.rowIndex;
    var oCells = uTable.rows.item(index).cells;
    console.log(index)
    console.log(oCells)
    let delcityname = oCells[1].innerHTML
    console.log(delcityname)
    let eleidx = intercities.indexOf(delcityname)
    if (eleidx > -1) intercities.splice(eleidx, 1);
    uTable.deleteRow(el.parentNode.parentNode.rowIndex);
}


const logout = document.getElementById("logoutbtn")
logout.addEventListener('click', (e) => {
    e.preventDefault();
    sessionStorage.clear()
    window.location.href = 'index.html'
});


let addlocationform = document.getElementById('addlocationform')
addlocationform.addEventListener('submit', async (e) => {
    e.preventDefault()
    if (addcities.innerHTML != "" && intercities.length > 0) {
        let formData = {}
        formData.locationNames = intercities

        let alurl = serverurl + '/location/addlocations';
        let fddata = await fetch(alurl, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
            },
            body: JSON.stringify(formData)
        })
        let resdata = await fddata.json();
        if (resdata.length > 0) {
            showToast('Locations added successfully', 'success')
            addcities.innerHTML = ""
        } else showToast('Some error occured.', 'error')

    } else {
        showToast('Please give at least one location.', 'error')
    }
})


const newrouteform = document.getElementById('newrouteform')
newrouteform.addEventListener('submit', async (e) => {
    e.preventDefault()
    if (document.getElementById('newroutename').style.border != "2px solid red" && document.getElementById('newroutename').value != "") {
        const formData = new FormData(newrouteform);
        let formDataObject = Object.fromEntries(formData.entries());
        console.log(formDataObject)
        let formDataJsonString = JSON.stringify(formDataObject);
        let nrurl = serverurl + '/route/createroute'
        let res = await fetch(nrurl, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            },
            body: formDataJsonString
        })
        let resdata = await res.json();
        if (resdata.routeName) {
            showToast('Created a route successfully', 'success')
        } else showToast('Some Error Occured', 'error')
    } else {
        if (document.getElementById('newroutename').value != "") {
            showToast('Please fill the Route name')
        } else showToast('Route already exists.', 'error')
    }

})


const addrinp = document.getElementById('newroutename');

addrinp.addEventListener('input', async (e) => {
    let searchTerm = e.target.value;
    if (searchTerm === "") searchTerm = "hello"
    debounceTimer = setTimeout(() => {
        let feturl = serverurl + 'route/getroute/' + searchTerm;
        fetch(feturl, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json"
            }
        })
            .then((res) => res.json())
            .then((matchedItems) => {
                console.log(matchedItems);
                if (matchedItems.length > 0) addrinp.style.border = "2px solid red"
                else addrinp.style.border = "1px solid black"
            })
    }, 500)
});






let mobinp = document.getElementById('drivernumber')
mobinp.addEventListener('input', (e) => {
    let mob = mobinp.value
    if (mob === "") mobinp.style.border = "1px solid black"
    if (!validateMobileNumber(mob)) {
        document.getElementById('mobp').innerHTML = 'Give Correct Mobile number'
        mobinp.style.border = "2px solid red"
    } else {
        console.log("else of mob")
        document.getElementById('mobp').textContent = ''
        mobinp.style.border = "1px solid black"
    }
})

let emailinp = document.getElementById('driveremail')
emailinp.addEventListener('input', (e) => {
    let email = emailinp.value
    if (email === "") emailinp.style.border = "1px solid black"
    if (!validateEmail(email)) {
        document.getElementById('emailp').innerHTML = 'Give Correct Email'
        emailinp.style.border = "2px solid red"
    } else {
        document.getElementById('emailp').textContent = ''
        emailinp.style.border = "1px solid black"
    }
})


function validateMobileNumber(mobileNumber) {
    const regex = /^(\+91[\-\s]?)?[0]?(91)?[789]\d{9}$/;
    return regex.test(mobileNumber);
}

function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}


let adddriverform = document.getElementById('adddriverform')
adddriverform.addEventListener('submit', async (e) => {
    e.preventDefault()
    const formData = new FormData(adddriverform);
    let formDataObject = Object.fromEntries(formData.entries());
    let formDataJsonString = JSON.stringify(formDataObject);
    if (mobinp.style.border === "2px solid red") showToast('Mobile number should be correct.', 'error')
    else if (emailinp.style.border === "2px solid red") showToast('Email Id should be correct.', 'error')
    else {
        let sdurl = serverurl + '/driver/adddriver'
        let addd = await fetch(sdurl, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
            },
            body: formDataJsonString
        })
        let addedriver = await addd.json()
        if (addedriver == null) showToast('Error occured.', 'error')
        if (addedriver.driverName) {
            showToast('Driver added successfully', 'success')
        }
        else showToast('Error occured.', 'error')
    }

})

