const electron = require('electron');
const app = electron.app;
const menu = electron.Menu;
const BrowserWindow = electron.BrowserWindow;
const path = require('path');
const URL = require('url');
const crypto = require('crypto');

let mainWindow = null;

app.on('window-all-closed',() => {
    if (process.platform !== 'darwin') app.quit();
});

app.on('ready', () => {

    const hashes = crypto.getHashes();
    console.log(hashes);

    console.log(process.versions);
    mainWindow = new BrowserWindow({
        //frame:false,
        webPreferences:{
            nodeIntegration: true,
            webSecurity: false
        }
    });
    mainWindow.maximize()

    //const appMenu = menu.buildFromTemplate(menuObj.template);
    //menu.setApplicationMenu(appMenu);
    let url="";
    if(process.env.NODE_ENV!=='production'){
        mainWindow.webContents.openDevTools();
        url="http://localhost:"+process.env.ELECTRON_WEBPACK_WDS_PORT;
    }
    else
    {
        //mainWindow.webContents.openDevTools();
        url=URL.format({pathname:path.join(__dirname,'index.html'),protocol:'file'});
    }
    //url='file://'+app.getAppPath()+"../../dist/renderer/index.html";
    //console.log(url);
    //url=URL.format({pathname:path.join(__dirname,'index.html'),protocol:'file'});
    mainWindow.loadURL(url);
    mainWindow.on('closed', () => { mainWindow = null; });
    console.log("中文啊");
});