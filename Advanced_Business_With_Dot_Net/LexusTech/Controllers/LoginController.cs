using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Mvc;
using LexusTech.Models;
using System.Security.Claims;
using LexusTech.Infrastructure.Interfaces;
using System.Security.Authentication;

namespace LexusTech.Controllers
{
    public class LoginController : Controller
    {
        private readonly ILoginService _loginService;

        public LoginController(ILoginService loginService)
        {
            _loginService = loginService ?? throw new ArgumentNullException(nameof(loginService));
           
        }

        public IActionResult Logar()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Logar(Login login)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    var user = await _loginService.Autenticar(login.Email, login.Senha);

                    var claims = new List<Claim>
                    {
                        new Claim(ClaimTypes.Name, user.Email),
                        new Claim(ClaimTypes.NameIdentifier, user.Id.ToString()),
                        new Claim("FullName", user.Nome)
                    };

                    var claimsIdentity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);

                    await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, new ClaimsPrincipal(claimsIdentity));

                    var logins = new Login
                    {
                        Id = user.Id,
                        Email = user.Email
                    };

                    await _loginService.Criar(logins);

                    return RedirectToAction("Index", "Home");
                }
                catch (AuthenticationException)
                {
                    return RedirectToAction("MensagemErro", "Login");
                }
            }
            return View(login);
        }


        [HttpGet("MensagemErro")]
        public IActionResult MensagemErro()
        {
            return View();
        }

        public async Task<IActionResult> Logout()
        {
            await HttpContext.SignOutAsync(CookieAuthenticationDefaults.AuthenticationScheme);
            return RedirectToAction("Logar", "Login");
        }
    }
}
