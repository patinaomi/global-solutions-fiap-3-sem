
    using LexusTech.Models;
    using Microsoft.EntityFrameworkCore;

    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        public required DbSet<Usuario> T_Usuario { get; set; } = null!;
        public required DbSet<Login> T_Login { get; set; } = null!;
        public required DbSet<LoginLog> T_LoginLog { get; set; } = null!;
        public DbSet<Endereco> T_Endereco { get; set; }
        public DbSet<Comodo> T_Comodo { get; set; }
        public DbSet<Item> T_Item_Casa { get; set; }
        public DbSet<Consumo> T_Consumo { get; set; }
        public DbSet<Imagem> T_Imagem { get; set; }
        public DbSet<AlertaConsumo> T_Alerta_Consumo { get; set; }
        public DbSet<AlertasGerados> T_Alerta_Gerado { get; set; }


    }