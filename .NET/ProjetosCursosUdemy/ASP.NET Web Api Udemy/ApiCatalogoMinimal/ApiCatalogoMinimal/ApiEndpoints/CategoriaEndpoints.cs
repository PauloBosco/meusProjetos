using ApiCatalogoMinimal.Context;
using ApiCatalogoMinimal.Models;
using Microsoft.EntityFrameworkCore;

namespace ApiCatalogoMinimal.ApiEndpoints;

public static class CategoriaEndpoints
{
    public static void MapCategoriasEndpoints(this WebApplication app)
    {
        app.MapGet("/categorias", async (AppDbContext db) => await db.Categorias.ToListAsync()).WithTags("Categorias").RequireAuthorization();

        app.MapGet("/categorias/{id:int}", async (int id, AppDbContext db) =>
        {
            return await db.Categorias.FindAsync(id) is Categoria categoria ?
                        Results.Ok(categoria) :
                        Results.NotFound();
        });

        app.MapPost("/categorias", async (Categoria categoria, AppDbContext db) =>
        {
            db.Categorias.Add(categoria);
            await db.SaveChangesAsync();

            Results.Created($"/categorias/{categoria.CategoriaId}", categoria);
        });

        app.MapPut("/categorias/{id:int}", async (int id, Categoria categoria, AppDbContext db) =>
        {
            //CategoriaId refere-se a categoria do BD, e o id refere-se ao id que foi passado no Swagger
            if (categoria.CategoriaId != id) return Results.BadRequest();

            var categoriaDB = await db.Categorias.FindAsync(id);

            if (categoriaDB is null) return Results.NotFound();

            //Lembrar que tem que atribuir os valores que forem escritos no Swagger para a categoria que está no BD;
            categoriaDB.Nome = categoria.Nome;
            categoriaDB.Descricao = categoria.Descricao;

            await db.SaveChangesAsync();
            return Results.Ok(categoriaDB);

        });

        app.MapDelete("/categorias/{id:int}", async (int id, AppDbContext db) =>
        {
            var categoria = await db.Categorias.FindAsync(id);
            if (categoria is null)
            {
                Results.NotFound();
            }

#pragma warning disable CS8604 // Possível argumento de referência nula.
            db.Categorias.Remove(categoria);
#pragma warning restore CS8604 // Possível argumento de referência nula.
            await db.SaveChangesAsync();

            return Results.NoContent();

        });
    }
}
