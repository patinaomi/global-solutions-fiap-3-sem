using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace LexusTech.Controllers
{
    public class MapaController : Controller
    {
        private readonly ApplicationDbContext _context;

        public MapaController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        [Authorize]
        public IActionResult Index()
        {
            return View();
        }

        

    }
}