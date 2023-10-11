const Sequelize = require('sequelize');
const database = require('../database/db');

const Categoria = database.define('categoria',{
    nome:{
        type: Sequelize.STRING,
        allowNull:false
    },
    descricao:{
        type: Sequelize.STRING
    }
});

module.exports = Categoria;