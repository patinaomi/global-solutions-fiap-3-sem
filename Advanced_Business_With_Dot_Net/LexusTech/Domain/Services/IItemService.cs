
using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IItemService
    {
        Task<ItemDTO> Criar(ItemDTO contexto);
        Task<ItemDTO> ConsultarId(int id);
        Task<List<ItemDTO>> ConsultarTodos();
        Task<ItemDTO> Atualizar(ItemDTO contexto);
        Task Excluir(int id);
    }
}
