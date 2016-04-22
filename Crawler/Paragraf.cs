using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Crawler
{
    public class Paragraf
    {
        public string Link { get; set; }
        public string Body { get; set; }
        public string Date { get; set; }

        public Paragraf(string link, string body, string date)
        {
            this.Link = link;
            this.Body = body;
            this.Date = date;
        }
    }
}