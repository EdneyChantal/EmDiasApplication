const fs = require('fs');
const readline = require('readline');
const { google } = require('googleapis');
const { doesNotMatch } = require('assert');
const { SSL_OP_DONT_INSERT_EMPTY_FRAGMENTS } = require('constants');
const { emitWarning } = require('process');
const { resolve } = require('path');
const fileIdDir = '1OYVGWLc1hX4vQNediPyksprc_SJhrKgc';
const fileIdDirFim = '18-mcihWT6GCHsnZh-YWeL3hHRxH-zHee'
var moment = require('moment');
const base =  require('./apiBase');
const { response } = require('express');
const {Pool} = require('pg');
const  pool = new Pool(base.configBase); 

// If modifying these scopes, delete token.json.
const SCOPES = ['https://www.googleapis.com/auth/drive'];
// The file token.json stores the user's access and refresh tokens, and is
// created automatically when the authorization flow completes for the first
// time.
const TOKEN_PATH = 'token.json';

const getToken = (arq, tokenIni, tokenFim, vini) => {
  let vindexini = arq.indexOf(tokenIni, vini);
  if (vindexini != -1) {
    let vindexfim = arq.indexOf(tokenFim, vindexini);
    if (vindexfim !== -1) {
      return {
        value: arq.substring(vindexini + tokenIni.length, vindexfim).replace('\r', '').replace('\n', '').trim(),
        index: vindexfim
      };
    }
  }
  return null;
}
const  vidconta = 0 ; 


const readFile = async (file) => {
  return new Promise((resolve,reject)=>{
    fs.readFile(file.path, (err, data) => {
      if (err) return reject(err);

    const arq = new Buffer(data).toString();
    let movBanco = {};
    let ventra = true;
    let x = {};
    let vini = 0;
    x = getToken(arq, '<BANKID>', '<ACCTID>');
    if (x)  movBanco.codbanco = x.value;
    else return reject('não conseguiu pegar o código do banco');
    x = getToken(arq, '<ACCTID>', '<ACCTTYPE>');
    movBanco.agConta = x.value;
    movBanco.moviment = [];
    vini = x.index;
    while (ventra) {
      let m = {};
      
      x = getToken(arq, '<DTPOSTED>', '<TRNAMT>', x.index?x.index:0 );
      if (x) m.dtmov = moment(x.value.substr(0,8),'YYYYMMDD').toDate();
      else  ventra = false;
      
      x = getToken(arq, '<TRNAMT>', '<FITID>', x?x.index:0 );
      if (x) m.amount = parseFloat(x.value.replace(',','.'));
      else  ventra = false;

      x = getToken(arq, '<FITID>', '<CHECKNUM>', x?x.index:0  );
      if (x) m.document = parseFloat(x.value);
      else  ventra = false;

      x = getToken(arq, '<MEMO>', '</STMTTRN>', x?x.index:0  );
      if (x) m.history= x.value;
      else  ventra = false;

      if ( m && ventra) movBanco.moviment.push(m);
    }
    
    return resolve(movBanco);
  });
  });
}
const processMoviment = async (moviment,index,client,agConta) => {
  
   try {   
  //if (index==1) {
    let vidnatureplan =undefined; 
    let vidnotimport=undefined ;
    

    q = `select idnatureplan,ind_not_imp from tbconfigextrato where idworkspace = ${agConta.id_workspace} and position(tokenextrato in '${moviment.history}') > 0
           and id_accountank = ${agConta.id_accountbank}`
    const responseGetnatureG =  await  client.query(q);
   
    if (responseGetnatureG.rows.length > 0) {
       
       if (responseGetnatureG.rows[0].ind_not_imp) vidnotimport = 'S';
       else vidnatureplan = responseGetnatureG.rows[0].idnatureplan; 
        
    } else {


      q = `select idnatureplan,ind_not_imp from tbconfigextrato where idworkspace = ${agConta.id_workspace} and position(tokenextrato in '${moviment.history}') > 0
          `
      const responseGetnature=  await  client.query(q);
      if (responseGetnature.rows.length > 0) {
         if (responseGetnature.rows[0].ind_not_imp) vidnotimport = 'S';
         else vidnatureplan = responseGetnature.rows[0].idnatureplan; 
      }

    }
    if (!vidnotimport) {

         if (moviment.document && moviment.document!=0) {

          q = `select idmovementbank from tbmovementbank where  id_accountbank = ${agConta.id_accountbank} 
          and number_doc = '${moviment.document}' and movementdate = $1`;

           const respGetMov = await client.query(q,[moment(moviment.dtmov).format('YYYYMMDD')]);

            vidnotimport = respGetMov.rows.length==0?undefined:'S';

         }  else {

          q = `select idmovementbank from tbmovementbank where  id_accountbank = ${agConta.id_accountbank} 
          and movementvalue = $1 and movementdate = $2`;

           const respGetMov = await client.query(q,[moviment.amount,moment(moviment.dtmov).format('YYYYMMDD')]);

           vidnotimport = respGetMov.rows.length==0?undefined:'S';
         }
          //var vforce = (moviment.document?(moviment.document==0?'S':'N'):'S');
 
    }

    if (!vidnotimport){
      
       var paramIn = [agConta.id_accountbank,
                      vidnatureplan,
                      moment(moviment.dtmov).format('YYYYMMDD'),
                      moviment.amount,
                      moviment.history,
                      moviment.document 
                         ]
        q = `insert into tbmovementbank (id_accountbank,id_natureplan,movementdate,movementvalue,history,number_doc) values ($1,$2,$3,$4,$5,$6)`;                
        try {
          const respInsMov  = await  client.query(q,paramIn);
          await client.query('COMMIT');
        } catch (e) {
          console.log(e.stack);
          await client.query('ROLLBACK');
       
        }

  } //resprespGetMov.rows.length 
//} // in
 return 'ok'
} catch(e) {
   console.log(e.stack);
   return e.stack;
}
 

}
const processMovements = async (moviments,client,agConta) =>{
  const result = await Promise.all(moviments.map(async (moviment,index)=> processMoviment(moviment,index,client,agConta)));
  console.log('processou movimentos todos');
  return result;
}
const processFile = async (file,client,auth) => {
    let  q = '';                 
    console.log('processou file'+file.id + ' '+ 'inicio') ;
     try {
    const responseGet = await getFile(auth,file.id);
    const responseReadfile = await readFile(responseGet);
                 
      
     q = 'select id_accountbank, id_workspace  from tbaccountbank where cod_conta = $1';
    const responseAgConta = await  client.query(q,[responseReadfile.agConta]);

 
    if (responseAgConta.rows.length > 0 ) { 
        
        const resp = await processMovements(responseReadfile.moviment,client,{id_workspace:responseAgConta.rows[0].id_workspace,
                                                                              id_accountbank:responseAgConta.rows[0].id_accountbank});
        //console.log(responseReadfile);
 
    }  // if responseAgConta.rows.length
    
      const responseMove = await moveFile(auth,file.id);
      console.log('processou file'+file.id+ ' ' +'fim');
      return ('ok');

  } catch (e) {
    
    return e.stack ;
  }

}
const processFiles = async (files,client,auth) =>{
  try {
    console.log('process files all inicio');
   const ret = await Promise.all(files.map(async (file)=>processFile(file,client,auth)));
   console.log('process files all finalizou');
    return 'ok';
  } catch (e) {
    console.log('process files all erro');
    return 'problemas no processFiles ' + e.stack ; 
  }
}


const beginProcess = async ()=> {
  //return  new Promise (async (resolve,reject) => {
   try{

  const respondeContent = await new Promise ((resolve,reject) => {
    fs.readFile('credentials.json',(err,content) =>{
       if (err) return reject(err);
       else return resolve(content);
    })
  });
  const responseAuth = await new Promise((resolve,reject)=>{
     authorize(JSON.parse(respondeContent),(auth)=>{
          return resolve(auth);
     });
  });
  const responseListFiles = await listFiles(responseAuth) ;
  //console.log(responseListFiles);


  // map in all files 
  if (responseListFiles) {
     const client = await pool.connect();
     console.log('inicio process files')
     const respProcessFiles = await processFiles(responseListFiles,client,responseAuth);
     console.log('fim process files')
     client.release();
  } //if (responseListFiles) {
  console.log('fim de tudo');
   return 'ok'
}  catch(e) {
  console.log('erro no fim de tudo');
   console.log(e.stack);
   return e.stack ;
}

//}); // return new Promisse

}
beginProcess().then(content=>{
   console.log(content);
   console.log('finalizou');
});
// Load client secrets from a local file.
/*fs.readFile('credentials.json', (err, content) => {
  if (err) return console.log('Error loading client secret file:', err);
  // Authorize a client with credentials, then call the Google Drive API.
  authorize(JSON.parse(content), listFiles);
});*/

/**
 * Create an OAuth2 client with the given credentials, and then execute the
 * given callback function.
 * @param {Object} credentials The authorization client credentials.
 * @param {function} callback The callback to call with the authorized client.
 */
async function authorize(credentials, callback) {
  const { client_secret, client_id, redirect_uris } = credentials.installed;
  const oAuth2Client = new google.auth.OAuth2(
    client_id, client_secret, redirect_uris[0]);

  // Check if we have previously stored a token.
  fs.readFile(TOKEN_PATH, (err, token) => {
    if (err) return getAccessToken(oAuth2Client, callback);
    oAuth2Client.setCredentials(JSON.parse(token));
    callback(oAuth2Client);
  });
}

/**
 * Get and store new token after prompting for user authorization, and then
 * execute the given callback with the authorized OAuth2 client.
 * @param {google.auth.OAuth2} oAuth2Client The OAuth2 client to get token for.
 * @param {getEventsCallback} callback The callback for the authorized client.
 */
function getAccessToken(oAuth2Client, callback) {
  const authUrl = oAuth2Client.generateAuthUrl({
    access_type: 'offline',
    scope: SCOPES,
  });
  console.log('Authorize this app by visiting this url:', authUrl);
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });
  rl.question('Enter the code from that page here: ', (code) => {
    rl.close();
    oAuth2Client.getToken(code, (err, token) => {
      if (err) return console.error('Error retrieving access token', err);
      oAuth2Client.setCredentials(token);
      // Store the token to disk for later program executions
      fs.writeFile(TOKEN_PATH, JSON.stringify(token), (err) => {
        if (err) return console.error(err);
        console.log('Token stored to', TOKEN_PATH);
      });
      callback(oAuth2Client);
    });
  });
}

/**
 * Lists the names and IDs of up to 10 files.
 * @param {google.auth.OAuth2} auth An authorized OAuth2 client.
 */
async function listFiles(auth) {
  const drive = google.drive({ version: 'v3', auth });
  
  return new Promise((resolve,reject)=>{
    drive.files.list({
      pageSize: 10,
      q: `"${fileIdDir}" in parents`,
      fields: 'nextPageToken, files(id, name ,mimeType)',
    }, (err, res) => {
      if (err) return reject(err);
      //resolve(res);
      return resolve(res.data.files);
   });
});
}
function moveFile(auth,fileid) {
  const drive = google.drive({ version: 'v3', auth });
  return new  Promise((resolve,reject) => {
    drive.files.update({
      fileId: fileid,
      addParents: fileIdDirFim,
      removeParents: fileIdDir,
      fields: 'id, parents'
    },(err,res)=>{
       if (err) return reject(err);
       return resolve('ok')
    });

  });

}



async function getFile(auth, fileid) {
  const drive = google.drive({ version: 'v3', auth });

  return new Promise((resolve,reject)=>{
      drive.files.get({
        fileId: fileid,
        alt: 'media'
      }, { responseType: 'stream' }).then(res => {
        
          const dest = fs.createWriteStream('./tmp/text.txt');
          let progress = 0;
  
          res.data
            .on('end', function () {
              resolve(dest);
            })
            .on('error', function (err) {
              reject(err);
            })
            .on('data', d => {
              progress += d.length;
              if (process.stdout.isTTY) {
                process.stdout.clearLine();
                process.stdout.cursorTo(0);
                process.stdout.write(`Downloaded ${progress} bytes`);
              }
            })
            .pipe(dest);
  
        });
  })
 
}
