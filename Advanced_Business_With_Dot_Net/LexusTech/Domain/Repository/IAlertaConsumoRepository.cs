using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IAlertaConsumoRepository
    {
        Task<AlertaConsumo> Criar(AlertaConsumo contexto);
        Task<AlertaConsumo> ConsultarId(int id);
        Task<List<AlertaConsumo>> ConsultarTodos();
        Task<AlertaConsumo> Atualizar(AlertaConsumo contexto);
        Task Excluir(int id);

    }
}
