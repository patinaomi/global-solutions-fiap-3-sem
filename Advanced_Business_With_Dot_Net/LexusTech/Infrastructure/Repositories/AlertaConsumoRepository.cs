using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Repositories
{
    public class AlertaConsumoRepository : IAlertaConsumoRepository
    {
        private readonly ApplicationDbContext _context;

        public AlertaConsumoRepository(ApplicationDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<AlertaConsumo> Criar(AlertaConsumo contexto)
        {
            await _context.T_Alerta_Consumo.AddAsync(contexto);
            await _context.SaveChangesAsync();

            return contexto;
        }

        public async Task<AlertaConsumo> ConsultarId(int id)
        {
            var dado = await _context.T_Alerta_Consumo.FindAsync(id);

            if (dado == null)
            {
            
                return null;
            }

            return dado;

        }

        public async Task<List<AlertaConsumo>> ConsultarTodos()
        {
            return await _context.T_Alerta_Consumo.ToListAsync();
        }

        public async Task<AlertaConsumo> Atualizar(AlertaConsumo dado)
        {
            var dadoExistente = await _context.T_Alerta_Consumo.FindAsync(dado.Id);
            if (dadoExistente != null)
            {
                dadoExistente.Id = dado.Id;
                dadoExistente.IdUsuario = dado.IdUsuario;
                dadoExistente.Ambiente = dado.Ambiente;
                dadoExistente.ConsumoMinimo = dado.ConsumoMinimo;
                dadoExistente.EmailDestino = dado.EmailDestino;
                dadoExistente.Ativo = dado.Ativo;
                dadoExistente.TipoAlerta = dado.TipoAlerta;
           
                _context.T_Alerta_Consumo.Update(dadoExistente);
                await _context.SaveChangesAsync();

                return dadoExistente;
            }

            return null;
        }

        public async Task Excluir(int id)
        {
            var dado = await _context.T_Alerta_Consumo.FindAsync(id);
            if (dado != null)
            {
                _context.T_Alerta_Consumo.Remove(dado);
                await _context.SaveChangesAsync();
            }
        }
    }
}
