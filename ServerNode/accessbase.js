var pg = require('pg');
//or native libpq bindings
//var pg = require('pg').native
var base = require('./apiBase')

//var conString = "postgres://mvtpiaib:yRDTvIRmzlypqygqQTrhF4scJ_puSRHu@motty.db.elephantsql.com:5432/mvtpiaib" //Can be found in the Details page
var client = new pg.Client(base.conString);
client.connect(function(err) {
  if(err) {
    return console.error('could not connect to postgres', err);
  }
  client.query('SELECT NOW() AS "theTime"', function(err, result) {
    if(err) {
      return console.error('error running query', err);
    }
    console.log(result.rows[0].theTime);
    // >> output: 2018-08-23T14:02:57.117Z
    client.end();
  });
});