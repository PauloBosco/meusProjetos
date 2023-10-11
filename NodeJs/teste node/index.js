const express = require('express');
const jwt = require('jsonwebtoken');
const bodyParser = require('body-parser');
const mysql = require('mysql2');
const app = express();
const PORT = 4000;
const secretKey = "23H4HJF235VAD8FDSFDHG"

app.use(bodyParser.json());

const connection = mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'teste',
    database: 'testedatabase'
});

function validaToken(req, res, next){
    const header = req.header('Authorization');
    

    if (!header || !header.startsWith('Bearer ')) {
        return res.status(401).json({ mensagem: "Token não fornecido no formato Bearer" });
    }

    const token = header.split(' ')[1];

    if(!token){
        res.status(401).json({mensagem:"Token não fornecido"})
    }

    try {
        jwt.verify(token, secretKey,{expiresIn:300});
        next();
    } catch (error) {
        res.status(401).json({mensagem:"Token inválido"})
    }
}

app.post("/login", (req, res)=>{

    const {nome, senha} = req.body;

    if(nome === "Paulo" && senha === "123"){
        const payload ={
            id: 1,
            nome: nome
        };
        const token = jwt.sign(payload, secretKey, {expiresIn:300})

        res.json({ token });
    }else{
        res.status(401).json({Mensagem: "Autenticação Falhou"});
    }
});

app.get("/produtos",(req, res)=>{
    res.send("Aplicação rodando em Produtos");
})

app.get("/categorias",validaToken,(req, res)=>{
    
    //res.send("Aplicação rodando em Categorias")
    res.send({id:1, nome:"Paulo",curso: "ADS", auth: true});
})

app.get("/emprestimos",(req, res)=>{
    
    connection.query('SELECT * FROM emprestimos',(err, results)=>{

        if(!err){
            res.json(results);
            
        }else{
            res.status(500).json({mensagem: "Erro ao buscar emprestimos no banco de dados"})
        }
        
        connection.end();
    });
})

app.listen(PORT, ()=>{
    console.log(`Aplicação rodando na porta ${PORT}`);
});
