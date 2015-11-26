using Autofac;
using SuperBug.Politrange.Data.Contexts;
using SuperBug.Politrange.Data.Repositories;

namespace SuperBug.Politrange.Data
{
	public class DataModule: Module
	{
		protected override void Load(ContainerBuilder builder)
		{
		    builder.RegisterType<PolitrangeContext>().As<IPolitrangeContext>();

		    builder.RegisterType<SiteRepository>().As<ISiteRepository>();

		}
	}
}