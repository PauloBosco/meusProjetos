using Microsoft.EntityFrameworkCore;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Web.Http;
using TesteWebApi.context;
using TesteWebApi.models;
using TesteWebApi.Services;

namespace TesteWebApi.ApiEndpoints
{
    public static class LoginEndpoints
    {
        public static void  MapLoginEndpoints(this WebApplication app)
        {
            app.MapPost("/login", [AllowAnonymous] (UserModel usermodel, ITokenService tokenService) => 
            { 

                if(usermodel == null)
                {
                    return Results.BadRequest("Login Inválido");
                };

                if(usermodel.UserName == "paulo" && usermodel.Password == "123")
                {
                    var tokenString = tokenService.GerarToken(app.Configuration["Jwt:Key"]!,
                                                              app.Configuration["Jwt:Audience"]!,
                                                              app.Configuration["Jwt:Issuer"]!,
                                                              usermodel);
                    return Results.Ok(new { tokenString });
                }
                else
                {
                    return Results.BadRequest("Login Inválido");
                }

            });

            

        }
    }
}
