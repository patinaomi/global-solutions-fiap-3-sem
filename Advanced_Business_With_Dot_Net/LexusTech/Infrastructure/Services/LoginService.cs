using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using LexusTech.Repositories;
using System.Security.Authentication;

namespace LexusTech.Application.Services
{
    public class LoginService : ILoginService
    {
        private readonly ILoginRepository _loginRepository;
        private readonly ILoginLogRepository _loginLogRepository;

        public LoginService(ILoginRepository loginRepository, ILoginLogRepository loginLogRepository)
        {
            _loginRepository = loginRepository;
            _loginLogRepository = loginLogRepository;
        }

        public async Task<Login> Criar(Login login)
        {
            return await _loginRepository.Criar(login);
        }

        public async Task<Login> ConsultarId(int id)
        {
            return await _loginRepository.ConsultarId(id);
        }

        public async Task<List<Login>> ConsultarTodos()
        {
            return await _loginRepository.ConsultarTodos();
        }
        
        public async Task<Usuario> Autenticar(string email, string senha)
        {
            var login = await _loginRepository.Autenticar(email, senha);
            
            if (login == null)
            {
                throw new AuthenticationException("Usuário ou senha inválidos.");
            }

            var loginLog = new LoginLog
            {
                IdUsuario = login.Id,
                Email = login.Email,
                DataHora = DateTime.Now
            };

            await _loginLogRepository.Criar(loginLog);

            return login;
        }
    }
}
