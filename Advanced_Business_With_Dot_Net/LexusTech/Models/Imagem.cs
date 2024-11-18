
using System.ComponentModel.DataAnnotations;
namespace LexusTech.Models;

public class Imagem
{
    [Key]    
    public int Id { get; set; }

    [Required(ErrorMessage = "Nome é obrigatório")]
    public string? NomeImagem { get; set; }

    [Required(ErrorMessage = "O link da imagem é obrigatório")]
    public string? LinkImagem { get; set; }

}
