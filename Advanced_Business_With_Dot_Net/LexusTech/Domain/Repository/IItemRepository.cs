using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IItemRepository
    {
        Task<Item> Criar(Item contexto);
        Task<Item> ConsultarId(int id);
        Task<List<Item>> ConsultarTodos();
        Task<Item> Atualizar(Item contexto);
        Task Excluir(int id);

    }
}
