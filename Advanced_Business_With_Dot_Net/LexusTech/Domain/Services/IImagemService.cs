using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IImagemService
    {
        Task<Imagem> Criar(Imagem contexto);
        Task<Imagem> ConsultarId(int id);
        Task<List<Imagem>> ConsultarTodos();
        Task<Imagem> Atualizar(Imagem contexto);
        Task Excluir(int id);
    }
}
