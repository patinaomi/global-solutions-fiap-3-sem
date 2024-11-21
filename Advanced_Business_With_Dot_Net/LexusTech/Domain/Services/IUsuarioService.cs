using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IUsuarioService
    {
        Task<Usuario> Criar(Usuario usuario);
        Task<Usuario> ConsultarId(int id);
        Task<List<Usuario>> ConsultarTodos();
        Task<Usuario> Atualizar(Usuario usuario);
        Task Excluir(int id);
    }
}
