using System.Data.Entity;
using SuperBug.Politrange.Models;

namespace SuperBug.Politrange.Data.Contexts
{
    public class PolitrangeContext: DbContext, IPolitrangeContext
    {
        public PolitrangeContext()
            : base("PolitrangeContext")
        {
        }

        public DbSet<Keyword> Keywords { get; set; }
        public DbSet<Person> Persons { get; set; }
        public DbSet<PersonPageRank> PersonPageRanks { get; set; }
        public DbSet<Page> Pages { get; set; }
        public DbSet<Site> Sites { get; set; }
    }
}