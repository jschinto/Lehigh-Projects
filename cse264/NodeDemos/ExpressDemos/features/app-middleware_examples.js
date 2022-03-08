const express = require("express");
const logger = require("morgan");
const http = require("http");

const app = express();

app.use(logger("short"));

let reject = false;

app.use(function(request, response, next) {
  if (reject) {
	  reject = !reject;
    next();
  } else {
	  reject = !reject;
    response.statusCode = 403;
    response.end("Not authorized.");
  }
});

app.use(function(request, response) {
  response.writeHead(200, { "Content-Type": "text/plain" });
  response.end("Hello, World!");
});

//http.createServer(app).listen(3000, () => console.log("Listening on port 3000"));
app.listen(3000);
