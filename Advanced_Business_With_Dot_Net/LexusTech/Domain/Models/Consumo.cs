
using System.ComponentModel.DataAnnotations;
namespace LexusTech.Models;

public class Consumo
{
    [Key]    
    public int IdConsumo { get; set; }
    public int IdUsuario { get; set; }
    public string? Comodo { get; set; }
    public string? Item { get; set; }
    public int ConsumoDiario { get; set; }
    public DateTime DataConsumo { get; set; }
    public int Valor { get; set; }

}
