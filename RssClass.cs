using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace ParserConsole
{
    [Serializable]
    [XmlRoot(ElementName="rss")]
    public class RssClass
    {
        [XmlElement(ElementName = "channel")]
        public ChannelClass Channel { get; set; }
    }

    public class ChannelClass
    {
        [XmlElement(ElementName = "item")]
        //public List<ItemClass> Items { get; set; }
        public List<LinkClass> Links { get; set; }
    }

    public class ItemClass
    {
        [XmlElement(ElementName = "title")]
        public string Title { get; set; }
    }

    public class LinkClass
    {
        [XmlElement(ElementName = "link")]
        public string Link { get; set; }

        public override string ToString()
        {
            return string.Format(Link);
        }
    }
}
