using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
namespace LexusTech.Models;

    public class ImagemDTO
    {
        [Key]    
        public int Id { get; set; }
        [Required]
        [ForeignKey("Usuario")]
        public int IdUsuario { get; set; }

        [Required(ErrorMessage = "Nome é obrigatório")]
        public string? NomeImagem { get; set; }

        [Required(ErrorMessage = "O link da imagem é obrigatório")]
        [Url]
        public string? LinkImagem { get; set; }

    }