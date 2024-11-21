using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Repositories
{
    public class ComodoRepository : IComodoRepository
    {
        private readonly ApplicationDbContext _context;

        public ComodoRepository(ApplicationDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<Comodo> Criar(Comodo contexto)
        {
            await _context.T_Comodo.AddAsync(contexto);
            await _context.SaveChangesAsync();

            return contexto;
        }

        public async Task<Comodo> ConsultarId(int id)
        {
            var dado = await _context.T_Comodo.FindAsync(id);

            if (dado == null)
            {
            
                return null;
            }

            return dado;

        }

        public async Task<List<Comodo>> ConsultarTodos()
        {
            return await _context.T_Comodo.ToListAsync();
        }

        public async Task<Comodo> Atualizar(Comodo dado)
        {
            var dadoExistente = await _context.T_Comodo.FindAsync(dado.Id);
            if (dadoExistente != null)
            {
                dadoExistente.Id = dado.Id;
                dadoExistente.IdUsuario = dado.IdUsuario;
                dadoExistente.Descricao = dado.Descricao;
           
                _context.T_Comodo.Update(dadoExistente);
                await _context.SaveChangesAsync();

                return dadoExistente;
            }

            return null;
        }

        public async Task Excluir(int id)
        {
            var dado = await _context.T_Comodo.FindAsync(id);
            if (dado != null)
            {
                _context.T_Comodo.Remove(dado);
                await _context.SaveChangesAsync();
            }
        }
    }
}
