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
            var keys = dbConnect.SelectKeywords();

            var listUrlsNavigationPages = GetUrlsNavigationPages();
            PrintControl(listUrlsNavigationPages);

            var listUrlsContentPages = GetUrlsContentPages(listUrlsNavigationPages);
            PrintControl(listUrlsContentPages);

            var listParagrafs = GetListParagrafs(dbConnect, listUrlsContentPages);
            PrintControl(listParagrafs);

            var listKeywords = GetListKeywords(keys, listParagrafs);
            PrintControl(listParagrafs, listKeywords);

            SaveResultToTxt(listKeywords);
            Console.ReadKey();
        }

        private static void PrintControl(List<Paragraf> listParagrafs, List<Keyword> listKeywords)
        {
            //foreach (var item in listKeywords)
            //{
            //    Console.WriteLine(item.ToString());
            //}
            Console.WriteLine(listParagrafs.Count + ": кол-во элементов в списке" + nameof(listKeywords));
        }

        private static void PrintControl(List<Paragraf> listParagrafs)
        {
            //foreach (var item in listParagrafs)
            //{
            //    Console.WriteLine("{0}  {1}", item.Link, item.Body);
            //}
            Console.WriteLine(listParagrafs.Count + ": кол-во элементов в списке" + nameof(listParagrafs));
        }

        private static void PrintControl(List<string> listUrlsNavigationPages)
        {
            //foreach (var item in listUrlsNavigationPages)
            //{
            //    Console.WriteLine(item);
            //}
            Console.WriteLine(listUrlsNavigationPages.Count + ": кол-во URL в проверенном списке" + nameof(listUrlsNavigationPages));
        }

        private static List<Keyword> GetListKeywords(List<string> keys, List<Paragraf> listParagrafs)
        {
            List<Keyword> listKeyword = new List<Keyword>();
            foreach (var pair in listParagrafs)
            {
                KeywordsStatistic keywordsStatistic = new KeywordsStatistic();
                var keywords = keywordsStatistic.GetKeywordsStatistic(keys, pair.Body, pair.Link);
                listKeyword.AddRange(keywords);
            }
            return listKeyword;
        }

        private static List<Paragraf> GetListParagrafs(DBConnect dbConnect, List<string> listUrlsContentPages)
        {
            List<Paragraf> htmlParagrafs = new List<Paragraf>();
            foreach (var item in listUrlsContentPages)
            {
                DownloadHtmlHelper downloadHtmlHelper = new DownloadHtmlHelper();
                string html = downloadHtmlHelper.DownloadHtml(item, Encoding.UTF8);
                var paragraf = GetParagrafs(html);
                dbConnect.InserttUrlContentPageIntoDatebase(item);

                htmlParagrafs.Add(new Paragraf(item, paragraf[0], paragraf[1]));
            }
            return htmlParagrafs;
        }

        private static List<string> GetUrlsContentPages(List<string> listUrlsNavigationPages)
        {
            List<string> listContentPages = new List<string>();
            foreach (var item in listUrlsNavigationPages)
            {
                listContentPages.AddRange(GetUrlsContentPerDay(item));
            }
            return listContentPages;
        }

        private static List<string> GetUrlsNavigationPages()
        {
            string baseUrl = "http://lenta.ru/";
            string url = "";
            List<string> goodUrls = new List<string>();

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

            return goodUrls;
        }

        private static void SaveResultToTxt(List<Keyword> listElements)
        {
            if (File.Exists(@"D:\Log.txt"))
            {
                File.Delete(@"D:\Log.txt");
            }

            using (FileStream fs = new FileStream(@"D:\Log.txt", FileMode.Append))
            {
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
            }
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

                    for (int i = 1; i < /*paragrafs.Length*/ 10; i++)
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


        public static string[] GetParagrafs(string html)
        {
            string paragrafs = "";
            string date = "";
            string[] paragrafAndDate = new string[2];

            try
            {
                paragrafs = html.Substring("articleBody\">", "</div>", 0);
                paragrafAndDate[0] = StripHtmlTagsUsingRegex(paragrafs);

                date = html.Substring("g-date\" datetime=\"", "\"", 0);
                paragrafAndDate[1] = StripHtmlTagsUsingRegex(date);
            }

            catch
            {
            }

            return paragrafAndDate;
        }

        static string StripHtmlTagsUsingRegex(string inputString)
        {
            return Regex.Replace(inputString, @"<[^>]*>", String.Empty);
        }



    }
}
