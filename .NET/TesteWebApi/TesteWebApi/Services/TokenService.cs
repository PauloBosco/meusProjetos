using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using TesteWebApi.models;

namespace TesteWebApi.Services;

public class TokenService : ITokenService
{
    public string GerarToken(string key, string audience, string issuer, UserModel user)
    {
        var claims = new List<Claim>()
        {
            new Claim(ClaimTypes.Name, user.UserName!),
            new Claim(ClaimTypes.Email, user.Email!)
        };
       
        var securityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(key));
        var credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);
        var token = new JwtSecurityToken(expires: DateTime.Now.AddMinutes(120),
                                         audience: audience,
                                         claims: claims,
                                         issuer: issuer,
                                         signingCredentials: credentials);

        var tokenHandler = new JwtSecurityTokenHandler();
        var stringToken = tokenHandler.WriteToken(token);
        return stringToken;
    }
}
