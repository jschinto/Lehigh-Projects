/*
Name: Jake Schinto
Email: jjs220@lehigh.edu
Class: cse 264-010
Assignment: 3, Express Calendar
*/
var express = require("express");
var path = require("path");

var app = express();

app.set("views", path.resolve(__dirname, "views"));
app.set("view engine", "ejs");

app.get("/", function(request, response) {
  response.end("Welcome to my homepage!");
});

app.get("/about", function(request, response) {
  response.end("Welcome to the about page!");
});

app.get("/weather", function(request, response) {
  response.end("The current weather is NASTY.");
});

app.get("/hello/:who", function(request, response) {
  response.end("Hello, " + request.params.who + ".");
  // Fun fact: this has some security issues!
});

app.get("/color", function(request, response) {
  response.end(`The color is ${request.query.colorname}`);
});

app.get("/redirect_home", function (request, response) {
  response.redirect("/");
});

app.get("/sendfile", function (request, response) {
  var filePath = path.resolve(__dirname, "cool-facts.txt");
  response.sendFile(filePath);
});

var curDate = new Date();
function setCurDate(request) {
  var month = parseInt(request.query.month);
  var year = parseInt(request.query.year);

  if(month >= 1 && month <= 12) {
    curDate.setMonth(month - 1);
  }
  else if(typeof request.query.month !== 'undefined') {
    return false;
  }
  else {
    var d = new Date();
    curDate.setMonth(d.getMonth());
  }

  if(!isNaN(year)) {
    curDate.setFullYear(year);
  }
  else if(typeof request.query.year !== 'undefined') {
    return false;
  }
  else {
    var d = new Date();
    curDate.setFullYear(d.getFullYear());
  }
  if(isNaN(curDate.getTime())) {
    return false;
  }
  return true;
}

app.get("/calendar", function(request, response) {
  if(!setCurDate(request)){
    response.statusCode = 404;
    response.end("404!");
    return;
  }
  var cal = getCalendar();
  response.render("calendar", cal);
});

function getCalendar(theDate) {
  const monthNames = ["", "January", "February", "March", "April",
        "May", "June", "July", "August", "September", "October",
        "November", "December"];
        
  let month = curDate.getMonth() + 1;
  let year = curDate.getFullYear();

  // Calculate the last day of the month taking leap year into account
  // How would you do this using the js Date object?
  if (month === 4 || month === 6 || month === 9 || month === 11)
    lastDay = 30;
  else if (month !== 2)
    lastDay = 31;
  else if (year % 4 === 0 && (year % 100 !== 0 || year % 400 === 0))
    lastDay = 29;
  else
    lastDay = 28;

  // Use the day of the week number for the first day of the month to set the day
  // number of the calendar cell in the upper left corner. This will be < 1 if the 
  // cell is supposed to be blank.
  let firstDayOfMonth = new Date(year, month-1, 1);
  let dday = 1 - firstDayOfMonth.getDay();

  // When done is true, stop printing the calendar.
  let done = false;

  var out = "";
  var isCurDate = ((month === ((new Date()).getMonth() + 1)) && (year === ((new Date()).getFullYear())));
  var curDay = (new Date()).getDate();
  var style = ' style="background-color: #F2EFDE;"'

  for (let row = 1; !done; ++row) {
    out += "<tr>";
    for (let colday = 1; colday <= 7; ++colday, ++dday) {
      if (dday < 1 || dday > lastDay) {
        out += "<td" + style + "></td>";
      }
      else {
        if(isCurDate && dday === curDay) {
          out += '<td style="background-color: #F0C81A;">' + dday + "</td>";
        }
        else {
          out += "<td" + style + ">" + dday + "</td>";
        }
      }
      if (!done) {
        done = (dday === lastDay);
      }
    }
    out += "</tr>";
  }

  return {
    monthYear: monthNames[month] + " " + year,
    days: out,
    month: (month - 1) + "",
    year: year + ""
  }
}

app.use(function(request, response) {
  response.statusCode = 404;
  response.end("404!");
});

app.listen(3000);
