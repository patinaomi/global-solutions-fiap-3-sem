
    using LexusTech.Models;
    using Microsoft.EntityFrameworkCore;

    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        public DbSet<Usuario> T_Usuario { get; set; }
        public DbSet<Endereco> T_Endereco { get; set; }
        public DbSet<Comodo> T_Comodo { get; set; }
        public DbSet<Item> T_Item_Casa { get; set; }
        public DbSet<Consumo> T_Consumo { get; set; }
    }