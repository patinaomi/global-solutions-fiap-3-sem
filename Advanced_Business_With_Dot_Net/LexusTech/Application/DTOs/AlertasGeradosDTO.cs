using System.ComponentModel.DataAnnotations;

namespace LexusTech.DTOs;

public class AlertasGeradosDTO
{
    [Key]
    public int Id { get; set; }

    [Required(ErrorMessage = "O campo 'IdConsumo' é obrigatório.")]
    [Range(1, int.MaxValue, ErrorMessage = "O campo 'IdConsumo' deve ser maior que zero.")]
    public int IdConsumo { get; set; }

    [Required(ErrorMessage = "O campo 'Comodo' é obrigatório.")]
    [StringLength(50, ErrorMessage = "O campo 'Comodo' deve ter no máximo 50 caracteres.")]
    public string? Comodo { get; set; }

    [Required(ErrorMessage = "O campo 'Item' é obrigatório.")]
    [StringLength(50, ErrorMessage = "O campo 'Item' deve ter no máximo 50 caracteres.")]
    public string? Item { get; set; }

    [Required(ErrorMessage = "O campo 'ConsumoDiario' é obrigatório.")]
    public string? ConsumoDiario { get; set; }

    [Required(ErrorMessage = "O campo 'DataConsumo' é obrigatório.")]
    public DateTime DataConsumo { get; set; }

    [StringLength(50, ErrorMessage = "O campo 'ConsumoDiarioAnterior' deve ter no máximo 50 caracteres.")]
    public string? ConsumoDiarioAnterior { get; set; }

    [StringLength(50, ErrorMessage = "O campo 'VariacaoConsumo' deve ter no máximo 50 caracteres.")]
    public string? VariacaoConsumo { get; set; }

    [StringLength(255, ErrorMessage = "O campo 'Recomendacao' deve ter no máximo 255 caracteres.")]
    public string? Recomendacao { get; set; }
}
