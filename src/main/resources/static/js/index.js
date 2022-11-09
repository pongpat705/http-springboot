window.addEventListener('load', init(), false );
function init() {
    getData();
}

function getData(){
    let httpRequest = new XMLHttpRequest();
    httpRequest.open('GET', 'api/hi', true);
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    httpRequest.send('')
    httpRequest.onload = function() {
        if (this.status === 200) {
            let raw = JSON.parse(httpRequest.response);
            let element = document.getElementById("data");

            element.innerText = JSON.stringify(raw.data);
            console.log(httpRequest.response);
        }
    }
}

function postData(){
let nameElm = document.getElementById("name");
let nameData = nameElm.value;
let requestBody = {
    name : nameData
}
console.log(requestBody);
let httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', 'api/hi', true);
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    httpRequest.send(JSON.stringify(requestBody))
    httpRequest.onload = function() {
        if (this.status === 200) {
            let raw = JSON.parse(httpRequest.response);
            let element = document.getElementById("data");

            element.innerText = JSON.stringify(raw.data);
            console.log(httpRequest.response);
        }
    }
}