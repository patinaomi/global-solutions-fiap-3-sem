using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;

namespace LexusTech.Application.Services
{
    public class ImagemService : IImagemService
    {
        private readonly IImagemRepository _contextoRepository;

        public ImagemService(IImagemRepository contextoRepository)
        {
            _contextoRepository = contextoRepository;
        }

        public async Task<Imagem> Criar(Imagem contexto)
        {
            return await _contextoRepository.Criar(contexto);
        }

        public async Task<List<Imagem>> ConsultarTodos()
        {
            var dados = await _contextoRepository.ConsultarTodos();
            return dados.ToList(); 
        }

        public async Task<Imagem> ConsultarId(int id)
        {
            return await _contextoRepository.ConsultarId(id);
        }


        public async Task<Imagem> Atualizar(Imagem contexto)
        {
            return await _contextoRepository.Atualizar(contexto);
        }

        public async Task Excluir(int id)
        {
            await _contextoRepository.Excluir(id);
        }
    }
}
