using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface ILoginLogService
    {
        Task<LoginLog> Criar(LoginLog loginLog);
        Task<List<LoginLog>> ConsultarTodos();
        Task<LoginLog> ConsultarId(int id);

    }
}
