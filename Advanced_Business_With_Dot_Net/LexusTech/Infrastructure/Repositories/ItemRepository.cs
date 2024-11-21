using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Repositories
{
    public class ItemRepository : IItemRepository
    {
        private readonly ApplicationDbContext _context;

        public ItemRepository(ApplicationDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<Item> Criar(Item contexto)
        {
            await _context.T_Item_Casa.AddAsync(contexto);
            await _context.SaveChangesAsync();

            return contexto;
        }

        public async Task<Item> ConsultarId(int id)
        {
            var contexto = await _context.T_Item_Casa.FindAsync(id);

            if (contexto == null)
            {
            
                return null;
            }

            return contexto;

        }

        public async Task<List<Item>> ConsultarTodos()
        {
            return await _context.T_Item_Casa.ToListAsync();
        }

        public async Task<Item> Atualizar(Item contexto)
        {
            var itemExistente = await _context.T_Item_Casa.FindAsync(contexto.Id);
            if (itemExistente != null)
            {
                itemExistente.DescricaoItem = contexto.DescricaoItem;

                _context.T_Item_Casa.Update(itemExistente);
                await _context.SaveChangesAsync();

                return itemExistente;
            }

            return null;
        }

        public async Task Excluir(int id)
        {
            var contexto = await _context.T_Item_Casa.FindAsync(id);
            if (contexto != null)
            {
                _context.T_Item_Casa.Remove(contexto);
                await _context.SaveChangesAsync();
            }
        }
    }
}
