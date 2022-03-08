/*
  Name: Jake Schinto
  Email: jjs220@lehigh.edu
  Class: cse 264-010
  Assignment: 6, Simple Media Player
*/
window.$ = window.jQuery = require('jquery');

const ipcRenderer = require('electron').ipcRenderer
//const { BrowserWindow } = require('electron').remote

$("#select-file").click((event) => {
  ipcRenderer.send('open-file-dialog')
})

ipcRenderer.on('selected-directory', (event, path) => {
  path += "";
  var i = path.length - 1;
  while (path[i] != "." && i > 0) {
    i--;
  }
  var type = path.substring(i + 1);
  var elem;
  if (type === "mp3" || type === "wav" || type === "ogg") {
    elem = "<audio controls>" +
      "<source src='" + path + "' type='audio/" + type + "'>" +
      "Your browser does not support the audio element." +
      "</audio>";
  }
  else if (type === "mp4" || type === "mov") {
    elem = "<video width='420' height='340' controls>" +
      "<source src='" + path + "' type='video/mp4'>" +
      "Your browser does not support the video tag." +
      "</video>";
  }
  else {
    elem = "Your browser does not support that file type";
  }
  $("#media").html(elem)
})

