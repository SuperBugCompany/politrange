using System.Collections.Generic;
using SuperBug.Politrange.Models;

namespace SuperBug.Politrange.Services.States
{
    public interface IStatService
    {
        IEnumerable<PersonPageRank> GetPageRanksBySite(int id);
    }
}