using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;

namespace LexusTech.Application.Services
{
    public class ComodoService : IComodoService
    {
        private readonly IComodoRepository _contextoRepository;

        public ComodoService(IComodoRepository contextoRepository)
        {
            _contextoRepository = contextoRepository;
        }

        public async Task<Comodo> Criar(Comodo contexto)
        {
            return await _contextoRepository.Criar(contexto);
        }

        public async Task<List<Comodo>> ConsultarTodos()
        {
            var dados = await _contextoRepository.ConsultarTodos();
            return dados.ToList(); 
        }

        public async Task<Comodo> ConsultarId(int id)
        {
            return await _contextoRepository.ConsultarId(id);
        }


        public async Task<Comodo> Atualizar(Comodo contexto)
        {
            return await _contextoRepository.Atualizar(contexto);
        }

        public async Task Excluir(int id)
        {
            await _contextoRepository.Excluir(id);
        }
    }
}
