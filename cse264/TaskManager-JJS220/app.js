/*
Name: Jake Schinto
Email: jjs220@lehigh.edu
Class: cse 264-010
Assignment: 4, Task Manager
*/
var express = require("express");

var app = express();
let path = require("path");
var bodyParser = require("body-parser");
app.use(bodyParser.json());

let publicPath = path.resolve(__dirname, "public");
app.use(express.static(publicPath));

class Task {
  constructor(id, desc, type, date) {
    this.id = id;
    this.desc = desc;
    this.type = type;
    this.date = date;
  }
  isequals(id) {
    return this.id === id;
  }
}
var tasks = [];
tasks[0] = 0;

app.get("/task", function (request, response) {
  response.end(JSON.stringify(tasks));
});

app.post("/task", function (request, response) {
  let id = request.body.id;
  let desc = request.body.desc;
  let type = request.body.type;
  let date = request.body.date;
  tasks.push(new Task(id, desc, type, date));
  tasks[0]++;
  response.end("200");
});

app.delete("/task", function (request, response) {
  var toDel = request.body;
  for (let x = 0; x < toDel.length; x++) {
    for (let y = 1; y < tasks.length; y++) {
      if (tasks[y].isequals(toDel[x])) {
        tasks.splice(y, 1);
        break;
      }
    }
  }
  response.end("200");
});

app.use(function (request, response) {
  response.statusCode = 404;
  response.end("404!");
});

app.listen(3000);
