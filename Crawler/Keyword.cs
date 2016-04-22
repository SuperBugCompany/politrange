using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Crawler
{
    public class Keyword
    {
        public string Link { get; set; }
        public string Name { get; set; }
        public int Frequenty { get; set; }

        public Keyword(string link, string Name, int frequenty)
        {
            this.Link = link;
            this.Name = Name;
            this.Frequenty = frequenty;
        }

        public override string ToString()
        {
            return string.Format("{0}, {1}, {2}", Link, Name, Frequenty);
        }
    }
}