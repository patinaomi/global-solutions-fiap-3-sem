using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Repositories
{
    public class LoginLogRepository : ILoginLogRepository
    {
        private readonly ApplicationDbContext _context;

        public LoginLogRepository(ApplicationDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<LoginLog> Criar(LoginLog loginLog)
        {
            await _context.T_LoginLog.AddAsync(loginLog);
            await _context.SaveChangesAsync();

            return loginLog;
        }

        public async Task<LoginLog> ConsultarId(int id)
        {
            var loginLog = await _context.T_LoginLog.FindAsync(id);

            if (loginLog == null)
            {
            
                return null;
            }

            return loginLog;

        }

        public async Task<List<LoginLog>> ConsultarTodos()
        {
            return await _context.T_LoginLog.ToListAsync();
        }

    }
}
