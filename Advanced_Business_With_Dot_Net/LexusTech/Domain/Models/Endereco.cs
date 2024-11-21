
using System.ComponentModel.DataAnnotations;
namespace LexusTech.Models;

public class Endereco
{
    [Key]    
    public int Id { get; set; }
    public int IdUsuario { get; set; }
    public string? CEP { get; set; }
    public string? Estado { get; set; }
    public string? Cidade { get; set; }
    public string? Bairro { get; set; }
    public string? Rua { get; set; }
    public string? Complemento { get; set; }

}
