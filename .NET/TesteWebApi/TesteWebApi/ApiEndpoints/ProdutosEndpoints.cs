using Microsoft.EntityFrameworkCore;
using TesteWebApi.context;
using TesteWebApi.models;

namespace TesteWebApi.ApiEndpoints
{
    public static class ProdutosEndpoints
    {
        public static void MapProdutosEndpoints(this WebApplication app)
        {


            app.MapGet("/produtos", async (AppDbContext db) =>
            {
                return await db.Produtos.AsNoTracking().FirstOrDefaultAsync();
            });

            app.MapGet("/produtos/{id:int}", async (AppDbContext db, int id) =>
            {

                var produtoDb = await db.Produtos.AsNoTracking().FirstOrDefaultAsync(x => x.Id == id);
                if (produtoDb == null)
                {
                    return Results.NotFound("Produto Não encontrado");
                }

                return Results.Ok(produtoDb);

                //return db.Produtos.Find(id) is Produto produto ? Results.Ok(produto):Results.NotFound();

            });

            app.MapPost("/produtos", async (AppDbContext db, Produto produto) =>
            {

                db.Produtos.Add(produto!);
                await db.SaveChangesAsync();

                return Results.Created($"/produtos/{produto.Id}", produto);
            });

            app.MapPut("/produtos/{id:int}", async (AppDbContext db, Produto produto, int id) =>
            {
                if (produto.Id != id)
                {
                    return Results.NotFound("Id não encontrado");
                }

                var produtoDB = await db.Produtos.FirstOrDefaultAsync(x => x.Id == id);
                if (produtoDB == null)
                {
                    return Results.NotFound("Produto não encontrado");
                }

                produtoDB.Nome = produto.Nome;
                produtoDB.Descricao = produto.Descricao;

                await db.SaveChangesAsync();

                return Results.Ok(produtoDB);
            });

            app.MapDelete("/produtos/{id:int}", async (AppDbContext db, int id) =>
            {
                var produtoDB = await db.Produtos.FirstOrDefaultAsync(x => x.Id == id);
                if (produtoDB == null)
                {
                    Results.NotFound("Produto não encontrado");
                }

                db.Produtos.Remove(produtoDB!);
                await db.SaveChangesAsync();

                return Results.Ok();
            });

        }
    }
}
    