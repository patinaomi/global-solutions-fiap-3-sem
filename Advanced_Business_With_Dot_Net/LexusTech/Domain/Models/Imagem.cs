
using System.ComponentModel.DataAnnotations;
namespace LexusTech.Models;

public class Imagem
{
    [Key]    
    public int Id { get; set; }
    public int IdUsuario { get; set; }
    public string? NomeImagem { get; set; }
    public string? LinkImagem { get; set; }

}
