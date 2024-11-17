using System.ComponentModel.DataAnnotations;

namespace LexusTech.Models
{
    public class LoginLog
    {
        [Key]
        public int IdLogin { get; set; }
        [Required]
        public int IdUsuario { get; set; }
        [Required]
        public string? Email { get; set; }
        [Required]
        public DateTime DataHora { get; set; }
    }
}