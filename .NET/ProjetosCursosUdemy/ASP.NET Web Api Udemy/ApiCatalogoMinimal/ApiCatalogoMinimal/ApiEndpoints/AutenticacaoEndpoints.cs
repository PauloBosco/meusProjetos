using ApiCatalogoMinimal.Models;
using ApiCatalogoMinimal.Services;
using Microsoft.AspNetCore.Authorization;
using System.Runtime.CompilerServices;

namespace ApiCatalogoMinimal.ApiEndpoints;

static class AutenticacaoEndpoints
{
    public static void MapAutenticacaoEndpoints(this WebApplication app)
    {
        //EndPonint Para login

        app.MapPost("/login", [AllowAnonymous] (UserModel userModel, ITokenService tokenService) =>
        {
            if (userModel == null)
            {
                return Results.BadRequest("Login Inv�lido");
            }
            if (userModel.UserName == "macoratti" && userModel.Password == "numsey#123")
            {
#pragma warning disable CS8604 // Possível argumento de referência nula.
                var tokenString = tokenService.GerarToken(app.Configuration["Jwt:Key"],
                                                          app.Configuration["Jwt:Issuer"],
                                                          app.Configuration["Jwt:Audience"],
                                                          userModel);

#pragma warning restore CS8604 // Possível argumento de referência nula.
                return Results.Ok(new { token = tokenString });
            }
            else
            {
                return Results.BadRequest("Login Inv�lido");
            }
        }).Produces(StatusCodes.Status400BadRequest)
                      .Produces(StatusCodes.Status200OK)
                      .WithName("Login")
                      .WithTags("Autenticacao");
    }
}
