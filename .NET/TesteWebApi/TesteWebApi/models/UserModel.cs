using System.Security.Claims;

namespace TesteWebApi.models;

public class UserModel
{
    public string? UserName { get; set; }
    public string? Password { get; set; }
    public string? Email { get; set; }
}
