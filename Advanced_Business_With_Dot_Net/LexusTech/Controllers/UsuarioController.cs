using LexusTech.Models;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Security.Claims;
using System.Threading.Tasks;

[Route("Usuario")] 
public class UsuarioController : Controller
{
    private readonly ApplicationDbContext _context;

    public UsuarioController(ApplicationDbContext context)
    {
        _context = context;
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
    public async Task<IActionResult> Criar([Bind("Id,Nome,Sobrenome,Telefone,Email,Senha")] Usuario usuario)
    {
        if (ModelState.IsValid)
        {
            _context.Add(usuario);
            await _context.SaveChangesAsync();

            TempData["SuccessMessage"] = "Usuário cadastrado com sucesso!";
            return RedirectToAction("Mensagem");
        }
        return View(usuario);
    }

    [HttpGet("Mensagem")]
    public IActionResult Mensagem()
    {
        return View();
    }

    [HttpGet("Consultar")]
    public async Task<IActionResult> Consultar()
    {
        var usuarios = await _context.T_Usuario.ToListAsync(); 
        return View(usuarios); 
    }

    [HttpGet("Atualizar")]
    public async Task<IActionResult> Atualizar()
    {
        var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

        if (string.IsNullOrEmpty(userIdString) || !int.TryParse(userIdString, out var userId))
        {
            return RedirectToAction("Error");
        }

        var usuario = await _context.T_Usuario.FindAsync(userId);
        if (usuario == null)
        {
            return NotFound();
        }

        return View(usuario);
    }

    [HttpPost("Atualizar")]
    public async Task<IActionResult> Atualizar(Usuario usuario)
    {
        if (!ModelState.IsValid)
        {
            return View(usuario);
        }

        var userIdString = User.FindFirst(ClaimTypes.NameIdentifier)?.Value;

        if (string.IsNullOrEmpty(userIdString) || !int.TryParse(userIdString, out var userId))
        {
            return RedirectToAction("Error");
        }

        var usuarioExistente = await _context.T_Usuario.FindAsync(userId);
        if (usuarioExistente == null)
        {
            return NotFound();
        }

        usuarioExistente.Nome = usuario.Nome;
        usuarioExistente.Sobrenome = usuario.Sobrenome;
        usuarioExistente.Telefone = usuario.Telefone;
        usuarioExistente.Email = usuario.Email;
        usuarioExistente.Senha = usuario.Senha;

        await _context.SaveChangesAsync();

        TempData["SuccessMessage"] = "Usuário atualizado com sucesso!";
        return RedirectToAction("MensagemAtualizacao");
    }

    [HttpGet("MensagemAtualizacao")]
    public IActionResult MensagemAtualizacao()
    {
        return View();
    }

    [HttpGet("ConfirmarExcluir/{id}")]
    public async Task<IActionResult> ConfirmarExcluir(int id)
    {
        var usuario = await _context.T_Usuario.FindAsync(id);
        if (usuario == null)
        {
            return NotFound();
        }

        return View(usuario);
    }


    [HttpPost("Excluir")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Excluir(int id)
    {
        var usuario = await _context.T_Usuario.FindAsync(id);
        if (usuario != null)
        {
            // Exclui o usuário do banco de dados
            _context.T_Usuario.Remove(usuario);
            await _context.SaveChangesAsync();

            // Desloga o usuário
            await _context.SaveChangesAsync();
            await HttpContext.SignOutAsync();
            
            // Redireciona para a página de login ou para onde você preferir
            TempData["SuccessMessage"] = "Usuário excluído com sucesso.";
            return RedirectToAction("Home", "Index"); 
        }

        TempData["ErrorMessage"] = "Usuário não encontrado.";
        return RedirectToAction(nameof(Index)); // Ou para outra página desejada
    }



}