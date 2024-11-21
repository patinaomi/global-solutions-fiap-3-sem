using Microsoft.AspNetCore.Mvc;
using LexusTech.Models;
using System.Security.Claims;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc.Rendering;
using LexusTech.Infrastructure.Interfaces;

namespace LexusTech.Controllers
{
    public class ConsumoController : Controller
    {
        private readonly IConsumoService _contextoService;

        public ConsumoController(IConsumoService contextoService)
        {
            _contextoService = contextoService;
        }

        [HttpGet]
        [Authorize]
        public IActionResult Criar()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Criar(Consumo contexto)
        {
            if (ModelState.IsValid)
            {
                await _contextoService.Criar(contexto);
                TempData["SuccessMessage"] = "Consumo cadastrado com sucesso, clique em consultar ou Dashboard!";
                return RedirectToAction("Consultar");
            }
            return View(contexto);
        }

        [HttpGet("Consumo/Consultar", Name = "ConsultarConsumo")]
        public async Task<IActionResult> Consultar()
        {
            var dados = await _contextoService.ConsultarTodos();
            return View(dados);
        }

        [HttpGet("Consumo/Atualizar")]
        public async Task<IActionResult> Atualizar(int id)
        {
            var consumo = await _contextoService.ConsultarId(id);
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

            await _contextoService.Atualizar(consumo);

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
