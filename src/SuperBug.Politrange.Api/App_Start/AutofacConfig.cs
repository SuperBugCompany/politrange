using System.Reflection;
using System.Web.Http;
using Autofac;
using Autofac.Integration.WebApi;
using Owin;
using SuperBug.Politrange.Data;
using SuperBug.Politrange.Services;

namespace SuperBug.Politrange.Api
{
	public class AutofacConfig
	{
		public static void Configure(IAppBuilder app, HttpConfiguration config)
		{
			ConfigureAutofacContainer(app, config);
		}

		private static void ConfigureAutofacContainer(IAppBuilder app, HttpConfiguration config)
		{
			var builder = new ContainerBuilder();
			builder.RegisterApiControllers(Assembly.GetExecutingAssembly());

			RegisterComponents(builder);

			IContainer container = builder.Build();
			config.DependencyResolver = new AutofacWebApiDependencyResolver(container);
			app.UseAutofacMiddleware(container);
			app.UseAutofacWebApi(config);
		}

		private static void RegisterComponents(ContainerBuilder builder)
		{
		    builder.RegisterModule<DataModule>();
		    builder.RegisterModule<ServiceModule>();

		}
	}
}