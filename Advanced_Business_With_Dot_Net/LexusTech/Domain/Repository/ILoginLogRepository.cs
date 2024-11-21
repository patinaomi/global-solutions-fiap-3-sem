using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface ILoginLogRepository
    {
        Task<LoginLog> Criar(LoginLog loginLog);
        Task<LoginLog> ConsultarId(int id);
        Task<List<LoginLog>> ConsultarTodos();
 

    }
}
