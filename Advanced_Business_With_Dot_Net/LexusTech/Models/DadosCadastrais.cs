
namespace LexusTech.Models;

public class DadosCadastrais
{
    public required Usuario Usuario { get; set; } = new Usuario();
    public required Endereco Endereco { get; set; } = new Endereco();
    public required Comodo Comodo { get; set; } = new Comodo();
    public required Item Item { get; set; }  = new Item();

}