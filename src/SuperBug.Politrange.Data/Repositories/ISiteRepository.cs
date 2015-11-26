using System.Collections.Generic;
using SuperBug.Politrange.Models;

namespace SuperBug.Politrange.Data.Repositories
{
    public interface ISiteRepository
    {
        IEnumerable<Site> GetAllSite();
        Site GetSiteById(int id);
    }
}