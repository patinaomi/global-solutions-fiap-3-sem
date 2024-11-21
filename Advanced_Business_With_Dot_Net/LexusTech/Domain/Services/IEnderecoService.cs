using LexusTech.Models;

namespace LexusTech.Infrastructure.Interfaces
{
    public interface IEnderecoService
    {
        Task<Endereco> Criar(Endereco endereco);
        Task<Endereco> ConsultarId(int id);
        Task<List<Endereco>> ConsultarTodos();
        Task<Endereco> Atualizar(Endereco endereco);
        Task Excluir(int id);
    }
}
