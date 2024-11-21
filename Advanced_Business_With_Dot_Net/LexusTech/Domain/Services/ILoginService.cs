using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface ILoginService
    {
        Task<Login> Criar(Login login);
        Task<Login> ConsultarId(int id);
        Task<List<Login>> ConsultarTodos();
        Task<Usuario> Autenticar(string email, string senha);

    }
}
