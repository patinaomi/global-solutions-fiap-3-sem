using LexusTech.Infrastructure.Interfaces;
using LexusTech.DTOs;
using LexusTech.Models;

namespace LexusTech.Application.Services
{
    public class ItemService : IItemService
    {
        private readonly IItemRepository _contextoRepository;

        public ItemService(IItemRepository contextoRepository)
        {
            _contextoRepository = contextoRepository;
        }

        public async Task<ItemDTO> Criar(ItemDTO contexto)
        {
            var model = MapearDTOParaModel(contexto);
            var resultado = await _contextoRepository.Criar(model);
            return MapearModelParaDTO(resultado);
        }

        public async Task<List<ItemDTO>> ConsultarTodos()
        {
            var dados = await _contextoRepository.ConsultarTodos();
            return dados.Select(MapearModelParaDTO).ToList();
        }

        public async Task<ItemDTO> ConsultarId(int id)
        {
            var dado = await _contextoRepository.ConsultarId(id);
            if (dado == null)
                throw new KeyNotFoundException($"Item com ID {id} não encontrado.");

            return MapearModelParaDTO(dado);
        }

        public async Task<ItemDTO> Atualizar(ItemDTO contexto)
        {
            var model = MapearDTOParaModel(contexto);
            var atualizado = await _contextoRepository.Atualizar(model);
            return MapearModelParaDTO(atualizado);
        }

        public async Task Excluir(int id)
        {
            await _contextoRepository.Excluir(id);
        }

        private static Item MapearDTOParaModel(ItemDTO dto)
        {
            return new Item
            {
                Id = dto.Id,
                IdUsuario = dto.IdUsuario,
                DescricaoItem = dto.DescricaoItem,
                
            };
        }

        private static ItemDTO MapearModelParaDTO(Item model)
        {
            return new ItemDTO
            {
                Id = model.Id,
                IdUsuario = model.IdUsuario,
                DescricaoItem = model.DescricaoItem,
                
            };
        }
    }
}
