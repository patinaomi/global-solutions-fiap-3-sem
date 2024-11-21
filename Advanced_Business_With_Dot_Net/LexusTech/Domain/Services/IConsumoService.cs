using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IConsumoService
    {
        Task<Consumo> Criar(Consumo contexto);
        Task<Consumo> ConsultarId(int id);
        Task<List<Consumo>> ConsultarTodos();
        Task<Consumo> Atualizar(Consumo contexto);
        Task Excluir(int id);

    }
}
