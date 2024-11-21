
using Microsoft.AspNetCore.Mvc;
using LexusTech.Infrastructure.Interfaces;

namespace LexusTech.Controllers
{
    public class LoginLogController : Controller
    {
        private readonly ILoginLogRepository _loginLogRepository;

        public LoginLogController(ILoginLogRepository loginLogRepository)
        {
            _loginLogRepository = loginLogRepository;
        }

        [HttpGet]
        public async Task<IActionResult> Index()
        {
            var logs = await _loginLogRepository.ConsultarTodos();

            return View(logs);
        }
    }
}
