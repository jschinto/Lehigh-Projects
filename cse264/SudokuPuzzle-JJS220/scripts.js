/*
  Name: Jake Schinto
  Email: jjs220@lehigh.edu
  Class: cse 264-010
  Assignment: Final Project, Sudoku
*/
var answer = [];
var maxAttempts = 100;
var selected = null;
var created = false;

function random() {
    try {
        randomGen();
        created = true;
    } catch (error) {
        random(); //try again if error
    }
}

function randomGen() {//random board generator.  This was hard because it needed to be solveable
    let tries = 0;
    for (let y = 0; y < 9; y++) {
        answer[y] = new Array(9);
        for (let x = 0; x < 9; x++) {
            answer[y][x] = randomIntFromInterval(1, 9); //choose a random answer
            if (answer[y].indexOf(answer[y][x]) != x) {//check if in the same row
                x--;
            }
            else {
                let found = false;
                for (let y2 = 0; y2 < y; y2++) {//check if in same column
                    if (answer[y2][x] == answer[y][x]) {
                        if (tries >= maxAttempts) {
                            y = y - 2;
                            tries = 0;
                        }
                        else {
                            tries++;
                            x--;
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {//check if in same box
                    for (let y2 = y - (y % 3); y2 < y; y2++) {
                        for (let x2 = 0; x2 < 3; x2++) {
                            let x3 = x - (x % 3) + x2;
                            if (answer[y2][x3] == answer[y][x]) {
                                if (tries >= maxAttempts) {
                                    y = y - 2;
                                    tries = 0;
                                }
                                else {
                                    tries++;
                                    x--;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    //now display 40% of the answers to make the game
    let count = 0;
    for (let y = 0; y < 9; y++) {
        for (let x = 0; x < 9; x++) {
            let show = randomIntFromInterval(1, 10);
            if (count >= 45) {//no more than 45
                break;
            }
            if (show <= 4) {
                getElem(x, y).innerHTML = answer[y][x];
                count++;
            }
            else {
                getElem(x, y).innerHTML = "&nbsp;";
            }
        }
    }
    if (count < 25) {//so there are at least 25 filled
        for (; count <= 25; count++) {
            try {
                hintGen();
            }
            catch (error) {

            }
        }
    }
    document.getElementById("out").innerHTML = "Select a Cell and Pick a Number<br/>Press Hint for Some Help<br/>When You are Done Press Check"
    initOnClick();
}

function randomIntFromInterval(min, max) {//min and max included
    return Math.floor(Math.random() * (max - min + 1) + min);
}

function getElem(x, y) {
    return document.getElementById("r" + ((y + 1) + "") + ((x + 1) + ""));
}

function initOnClick() {
    for (let y = 0; y < 9; y++) {
        for (let x = 0; x < 9; x++) {
            getElem(x, y).onclick = function () {
                if (this == selected) {
                    selected.classList.remove("selected");
                    selected = null;
                    return;
                }
                this.classList.add("selected");
                if (selected != null) {
                    selected.classList.remove("selected");
                }
                selected = this;
            };
        }
    }
}

function hint() {
    try {
        if (created) {
            hintGen();
        }
    } catch (error) {
        hint();
    }
}

function hintGen() { //give a random hint
    let starty = randomIntFromInterval(0, 8);
    for (let y = 0; y < 9; y++) {
        let y2 = (starty + y) % 9;
        let startx = randomIntFromInterval(0, 8);
        for (let x = 0; x < 9; x++) {
            let x2 = (startx + x) % 9;
            let temp = getElem(x2, y2);
            if (temp.innerHTML == "&nbsp;") {
                temp.innerHTML = answer[y2][x2];
                return;
            }
        }
    }
    alert("You must have an empty cell to receive a hint!");
}

function check() {
    try {
        if (created) {
            checkGen();
        }
    } catch (error) {
        check();
    }
}

function checkGen() {
    for (let y = 0; y < 9; y++) {
        for (let x = 0; x < 9; x++) {
            let elem = getElem(x, y);
            if (elem.innerHTML != answer[y][x]) {
                alert("Your answer is INCORRECT\nClear some boxes and try a hint!");
                return;
            }
        }
    }
    alert("Your answer is CORRECT\nGood Job!");
}

function bNum(num) {
    if (selected != null) {
        selected.innerHTML = num;
        selected.classList.remove("selected");
        selected = null;
    }
}