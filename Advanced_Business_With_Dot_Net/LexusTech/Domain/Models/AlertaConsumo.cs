using System.ComponentModel.DataAnnotations;

namespace LexusTech.Models;

public class AlertaConsumo
{
    [Key]
    public int Id { get; set; }
    public int IdUsuario { get; set; }
    public string? Ambiente { get; set; }
    public int ConsumoMinimo { get; set; }
    public string? EmailDestino { get; set; }
    public string? Ativo { get; set; }
    public string? Descricao { get; set; }
    public string? TipoAlerta { get; set; }
}
