using System.Collections.Generic;
using SuperBug.Politrange.Data.Repositories;
using SuperBug.Politrange.Models;

namespace SuperBug.Politrange.Services.Sites
{
    public class SiteService : ISiteService
    {
        private readonly ISiteRepository siteRepository;

        public SiteService(ISiteRepository siteRepository)
        {
            this.siteRepository = siteRepository;
        }

        public IEnumerable<Site> GetAll()
        {
            return siteRepository.GetAllSite();
        }

        public Site GetSitebyId(int id)
        {
            return siteRepository.GetSiteById(id);
        }
    }
}