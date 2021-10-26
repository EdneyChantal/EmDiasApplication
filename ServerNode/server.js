var http = require('http');
var url = require('url');
var fs = require('fs');
var path = require('path');
var httpProxy = require('http-proxy');
var express = require("express"),
     bodyParser = require('body-parser');

//var cvs = require('csv-string');
var Report = require('fluentreports/lib/fluentReports' ).Report;
var demo1 = require('./report/demo5');
var relpendencia = require('./report/relpendencia');

var contentTypes = {
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

var optionsWebService = {
  host: 'scmweb09',
  port: '80',
  path: '',
  method: 'GET',
  headers: {
    'Content-Type': 'text/json;',
    'Content-Length': 0
  }
};  

var proxy = httpProxy.createProxyServer({});


app = express();
app.set('port', 8080);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json({ type: 'application/json' }));


app.get(['/SistemasWeb/*','/webservice/*','/webservicebi/*'],function(req,res){
  res.setTimeout(900000);
  proxy.web(req,res,{target:'http://scmweb09'});
});

app.get('/report/demo1',function(req,res) {
  demo1.printreport(res);
});
app.get('/report/relpendencia/:tpperiodo/:dtrecini/:dtrecfim/:dtini/:dtfim/:convenio/:fornec/:contabil',function(req,res) {

  let vtp = req.params.tpperiodo;
  let vdtrecini = req.params.dtrecini.replace(/\./g,'/');
  let vdtrecfim = req.params.dtrecfim.replace(/\./g,'/');
  let vdtini = req.params.dtini.replace(/\./g,'/');
  let vdtfim = req.params.dtfim.replace(/\./g,'/');
  let vparams = `Períodos: Recebimento Inicial ${vdtrecini}, Final ${vdtrecfim }; Apropriação Inicial ${vdtini}, Final ${vdtfim};  Tipo de Apropriação ${vtp}`; 
  let vconvenio = JSON.parse(req.params.convenio);
  let vfornec = JSON.parse(req.params.fornec);
  let vcontabil = JSON.parse(req.params.contabil);
  
  let getLista = function(objpar) {
    let pltc= '';  
    if (objpar && objpar.values) {
      objpar.values.forEach((v2,i)=>{
          if (v2['nome'][0]) {
            if (pltc != '')  pltc += ',';
             pltc += v2['nome'][0] ; 
          }   
      });
    }
    return pltc; 
  }
  let montaWhereC = function(objpar) {
    if (objpar && objpar.values && objpar.values.length > 0  && getLista(objpar) != '' ) {
       if (objpar.exceto) {
         return  'and s.cd_fornecedor in ( select cv.cd_fornecedor from dbamv.convenio cv where cv.cd_convenio not in (' + getLista(objpar)  +'))';
       } else {
         return   'and s.cd_fornecedor in ( select cv.cd_fornecedor from dbamv.convenio cv where cv.cd_convenio in (' + getLista(objpar)  +'))';
       }
    } else return '' ;
  }
  let montaWhereF = function(objpar) {
    if (objpar && objpar.values && objpar.values.length > 0 && getLista(objpar) != '' ) {
       if (objpar.exceto) {
         return  ' and s.cd_fornecedor not in (' + getLista(objpar)  +')';
       } else {
         return   ' and s.cd_fornecedor in ( ' + getLista(objpar)  +')';
       }
    } else return '' ;
  }
  let montaWhereCo = function(objpar) {
    if (objpar && objpar.values && objpar.values.length > 0 && getLista(objpar) != '') {
       if (objpar.exceto) {
         return  ' and s.cd_contabil not in  (' + getLista(objpar)  +')';
       } else {
         return   ' and s.cd_contabil in ( ' + getLista(objpar)  +')';
       }
    } else return '' ;
  }
 
  let pwhere = montaWhereC(vconvenio) + ' ' + montaWhereF(vfornec) + ' ' + montaWhereCo(vcontabil);

   
  var url=`/webservice/api/get_appwebmob_relatorio_relpend/select/param_json?param_json=` + 
    encodeURIComponent(`{PDTRECINI:"'${vdtrecini}'",PDTRECFIM:"'${vdtrecfim}'",PTPPERIODO:"'${vtp}'",PDTINI:"'${vdtini}'",PDTFIM:"'${vdtfim}'",PWHERE:"${pwhere}"}`); 

 /* let paramjson={
    PDTRECINI:`"'${vdtrecini}'"`,
    PDTRECFIM:`"'${vdtrecfim}'"`,
    PTPPERIODO:`"'${vtp}'"`,
    PDTINI:`"'${vdtini}'"`,
    PDTFIM:`"'${vdtfim}'"`,
    PWHERE:`"${pwhere}"`
  };

  url = `/webservice/api/get_appwebmob_relatorio_relpend/select/param_json?param_json=` + JSON.stringify(paramjson);*/
  optionsWebService.path = url;

  
  var request2 = http.request(optionsWebService, function(resp) {
    var msg = '';
    resp.setEncoding('utf8');
    resp.on('data', (chunk) =>{
        msg += chunk;
    });
  
    resp.on('end', function() {
      var dataretorno = {};
      dataretorno  = JSON.parse(msg);
      //var vprint = ''; 
      //res.setHeader('Content-type','application/octet-stream');
      //res.send(dataretorno);
      relpendencia.printreport(res,dataretorno,vparams);
    }); 
  });
  request2.write('');
  request2.end();
 //relpendencia.printreport(res,null);

});

app.get('/arquivotxt/:teste',function(req,res){
     //var caminho = url.parse(req.url).pathname;
	 //var ficheiro = path.join(__dirname, 'public', caminho);
 var url=`/webservicebi/api/get_dashpdd_bi_cons_2/select/param_json?param_json={PDATA:"'01/01/2018'",PCOLUNA:'cubo_pdd.tipocliente',PWHERE:''}`;
   
optionsWebService.path = url;

var request2 = http.request(optionsWebService, function(resp) {
var msg = '';
resp.on('data', (chunk) =>{
    //console.log(cvs.stringify(JSON.parse(chunk),','));
    /*console.log(bufferify.decode(1,{"Table1":[{"SALDO_CONTABIL":'number',
                                            "SALDO_FINANCEIRO":'number',
                                            "SALDO_CONCILIAR":'number',
                                            "VALOR_PREVISTO":'number',
                                            "VALOR_CONCILIAR":'number',
                                            "VALOR_RECEBIDO":'number',
                                            "VALOR_GLOSA":'number',
                                            "SALDO_A_RECEBER_MES":'number',
                                            "AVENCER":'number',
                                          "ATE90DIAS":'number',
                                        "ATE180DIAS":'number',
                                      "ATE360DIAS":'number',
                                    "MAIS1ANO":'number',
                                  "SALDO_A_RECEBER_ANTERIOR":'number',
                                "VALOR_A_CONCILIAR_ANTERIOR":'number',
                              "SALDO_FINAL_ANTERIOR":'number',
                            "TIPOCLIENTE":'string'}]},chunk));*/

    //console.log(chunk.toJSON());
    msg += chunk;
    /* for (var property in chunk) {
       console.log(property + ' ' + typeof chunk[property]);
       
    }*/
  });
  
  resp.on('end', function() {

    var dataretorno = {};
    
    dataretorno  = JSON.parse(msg).replace('"','');
    var vprint = ''; 
   // console.log(dataretorno)
    //arrayBufferToData.toJSON(dataretorno);
/*console.log(bufferify.decode(0,{Table1:[{SALDO_CONTABIL:'number',
                                            SALDO_FINANCEIRO:'number',
                                            SALDO_CONCILIAR:'number',
                                            VALOR_PREVISTO:'number',
                                            VALOR_CONCILIAR:'number',
                                            VALOR_RECEBIDO:'number',
                                            VALOR_GLOSA:'number',
                                            SALDO_A_RECEBER_MES:'number',
                                            AVENCER:'number',
                                          ATE90DIAS:'number',
                                        ATE180DIAS:'number',
                                      ATE360DIAS:'number',
                                    MAIS1ANO:'number',
                                  SALDO_A_RECEBER_ANTERIOR:'number',
                                VALOR_A_CONCILIAR_ANTERIOR:'number',
                              SALDO_FINAL_ANTERIOR:'number',
                            TIPOCLIENTE:'string'}]},dataretorno));*/

   // console.log(typeof dataretorno);


    /*for (var property in dataretorno) {
       console.log(property + ' ' + dataretorno[property]);
       
    }*/
    
    
    
    /*dataretorno['Table1'].forEach((v,i)=>{
       let vlinha ='';
       if (i==0) {
         for (var property in v){
              vprint += property + ';'
         }
         linha += '/r/n';
         vprint += vlinha;
         vlinha ='';
       }
         
       v.forEach((v1)=>{
           vlinha += v1 + ';';
       })  
       vprint += vlinha;
    });*/

     
 
    res.setHeader('Content-type','application/octet-stream');
    res.send(dataretorno);


  });
});
request2.write('');
request2.end();

//res.send('ok');
		
	  
     /*console.log(req.params);
     var myarq = ficheiro.replace('\arquivotxt','');
     res.setHeader('Content-type','application/octet-stream');
     res.end('isso é um teste ok ');*/
});
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
 
 http.createServer(app).listen(8080,function(){
   console.log('arrancou');
 });
/*http.createServer(function (pedido, resposta) {
 
// Aqui vamos escrever o código do servidor que vai ser
// executado sempre que for feito um pedido

var caminho = url.parse(pedido.url).pathname;

if (caminho==='/' || (caminho.substr(-1,1)==='/')) {
 var ficheiro = path.join(__dirname, 'public', caminho, 'index.html');
} else {
 var ficheiro = path.join(__dirname, 'public', caminho);
}
// proxy reverso ------
if (caminho.indexOf('/SistemasWeb')>=0 || caminho.indexOf('/webservice')>=0 ) {
  proxy.web(pedido,resposta,{target:'http://scmweb09'});
} else if (caminho.indexOf('/arquivotxt')>=0 ){ 
  console.log(pedido.params);
  var myarq = ficheiro.replace('\arquivotxt','');
  resposta.setHeader('Content-type','application/octet-stream');
  resposta.end('isso é um teste ok ');
  //fs.readFile(path.join(myarq,'gtfinan','0.chunk.js.map'),function(erro,data){
   //  resposta.end(data);
  //});
 
} else {
  var extensao = path.extname(ficheiro).slice(1);
  if (extensao && contentTypes[extensao]) resposta.setHeader('Content-Type', contentTypes[extensao || 'html']);
  fs.readFile(ficheiro, function (erro, dados) {
     if (erro) {
        resposta.writeHead(404);
        resposta.end();
     } else {
       resposta.end(dados);
     }
  });    
  
}  

}).listen(8080, 'localhost', function () {
  console.log('--- O servidor arrancou –--');
});*/