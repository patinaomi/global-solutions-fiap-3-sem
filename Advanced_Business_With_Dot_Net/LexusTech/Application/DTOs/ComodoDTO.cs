    using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
namespace LexusTech.Models;

    public class DomodoDTO
    {
        [Key]    
        public int Id { get; set; }
        [Required]
        [ForeignKey("Usuario")]
        public int IdUsuario { get; set; }

        [Required(ErrorMessage = "Pelo menos um ambiente é obrigatório")]
        public string? Descricao { get; set; }

    }