using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;

namespace LexusTech.Application.Services
{
    public class EnderecoService : IEnderecoService
    {
        private readonly IEnderecoRepository _contextoRepository;

        public EnderecoService(IEnderecoRepository contextoRepository)
        {
            _contextoRepository = contextoRepository;
        }

        public async Task<Endereco> Criar(Endereco contexto)
        {
            return await _contextoRepository.Criar(contexto);
        }

        public async Task<List<Endereco>> ConsultarTodos()
        {
            var dados = await _contextoRepository.ConsultarTodos();
            return dados.ToList(); 
        }

        public async Task<Endereco> ConsultarId(int id)
        {
            return await _contextoRepository.ConsultarId(id);
        }


        public async Task<Endereco> Atualizar(Endereco contexto)
        {
            return await _contextoRepository.Atualizar(contexto);
        }

        public async Task Excluir(int id)
        {
            await _contextoRepository.Excluir(id);
        }
    }
}
