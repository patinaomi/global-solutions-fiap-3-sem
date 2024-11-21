
using System.ComponentModel.DataAnnotations;
namespace LexusTech.Models;

public class Item
{
    [Key]    
    public int Id { get; set; }
    public int IdUsuario { get; set; }
    public string? DescricaoItem { get; set; }

}
