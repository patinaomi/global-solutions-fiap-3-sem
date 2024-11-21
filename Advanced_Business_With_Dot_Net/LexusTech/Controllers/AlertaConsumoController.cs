using Microsoft.AspNetCore.Mvc;
using LexusTech.DTOs;
using Microsoft.AspNetCore.Authorization;
using LexusTech.Infrastructure.Interfaces;
using System.Security.Claims;
using LexusTech.Models;

namespace LexusTech.Controllers
{
    public class AlertaConsumoController : Controller
    {
        private readonly IAlertaConsumoService _contextoService;

        public AlertaConsumoController(IAlertaConsumoService contextoService)
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
        public async Task<IActionResult> Criar(AlertaConsumoDTO contexto)
        {
            if (ModelState.IsValid)
            {
                if (!string.IsNullOrEmpty(contexto.Descricao))
                {
                    contexto.Descricao = string.Join(",", contexto.Descricao.Split(',').Select(d => d.Trim()));
                }

                await _contextoService.Criar(contexto);
                TempData["SuccessMessage"] = "Ambiente cadastrado com sucesso! Clique em continuar.";
                return RedirectToAction("Consultar");
            }
            return View(contexto);
        }

        [HttpGet("AlertaConsumo/Consultar", Name = "ConsultarAlertaConsumo")]
        public async Task<IActionResult> Consultar()
        {
            var dados = await _contextoService.ConsultarTodos();
            return View(dados); 
        }

        [HttpGet("AlertaConsumo/Atualizar")]
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


        [HttpPost("AlertaConsumo/Atualizar", Name = "AlertaConsumoAtualizar")]
        public async Task<IActionResult> Atualizar(AlertaConsumo contexto)
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

            dadosExistente.Ambiente = contexto.Ambiente;
            dadosExistente.ConsumoMinimo = contexto.ConsumoMinimo;
            dadosExistente.EmailDestino = contexto.EmailDestino;
            dadosExistente.Ativo = contexto.Ativo;
            dadosExistente.Descricao = contexto.Descricao;
            dadosExistente.TipoAlerta = contexto.TipoAlerta;
            
            await _contextoService.Atualizar(dadosExistente);

            TempData["SuccessMessage"] = "Item atualizado com sucesso!";
            return RedirectToAction("MensagemAtualizacao");
        }

        [HttpGet("AlertaConsumo/MensagemAtualizacao")]
        public IActionResult MensagemAtualizacao()
        {
            return View();
        }

        [HttpGet("AlertaConsumo/ConfirmarExcluir/{id}")]
        public async Task<IActionResult> ConfirmarExcluir(int id)
        {
            var contexto = await _contextoService.ConsultarId(id);
            
            if (contexto == null)
            {
                return NotFound();
            }

            return View(contexto);
        }


        [HttpPost("AlertaConsumo/Excluir")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Excluir(int id)
        {
            var contexto = await _contextoService.ConsultarId(id);
            
            if (contexto != null)
            {
                await _contextoService.Excluir(id);
                
                // Redireciona para a página de login ou para onde você preferir
                TempData["SuccessMessage"] = "Dado excluído com sucesso.";
                return RedirectToAction("MensagemExclusao", "Endereco"); 
            }

            TempData["ErrorMessage"] = "Dado não encontrado.";
            return RedirectToAction(nameof(Index));
        }

        [HttpGet("AlertaConsumo/MensagemExclusao")]
        public IActionResult MensagemExclusao()
        {
            return View();
        }
    }
}
