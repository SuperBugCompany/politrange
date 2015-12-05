using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Crawler
{
    public class DownloadHtmlHelper
    {
        public string DownloadHtml(string uri, Encoding encoding)
        {
            HttpWebRequest request = WebRequest.Create(uri) as HttpWebRequest;

            //WebProxy wp = ExecuteProxi(proxiList);
            //request.Proxy = new WebProxy("178.215.111.70", 9999);

            request.UserAgent = "Mozilla / 5.0(Windows NT 6.1; WOW64) AppleWebKit / 537.36(KHTML, like Gecko) Chrome / 46.0.2490.86 Safari / 537.36";
            request.Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*;q=0.8";
            request.KeepAlive = true;

            HttpWebResponse response = request.GetResponse() as HttpWebResponse;

            StreamReader sr = new StreamReader(response.GetResponseStream(), encoding);
            sr.ReadLine();
            string html = sr.ReadToEnd();

            return html;
        }
    }
}
