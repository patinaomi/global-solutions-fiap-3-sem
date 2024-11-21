using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Repositories
{
    public class ImagemRepository : IImagemRepository
    {
        private readonly ApplicationDbContext _context;

        public ImagemRepository(ApplicationDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<Imagem> Criar(Imagem contexto)
        {
            await _context.T_Imagem .AddAsync(contexto);
            await _context.SaveChangesAsync();

            return contexto;
        }

        public async Task<Imagem> ConsultarId(int id)
        {
            var dado = await _context.T_Imagem.FindAsync(id);

            if (dado == null)
            {
            
                return null;
            }

            return dado;

        }

        public async Task<List<Imagem>> ConsultarTodos()
        {
            return await _context.T_Imagem.ToListAsync();
        }

        public async Task<Imagem> Atualizar(Imagem contexto)
        {
            var dadoExistente = await _context.T_Imagem.FindAsync(contexto.Id);
            if (dadoExistente != null)
            {
                dadoExistente.Id = contexto.Id;
                dadoExistente.NomeImagem = contexto.NomeImagem;
                dadoExistente.LinkImagem = contexto.LinkImagem;
           
                _context.T_Imagem.Update(dadoExistente);
                await _context.SaveChangesAsync();

                return dadoExistente;
            }

            return null;
        }

        public async Task Excluir(int id)
        {
            var dado = await _context.T_Imagem.FindAsync(id);
            if (dado != null)
            {
                _context.T_Imagem.Remove(dado);
                await _context.SaveChangesAsync();
            }
        }
    }
}
