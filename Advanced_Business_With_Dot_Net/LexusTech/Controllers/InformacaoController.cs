using Microsoft.AspNetCore.Mvc;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

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
        public IActionResult Index()
        {
            return View();
        }

        

    }
}