const Sequelize = require('sequelize');
const database = require('../database/db');
const Categoria = require('../models/categoria')

const Produto = database.define('produto',{
    nome:{
        type: Sequelize.STRING,
        allowNull:false
    },
    preco:{
        type: Sequelize.DOUBLE
    },
    descricao:{
        type: Sequelize.STRING
    }
});

Produto.belongsTo(Categoria,{
    foreignKey: 'categoriaId',
    as: 'categoria'
});

Categoria.hasMany(Produto, { 
    foreignKey: 'categoriaId',
    as: 'produtos'
});

module.exports = Produto;