using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ListUrlsJneGenerate
{
    public class Element
    {
        public string Link { get; set; }
        public string Name { get; set; }
        public int Frequenty { get; set; }

        public Element(string link, string Name, int frequenty)
        {
            this.Link = link;
            this.Name = Name;
            this.Frequenty = frequenty;
        }

        public override string ToString()
        {
            return string.Format("[{0}, {1}, {2}]", Link, Name, Frequenty);
        }

    }
}
