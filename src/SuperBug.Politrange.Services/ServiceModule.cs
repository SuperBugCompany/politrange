using Autofac;
using SuperBug.Politrange.Services.Sites;
using SuperBug.Politrange.Services.States;

namespace SuperBug.Politrange.Services
{
    public class ServiceModule: Module
    {
        protected override void Load(ContainerBuilder builder)
        {
            builder.RegisterType<SiteService>().As<ISiteService>();
        }
    }
}