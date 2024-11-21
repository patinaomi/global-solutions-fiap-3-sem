using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
namespace LexusTech.Models;

public class ItemDTO
{
    [Key]    
    public int Id { get; set; }
    
    [Required]
    public int IdUsuario { get; set; }

    [Required(ErrorMessage = "Pelo menos um item/eletrodoméstico é obrigatório")]
    public string? DescricaoItem { get; set; }

}
