using ApiCatalogoMinimal.ApiEndpoints;
using ApiCatalogoMinimal.AppServicesExtensions;

var builder = WebApplication.CreateBuilder(args);

builder.AddApiSwagger();
builder.AddPersistence();
builder.Services.AddCors();
builder.AddAutenticationJwt();

var app = builder.Build();

app.MapAutenticacaoEndpoints();
//Definindo EndPoints
app.MapCategoriasEndpoints();
app.MapProdutosEndpoints();


var environment = app.Environment;
app.UseExceptionHandling(environment)
    .UseSwaggerMiddleware()
    .UseAppCors();

app.UseHttpsRedirection();

app.UseAuthentication();
app.UseAuthorization();

app.Run();
