using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Repositories
{
    public class ConsumoRepository : IConsumoRepository
    {
        private readonly ApplicationDbContext _context;

        public ConsumoRepository(ApplicationDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<Consumo> Criar(Consumo contexto)
        {
            await _context.T_Consumo.AddAsync(contexto);
            await _context.SaveChangesAsync();

            return contexto;
        }

        public async Task<Consumo> ConsultarId(int id)
        {
            var dado = await _context.T_Consumo.FindAsync(id);

            if (dado == null)
            {
            
                return null;
            }

            return dado;

        }

        public async Task<List<Consumo>> ConsultarTodos()
        {
            return await _context.T_Consumo.ToListAsync();
        }

        public async Task<Consumo> Atualizar(Consumo contexto)
        {
            var dadoExistente = await _context.T_Consumo.FindAsync(contexto.IdConsumo);
            if (dadoExistente != null)
            {
                dadoExistente.IdConsumo = contexto.IdConsumo;
                dadoExistente.IdUsuario = contexto.IdUsuario;
                dadoExistente.Comodo = contexto.Comodo;
                dadoExistente.Item = contexto.Item;
                dadoExistente.ConsumoDiario = contexto.ConsumoDiario;
                dadoExistente.DataConsumo = contexto.DataConsumo;
                dadoExistente.Valor = contexto.Valor;
           
                _context.T_Consumo.Update(dadoExistente);
                await _context.SaveChangesAsync();

                return dadoExistente;
            }

            return null;
        }

        public async Task Excluir(int id)
        {
            var dado = await _context.T_Consumo.FindAsync(id);
            if (dado != null)
            {
                _context.T_Consumo.Remove(dado);
                await _context.SaveChangesAsync();
            }
        }


    }
}
