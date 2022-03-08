/*
Name: Jake Schinto
Email: jjs220@lehigh.edu
Class: cse 264-010
Assignment: 4, Task Manager
*/
// When the page onload event triggers (the page finishes loading) setup the needed event handlers and ajax calls.
$(() => {
    let tasks = [];
    let tot = 0;
    // Use an ajax call to load an array of state names from the server for use by the autocomplete event below.
    function load() {
        $.ajax(
            "/task",
            {
                type: "GET",
                processData: false,
                // No data: field is necessary because the path (/getstatelist) completely defines what needs to be
                // done by the server.
                dataType: "json",
                success: function (json) {
                    tot = json.splice(0, 1);
                    tasks = json;
                    $("#tasks").empty();
                    for (var i = 0; i < tasks.length; i++) {
                        $("#tasks").append('<tr><td>' + '<input type="checkbox" id=' + tasks[i].id + '>' + '</td><td>' + tasks[i].desc + '</td><td>' + tasks[i].type + '</td><td>' + tasks[i].date + '</td></tr>')
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error: " + jqXHR.responseText);
                    alert("Error: " + textStatus);
                    alert("Error: " + errorThrown);
                }
            });
    }
    
    $("#add").click(function () {
        $.ajax(
            "/task",
            {
                type: "POST",
                processData: false,
                contentType: 'application/json',
                data: JSON.stringify({ "id": "task" + tot, "desc": $("#desc").val(), "type": $("#type option:selected").text(), "date": $("#date").val() }),
                dataType: "json",
                success: function (json) {
                    load();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error: " + jqXHR.responseText);
                    alert("Error: " + textStatus);
                    alert("Error: " + errorThrown);
                }
            });
    });

    $("#del").click(function () {
        var check = $("[type=checkbox]:checked");
        var toDel = [];
        for (let x = 0; x < check.length; x++) {
            toDel[x] = check[x].id;
        }
        if(toDel.length===0) {
            return;
        }
        if(!confirm("Are you sure you want to delete the selected task(s)?")) {
            return;
        }
        $.ajax(
            "/task",
            {
                type: "DELETE",
                processData: false,
                contentType: 'application/json',
                data: JSON.stringify(toDel),
                dataType: "json",
                success: function (json) {
                    load();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error: " + jqXHR.responseText);
                    alert("Error: " + textStatus);
                    alert("Error: " + errorThrown);
                }
            });
    });

    $("#date").datepicker();

    load();
});