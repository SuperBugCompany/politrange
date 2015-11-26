using System.Data.Entity;
using SuperBug.Politrange.Models;

namespace SuperBug.Politrange.Data.Contexts
{
    public interface IPolitrangeContext
    {
        DbSet<Keyword> Keywords { get; set; }
        DbSet<Person> Persons { get; set; }
        DbSet<PersonPageRank> PersonPageRanks { get; set; }
        DbSet<Page> Pages { get; set; }
        DbSet<Site> Sites { get; set; }
    }
}