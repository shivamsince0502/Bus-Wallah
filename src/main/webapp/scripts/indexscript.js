// const serverurl = 'http://localhost:8080/buswallah/services/';

const searchBox1 = document.getElementById('startloc');
const resultsList1 = document.getElementById('result-list1');

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
    resultsList1.innerHTML = ''
});

resultsList1.addEventListener('dragover', (e) => {
    let currow = e.target
    console.log(currow)
})



const searchBox2 = document.getElementById('endloc');
const resultsList2 = document.getElementById('result-list2');

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
    searchBox2.value = clickedItem.textContent;
    resultsList2.innerHTML = ''

});

resultsList2.addEventListener('dragover', (e) => {
    let currow = e.target
    console.log(currow)
})



const searchBox3 = document.getElementById('inbetweenloc');
const resultsList3 = document.getElementById('result-list3');


async function searchItems3(searchTerm) {
    resultsList3.innerHTML = "";
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
                    let pfrom = document.getElementById('interlocid').innerHTML = 'No locations found try else'

                } else {
                    matchedItems.forEach(item => {
                        document.getElementById('interlocid').innerHTML = ""
                        const li = document.createElement('option');
                        li.value = item;
                        li.textContent = li.value
                        resultsList3.appendChild(li);
                    });
                }
                resultsList3.style.display = "block"
            })
    }, 500)
}

searchBox3.addEventListener('input', event => {
    const searchTerm = event.target.value;
    if (searchTerm != "")
        searchItems3(searchTerm);
    else resultsList3.innerHTML = ""
});

resultsList3.addEventListener('click', event => {
    const clickedItem = event.target;
    searchBox3.value = clickedItem.textContent;
    resultsList3.innerHTML = ''
});

resultsList3.addEventListener('dragover', (e) => {
    let currow = e.target
    console.log(currow)
})

let intercities = [];
let addlocation = document.getElementById('add-inter')
let addcities = document.getElementById('inter-cities-list')
addlocation.addEventListener('click', (e) => {
    addcities.innerHTML = "";
    let interloc = searchBox3.value
    intercities.push(interloc);
    searchBox3.value = "";
    for (i = 0; i < intercities.length; ++i) {
        if (i > 0 && intercities[i - 1] == intercities[i]) continue;
        if (intercities[i] === "") continue;
        var li = document.createElement('li');
        li.innerText = intercities[i];
        addcities.appendChild(li);
    }
})

sessionStorage.clear();
let flip = document.getElementById('flip-loc');
flip.addEventListener('click', (e) => {
    let s1 = searchBox1.value;
    searchBox1.value = searchBox2.value;
    searchBox2.value = s1;
})


let searchBus = document.getElementById('search-bus')
searchBus.addEventListener('submit', async (e) => {
    e.preventDefault()
    const formData = new FormData(searchBus);
    let formDataObject = Object.fromEntries(formData.entries());
    formDataObject.intercities = intercities;
    sessionStorage.setItem("start", formDataObject.start)
    sessionStorage.setItem("end", formDataObject.end);
    let len = formDataObject.intercities.length
    sessionStorage.setItem("len", len.toString())
    for (let i = 0; i < len; i++) {
        let key = i.toString();
        sessionStorage.setItem(key, intercities[i]);
    }
    window.location.href = "showroutes.html";
})




