using AutoMapper;
using SuperBug.Politrange.Api.Models.ViewModels;
using SuperBug.Politrange.Models;

namespace SuperBug.Politrange.Api.Mappers
{
	public class DomainToViewModelMappingProfile: Profile
	{
		protected override void Configure()
		{
		    Mapper.CreateMap<PersonPageRank, CommonStatViewModel>();
		}
	}
}