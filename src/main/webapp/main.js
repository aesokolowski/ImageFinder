var resultList = document.querySelector('ul.results');
var urlInput = document.querySelector('input[name=url]')

window.onload = function() {
    reset();
};

apiCallBack = function(xhr, callback) {
    if (xhr.readyState == XMLHttpRequest.DONE) {
        if (xhr.status != 200) { 
            swapFrames(document.getElementById("wait-frame"), document.getElementById("error-frame"));
            document.getElementById("error-headline").innerText = "Error: " + xhr.status + ": " + xhr.statusText + "!";
            console.error(xhr.responseText);
            document.title = "***Failure!***";
            throw 'API call returned bad code: ' + xhr.status;
        }

        let response = xhr.responseText ? JSON.parse(xhr.responseText) : null;

        if (callback) {
            callback(response);
        }
    }
};

updateList = function(response) {
    resultList.innerHTML = '';
    for (var i = 0; i < response.length; i++) {
        var img = document.createElement("img");
        let container = document.createElement("div");

        img.className = "result-element";
        container.className = "result-container";
        img.width = 200;
        img.src = response[i];
        resultList.appendChild(container);
        container.appendChild(img);
        
        let ext = img.src.substr(-4).toLowerCase();
        console.log(ext);
        if (ext === ".svg" || ext === ".ico") {
            let iconBox = document.createElement("div");

            iconBox.className = "iconBox alt-secondary-text-color";
            iconBox.innerHTML = "<p>Icon</p>";
            container.appendChild(iconBox);
        }
    }

    document.title = "***Scraping complete!***";
    document.querySelector("body").className = "alt-body";
    swapFrames(document.getElementById("wait-frame"), document.getElementById("success-frame"));
    document.getElementById("success-message").innerText = "Scroll down to see your results! (Well the non-broken on" +
            "es at least...)";
};

makeApiCall = function(url, method, obj, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.onreadystatechange = apiCallBack.bind(null, xhr, callback);
    xhr.send(obj ? obj instanceof FormData || obj.constructor == String ? obj : JSON.stringify(obj) : null);
};

swapFrames = function(frameToHide, frameToShow) {
    frameToHide.className = "hidden";
    frameToShow.className = "vertical";
};

reset = function() {
    let input = document.querySelector("input");
    let len = input.value.length * 2;

    input.value = "http://";
    input.focus();
    // put cursor at end of text:
    input.setSelectionRange(len, len);
    document.querySelector("body").className = null;
};

handleClick_Reset = function(element) {
    swapFrames(element, document.getElementById("form-frame"));
    reset();
    document.title = "Image Scraper";
};

toggleHelpBox = function() {
    let helpBox = document.getElementById("help-box");

    if (helpBox.className === "hidden") {
        helpBox.className = "help-box-show";
    } else {
        helpBox.className = "hidden";
    }
};

document.getElementById("submit-button").addEventListener("click", function(event) {
    event.preventDefault();

    let inputVal = urlInput.value;

    swapFrames(document.getElementById("form-frame"), document.getElementById("wait-frame"));
    document.getElementById("wait-headline").innerHTML = "Collecting images at " + inputVal + " at polite speed...";
    document.title = "Still scraping " + inputVal + "...";
    makeApiCall('/main?url=' + urlInput.value, 'POST', null, updateList);
});

document.getElementById("reset-button-error").addEventListener("click", function(event) {
    event.preventDefault();
    handleClick_Reset(document.getElementById("error-frame"));
});

document.getElementById("reset-button-success").addEventListener("click", function(event) {
    event.preventDefault();
    handleClick_Reset(document.getElementById("success-frame"));
});

document.getElementById("help-button").addEventListener("click", function(event) {
    event.preventDefault();
    toggleHelpBox();
});
