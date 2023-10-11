using TesteWebApi.models;

namespace TesteWebApi.Services
{
    public interface ITokenService
    {
        string GerarToken(string key, string audience, string issuer, UserModel user);
    }
}
