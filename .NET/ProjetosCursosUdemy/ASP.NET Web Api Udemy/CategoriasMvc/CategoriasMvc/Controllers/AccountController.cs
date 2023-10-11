using CategoriasMvc.Models;
using CategoriasMvc.Services;
using Microsoft.AspNetCore.Mvc;

namespace CategoriasMvc.Controllers
{
    public class AccountController : Controller
    {
        private readonly IAutenticacao _autenticacaoService;

        public AccountController(IAutenticacao autenticacaoService)
        {
            _autenticacaoService = autenticacaoService;
        }

        [HttpGet]
        public ActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public async Task<ActionResult> Login(UsuarioViewModel model)
        {
            if(!ModelState.IsValid)
            {
                ModelState.AddModelError(string.Empty, "Login inválido....");
            }

            var result = await _autenticacaoService.AutenticaUsuario(model);
            
            if(result is null)
            {
                ModelState.AddModelError(string.Empty, "Login inválido....");
                return View(model);
            }

            //Armazena o Token no cookie
            Response.Cookies.Append("X-Access-Token", result.Token, new CookieOptions()
            {
                Secure = true,
                HttpOnly = true,
                SameSite = SameSiteMode.Strict
            });
            return Redirect("/");
        }
    }
}
