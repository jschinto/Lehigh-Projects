var puzzle = document.getElementById("puzzle");
var rows = puzzle.getElementsByTagName("tr");
var tiles = [];
var brow = 3;
var bcol = 3;
for (var r = 0; r < rows.length; r++) {
    var cols = rows[r].cells;
    tiles[r] = [];
    for (var c = 0; c < cols.length; c++) {
        cols[c].row = r;
        cols[c].col = c;
        cols[c].onclick = function () {
            swap(this);
        };
        tiles[r][c] = cols[c];
    }
}
function swap(tile) {
    if (Math.abs(tile.row - brow) + Math.abs(tile.col - bcol) === 1) {
        tiles[brow][bcol].innerHTML = tile.innerHTML;
        tile.innerHTML = "";
        brow = tile.row;
        bcol = tile.col;
        if (checkresult()) {
            puzzle.style = "background-color: #F2B134;"
        }
        else {
            puzzle.style = "";
        }
    }
}
function checkresult() {
    for (var r = 0; r < 4; r++) {
        for (var c = 0; c < 4; c++) {
            if (tiles[r][c].innerHTML.trim() != ((4 * r) + (c + 1)) + "" && tiles[r][c].innerHTML != "") {
                return false;
            }
        }
    }
    return true;
}
function reset() {
    for (var r = 0; r < 4; r++) {
        for (var c = 0; c < 4; c++) {
            tiles[r][c].innerHTML = ((4 * r) + (c + 1)) + "";
        }
    }
    brow = 3;
    bcol = 3;
    tiles[3][3].innerHTML = ""
    puzzle.style = "";
}
function scramble() {
    for (var i = 0; i < 500; i++) {
        var r = Math.round(Math.random() * 2) - 1;
        var c = 0;
        if (r === 0) {
            while (c === 0) {
                c = Math.round(Math.random() * 2) - 1;
            }
        }
        if (r + brow >= 0 && r + brow < 4 && c + bcol >= 0 && c + bcol < 4) {
            swap(tiles[brow + r][bcol + c]);
        }
        else {
            i--;
        }
    }
}