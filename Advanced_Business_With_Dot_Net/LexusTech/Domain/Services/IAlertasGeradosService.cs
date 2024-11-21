using LexusTech.DTOs;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IAlertasGeradosService
    {
        Task<List<AlertasGeradosDTO>> ConsultarTodos();

    }
}
