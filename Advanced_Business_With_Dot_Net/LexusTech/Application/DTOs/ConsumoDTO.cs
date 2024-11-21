    using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
namespace LexusTech.Models;

    public class ConsumoDTO
    {
        [Key]    
        public int IdConsumo { get; set; }
        [Required]
        [ForeignKey("Usuario")]
        public int IdUsuario { get; set; }
        [Required(ErrorMessage = "Pelo menos um ambiente é obrigatório")]
        public string? Comodo { get; set; }
        [Required(ErrorMessage = "Pelo menos um item é obrigatório")]
        public string? Item { get; set; }
        [Required]
        public int ConsumoDiario { get; set; }
        [Required]
        public DateTime DataConsumo { get; set; }
        [Required]
        public int Valor { get; set; }

    }