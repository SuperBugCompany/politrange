using System;

namespace SuperBug.Politrange.Models
{
	public class Page
	{
		public int PageId { get; set; }
		public string Uri { get; set; }
		public DateTime FoundDate { get; set; }
		public DateTime LastScanDate { get; set; }
		public Site Site { get; set; }
	}
}