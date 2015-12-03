using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Xml.Serialization;
using System.Linq;

namespace rssсclass
{
    class Program
    {
        static void Main(string[] args)
        {
            RssClass res = ReadData("http://lenta.ru/rss").Result;
            Thread.Sleep(1000);

            foreach (var item in res.Channel.Links)
            {
                Console.WriteLine(item);
            }
            Console.ReadKey();
        }

        private async static Task<RssClass> ReadData(string url)
        {
            WebClient web = new WebClient();
            string s = await web.DownloadStringTaskAsync(new Uri(url, UriKind.Absolute));
            XmlSerializer xml = new XmlSerializer(typeof(RssClass));
            RssClass res = xml.Deserialize(new MemoryStream(Encoding.UTF8.GetBytes(s))) as RssClass;
            return res;
        }
    }
}
