/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */

function saveData() {
    saveButton.disabled = "disabled";
    saveButton.value = "Saving...";
    console.log("Saving data");
    Vue.nextTick(function() {
        var xhr = new XMLHttpRequest();
        xhr.addEventListener("load", function() {
            console.log("Saving data (3)");
            saveButton.disabled = false;
            saveButton.value = "Save!";
        });
        xhr.open("POST", "http://localhost:4666/data/upload");
        console.log("Saving data (2)");
        xhr.send(JSON.stringify(vm.$data));
    });
}

var saveButton = document.getElementById("save-button");
saveButton.addEventListener("click", saveData);