using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace LexusTech.Models
{
    public class LoginLogDTO
    {
        [Key]
        public int IdLogin { get; set; }
        
        [Required]
        [ForeignKey("Usuario")]
        public int IdUsuario { get; set; }
        [Required]
        [EmailAddress]
        public string? Email { get; set; }
        [Required]
        public DateTime DataHora { get; set; }
    }
}