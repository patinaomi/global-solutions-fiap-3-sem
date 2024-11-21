using Microsoft.AspNetCore.Mvc;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Authorization;
using LexusTech.Infrastructure.Interfaces;
using System.Security.Claims;

namespace LexusTech.Controllers
{
    public class ComodoController : Controller
    {
        //private readonly ApplicationDbContext _context;
        private readonly IComodoService _contextoService;

        public ComodoController(IComodoService contextService)
        {
            _contextoService = contextService;
        }

        [HttpGet]
        [Authorize]
        public IActionResult Criar()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Criar(Comodo contexto)
        {
            if (ModelState.IsValid)
            {
                if (contexto.Descricao != null && contexto.Descricao.Any())
                {
                    contexto.Descricao = string.Join(",", contexto.Descricao);
                }

                await _contextoService.Criar(contexto);
                TempData["SuccessMessage"] = "Ambientes cadastrada com sucesso, clique em continuar!";
            }
            return View(contexto);
        }

        [HttpGet("Comodo/Consultar", Name = "ConsultarComodo")]
        public async Task<IActionResult> Consultar()
        {
            var dados = await _contextoService.ConsultarTodos();
            return View(dados); 
        }

        [HttpGet("Comodo/Atualizar")]
        public async Task<IActionResult> Atualizar()
        {
            var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

            if (string.IsNullOrEmpty(userIdString) || !int.TryParse(userIdString, out var userId))
            {
                return RedirectToAction("Error");
            }

            var dados = await _contextoService.ConsultarId(userId);
            if (dados == null)
            {
                return NotFound();
            }

            return View(dados);
        }

        [HttpPost("Comodo/Atualizar", Name = "ComodoAtualizar")]
        public async Task<IActionResult> Atualizar(Comodo contexto)
        {
            if (!ModelState.IsValid)
            {
                return View(contexto);
            }

            var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

            if (string.IsNullOrEmpty(userIdString) || !int.TryParse(userIdString, out var userId))
            {
                return RedirectToAction("Error");
            }

            var dadosExistente = await _contextoService.ConsultarId(userId);
            if (dadosExistente == null)
            {
                return NotFound();
            }

            dadosExistente.Id = contexto.Id;
            //dadosExistente.IdUsuario = contexto.IdUsuario;
            dadosExistente.Descricao = contexto.Descricao;
    
            await _contextoService.Atualizar(dadosExistente);

            TempData["SuccessMessage"] = "Item atualizado com sucesso!";
            return RedirectToAction("MensagemAtualizacao");
        }

        [HttpGet("MensagemAtualizacao")]
        public IActionResult MensagemAtualizacao()
        {
            return View();
        }


    }
}