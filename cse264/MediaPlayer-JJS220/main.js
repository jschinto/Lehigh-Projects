/*
  Name: Jake Schinto
  Email: jjs220@lehigh.edu
  Class: cse 264-010
  Assignment: 6, Simple Media Player
*/

'use strict';

const electron = require('electron');
const ipcMain = require('electron').ipcMain
const dialog = require('electron').dialog
const app = electron.app;
const BrowserWindow = electron.BrowserWindow;

let mainWindow = null;

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') app.quit();
});

app.on('ready', () => {
  mainWindow = new BrowserWindow();
  mainWindow.loadURL(`file://${__dirname}/index.html`);
  mainWindow.on('closed', () => { mainWindow = null; });
});

ipcMain.on('open-file-dialog', (event) => {
  console.log("2")
  dialog.showOpenDialog({
    properties: ['openFile']
  }, (files) => {
    if (files) {
      event.sender.send('selected-directory', files)
    }
  })
})