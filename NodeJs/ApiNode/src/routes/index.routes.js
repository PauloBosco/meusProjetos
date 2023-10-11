const express = require('express');
const produtoController = require('../controllers/produtoController');
const categoriaController = require('../controllers/categoriaController');
const router = express.Router();

//RotaProdutos
router.route("/produtos").get(produtoController.all).post(produtoController.create)
router.route("/produto/:id")
                .get(produtoController.retornaPorId)
                .put(produtoController.update)
                .delete(produtoController.delete)

router.route("/produto").all((request, response) => {
    response.status(400).json("Rota somente com número ID");
});

router.route("/produtos/:id").all((request, response) => {
    response.status(400).json("Essa rota não usa IDs");
});


//RotaCategorias
router.route("/categorias").get(categoriaController.all).post(categoriaController.create)
router.route("/categorias/produtos").get(categoriaController.allProdutos)
router.route("/categoria/:id")
                .get(categoriaController.retornaPorId)
                .put(categoriaController.update)
                .delete(categoriaController.delete)

router.route("/categoria").all((request, response) => {
    response.status(400).json("Rota somente com número ID");
});

router.route("/categorias/:id").all((request, response) => {
    response.status(400).json("Essa rota não usa IDs");
});

module.exports = router;
