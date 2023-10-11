using ApiCatalogoMinimal.Context;
using ApiCatalogoMinimal.Models;
using Microsoft.EntityFrameworkCore;

namespace ApiCatalogoMinimal.ApiEndpoints
{
    public static class ProdutosEndpoints
    {
        public static void MapProdutosEndpoints(this WebApplication app)
        {
            // ------------------------------ENDPOINTS PRODUTO ---------------------------------------

            app.MapGet("/produtos", async (AppDbContext db) => await db.Produtos.ToListAsync()).WithTags("Produtos").RequireAuthorization();

            app.MapGet("/produtos/{id:int}", async (int id, AppDbContext db) =>
            {
                return await db.Produtos.FindAsync(id) is Produto produto ? Results.Ok(produto) : Results.NotFound();
            });

            app.MapPost("/produtos", async (AppDbContext db, Produto produto) =>
            {
                db.Produtos.Add(produto);
                await db.SaveChangesAsync();

                return Results.Created($"/produtos/{produto.ProdutoId}", produto);
            });

            app.MapPut("/produtos/{id:int}", async (int id, Produto produto, AppDbContext db) =>
            {
                if (produto.ProdutoId != id) Results.BadRequest();

                var produtoDB = await db.Produtos.FindAsync(id);
                if (produtoDB is null) return Results.NotFound();

                produtoDB.Nome = produto.Nome;
                produtoDB.Descricao = produto.Descricao;
                produtoDB.Preco = produto.Preco;
                produtoDB.Imagem = produto.Imagem;
                produtoDB.Estoque = produto.Estoque;

                db.Produtos.Add(produtoDB);
                await db.SaveChangesAsync();

                return Results.Ok(produtoDB);
            });

            app.MapDelete("/produtos/{id:int}", async (int id, AppDbContext db) =>
            {
                var produto = await db.Produtos.FindAsync(id);
                if (produto is null)
                {
                    Results.NotFound();
                }

#pragma warning disable CS8604 // Possível argumento de referência nula.
                db.Produtos.Remove(produto);
#pragma warning restore CS8604 // Possível argumento de referência nula.
                await db.SaveChangesAsync();

                return Results.NoContent();

            });
        }
    }
}
