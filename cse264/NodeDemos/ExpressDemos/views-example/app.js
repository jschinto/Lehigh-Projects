const express = require("express");
const path = require("path");

const app = express();

app.set("views", path.resolve(__dirname, "views"));
app.set("view engine", "ejs");

app.get("/", function(request, response) {
  response.render("index", {
    message: "Hey everyone! This is my webpage."
  });
});

app.get("/:id", function(request, response) {
  response.render("index", {
    message: "Hey everyone! This is " + request.params.id + "'s webpage."
  });
});

app.listen(3000);
