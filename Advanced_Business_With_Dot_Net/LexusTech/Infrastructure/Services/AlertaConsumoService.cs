using LexusTech.Infrastructure.Interfaces;
using LexusTech.DTOs;
using LexusTech.Models;

namespace LexusTech.Application.Services
{
    public class AlertaConsumoService : IAlertaConsumoService
    {
        private readonly IAlertaConsumoRepository _contextoRepository;

        public AlertaConsumoService(IAlertaConsumoRepository contextoRepository)
        {
            _contextoRepository = contextoRepository;
        }

        public async Task<AlertaConsumoDTO> Criar(AlertaConsumoDTO contexto)
        {
            var model = MapearDTOParaModel(contexto);
            var resultado = await _contextoRepository.Criar(model);
            return MapearModelParaDTO(resultado);
        }

        public async Task<List<AlertaConsumoDTO>> ConsultarTodos()
        {
            var dados = await _contextoRepository.ConsultarTodos();
            return dados.Select(MapearModelParaDTO).ToList();
        }

        public async Task<AlertaConsumoDTO> ConsultarId(int id)
        {
            var dado = await _contextoRepository.ConsultarId(id);
            if (dado == null)
                throw new KeyNotFoundException($"Alerta de consumo com ID {id} não encontrado.");

            return MapearModelParaDTO(dado);
        }

        public async Task<AlertaConsumoDTO> Atualizar(AlertaConsumoDTO contexto)
        {
            var model = MapearDTOParaModel(contexto);
            var atualizado = await _contextoRepository.Atualizar(model);
            return MapearModelParaDTO(atualizado);
        }

        public async Task Excluir(int id)
        {
            await _contextoRepository.Excluir(id);
        }

        private static AlertaConsumo MapearDTOParaModel(AlertaConsumoDTO dto)
        {
            return new AlertaConsumo
            {
                Id = dto.Id,
                IdUsuario = dto.IdUsuario,
                Ambiente = dto.Ambiente,
                ConsumoMinimo = dto.ConsumoMinimo,
                EmailDestino = dto.EmailDestino,
                Ativo = dto.Ativo,
                TipoAlerta = dto.TipoAlerta
            };
        }

        private static AlertaConsumoDTO MapearModelParaDTO(AlertaConsumo model)
        {
            return new AlertaConsumoDTO
            {
                Id = model.Id,
                IdUsuario = model.IdUsuario,
                Ambiente = model.Ambiente,
                ConsumoMinimo = model.ConsumoMinimo,
                EmailDestino = model.EmailDestino,
                Ativo = model.Ativo,
                TipoAlerta = model.TipoAlerta
            };
        }
    }
}
