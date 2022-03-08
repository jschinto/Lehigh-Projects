/*
Name: Jake Schinto
Email: jjs220@lehigh.edu
Class: cse 264-010
Assignment: 5, Word Search
*/
// When the page onload event triggers (the page finishes loading) setup the needed event handlers and ajax calls.
$(() => {
    let tasks = [];
    let tot = 0;
    // Use an ajax call to load an array of state names from the server for use by the autocomplete event below.

    var HOST = "localhost:3000";
    //var HOST = "abelard.cse.lehigh.edu:3000"
    var SERVER = "http://" + HOST + "/wordsearch/";

    function nullFcn(result) {
    }

    // Utility method for encapsulating the jQuery Ajax Call
    function doAjaxCall(method, cmd, params, fcn) {
        $.ajax(
            SERVER + cmd,
            {
                type: method,
                processData: true,
                data: params,
                dataType: "json",
                success: function (result) {
                    fcn(result)
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error: " + jqXHR.responseText);
                    alert("Error: " + textStatus);
                    alert("Error: " + errorThrown);
                }
            }
        );
    }

    function puzzle(result) {
        if (!result.success) {
            alert("There was an error creating the puzzle, try again!");
            return;
        }
        $("#userForm").hide();
        $("#reg").hide();
        $("#currName").html("You are logged in as: <b>" + username + "</b>");
        $("#currName").addClass("removeMarg");
        $("#manager").show();
        $("#rest").show();
        let grid = result.grid;
        $("#theme").html("Puzzle Theme: " + result.theme);
        $("#puzzle").empty();
        for (let r = 0; r < result.nrows; r++) {
            $("#puzzle").append("<tr id=\"r" + r + "\"></tr>");
            for (let c = 0; c < result.ncols; c++) {
                let space = $("<td class=\"gridspace\" data-r=\"" + r + "\" data-c=\"" + c + "\">" + grid.charAt(0) + "</td>");
                space.click(function () {
                    if ($(this).hasClass("selected")) {
                        $(this).removeClass("selected");
                    } else {
                        $(this).addClass("selected");
                    }
                });
                $("#r" + r).append(space);
                grid = grid.substring(1);
            }
        }
        //$("#rest").width($("#score").width()+$("#words").width()+30);
    }

    function scoreboard(players) {
        $("#players").empty();
        for(let i = 0; i < players.length; i++) {
            let name = players[i].name;
            let score = players[i].score;
            let winner = players[i].winner;
            //let winner = true;
            let row = $("<tr></tr>");
            row.append("<td>"+name+"</td><td>"+score+"</td>");
            if(winner) {
                row.addClass("claimed");
            }
            $("#players").append(row);
        }
        //$("#rest").width($("#score").width()+$("#words").width()+30);
    }

    function setClaimed(words) {
        let theList = words.words;
        $("#claim").empty();
        for(let i = 0; i < theList.length; i++) {
            let text = theList[i].text;
            //text = "dsfadsasdafasddfsafasdfafadsfds"
            $("#claim").append("<tr><td>"+text+"</td></tr>");
            let letters = theList[i].letters;
            for(let x = 0; x < letters.length; x++) {
                let r = letters[x].r;
                let c = letters[x].c;
                if(!$("[data-r=\""+ r + "\"][data-c=\"" + c + "\"]").hasClass("claimed")) {
                    $("[data-r=\""+ r + "\"][data-c=\"" + c + "\"]").addClass("claimed");
                }
            }
        }
        //$("#rest").width($("#score").width()+$("#words").width()+30);
    }

    function load() {
        //on page load
        $("#rest").hide();
        $("#manager").hide();
        var socket = io.connect("http://"+HOST);
        socket.on('players', function(players) {
            scoreboard(players);
        });
        socket.on('gridupdates', function(words){
            setClaimed(words);
        });
    }

    var id = "";
    var username = "";

    function setUser(result) {
        if (!result.success) {
            alert("There was an error logging you in, try again!");
            return;
        }
        id = result.id;
        username = result.username;
        let data = {
            "id": id
        };
        doAjaxCall("GET", "puzzle", data, puzzle);
    }

    $("#reg").click(function () {
        let data = {
            "username": $("#user").val()
        };
        doAjaxCall("GET", "login", data, setUser);
    });

    function onSubmit(result) {
        $("#puzzle td.selected").each(function () {
            $(this).removeClass("selected");
        });
    }

    $("#sub").click(function () {
        let lst = new Array();
        $("#puzzle td.selected").each(function () {
            let r = parseInt($(this).attr("data-r"));
            let c = parseInt($(this).attr("data-c"));
            let temp = {
                "r": r,
                "c": c
            }
            lst.push(temp);
        });
        let data = {
            "id": id,
            "letters": lst
        }
        doAjaxCall("GET", "submit", data, onSubmit);
    });

    load();
});