
using System.ComponentModel.DataAnnotations;
namespace LexusTech.Models;

public class Consumo
{
    [Key]    
    public int IdConsumo { get; set; }
    public int IdUsuario { get; set; }
    public int IdComodo { get; set; }
    public int IdItemCasa { get; set; }
    public decimal ConsumoDiario { get; set; }
    public DateTime DataConsumo { get; set; }
    public int Valor { get; set; }

}
