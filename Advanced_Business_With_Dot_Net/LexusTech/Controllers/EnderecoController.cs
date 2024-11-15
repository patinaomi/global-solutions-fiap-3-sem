using Microsoft.AspNetCore.Mvc;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;
using System.Security.Claims;

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

        [HttpGet("Endereco/Atualizar")]
        public async Task<IActionResult> Atualizar()
        {
            var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

            if (string.IsNullOrEmpty(userIdString) || !int.TryParse(userIdString, out var userId))
            {
                return RedirectToAction("Error");
            }

            var usuario = await _context.T_Endereco.FindAsync(userId);
            if (usuario == null)
            {
                return NotFound();
            }

            return View(usuario);
        }

        [HttpPost("Endereco/Atualizar", Name = "EnderecoAtualizar")]
        public async Task<IActionResult> Atualizar(Endereco endereco)
        {
            if (!ModelState.IsValid)
            {
                return View(endereco);
            }

            var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

            if (string.IsNullOrEmpty(userIdString) || !int.TryParse(userIdString, out var userId))
            {
                return RedirectToAction("Error");
            }

            var enderecoExistente = await _context.T_Endereco.FindAsync(userId);
            if (enderecoExistente == null)
            {
                return NotFound();
            }

            enderecoExistente.CEP = endereco.CEP;
            enderecoExistente.Estado = endereco.Estado;
            enderecoExistente.Cidade = endereco.Cidade;
            enderecoExistente.Bairro = endereco.Bairro;
            enderecoExistente.Rua = endereco.Rua;
            enderecoExistente.Complemento = endereco.Complemento;

            await _context.SaveChangesAsync();

            TempData["SuccessMessage"] = "Usuário atualizado com sucesso!";
            return RedirectToAction("MensagemAtualizacao");
        }

        [HttpGet("MensagemAtualizacao")]
        public IActionResult MensagemAtualizacao()
        {
            return View();
        }


    }
}