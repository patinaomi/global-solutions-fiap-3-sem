using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Security.Claims;

[Route("Item")] 
public class ItemController : Controller
{
    private readonly IItemService _itemService;

    public ItemController(IItemService itemService)
    {
        _itemService = itemService;
    }

    // usar essaa tag para permitir que todos possam fazer cadastrado, mas quem não estiver logado, não vai conseguir acessar nada.
    [AllowAnonymous]
    [HttpGet("Criar")]
    public IActionResult Criar()
    {
        return View();
    }

    [AllowAnonymous]
    [HttpPost("Criar")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Criar(Item contexto)
    {
        if (ModelState.IsValid)
        
        {   
            var itemDto = new ItemDTO
            {
                IdUsuario = contexto.IdUsuario,
                DescricaoItem = contexto.DescricaoItem,
               
            };

            await _itemService.Criar(itemDto);
            TempData["SuccessMessage"] = "Item cadastrado com sucesso!";
        }
        return View(contexto);
    }

    [HttpGet("Mensagem")]
    public IActionResult Mensagem()
    {
        return View();
    }

    [HttpGet("Consultar")]
    public async Task<IActionResult> Consultar()
    {
        var contextos = await _itemService.ConsultarTodos(); 
        return View(contextos); 
    }

    [HttpGet("AtualizarDados")]
    public async Task<IActionResult> AtualizarDados()
    {
        var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

        if (string.IsNullOrEmpty(userIdString) || !int.TryParse(userIdString, out var userId))
        {
            return RedirectToAction("Error");
        }

        var contexto = await _itemService.ConsultarId(userId);
        if (contexto == null)
        {
            return NotFound();
        }

        return View(contexto);
    }

    [HttpPost("AtualizarDados")]
    public async Task<IActionResult> Atualizar(ItemDTO itemDTO)
    {
        if (!ModelState.IsValid)
        {
            return View(itemDTO); 
        }

        var itemAtualizado = await _itemService.Atualizar(itemDTO);

        if (itemAtualizado == null)
        {
           
            return NotFound(); 
        }

        return RedirectToAction("Consultar"); 
    }


    [HttpGet("MensagemAtualizacao")]
    public IActionResult MensagemAtualizacao()
    {
        return View();
    }

    [HttpGet("ConfirmarExcluir/{id}")]
    public async Task<IActionResult> ConfirmarExcluir(int id)
    {
        var contexto = await _itemService.ConsultarId(id);
        
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
        var contexto = await _itemService.ConsultarId(id);
        
        if (contexto != null)
        {
            // Exclui o usuário do banco de dados
            await _itemService.Excluir(id);
            
            TempData["SuccessMessage"] = "Item excluído com sucesso.";
            return RedirectToAction("MensagemExclusao", "Item"); 
        }

        TempData["ErrorMessage"] = "Item não encontrado.";
        return RedirectToAction(nameof(Index));
    }

    [HttpGet("MensagemExclusao")]
    public IActionResult MensagemExclusao()
    {
        return View();
    }



}