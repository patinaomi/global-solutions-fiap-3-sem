using Microsoft.AspNetCore.Mvc;
using LexusTech.Infrastructure.Interfaces;

namespace LexusTech.Controllers
{
    public class AlertasGeradosController : Controller
    {
        private readonly IAlertasGeradosService _contextoService;

        public AlertasGeradosController(IAlertasGeradosService contextoService)
        {
            _contextoService = contextoService;
        }

        
        [HttpGet("AlertasGerados/Consultar", Name = "ConsultarAlertasGerados")]
        public async Task<IActionResult> Consultar()
        {
            var dados = await _contextoService.ConsultarTodos();
            return View(dados); 
        }
    }
}
