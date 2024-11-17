using LexusTech.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Controllers
{
    public class LoginLogController : Controller
    {
        private readonly ApplicationDbContext _context;

        public LoginLogController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<IActionResult> Index()
        {
            var logs = await _context.T_Login
                .ToListAsync(); 

            return View(logs); 
        }
    }
}
