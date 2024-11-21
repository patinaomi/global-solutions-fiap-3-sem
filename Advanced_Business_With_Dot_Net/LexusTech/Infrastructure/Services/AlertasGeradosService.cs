using LexusTech.Infrastructure.Interfaces;
using LexusTech.DTOs;
using LexusTech.Models;

namespace LexusTech.Application.Services
{
    public class AlertasGeradosService : IAlertasGeradosService
    {
        private readonly IAlertasGeradosRepository _contextoRepository;

        public AlertasGeradosService(IAlertasGeradosRepository contextoRepository)
        {
            _contextoRepository = contextoRepository;
        }

        public async Task<List<AlertasGeradosDTO>> ConsultarTodos()
        {
            var dados = await _contextoRepository.ConsultarTodos();
            return dados.Select(MapearModelParaDTO).ToList();
        }

    
        private static AlertasGerados MapearDTOParaModel(AlertasGeradosDTO dto)
        {
            return new AlertasGerados
            {
                Id = dto.Id,
                IdConsumo = dto.IdConsumo,
                Comodo = dto.Comodo,
                Item = dto.Item,
                ConsumoDiario = dto.ConsumoDiario,
                DataConsumo = dto.DataConsumo,
                ConsumoDiarioAnterior = dto.ConsumoDiarioAnterior,
                VariacaoConsumo = dto.VariacaoConsumo,
                Recomendacao = dto.Recomendacao
            };
        }

        private static AlertasGeradosDTO MapearModelParaDTO(AlertasGerados model)
        {
            return new AlertasGeradosDTO
            {
                Id = model.Id,
                IdConsumo = model.IdConsumo,
                Comodo = model.Comodo,
                Item = model.Item,
                ConsumoDiario = model.ConsumoDiario,
                DataConsumo = model.DataConsumo,
                ConsumoDiarioAnterior = model.ConsumoDiarioAnterior,
                VariacaoConsumo = model.VariacaoConsumo,
                Recomendacao = model.Recomendacao
            };
        }
    }
}
