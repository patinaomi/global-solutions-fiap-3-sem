using LexusTech.DTOs;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IAlertaConsumoService
    {
        Task<AlertaConsumoDTO> Criar(AlertaConsumoDTO contexto);
        Task<AlertaConsumoDTO> ConsultarId(int id);
        Task<List<AlertaConsumoDTO>> ConsultarTodos();
        Task<AlertaConsumoDTO> Atualizar(AlertaConsumoDTO contexto);
        Task Excluir(int id);
    }
}
