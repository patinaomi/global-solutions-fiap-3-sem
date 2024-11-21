using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;

namespace LexusTech.Application.Services
{
    public class ConsumoService : IConsumoService
    {
        private readonly IConsumoRepository _contextoRepository;

        public ConsumoService(IConsumoRepository contextoRepository)
        {
            _contextoRepository = contextoRepository;
        }

        public async Task<Consumo> Criar(Consumo contexto)
        {
            return await _contextoRepository.Criar(contexto);
        }

        public async Task<List<Consumo>> ConsultarTodos()
        {
            var dados = await _contextoRepository.ConsultarTodos();
            return dados.ToList(); 
        }

        public async Task<Consumo> ConsultarId(int id)
        {
            return await _contextoRepository.ConsultarId(id);
        }


        public async Task<Consumo> Atualizar(Consumo contexto)
        {
            return await _contextoRepository.Atualizar(contexto);
        }

        public async Task Excluir(int id)
        {
            await _contextoRepository.Excluir(id);
        }

    }
}
