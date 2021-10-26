const express = require('express');
const app = express();
const port = 3000;
const base = require('./coreBase');
const url = require('url');
const path = require('path');
const bodyParser = require('body-parser');

const contentTypes = {
    'html' : 'text/html',
    'json' : 'text/json',
    'css'  : 'text/css',
    'ico'  : 'image/x-icon',
    'png'  : 'image/png',
    'svg'  : 'image/svg+xml',
    'js'   : 'application/javascript',
    'otf'  : 'application/x-font-otf',
    'ttf'  : 'application/x-font-ttf',
    'eot'  : 'application/vnd.ms-fontobject',
    'woff' : 'application/x-font-woff',
    'woff2': 'application/font-woff2',
    'zip'  : 'application/zip'
  }
  
  const optionsWebService = {
    host: 'scmweb09',
    port: '80',
    path: '',
    method: 'GET',
    headers: {
      'Content-Type': 'text/json;',
      'Content-Length': 0
    }
  };  
  
app.set('port', 3000);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');
app.use(bodyParser.urlencoded({
      extended: true
}));

app.use(bodyParser.json({ type: 'application/json' }));


// rest web api's  provide the application data---------------------
app.get('/hello', (req, res) => {
    res.send('Hello World, from express');
});

app.get('/listnature', async (req, res) => {
    const r = await base.listNature();
    //console.log(r);
    res.json(r);
});


// web server  provide the pages of a web application -----------------------
app.get('/*',function(req,res){
    var caminho = url.parse(req.url).pathname;
    if (caminho==='/' || (caminho.substr(-1,1)==='/')) {
       var ficheiro = path.join(__dirname, 'public', caminho, 'index.html');
    } else {
      var ficheiro = path.join(__dirname, 'public', caminho);
    } 
      
     var extensao = path.extname(ficheiro).slice(1);
       if (extensao && contentTypes[extensao]) res.setHeader('Content-Type', contentTypes[extensao || 'html']);
       fs.readFile(ficheiro, function (erro, dados) {
          if (erro) {
            res.writeHead(404);
            res.end();
         } else {
           res.end(dados);
         }
      });    
         
   
  });
app.listen(port, () => console.log(`Hello world app listening on port ${port}!`))