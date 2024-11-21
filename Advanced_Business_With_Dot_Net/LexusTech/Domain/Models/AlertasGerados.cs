using System.ComponentModel.DataAnnotations;

namespace LexusTech.Models;

public class AlertasGerados
{
    [Key]
    public int Id { get; set; }
    public int IdConsumo { get; set; }
    public string? Comodo { get; set; }
    public string? Item { get; set; }
    public string? ConsumoDiario { get; set; }
    public DateTime DataConsumo { get; set; }
    public string? ConsumoDiarioAnterior { get; set; }
    public string? VariacaoConsumo { get; set; }
    public string? Recomendacao { get; set; }
}
