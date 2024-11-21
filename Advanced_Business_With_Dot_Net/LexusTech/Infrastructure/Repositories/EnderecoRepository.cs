using LexusTech.Infrastructure.Interfaces;
using LexusTech.Models;
using Microsoft.EntityFrameworkCore;

namespace LexusTech.Repositories
{
    public class EnderecoRepository : IEnderecoRepository
    {
        private readonly ApplicationDbContext _context;

        public EnderecoRepository(ApplicationDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));
        }

        public async Task<Endereco> Criar(Endereco endereco)
        {
            await _context.T_Endereco.AddAsync(endereco);
            await _context.SaveChangesAsync();

            return endereco;
        }

        public async Task<Endereco> ConsultarId(int id)
        {
            var dado = await _context.T_Endereco.FindAsync(id);

            if (dado == null)
            {
            
                return null;
            }

            return dado;

        }

        public async Task<List<Endereco>> ConsultarTodos()
        {
            return await _context.T_Endereco.ToListAsync();
        }

        public async Task<Endereco> Atualizar(Endereco endereco)
        {
            var dadoExistente = await _context.T_Endereco.FindAsync(endereco.Id);
            if (dadoExistente != null)
            {
                dadoExistente.CEP = endereco.CEP;
                dadoExistente.Estado = endereco.Estado;
                dadoExistente.Cidade = endereco.Cidade;
                dadoExistente.Bairro = endereco.Bairro;
                dadoExistente.Rua = endereco.Rua;
                dadoExistente.Complemento = endereco.Complemento;

                _context.T_Endereco.Update(dadoExistente);
                await _context.SaveChangesAsync();

                return dadoExistente;
            }

            return null;
        }

        public async Task Excluir(int id)
        {
            var dado = await _context.T_Endereco.FindAsync(id);
            if (dado != null)
            {
                _context.T_Endereco.Remove(dado);
                await _context.SaveChangesAsync();
            }
        }
    }
}
