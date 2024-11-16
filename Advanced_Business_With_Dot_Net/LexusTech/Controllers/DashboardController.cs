using System.Security.Claims;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Controllers
{
    [Route("Dashboard")]
    public class DashboardController : Controller
    {
        private readonly ApplicationDbContext _context;

        public DashboardController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet("Index")]  // Especifica a rota para a ação Index
        [Authorize]
        public async Task<IActionResult> Index()
        {
            var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

            if (string.IsNullOrEmpty(userIdString) || !int.TryParse(userIdString, out var userId))
            {
                return RedirectToAction("Error");
            }

            var consumos = await _context.T_Consumo
                                         .Where(c => c.IdUsuario == userId)
                                         .ToListAsync();

            return View(consumos);
        }
    }
}
