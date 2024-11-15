using Microsoft.AspNetCore.Mvc;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Controllers
{
    public class ItemController : Controller
    {
        private readonly ApplicationDbContext _context;

        public ItemController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult Criar()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Criar(Item item)
        {
            if (ModelState.IsValid)
            {
                if (item.DescricaoItem != null && item.DescricaoItem.Any())
                {
                    item.DescricaoItem = string.Join(",", item.DescricaoItem);
                }

                _context.Add(item);
                await _context.SaveChangesAsync();

                TempData["SuccessMessage"] = "Itens cadastrados com sucesso, clique em continuar!";
            }
            return View(item);
        }

        [HttpGet("Item/Consultar", Name = "ConsultarItem")]
        public async Task<IActionResult> Consultar()
        {
            var dados = await _context.T_Item_Casa.ToListAsync(); 
            return View(dados); 
        }


    }
}