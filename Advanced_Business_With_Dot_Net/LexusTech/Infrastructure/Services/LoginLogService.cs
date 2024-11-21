using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using LexusTech.Repositories;
using System.Security.Authentication;

namespace LexusTech.Application.Services
{
    public class LoginLogService : ILoginLogService
    {
        private readonly ILoginLogRepository _loginLogRepository;

        public LoginLogService(ILoginLogRepository loginLogRepository)
        {
            _loginLogRepository = loginLogRepository;
        }

        public async Task<LoginLog> Criar(LoginLog loginLog)
        {
            await _loginLogRepository.Criar(loginLog);

            return loginLog;
        }

        public async Task<List<LoginLog>> ConsultarTodos()
        {
            return await _loginLogRepository.ConsultarTodos();
        }

        public async Task<LoginLog> ConsultarId(int id)
        {
            return await _loginLogRepository.ConsultarId(id);
        }
    
    }
}
