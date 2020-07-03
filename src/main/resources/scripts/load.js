/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */

var vm = null;

function loadData() {
    console.log("Loading data");
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("load", function() {
        console.log("Loading data (3)");
        var respTxt = xhr.responseText;
        var data = JSON.parse(respTxt);
        vm = new Vue({
            el: '#scope',
            data: data
        });

        //High priority task -- replace/improve the code below.
        var selectors = document.getElementsByClassName("selector");
        selectors[0].selectedIndex=0;
        vm.$data.characters.characterSelected = vm.$data.characters.characterList[0];
        vm.$data.shrines.shrineSelected = vm.$data.shrines.shrineList[0];
        vm.$data.spells.spellSelected = vm.$data.spells.spellList[0];
        vm.$data.baseMonsters.baseMonsterSelected = vm.$data.baseMonsters.baseMonsterList[0];
        vm.$data.uniqueMonsters.uniqueMonsterSelected = vm.$data.uniqueMonsters.uniqueMonsterList[0];
        vm.$data.quests.questSelected = vm.$data.quests.questList[0];
        vm.$data.baseItems.baseItemSelected = vm.$data.baseItems.baseItemList[0];
        vm.$data.itemModifiers.itemModifierSelected = vm.$data.itemModifiers.itemModifierList[0];
        vm.$data.uniqueItems.uniqueItemSelected = vm.$data.uniqueItems.uniqueItemList[0];

        //This hides the ugly Vue.js code in the button while it is loading. FIXME -- devise a better way.
        var elementToReveal = document.getElementById("scope");
        elementToReveal.style.display = "block";
    });
    xhr.open("GET", "http://localhost:4666/data/obtain");
    console.log("Loading data (2)");
    xhr.send();
}

loadData();
