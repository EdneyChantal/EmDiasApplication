const base =  require('./apiBase');
const {Pool} = require('pg');
const  pool = new Pool(base.configBase); 



const listNature = async ()=>{
    const client = await pool.connect();
  
    q = 'select id_natureplan,id_workspace,ds_natureplan,id_natureplan_father,ind_type from tbnatureplan';
    const response = await  client.query(q);

    
  
    client.release();

    return response.rows;
};

module.exports.listNature = listNature ;