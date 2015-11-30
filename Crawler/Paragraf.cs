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

        public Paragraf(string link, string body)
        {
            this.Link = link;
            this.Body = body;
        }
    }
}
