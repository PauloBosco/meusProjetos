const express = require('express');
const sequelize = require('./src/database/db');
const Routes = require('./src/routes/index.routes');

const app = express();

sequelize.sync().then(()=>console.log("Banco de dados Conectado com Sucesso!!!"));

app.use(express.json());


app.use("/api", Routes);


app.listen(5000, ()=>{
    console.log("Aplicação rodando na porta 5000 ");
});