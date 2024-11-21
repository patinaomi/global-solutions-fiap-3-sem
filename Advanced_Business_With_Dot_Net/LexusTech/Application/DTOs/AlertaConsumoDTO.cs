using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace LexusTech.DTOs;

public class AlertaConsumoDTO
{
    [Key]
    public int Id { get; set; }

    [Required]
    [ForeignKey("Usuario")]
    public int IdUsuario { get; set; }

    [Required]
    [MaxLength(100)]
    public string Ambiente { get; set; } = string.Empty;

    [Required]
    public int ConsumoMinimo { get; set; }

    [Required]
    [EmailAddress(ErrorMessage = "O e-mail fornecido não é válido.")]
    public string EmailDestino { get; set; } = string.Empty;

    [Required]
    public string Ativo { get; set; } = string.Empty;

    [Required]
    [MaxLength(255)]
    public string Descricao { get; set; } = string.Empty;

    [Required]
    [MaxLength(50)]
    public string TipoAlerta { get; set; } = string.Empty;
}
