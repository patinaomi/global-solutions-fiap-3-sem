using Microsoft.AspNetCore.Mvc;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;
using System.Security.Claims;
using Microsoft.AspNetCore.Authorization;

namespace LexusTech.Controllers
{
    public class ConsumoController : Controller
    {
        private readonly ApplicationDbContext _context;

        public ConsumoController(ApplicationDbContext context)
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
        public async Task<IActionResult> Criar(Consumo consumo)
        {
            if (ModelState.IsValid)
            {
                _context.Add(consumo);
                await _context.SaveChangesAsync();

                TempData["SuccessMessage"] = "Consumo cadastrado com sucesso, clique em consultar ou Dashboard!";
            
            }
            return View(consumo);
        }

        [HttpGet("Consumo/Consultar", Name = "ConsultarConsumo")]
        public async Task<IActionResult> Consultar()
        {
            var dados = await _context.T_Consumo.ToListAsync(); 
            return View(dados); 
        }

        [HttpGet("Consumo/Atualizar")]
        public async Task<IActionResult> Atualizar()
        {
            var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

            if (string.IsNullOrEmpty(userIdString) || !int.TryParse(userIdString, out var userId))
            {
                return RedirectToAction("Error");
            }

            var consumo = await _context.T_Consumo.FindAsync(userId);
            if (consumo == null)
            {
                return NotFound();
            }

            return View(consumo);
        }

        [HttpPost("Consumo/Atualizar", Name = "ConsumoAtualizar")]
        public async Task<IActionResult> Atualizar(Consumo consumo)
        {
            if (!ModelState.IsValid)
            {
                return View(consumo);
            }

            var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

            if (string.IsNullOrEmpty(userIdString) || !int.TryParse(userIdString, out var userId))
            {
                return RedirectToAction("Error");
            }

            var consumoExistente = await _context.T_Consumo.FindAsync(userId);
            if (consumoExistente == null)
            {
                return NotFound();
            }

            consumoExistente.IdUsuario = consumo.IdUsuario;
            consumoExistente.IdComodo = consumo.IdComodo;
            consumoExistente.IdItemCasa = consumo.IdItemCasa;
            consumoExistente.DataConsumo = consumo.DataConsumo;
            consumoExistente.ConsumoDiario = consumo.ConsumoDiario;
            consumoExistente.Valor = consumo.Valor;

            await _context.SaveChangesAsync();

            TempData["SuccessMessage"] = "Consumo atualizado com sucesso!";
            return RedirectToAction("MensagemAtualizacao");
        }

        [HttpGet("MensagemAtualizacao")]
        public IActionResult MensagemAtualizacao()
        {
            return View();
        }


    }
}