using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Repositories
{
    public class AlertasGeradosRepository : IAlertasGeradosRepository
    {
        private readonly ApplicationDbContext _context;

        public AlertasGeradosRepository(ApplicationDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<List<AlertasGerados>> ConsultarTodos()
        {
            return await _context.T_Alerta_Gerado.ToListAsync();
        }

        
    }
}
