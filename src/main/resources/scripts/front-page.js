/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */

window.onload = function() {
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("load", function() {
        var parsedLoadStatus = JSON.parse(xhr.responseText);
        if(parsedLoadStatus.loaded) {
            setDocumentToLoadedState();
        } else {
            setDocumentToUnloadedState();
        }
    });
    xhr.open("GET", "/loaded");
    xhr.send();
};

function setDocumentToUnloadedState() {
    alterVisibility("loaded", "unloaded");
}

function setDocumentToLoadedState() {
    alterVisibility("unloaded", "loaded");
}

function alterVisibility(classToHide, classToShow) {
var elementsToUnload = document.getElementsByClassName(classToHide);
    for(var i = 0; i < elementsToUnload.length; i++) {
        elementsToUnload[i].style.display = "none";
    }
    var elementsToLoad = document.getElementsByClassName(classToShow);
    for(var i = 0; i < elementsToLoad.length; i++) {
        elementsToLoad[i].style.display = "block";
    }
}

function uploadClick() {
    console.log("Upload click activated");
    var selectedFile = document.getElementById("load-diablo-exe").files[0];
    var form = new FormData();
    form.append("diablo-exe", selectedFile);
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("load", function() {
        setDocumentToLoadedState();
    });
    xhr.open("POST", "/binary/load");
    console.log("Sending file...");
    xhr.send(form);
}

var uploadButton = document.getElementById("upload-button");
uploadButton.addEventListener("click", uploadClick);
