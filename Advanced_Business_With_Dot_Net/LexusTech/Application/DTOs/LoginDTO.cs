using System.ComponentModel.DataAnnotations;

namespace LexusTech.Models
{
    public class LoginDTO
    {
        [Key]
        public int IdLogin { get; set; }

        [Required]
        [EmailAddress]
        public string? Email { get; set; }

        [Required]
        [DataType(DataType.Password)]
        public string? Senha { get; set; }

    }
}