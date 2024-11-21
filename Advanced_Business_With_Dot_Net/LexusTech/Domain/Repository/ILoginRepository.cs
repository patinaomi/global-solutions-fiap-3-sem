
using LexusTech.Models;

namespace LexusTech.Repositories
{
    public interface ILoginRepository
    {
        Task<Login> Criar(Login login);
        Task<List<Login>> ConsultarTodos();
        Task<Login> ConsultarId(int id);
        Task<Usuario> Autenticar(string email, string senha);

    }
}
