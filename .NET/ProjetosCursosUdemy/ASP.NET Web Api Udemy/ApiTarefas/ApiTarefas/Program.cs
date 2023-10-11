using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);


builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddDbContext<AppDbContext>(options => 
                    options.UseInMemoryDatabase("TarefasDB"));

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.MapGet("/tarefas", async (AppDbContext db) => await db.Tarefas.ToListAsync());

app.MapGet("/tarefas/{id}", async (int id, AppDbContext db) =>
    await db.Tarefas.FindAsync(id) is Tarefa tarefa? Results.Ok(tarefa) : Results.NotFound());


app.MapGet("/tarefas/concluida", async (AppDbContext db) =>
            await db.Tarefas.Where(t => t.isConcluida == true).ToListAsync());

app.MapPost("/tarefas", async(Tarefa tarefa, AppDbContext db) =>
{
    db.Tarefas.Add(tarefa);
    await db.SaveChangesAsync();
    return Results.Created($"/tarefas/{tarefa.Id}", tarefa);
});

app.MapPut("/tarefas/{id}", async (int id, Tarefa inputTarefa, AppDbContext db) =>
{
    var tarefa = await db.Tarefas.FindAsync(id);
    if(tarefa is null) return Results.NotFound();

    tarefa.Nome = inputTarefa.Nome;
    tarefa.isConcluida = inputTarefa.isConcluida;

    await db.SaveChangesAsync();
    return Results.NoContent();

});

app.MapDelete("tarefas/{id}", async( int id, AppDbContext db) =>
{
    if(await db.Tarefas.FindAsync(id) is Tarefa tarefa)
    {
        db.Tarefas.Remove(tarefa);
        db.SaveChangesAsync();
        return Results.Ok(tarefa);
    }
    return Results.NotFound();
});

app.Run();

class Tarefa
{
    public int Id { get; set; }
    public string? Nome { get; set; }
    public bool isConcluida { get; set; }
}

class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext>options) :base(options)   
    {
        
    }
    
    public DbSet<Tarefa> Tarefas => Set<Tarefa>();

}