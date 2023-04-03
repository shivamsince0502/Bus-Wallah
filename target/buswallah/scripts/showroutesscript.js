// const serverurl = 'http://localhost:8080/buswallah/services/';

const totalbody = document.getElementById('parent-div')
window.onload = startFunction;


async function startFunction() {
    let start = sessionStorage.getItem("start")
    let end = sessionStorage.getItem("end")
    let intercities = []
    let len = Number(sessionStorage.getItem("len"))
    console.log(len)
    for (let i = 0; i < len; i++) {
        console.log(sessionStorage.getItem(i.toString()))
        intercities.push(sessionStorage.getItem(i.toString()))
    }
    for (let j = 0; j < len; j++) {
        console.log(intercities[j]);
    }
    let formDataObject = {};
    formDataObject.start = start;
    formDataObject.end = end
    formDataObject.intercities = intercities;
    let formDataJsonString = JSON.stringify(formDataObject)
    let srurl = serverurl + 'route/getbusinter';
    let searchres = await fetch(srurl, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        },
        body: formDataJsonString
    })
    let searchresult = await searchres.json();

    if (searchresult.length > 0) console.log('search result came')
    else console.log('no result came')

    console.log(searchresult);


    if (searchresult.size === 0) {
        totalbody.innerHTML = '<h1>No result found :(</h1?'
    } else {
        for (let i = 0; i < searchresult.length; i++) {
            let route = searchresult[i];
            let tabNameDiv = document.createElement('div');
            tabNameDiv.classList.add('tab-name');

            let routesPathDiv = document.createElement('div');
            routesPathDiv.classList.add('routes-path');
            let routeCardDiv = document.createElement('div');
            routeCardDiv.classList.add('route-card');
            let routeno = document.createElement('p')
            routeno.textContent = 'Route no ' + Number(i + 1);
            routeno.setAttribute('class', 'h5')
            routesPathDiv.appendChild(routeno)
            for (let j = 0; j < route.length; j++) {
                let subroute = route[j]
                let bus = subroute.bus;
                let busroute = subroute.route;
                let pathlocations = subroute.pathLocations
                let totalDistance = subroute.totalDistance;
                let rdataarray = [busroute.routeName, bus.busName, bus.busNumber, totalDistance, totalDistance * 6, bus.noOfSeats]
                let showBus1Div = document.createElement('div');
                showBus1Div.classList.add('show-bus');
                let startEnd1Div = document.createElement('div');
                startEnd1Div.classList.add('start-end');
                let start1Span = document.createElement('span');
                start1Span.style.margin = '0 10px 20px 0';
                start1Span.classList.add('loc')
                start1Span.textContent = pathlocations[0];

                let routeLine1Div = document.createElement('div');
                routeLine1Div.id = 'route-line';

                let end1Span = document.createElement('span');
                end1Span.style.margin = '20px 10px 0px 0';
                end1Span.classList.add('loc')
                end1Span.textContent = pathlocations[pathlocations.length - 1];

                let tabDiv2_1Div = document.createElement('div');
                tabDiv2_1Div.id = 'tab-div2';
                tabDiv2_1Div.classList.add('tab-div');

                for (let k = 0; k < rdataarray.length; k++) {
                    const label = document.createElement('label');
                    label.textContent = rdataarray[k];
                    tabDiv2_1Div.appendChild(label);
                }
                const bookBtn1 = document.createElement('button');
                bookBtn1.classList.add('btn', 'btn-dark', 'book-btn');
                bookBtn1.textContent = 'Book';
                bookBtn1.setAttribute('onclick', 'bookBus(this)')
                tabDiv2_1Div.appendChild(bookBtn1);
                startEnd1Div.appendChild(start1Span);
                startEnd1Div.appendChild(routeLine1Div);
                startEnd1Div.appendChild(end1Span);
                showBus1Div.appendChild(startEnd1Div);
                showBus1Div.appendChild(tabDiv2_1Div);
                routeCardDiv.appendChild(showBus1Div);
            }
            routesPathDiv.appendChild(routeCardDiv);

            tabNameDiv.appendChild(routesPathDiv);

            totalbody.appendChild(tabNameDiv);
        }
    }
}

var bookPayload = {}
async function bookBus(button) {
    let parentElement = button.closest('.show-bus');
    let rotueName = parentElement.querySelector('label:nth-of-type(1)').textContent.trim();
    let busName = parentElement.querySelector('label:nth-of-type(2)').textContent.trim();
    let busNumber = parentElement.querySelector('label:nth-of-type(3)').textContent.trim();
    let fare = parentElement.querySelector('label:nth-of-type(5)').textContent.trim();
    let parent = button.parentNode.parentNode;
    let startSpan = parent.querySelector('.loc:first-child');
    let endSpan = parent.querySelector('.loc:last-child');
    bookPayload.routeName = rotueName
    bookPayload.busNumber = busNumber
    bookPayload.busName = busName
    bookPayload.fare = fare
    bookPayload.start = startSpan.textContent
    bookPayload.end = endSpan.textContent

    document.getElementById('routenamemod').textContent = 'Route Name : ' + rotueName
    document.getElementById('busnumbermod').textContent = 'Bus Number : ' + busNumber
    document.getElementById('busnamemod').innerHTML = 'Bus Name : ' + busName
    $("#bookbusmodal").modal('show')
}





let mobinp = document.getElementById('mobnumber')
mobinp.addEventListener('input', (e) => {
    let mob = mobinp.value
    if (mob === "") mobinp.style.border = "1px solid black"
    if (!validateMobileNumber(mob)) {
        document.getElementById('mobp').textContent = 'Give Correct Mobile number'
        mobinp.style.border = "2px solid red"
    } else {
        console.log("else of mob")
        document.getElementById('mobp').textContent = ''
        mobinp.style.border = "1px solid black"
    }
})

let emailinp = document.getElementById('emailid')
emailinp.addEventListener('input', (e) => {
    let email = emailinp.value
    if (email === "") emailinp.style.border = "1px solid black"
    if (!validateEmail(email)) {
        document.getElementById('emailp').textContent = 'Give Correct Email'
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

let bookbus = document.getElementById('book');
bookbus.addEventListener('click', async (e) => {
    e.preventDefault()
    if (mobinp.style.border != "2px solid red" || emailinp.style.border != "2px solid red") {
        if (mobinp.style.border != "2px solid red") bookPayload.userMob = mobinp.value
        if (emailinp.style.border != "2px solid red") bookPayload.userEmail = emailinp.value
        let bookurl = serverurl + '/booking/createbooking'
        let bookDataf = await fetch(bookurl, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
            },
            body: JSON.stringify(bookPayload)
        })
        let bookdata = await bookDataf.json();
         showToast('Booking Confirmed', 'success')
        if (bookdata.busName != null) {
            
            if (emailinp.style.border != "2px solid red") {
                let msg = "There is booking from " + mobinp.value + " to bus number : " + bookPayload.busNumber + " to route name : " + bookPayload.routeName + " at " + bookPayload.start + " to " + bookPayload.end;
                let to1 = bookdata.busName
                let to2 = emailinp.value
                console.log(to1)
                console.log(to2)
                let subjectE = "Booking Confirmed"
                sendmail(to1, subjectE, msg)
                sendmail(to2, subjectE, msg)
            }
        }
    }
})


function sendmail(to, subjectE, bodyE) {
    Email.send({
        Host: "smtp.elasticemail.com",
        Username: "shivam.kumar.code0502@gmail.com",
        Password: "4D3978A402A5E1334EE6AC18643CBF7D6609",
        From: "shivam.kumar.code0502@gmail.com",
        To: to,
        Subject: subjectE,
        Body: bodyE
    }).then(
        message => {

         showToast('Email Sent', 'success')
        }
    );
}