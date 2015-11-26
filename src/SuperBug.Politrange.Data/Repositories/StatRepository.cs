using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using SuperBug.Politrange.Data.Contexts;
using SuperBug.Politrange.Models;

namespace SuperBug.Politrange.Data.Repositories
{
    public class StatRepository: IStatRepository
    {
        private readonly IPolitrangeContext politrangeContext;

        public StatRepository(IPolitrangeContext politrangeContext)
        {
            this.politrangeContext = politrangeContext;
        }

        //Todo: Плохой запрос, необходимо исключать подгрузки таблицы Sites.
        public IEnumerable<PersonPageRank> GetPageRanksBySite(int siteId)
        {
            return politrangeContext.PersonPageRanks.Where(x => x.Page.Site.SiteId == siteId).Include(x => x.Person);
        }
    }
}