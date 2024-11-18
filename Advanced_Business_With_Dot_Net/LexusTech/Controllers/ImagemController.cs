using Microsoft.AspNetCore.Mvc;
using Oracle.ManagedDataAccess.Client;
using System;
using System.Data;
using LexusTech.Models;

public class ImagemController : Controller
{
    private readonly string _oracleConnectionString;  

    public ImagemController(IConfiguration configuration)
    {

        _oracleConnectionString = configuration.GetConnectionString("Oracle")
            ?? throw new ArgumentNullException("Oracle", "A string de conexão não pode ser nula.");
    }

    [HttpGet("Index")]
    public IActionResult Index()
    {
        return View();
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public IActionResult Criar(string nomeImagem, string linkImagem)
    {
        if (!string.IsNullOrEmpty(nomeImagem) && !string.IsNullOrEmpty(linkImagem))
        {
            // Chama a procedure do banco de dados
            using (var connection = new OracleConnection(_oracleConnectionString))
            {
                try
                {
                    connection.Open();
                    using (var command = new OracleCommand("RM553472.CadastrarImagemProcedure", connection))
                    {
                        command.CommandType = CommandType.StoredProcedure;
                        command.Parameters.Add(new OracleParameter("NomeImagem", nomeImagem));
                        command.Parameters.Add(new OracleParameter("LinkImagem", linkImagem));

                        // Executa a procedure
                        command.ExecuteNonQuery();
                    }

                    TempData["SuccessMessage"] = "Imagem cadastrada com sucesso!";
                }
                catch (Exception ex)
                {
                    TempData["ErrorMessage"] = "Erro ao cadastrar imagem: " + ex.Message;
                
                }
            }
        }
        else
        {
            TempData["ErrorMessage"] = "Informações inválidas.";
        }

        // Redireciona de volta para a view Index para exibir a mensagem
        return RedirectToAction("Index");
    }

    [HttpGet("Consultar")]
    public IActionResult Consultar()
    {
        List<Imagem> imagens = new List<Imagem>();

        using (var connection = new OracleConnection(_oracleConnectionString))
        {
            try
            {
                connection.Open();
                using (var command = new OracleCommand("SELECT \"Id\", \"NomeImagem\", \"LinkImagem\" FROM \"RM553472\".\"T_Imagem\"", connection))
                {
                    using (var reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            imagens.Add(new Imagem
                            {
                                Id = reader.GetInt32(0),
                                NomeImagem = reader.GetString(1),
                                LinkImagem = reader.GetString(2)
                            });
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                TempData["ErrorMessage"] = "Erro ao consultar as imagens: " + ex.Message;
            }
        }

        // Passa as imagens para a view Consultar
        return View(imagens);
    }


}