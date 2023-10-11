using ApiCatalogoMinimal.Models;

namespace ApiCatalogoMinimal.Services;

public interface ITokenService
{
    string GerarToken(string key, string issuer, string audience, UserModel user);
}
