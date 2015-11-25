using System.Collections.Generic;
using SuperBug.Politrange.Models;

namespace SuperBug.Politrange.Services.Sites
{
    public interface ISiteService
    {
        IEnumerable<Site> GetAll();
        Site GetSitebyId(int id);
    }
}