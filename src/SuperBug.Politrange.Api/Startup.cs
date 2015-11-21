using System.Web.Http;
using Microsoft.Owin;
using Owin;
using SuperBug.Politrange.Api.Mappers;

[assembly: OwinStartup(typeof(SuperBug.Politrange.Api.Startup))]

namespace SuperBug.Politrange.Api
{
	public class Startup
	{
		public void Configuration(IAppBuilder app)
		{
			HttpConfiguration config = new HttpConfiguration();
			WebApiConfig.Register(config);
			AutofacConfig.Configure(app, config);
			AutoMapperConfig.Configure();

			app.UseWebApi(config);
		}
	}
}