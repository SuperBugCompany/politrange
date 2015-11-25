using System.Web.Http;
using SuperBug.Politrange.Services.Sites;

namespace SuperBug.Politrange.Api.Controllers
{
    public class SiteController: ApiController
    {
        private readonly ISiteService siteService;

        public SiteController(ISiteService siteService)
        {
            this.siteService = siteService;
        }

        public IHttpActionResult Get()
        {
            var sites = siteService.GetAll();

            return Ok(sites);
        }

        public IHttpActionResult Get(int id)
        {
            var site = siteService.GetSitebyId(id);

            return Ok(site);
        }
    }
}