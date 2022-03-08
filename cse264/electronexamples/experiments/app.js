window.$ = window.jQuery = require('jquery');

const {BrowserWindow} = require('electron').remote
const path = require('path')

let win

$("#new-window").click((event) => {
  const modalPath = path.join('file://', __dirname, 'modal.html')
  win = new BrowserWindow({ width: 400, height: 275, transparent:true /*, frame:false */ })

  win.on('resize', updateReply)
  win.on('move', updateReply)
  win.on('close', () => { win = null })
  win.loadURL(modalPath)
  win.show()

  function updateReply () {
    const message = `Size: ${win.getSize()} Position: ${win.getPosition()}`;
    $("#manage-window-reply").html(message);
  }
})