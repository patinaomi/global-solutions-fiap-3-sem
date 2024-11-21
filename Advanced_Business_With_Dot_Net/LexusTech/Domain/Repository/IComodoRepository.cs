using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IComodoRepository
    {
        Task<Comodo> Criar(Comodo comodo);
        Task<Comodo> ConsultarId(int id);
        Task<List<Comodo>> ConsultarTodos();
        Task<Comodo> Atualizar(Comodo comodo);
        Task Excluir(int id);

    }
}
