﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Oracle.EntityFrameworkCore.Metadata;

#nullable disable

namespace LexusTech.Migrations
{
    [DbContext(typeof(ApplicationDbContext))]
    partial class ApplicationDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "8.0.10")
                .HasAnnotation("Relational:MaxIdentifierLength", 128);

            OracleModelBuilderExtensions.UseIdentityColumns(modelBuilder);

            modelBuilder.Entity("LexusTech.Models.AlertaConsumo", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("NUMBER(10)");

                    OraclePropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("Id"));

                    b.Property<string>("Ambiente")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("Ativo")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<int>("ConsumoMinimo")
                        .HasColumnType("NUMBER(10)");

                    b.Property<string>("Descricao")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("EmailDestino")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<int>("IdUsuario")
                        .HasColumnType("NUMBER(10)");

                    b.Property<string>("TipoAlerta")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.HasKey("Id");

                    b.ToTable("T_Alerta_Consumo");
                });

            modelBuilder.Entity("LexusTech.Models.AlertasGerados", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("NUMBER(10)");

                    OraclePropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("Id"));

                    b.Property<string>("Comodo")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("ConsumoDiario")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("ConsumoDiarioAnterior")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<DateTime>("DataConsumo")
                        .HasColumnType("TIMESTAMP(7)");

                    b.Property<int>("IdConsumo")
                        .HasColumnType("NUMBER(10)");

                    b.Property<string>("Item")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("Recomendacao")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("VariacaoConsumo")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.HasKey("Id");

                    b.ToTable("T_Alerta_Gerado");
                });

            modelBuilder.Entity("LexusTech.Models.Comodo", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("NUMBER(10)");

                    OraclePropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("Id"));

                    b.Property<string>("Descricao")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<int>("IdUsuario")
                        .HasColumnType("NUMBER(10)");

                    b.HasKey("Id");

                    b.ToTable("T_Comodo");
                });

            modelBuilder.Entity("LexusTech.Models.Consumo", b =>
                {
                    b.Property<int>("IdConsumo")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("NUMBER(10)");

                    OraclePropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("IdConsumo"));

                    b.Property<string>("Comodo")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<int>("ConsumoDiario")
                        .HasColumnType("NUMBER(10)");

                    b.Property<DateTime>("DataConsumo")
                        .HasColumnType("TIMESTAMP(7)");

                    b.Property<int>("IdUsuario")
                        .HasColumnType("NUMBER(10)");

                    b.Property<string>("Item")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<int>("Valor")
                        .HasColumnType("NUMBER(10)");

                    b.HasKey("IdConsumo");

                    b.ToTable("T_Consumo");
                });

            modelBuilder.Entity("LexusTech.Models.Endereco", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("NUMBER(10)");

                    OraclePropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("Id"));

                    b.Property<string>("Bairro")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("CEP")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("Cidade")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("Complemento")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("Estado")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<int>("IdUsuario")
                        .HasColumnType("NUMBER(10)");

                    b.Property<string>("Rua")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.HasKey("Id");

                    b.ToTable("T_Endereco");
                });

            modelBuilder.Entity("LexusTech.Models.Imagem", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("NUMBER(10)");

                    OraclePropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("Id"));

                    b.Property<int>("IdUsuario")
                        .HasColumnType("NUMBER(10)");

                    b.Property<string>("LinkImagem")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("NomeImagem")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.HasKey("Id");

                    b.ToTable("T_Imagem");
                });

            modelBuilder.Entity("LexusTech.Models.Item", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("NUMBER(10)");

                    OraclePropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("Id"));

                    b.Property<string>("DescricaoItem")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<int>("IdUsuario")
                        .HasColumnType("NUMBER(10)");

                    b.HasKey("Id");

                    b.ToTable("T_Item_Casa");
                });

            modelBuilder.Entity("LexusTech.Models.Login", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("NUMBER(10)");

                    OraclePropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("Id"));

                    b.Property<string>("Email")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("Senha")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.HasKey("Id");

                    b.ToTable("T_Login");
                });

            modelBuilder.Entity("LexusTech.Models.LoginLog", b =>
                {
                    b.Property<int>("IdLogin")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("NUMBER(10)");

                    OraclePropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("IdLogin"));

                    b.Property<DateTime>("DataHora")
                        .HasColumnType("TIMESTAMP(7)");

                    b.Property<string>("Email")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<int>("IdUsuario")
                        .HasColumnType("NUMBER(10)");

                    b.HasKey("IdLogin");

                    b.ToTable("T_LoginLog");
                });

            modelBuilder.Entity("LexusTech.Models.Usuario", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("NUMBER(10)");

                    OraclePropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("Id"));

                    b.Property<string>("Email")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("Nome")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("Senha")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("Sobrenome")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.Property<string>("Telefone")
                        .HasColumnType("NVARCHAR2(2000)");

                    b.HasKey("Id");

                    b.ToTable("T_Usuario");
                });
#pragma warning restore 612, 618
        }
    }
}
