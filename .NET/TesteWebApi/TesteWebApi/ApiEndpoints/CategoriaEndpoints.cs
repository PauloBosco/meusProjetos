using Microsoft.EntityFrameworkCore;
using System.Text.Json;
using System.Text.Json.Serialization;
using TesteWebApi.context;
using TesteWebApi.models;

namespace TesteWebApi.ApiEndpoints
{
    public static class CategoriaEndpoints
    {
        public static void  MapCategoriaEndpoints(this WebApplication app)
        {


            app.MapGet("/categorias", async (AppDbContext db) =>
            {
                var categorias = await db.Categorias.AsNoTracking().ToListAsync();

                var options = new JsonSerializerOptions
                {
                    DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull
                };

                return  Results.Json(categorias,options);
            }).RequireAuthorization();

            app.MapGet("/categorias/produtos", async (AppDbContext db) =>
            {
                return await db.Categorias.AsNoTracking().Include(p => p.Produtos).ToListAsync();
            });

            app.MapGet("/categorias/{id:int}", async (AppDbContext db, int id) =>
            {

                var categoriaDB = await db.Categorias.FirstOrDefaultAsync(x => x.Id == id);
                if (categoriaDB == null)
                {
                    return Results.NotFound("Produto Não encontrado");
                }

                var options = new JsonSerializerOptions
                {
                    DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull
                };

                return Results.Json(categoriaDB,options);

                //return db.Categorias.Find(id) is Categoria categoria? Results.Ok(categoria):Results.NotFound();

            });

            app.MapPost("/categorias", async (AppDbContext db, Categoria categoria) =>
            {

                db.Categorias.Add(categoria!);
                await db.SaveChangesAsync();

                return Results.Created($"/categorias/{categoria.Id}", categoria);
            });

            app.MapPut("/categorias/{id:int}", async (AppDbContext db, Categoria categoria, int id) =>
            {
                if (categoria.Id != id)
                {
                    return Results.NotFound("Id não encontrado");
                }

                var categoriaDB = await db.Categorias.FirstOrDefaultAsync(x => x.Id == id);
                if (categoriaDB == null)
                {
                    return Results.NotFound("Produto não encontrado");
                }

                categoriaDB.Nome = categoria.Nome;
                categoriaDB.Descricao = categoria.Descricao;

                await db.SaveChangesAsync();

                return Results.Ok(categoriaDB);
            });

            app.MapDelete("/categorias/{id:int}", async (AppDbContext db, int id) =>
            {
                var categoriaDB = await db.Categorias.FirstOrDefaultAsync(x => x.Id == id);
                if (categoriaDB == null)
                {
                    Results.NotFound("Produto não encontrado");
                }

                db.Categorias.Remove(categoriaDB!);
                await db.SaveChangesAsync();

                return Results.Ok();
            });

        }
    }
}
