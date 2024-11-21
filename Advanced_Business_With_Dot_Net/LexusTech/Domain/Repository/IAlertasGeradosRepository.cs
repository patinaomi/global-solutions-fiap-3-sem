using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IAlertasGeradosRepository
    {
        Task<List<AlertasGerados>> ConsultarTodos();
    }
}
