using Microsoft.AspNetCore.Mvc;
using LexusTech.Models;
using System.Security.Claims;
using Microsoft.AspNetCore.Authorization;
using LexusTech.Infrastructure.Interfaces;

namespace LexusTech.Controllers
{
    public class EnderecoController : Controller
    {
        private readonly IEnderecoService _contextoService;

        public EnderecoController(IEnderecoService contextoService)
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
        public async Task<IActionResult> Criar(Endereco endereco)
        {
            if (ModelState.IsValid)
            {
                await _contextoService.Criar(endereco);
                TempData["SuccessMessage"] = "Preferência cadastrada com sucesso, clique em continuar!";
                
            }
            return View(endereco);
        }

        [HttpGet("Endereco/Consultar", Name = "ConsultarEndereco")]
        public async Task<IActionResult> Consultar()
        {
            var dados = await _contextoService.ConsultarTodos();
            return View(dados);
        }

        [HttpGet("Endereco/Atualizar")]
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

        [HttpPost("Endereco/Atualizar", Name = "EnderecoAtualizar")]
        public async Task<IActionResult> Atualizar(Endereco contexto)
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

            dadosExistente.CEP = contexto.CEP;
            dadosExistente.Estado = contexto.Estado;
            dadosExistente.Cidade = contexto.Cidade;
            dadosExistente.Bairro = contexto.Bairro;
            dadosExistente.Rua = contexto.Rua;
            dadosExistente.Complemento = contexto.Complemento;
            
            await _contextoService.Atualizar(dadosExistente);

            TempData["SuccessMessage"] = "Item atualizado com sucesso!";
            return RedirectToAction("MensagemAtualizacao");
        }

        [HttpGet("Endereco/MensagemAtualizacao")]
        public IActionResult MensagemAtualizacao()
        {
            return View();
        }

        [HttpGet("ConfirmarExcluir/{id}")]
        public async Task<IActionResult> ConfirmarExcluir(int id)
        {
            var contexto = await _contextoService.ConsultarId(id);
            
            if (contexto == null)
            {
                return NotFound();
            }

            return View(contexto);
        }


        [HttpPost("Excluir")]
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

        [HttpGet("MensagemExclusao")]
        public IActionResult MensagemExclusao()
        {
            return View();
        }



    }
}