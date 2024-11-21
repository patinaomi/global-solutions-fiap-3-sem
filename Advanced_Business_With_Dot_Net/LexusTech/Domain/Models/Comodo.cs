
using System.ComponentModel.DataAnnotations;
namespace LexusTech.Models;

public class Comodo
{
    [Key]    
    public int Id { get; set; }
    public int IdUsuario { get; set; }
    public string? Descricao { get; set; }

}
