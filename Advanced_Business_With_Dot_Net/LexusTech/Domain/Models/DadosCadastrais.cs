
namespace LexusTech.Models;

// não desejo transformar este componente em dto ou outros fluxos, pois ele só tem uma responsabilidade que é mostrar das em um item do dashboard e nada mais.

public class DadosCadastrais
{
    public required Usuario Usuario { get; set; } = new Usuario();
    public required Endereco Endereco { get; set; } = new Endereco();
    public required Comodo Comodo { get; set; } = new Comodo();
    public required Item Item { get; set; }  = new Item();
    public required Imagem Imagem { get; set; }  = new Imagem();
    public required Consumo Consumo { get; set; }  = new Consumo();
    public required AlertaConsumo AlertaConsumo { get; set; }  = new AlertaConsumo();

}