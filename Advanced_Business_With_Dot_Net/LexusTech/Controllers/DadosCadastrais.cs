using LexusTech.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Security.Claims;

[Route("DadosCadastrais")]
public class DadosCadastraisController : Controller
{
    private readonly ApplicationDbContext _context;

    public DadosCadastraisController(ApplicationDbContext context)
    {
        _context = context;
    }

    [HttpGet("Consultar")]
    public async Task<IActionResult> Consultar()
    {
        var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

        // Verifica se userIdString é um inteiro válido
        if (int.TryParse(userIdString, out var userId))
        {
            var usuario = await _context.T_Usuario.FirstOrDefaultAsync(c => c.Id == userId);
            if (usuario == null)
            {
                return RedirectToAction("Error");
            }

            var endereco = await _context.T_Endereco.FirstOrDefaultAsync(e => e.IdUsuario == userId) ?? new Endereco();
            var comodo = await _context.T_Comodo.FirstOrDefaultAsync(d => d.IdUsuario == userId) ?? new Comodo();
            var item = await _context.T_Item_Casa.FirstOrDefaultAsync(t => t.IdUsuario == userId) ?? new Item();
            var imagem = await _context.T_Imagem.FirstOrDefaultAsync(t => t.IdUsuario == userId) ?? new Imagem();
            var consumo = await _context.T_Consumo.FirstOrDefaultAsync(t => t.IdUsuario == userId) ?? new Consumo();
            var alertaConsumo = await _context.T_Alerta_Consumo.FirstOrDefaultAsync(t => t.IdUsuario == userId) ?? new AlertaConsumo();

            // Cria a instância de DadosCadastrais
            var dadosCadastrais = new DadosCadastrais
            {
                Usuario = usuario,
                Endereco = endereco,
                Comodo = comodo,
                Item = item,
                Imagem = imagem,
                Consumo = consumo,
                AlertaConsumo = alertaConsumo
            };

            return View(dadosCadastrais);
        }

        return RedirectToAction("Error"); 
    }
}