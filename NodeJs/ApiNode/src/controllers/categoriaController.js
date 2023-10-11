const Categoria = require('../models/categoria');
const Produto = require('../models/produto');

module.exports={
    async all(request, response) {
        try {
          const categoria = await Categoria.findAll({attributes:{exclude:['createdAt','updatedAt']}});
          response.status(200).json(categoria);
        } catch (error) {
          console.log(error);
          response.status(400).send(error);
        }
      },
      async allProdutos(request, response) {
        try {
          const categoria = await Categoria.findAll({
            include: [{ 
              model: Produto, 
              as: 'produtos', 
              attributes:{
                exclude:['createdAt','updatedAt','categoriaId']
              } 
            }], 
                attributes:{
                  exclude: ['createdAt','updatedAt']} 
                });
          response.status(200).json(categoria);
        } catch (error) {
          console.log(error);
          response.status(400).send(error);
        }
      },
      async create(request, response) {
        try {
          await Categoria.create(request.body);
          response.status(201).json("Categoria criada com Sucesso!!!")
        } catch (error) {
          response.status(400).send(error);
        }
      },
      async retornaPorId (request, response) {
        try {
          const id = request.params.id;

          if (!/^\d+$/.test(id)) {
            return response.status(400).json("ID somente números");
          }

          const categoria = await Categoria.findByPk(id, {attributes:{exclude: ['createdAt','updatedAt']}});
          if(!categoria){
            return response.status(400).json("Categoria não encontrada")
          }
  
          return response.status(200).json(categoria);
          
        } catch (error) {
          response.status(400).send(error);
        }
      },
      async update (request, response){
        try {
          const {nome, descricao} = request.body;
          const id = request.params.id;
          const categoria = await Categoria.findOne({where: {id}});

          if(!categoria){
            return response.status(400).json("Categoria não encontrada")
          }

          categoria.nome = nome;
          categoria.descricao = descricao;
          
          await categoria.save();
          response.status(201).json("Categoria Atualizada!!")

        } catch (error) {
          response.status(400).send(error);
        }
      },
      async delete (request, response){
        try {
          const id = request.params.id;
          const categoria = await Categoria.destroy({where:{id}});

          if(!categoria){
            return response.status(400).json("Categoria não encontrada")
          }

          response.status(200).json("Categoria Removida");

        } catch (error) {
          response.status(400).send(error);
        }
      }
};