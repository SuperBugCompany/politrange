using System;
using System.Collections.Generic;
using System.Web.Http;
using AutoMapper;
using SuperBug.Politrange.Api.Models.ViewModels;
using SuperBug.Politrange.Models;
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
            IEnumerable<PersonPageRank> ratings = statService.GetPageRanksBySite(id);

            var ratingsViewModel = Mapper.Map<IEnumerable<PersonPageRank>, IEnumerable<CommonStatViewModel>>(ratings);

            return Ok(ratingsViewModel);
        }
    }

    
}