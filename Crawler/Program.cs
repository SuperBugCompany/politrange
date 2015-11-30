using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using xNet;

namespace Crawler
{
    class Program
    {
        static void Main(string[] args)
        {

            DBConnect dbConnect = new DBConnect();
            var keys = dbConnect.Select();

            string baseUrl = "http://lenta.ru/";
            string url = "";
            List<string> goodUrls = new List<string>();
            //string[] keys = { "Медведев", "Навальный", "Путин" };

            for (int i = 2013; i < 2015; i++)
            {
                for (int j = 10; j < 12; j++)
                {
                    for (int l = 30; l < 32; l++)
                    {
                        if (j < 10 && l < 10)
                        {
                            url = baseUrl + i + "/0" + j + "/0" + l + "/";
                        }

                        if (j > 9 && l < 10)
                        {
                            url = baseUrl + i + "/" + j + "/0" + l + "/";
                        }

                        else if (j < 10 && l > 9)
                        {
                            url = baseUrl + i + "/0" + j + "/" + l + "/";
                        }

                        else if (j > 9 && l > 9)
                        {
                            url = baseUrl + i + "/" + j + "/" + l + "/";
                        }

                        string str = GetUrlPerDay(url).ToString();

                        if (str.StartsWith("http"))
                        {
                            goodUrls.Add(str);
                        }
                    }
                }
            }

            //foreach (var item in goodUrls)
            //{
            //    Console.WriteLine(item);
            //}

            Console.WriteLine(goodUrls.Count + ": кол-во URL в проверенном списке (goodUrls)");

            // получаем в цикле полный список контент-страниц
            List<string> listContentPages = new List<string>();
            foreach (var item in goodUrls)
            {
                listContentPages.AddRange(GetUrlsContentPerDay(item));
            }

            Console.WriteLine(listContentPages.Count + ": кол-во URL в полном списке контент-страниц (listContentPages)");

            foreach (var item in listContentPages)
            {
                //Console.WriteLine(item);
                dbConnect.Insert(item);
            }

            // получаем в цикле html
            // извлекаем статистические данные
            // записываем в массив
            List<Paragraf> htmls = new List<Paragraf>();

            foreach (var item in listContentPages)
            {
                string html = DownloadHtml(item, Encoding.UTF8);
                var paragraf = GetParagrafs(html);
                htmls.Add(new Paragraf(item, paragraf));
            }

            Console.WriteLine(htmls.Count + ": кол-во элементов в списке htmls (должен соотвествовать по длине предыдущему)");

            //foreach (var item in htmls)
            //{
            //    Console.WriteLine("{0}  {1}", item.Link, item.Body);
            //}

            List<Keyword> listElements = new List<Keyword>();
            foreach (var pair in htmls)
            {
                var liel = ListElement(keys, pair.Body, pair.Link);
                listElements.AddRange(liel);
            }

            Console.WriteLine(htmls.Count + ": кол-во элементов в списке listElements (должен соотвествовать предыдущему)");

            Console.ReadKey();

            //foreach (var item in listElements)
            //{
            //    Console.WriteLine(item.ToString());
            //}

            using (FileStream fs = new FileStream(@"D:\Log.txt", FileMode.Append))
            {
                using (StreamWriter sw = new StreamWriter(fs))
                {
                    foreach (var item in listElements)
                    {
                        sw.WriteLine(item.ToString());
                    }
                    sw.Close();
                }
            }

            Console.ReadKey();            

        }

        // - See more at: http://www.csharpcoderr.com/2012/08/proverka-sushestvivaniya-url.html#sthash.bTY3Gf1x.dpuf
        static string GetUrlPerDay(string url)
        {
            HttpWebRequest request = WebRequest.Create(url) as HttpWebRequest;

            // прокси можно брать тут http://spys.ru/en/https-ssl-proxy/
            //request.Proxy = new WebProxy("178.215.111.70", 9999); 

            request.UserAgent = "Mozilla / 5.0(Windows NT 6.1; WOW64) AppleWebKit / 537.36(KHTML, like Gecko) Chrome / 46.0.2490.86 Safari / 537.36";
            request.Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*;q=0.8";
            request.KeepAlive = true;

            try
            {
                using (HttpWebResponse res = request.GetResponse() as HttpWebResponse)
                {
                    if (res.StatusDescription == "OK")
                    {
                        return url;
                    }
                }
            }

            catch
            {
                return "Ничего не анйдено";
            }

            return "Ничего не анйдено";
        }


        public static List<string> GetUrlsContentPerDay(string url)
        {
            List<string> urls = new List<string>();

            try
            {
                using (HttpRequest request = new HttpRequest())
                {
                    string sourcePage;

                    // прокси можно брать тут http://spys.ru/en/https-ssl-proxy/
                    //request.Proxy = Socks5ProxyClient.Parse("178.215.111.70: 9999"); ;

                    request.UserAgent = Http.ChromeUserAgent();
                    request.KeepAlive = true;

                    sourcePage = request.Get(url).ToString();

                    string[] row;
                    row = sourcePage.Substrings("<div class=\"titles\"><h3><a href=\"/", "\">", 0);

                    for (int i = 1; i < /*row.Length*/ 10; i++)
                    {
                        urls.Add("http://lenta.ru/" + row[i]);
                    }
                }
            }

            catch
            {

            }

            return urls;
        }


        public static string GetParagrafs(string html)
        {
            string row = "";

            try
            {
                row = html.Substring("articleBody\">", "</div>", 0);
                row = StripHtmlTagsUsingRegex(row);
            }

            catch
            {
            }

            return row;
        }

        static string StripHtmlTagsUsingRegex(string inputString)
        {
            return Regex.Replace(inputString, @"<[^>]*>", String.Empty);
        }

        public static string DownloadHtml(string uri, Encoding encoding)
        {
            //WebProxy wp = ParserVirgo.Proxi.WebanetLabsNet.ExecuteProxi(goodUrlsList);
            //WebProxy wp = new WebProxy("178.215.111.70", 9999);

            // 3 это формирует запрос, который уходит на сервер и обрабаотывается там и выдае ответ.
            HttpWebRequest request = WebRequest.Create(uri) as HttpWebRequest;

            //request.Proxy = wp;

            request.UserAgent = "Mozilla / 5.0(Windows NT 6.1; WOW64) AppleWebKit / 537.36(KHTML, like Gecko) Chrome / 46.0.2490.86 Safari / 537.36";
            request.Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*;q=0.8";
            request.KeepAlive = true;

            // 4 получаем ответ
            HttpWebResponse response = request.GetResponse() as HttpWebResponse;

            // 5 поток данных получаемых с сервера
            StreamReader sr = new StreamReader(response.GetResponseStream(), encoding);
            sr.ReadLine();
            string html = sr.ReadToEnd();

            // 6 получаем чтмл
            return html;
        }


        // на вход принимает массив ключей и ЧТМЛ,
        // возвращает объект
        static List<Keyword> ListElement(List<string> keys, string html, string link)
        {
            List<Keyword> lel = new List<Keyword>();

            string[] iteams = html.Split(new char[] { ' ', ',', '.' });

            foreach (var item in keys)
            {
                var result = (from t in iteams
                              where t.ToLower().Contains(item.ToLower())
                              select t).Count<string>();
                lel.Add(new Keyword(link, item, result));
            }
            return lel;
        } 

    }
}
