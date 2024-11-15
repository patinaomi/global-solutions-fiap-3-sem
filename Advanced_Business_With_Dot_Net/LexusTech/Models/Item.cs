
using System.ComponentModel.DataAnnotations;
namespace LexusTech.Models;

public class Item
{
    [Key]    
    public int Id { get; set; }
    public int IdUsuario { get; set; }

    [Required(ErrorMessage = "Pelo menos um item/eletrodoméstico é obrigatório")]
    public string? DescricaoItem { get; set; }

}
