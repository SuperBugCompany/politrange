using System.Collections.Generic;
using System.Linq;
using SuperBug.Politrange.Data.Repositories;
using SuperBug.Politrange.Models;

namespace SuperBug.Politrange.Services.States
{
    public class StatService: IStatService
    {
        private readonly IStatRepository statRepository;

        public StatService(IStatRepository statRepository)
        {
            this.statRepository = statRepository;
        }
        
        //Todo: Bad logic
        public IEnumerable<PersonPageRank> GetPageRanksBySite(int id)
        {
            IEnumerable<PersonPageRank> ranks = statRepository.GetPageRanksBySite(id);

            return ranks.GroupBy(x => x.Person).Select(s=> new PersonPageRank(){Person = s.Key, Rank = s.Sum(e=>e.Rank)});
        }
    }
}