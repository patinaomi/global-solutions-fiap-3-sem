using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace LexusTech.Migrations
{
    /// <inheritdoc />
    public partial class ItensCasa : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "T_Comodo",
                columns: table => new
                {
                    Id = table.Column<int>(type: "NUMBER(10)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    IdUsuario = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    Descricao = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_T_Comodo", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "T_Endereco",
                columns: table => new
                {
                    Id = table.Column<int>(type: "NUMBER(10)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    IdUsuario = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    CEP = table.Column<string>(type: "NVARCHAR2(8)", maxLength: 8, nullable: false),
                    Estado = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Cidade = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Bairro = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Rua = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Complemento = table.Column<string>(type: "NVARCHAR2(2000)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_T_Endereco", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "T_Item_Casa",
                columns: table => new
                {
                    Id = table.Column<int>(type: "NUMBER(10)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    IdUsuario = table.Column<int>(type: "NUMBER(10)", nullable: false),
                    DescricaoItem = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_T_Item_Casa", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "T_Usuario",
                columns: table => new
                {
                    Id = table.Column<int>(type: "NUMBER(10)", nullable: false)
                        .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1"),
                    Nome = table.Column<string>(type: "NVARCHAR2(100)", maxLength: 100, nullable: false),
                    Sobrenome = table.Column<string>(type: "NVARCHAR2(100)", maxLength: 100, nullable: false),
                    Telefone = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Email = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false),
                    Senha = table.Column<string>(type: "NVARCHAR2(2000)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_T_Usuario", x => x.Id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "T_Comodo");

            migrationBuilder.DropTable(
                name: "T_Endereco");

            migrationBuilder.DropTable(
                name: "T_Item_Casa");

            migrationBuilder.DropTable(
                name: "T_Usuario");
        }
    }
}
