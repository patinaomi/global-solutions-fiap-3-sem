using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using LexusTech.Models;
using System.Security.Claims;

namespace LexusTech.Controllers
{
    public class LoginController : Controller
    {
        private readonly ApplicationDbContext _context;

        public LoginController(ApplicationDbContext context)
        {
            _context = context;
        }

        public IActionResult Logar()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Logar(Login model)
        {
            if (ModelState.IsValid)
            {
                var user = await _context.T_Usuario
                    .FirstOrDefaultAsync(c => c.Email == model.Email && c.Senha == model.Senha);

                if (user != null && user.Nome != null && user.Email != null)
                {
                    var claims = new List<Claim>
                    {
                        new Claim(ClaimTypes.Name, user.Nome),
                        new Claim(ClaimTypes.Email, user.Email),
                        new Claim(ClaimTypes.NameIdentifier, user.Id.ToString()) 
                    };

                    var claimsIdentity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);

                    await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, new ClaimsPrincipal(claimsIdentity));

                    // Após a autenticação bem-sucedida, registra o log
                    var log = new LoginLog
                    {

                        IdUsuario = user.Id,
                        Email = user.Email, 
                        DataHora = DateTime.Now 
                    };

                    _context.T_Login.Add(log);
                    await _context.SaveChangesAsync();

                    return RedirectToAction("Index", "Home");
                }
                else
                {
                    return RedirectToAction("MensagemErro");
                }

                
            }
            return View(model);
        }

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