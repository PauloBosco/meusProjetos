const Produto = require('../models/produto');
const Categoria = require('../models/categoria');

module.exports = {
  async all(request, response) {
    try {
      const produtos = await Produto.findAll({ 
        include: [{ 
          model: Categoria, 
          as: 'categoria', 
          attributes:{
            exclude:['createdAt','updatedAt']} }], attributes:{exclude: ['categoriaId','createdAt','updatedAt']} });
      response.status(200).json(produtos);
    } catch (error) {
      console.log(error);
      response.status(400).send(error);
    }
  },
  async create(request, response) {
    try {
      await Produto.create(request.body);
      response.status(201).json("Produto Criado com Sucesso!!!")
    } catch (error) {
      response.status(400).send(error);
    }
  },
  async retornaPorId(request, response) {
    try {
      const id = request.params.id;

      if (!/^\d+$/.test(id)) {
        return response.status(400).json("ID somente números");
      }

      const produto = await Produto.findByPk(id, { 
        include: [{ 
          model: Categoria, 
          as: 'categoria', 
          attributes:{
            exclude:['createdAt','updatedAt']} }], attributes:{exclude: ['categoriaId','createdAt','updatedAt']} });
      if (!produto) {
        return response.status(400).json("Produto não encontrado")
      }

      return response.status(200).json(produto);

    } catch (error) {
      response.status(400).send(error);
    }
  },
  async update(request, response) {
    try {
      const { nome, preco, descricao } = request.body;
      const id = request.params.id;
      const produto = await Produto.findOne({ where: { id } });

      if (!produto) {
        return response.status(400).json("Produto não encontrado")
      }

      produto.nome = nome;
      produto.preco = preco;
      produto.descricao = descricao;

      await produto.save();
      response.status(201).json("Produto Atualizado!!")

    } catch (error) {
      response.status(400).send(error);
    }
  },
  async delete(request, response) {
    try {
      const id = request.params.id;
      const produto = await Produto.destroy({ where: { id } });

      if (!produto) {
        return response.status(400).json("Produto não encontrado")
      }

      response.status(200).json("Produto Removido");

    } catch (error) {
      response.status(400).send(error);
    }
  }
};