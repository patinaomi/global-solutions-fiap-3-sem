using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace LexusTech.Migrations
{
    /// <inheritdoc />
    public partial class LoginLogs : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropPrimaryKey(
                name: "PK_T_Login",
                table: "T_Login");

            migrationBuilder.RenameColumn(
                name: "Id",
                table: "T_Login",
                newName: "IdUsuario");

            migrationBuilder.AlterColumn<string>(
                name: "Email",
                table: "T_Login",
                type: "NVARCHAR2(2000)",
                nullable: false,
                defaultValue: "",
                oldClrType: typeof(string),
                oldType: "NVARCHAR2(2000)",
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "IdUsuario",
                table: "T_Login",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)")
                .OldAnnotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1");

            migrationBuilder.AddColumn<int>(
                name: "IdLogin",
                table: "T_Login",
                type: "NUMBER(10)",
                nullable: false,
                defaultValue: 0)
                .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1");

            migrationBuilder.AlterColumn<decimal>(
                name: "ConsumoDiario",
                table: "T_Consumo",
                type: "DECIMAL(18, 2)",
                nullable: false,
                oldClrType: typeof(decimal),
                oldType: "DECIMAL(18,2)");

            migrationBuilder.AddPrimaryKey(
                name: "PK_T_Login",
                table: "T_Login",
                column: "IdLogin");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropPrimaryKey(
                name: "PK_T_Login",
                table: "T_Login");

            migrationBuilder.DropColumn(
                name: "IdLogin",
                table: "T_Login");

            migrationBuilder.RenameColumn(
                name: "IdUsuario",
                table: "T_Login",
                newName: "Id");

            migrationBuilder.AlterColumn<string>(
                name: "Email",
                table: "T_Login",
                type: "NVARCHAR2(2000)",
                nullable: true,
                oldClrType: typeof(string),
                oldType: "NVARCHAR2(2000)");

            migrationBuilder.AlterColumn<int>(
                name: "Id",
                table: "T_Login",
                type: "NUMBER(10)",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "NUMBER(10)")
                .Annotation("Oracle:Identity", "START WITH 1 INCREMENT BY 1");

            migrationBuilder.AlterColumn<decimal>(
                name: "ConsumoDiario",
                table: "T_Consumo",
                type: "DECIMAL(18,2)",
                nullable: false,
                oldClrType: typeof(decimal),
                oldType: "DECIMAL(18, 2)");

            migrationBuilder.AddPrimaryKey(
                name: "PK_T_Login",
                table: "T_Login",
                column: "Id");
        }
    }
}
