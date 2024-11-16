using Microsoft.AspNetCore.Mvc;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Authorization;

namespace LexusTech.Controllers
{
    public class ComodoController : Controller
    {
        private readonly ApplicationDbContext _context;

        public ComodoController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        [Authorize]
        public IActionResult Criar()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Criar(Comodo comodo)
        {
            if (ModelState.IsValid)
            {
                if (comodo.Descricao != null && comodo.Descricao.Any())
                {
                    comodo.Descricao = string.Join(",", comodo.Descricao);
                }

                _context.Add(comodo);
                await _context.SaveChangesAsync();

                TempData["SuccessMessage"] = "Ambientes cadastrada com sucesso, clique em continuar!";
            }
            return View(comodo);
        }

        [HttpGet("Comodo/Consultar", Name = "ConsultarComodo")]
        public async Task<IActionResult> Consultar()
        {
            var dados = await _context.T_Comodo.ToListAsync(); 
            return View(dados); 
        }


    }
}