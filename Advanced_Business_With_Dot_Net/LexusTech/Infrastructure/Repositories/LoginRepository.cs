using Microsoft.EntityFrameworkCore;
using LexusTech.Models;

namespace LexusTech.Repositories
{
    public class LoginRepository : ILoginRepository
    {
        private readonly ApplicationDbContext _context;

        public LoginRepository(ApplicationDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<Login> Criar(Login login)
        {
            await _context.AddAsync(login);
            await _context.SaveChangesAsync();
            return login;
        }

        public async Task<List<Login>> ConsultarTodos()
        {
            return await _context.Set<Login>().ToListAsync();
        }

        public async Task<Login> ConsultarId(int id)
        {
            var login = await _context.T_Login.FindAsync(id);

            if (login == null)
            {
            
                return null;
            }

            return login;

        }

        public async Task<Usuario> Autenticar(string email, string senha)
        {
            return await _context.Set<Usuario>()
                .FirstOrDefaultAsync(u => u.Email == email && u.Senha == senha);
        }

    }
}
