using AutoMapper;

namespace SuperBug.Politrange.Api.Mappers
{
	public class AutoMapperConfig
	{
		public static void Configure()
		{
			Mapper.Initialize(mapper =>
			{
				mapper.AddProfile<ViewModelToDomainMappingProfile>();
				mapper.AddProfile<DomainToViewModelMappingProfile>();
			});
		}
	}
}