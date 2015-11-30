using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ParserConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            RssClass res = ReadData("http://lenta.ru/rss").Result;

            List<string> ul = new List<string>();
            foreach (var item in res.Channel.Links)
            {
                ul.Add(item.ToString());
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
