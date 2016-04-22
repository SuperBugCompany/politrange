using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Crawler
{
    public class KeywordsStatistic
    {
        public List<Keyword> GetKeywordsStatistic(List<string> keys, string html, string link)
        {
            List<Keyword> listKeyword = new List<Keyword>();

            string[] iteams = html.Split(new char[] { ' ', ',', '.' });

            foreach (var item in keys)
            {
                var result = (from t in iteams
                              where t.ToLower().Contains(item.ToLower())
                              select t).Count<string>();
                listKeyword.Add(new Keyword(link, item, result));
            }

            return listKeyword;
        }
    }
}
