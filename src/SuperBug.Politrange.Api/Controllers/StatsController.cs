using System;
using System.Web.Http;
using SuperBug.Politrange.Services.States;

namespace SuperBug.Politrange.Api.Controllers
{
    public class StatController: ApiController
    {
        private readonly IStatService statService;

        public StatController(IStatService statService)
        {
            this.statService = statService;
        }

        public IHttpActionResult GetStatBySiteId(int id)
        {
            var ratings = statService.GetPageRanksBySite(id);

            return Ok(ratings);
        }
    }

    
}