using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Repositories
{
    public class UsuarioRepository : IUsuarioRepository
    {
        private readonly ApplicationDbContext _context;

        public UsuarioRepository(ApplicationDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<Usuario> Criar(Usuario usuario)
        {
            await _context.T_Usuario.AddAsync(usuario);
            await _context.SaveChangesAsync();

            return usuario;
        }

        public async Task<Usuario> ConsultarId(int id)
        {
            var usuario = await _context.T_Usuario.FindAsync(id);

            if (usuario == null)
            {
            
                return null;
            }

            return usuario;

        }

        public async Task<List<Usuario>> ConsultarTodos()
        {
            return await _context.T_Usuario.ToListAsync();
        }

        public async Task<Usuario?> Atualizar(Usuario usuario)
        {
            var usuarioExistente = await _context.T_Usuario.FindAsync(usuario.Id);
            if (usuarioExistente != null)
            {
                usuarioExistente.Nome = usuario.Nome;
                usuarioExistente.Sobrenome = usuario.Sobrenome;
                usuarioExistente.Telefone = usuario.Telefone;
                usuarioExistente.Email = usuario.Email;
                usuarioExistente.Senha = usuario.Senha;

                _context.T_Usuario.Update(usuarioExistente);
                await _context.SaveChangesAsync();

                return usuarioExistente;
            }

            return null;
        }

        public async Task Excluir(int id)
        {
            var usuario = await _context.T_Usuario.FindAsync(id);
            if (usuario != null)
            {
                _context.T_Usuario.Remove(usuario);
                await _context.SaveChangesAsync();
            }
        }
    }
}
