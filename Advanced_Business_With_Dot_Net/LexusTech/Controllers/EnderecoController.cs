using Microsoft.AspNetCore.Mvc;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Controllers
{
    public class EnderecoController : Controller
    {
        private readonly ApplicationDbContext _context;

        public EnderecoController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult Criar()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Criar(Endereco endereco)
        {
            if (ModelState.IsValid)
            {
                _context.Add(endereco);
                await _context.SaveChangesAsync();

                TempData["SuccessMessage"] = "Preferência cadastrada com sucesso, clique em continuar!";
                // return RedirectToAction("Mensagem");
            }
            return View(endereco);
        }

        [HttpGet("Endereco/Consultar", Name = "ConsultarEndereco")]
        public async Task<IActionResult> Consultar()
        {
            var dados = await _context.T_Endereco.ToListAsync(); 
            return View(dados); 
        }


    }
}