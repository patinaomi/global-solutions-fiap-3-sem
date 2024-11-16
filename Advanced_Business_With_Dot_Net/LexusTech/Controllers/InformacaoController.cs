using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace LexusTech.Controllers
{
    public class InformacaoController : Controller
    {
        private readonly ApplicationDbContext _context;

        public InformacaoController(ApplicationDbContext context)
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