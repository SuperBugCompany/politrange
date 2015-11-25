using System.Collections.Generic;
using System.Linq;
using SuperBug.Politrange.Data.Contexts;
using SuperBug.Politrange.Models;

namespace SuperBug.Politrange.Data.Repositories
{
    public class SiteRepository: ISiteRepository
    {
        private readonly IPolitrangeContext politrangeContext;

        public SiteRepository(IPolitrangeContext politrangeContext)
        {
            this.politrangeContext = politrangeContext;
        }

        public IEnumerable<Site> GetAllSite()
        {
            return politrangeContext.Sites.ToList();
        }

        public Site GetSiteById(int id)
        {
            return politrangeContext.Sites.FirstOrDefault(x => x.SiteId == id);
        }
    }
}