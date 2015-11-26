using System;
using System.Collections.Generic;
using System.Linq;
using Moq;
using SuperBug.Politrange.Data.Repositories;
using SuperBug.Politrange.Models;
using SuperBug.Politrange.Services.States;
using Xunit;
using Xunit.Abstractions;

namespace SuperBug.Politrange.Service.UnitTest
{
    public class StatServiceTest
    {
        private readonly ITestOutputHelper output;

        Mock<IStatRepository> statRepositoryMock;
        IStatService statService;

        public StatServiceTest(ITestOutputHelper output)
        {
            this.output = output;
            statRepositoryMock = new Mock<IStatRepository>();

            statService = new StatService(statRepositoryMock.Object);
        }

        [Fact]
        public void ShouldBeReturnGroupByPersonSumRanks()
        {
            statRepositoryMock.Setup(m => m.GetPageRanksBySite(It.IsAny<int>())).Returns(GetRatings);

            var result = statService.GetPageRanksBySite(1).ToList();

            Assert.Equal(result[0].Rank, 25);

            foreach (PersonPageRank item in result)
            {
                output.WriteLine("{0}: {1}", item.Person.Name, item.Rank);
            }
        }

        #region Data for test

        private IEnumerable<PersonPageRank> GetRatings()
        {
            var sites = new List<Site>()
            {
                new Site() {Name = "Lenta.ru"},
                new Site() {Name = "Gazeta.ru"}
            };

            var persons = new List<Person>()
            {
                new Person() {Name = "Путин"},
                new Person() {Name = "Медведев"},
                new Person() {Name = "Навальный"}
            };

            var pages = new List<Page>()
            {
                new Page()
                {
                    Uri = "www.lenta.ru/new/1",
                    Site = sites[0],
                    FoundDate = new DateTime(2014, 1, 1),
                    LastScanDate = new DateTime(2015, 1, 1)
                },
                new Page()
                {
                    Uri = "www.lenta.ru/new/2",
                    Site = sites[0],
                    FoundDate = new DateTime(2013, 1, 1),
                    LastScanDate = new DateTime(2015, 1, 1)
                }
            };

            var ratings = new List<PersonPageRank>()
            {
                new PersonPageRank()
                {
                    Page = pages[0],
                    Person = persons[0],
                    Rank = 10
                },
                new PersonPageRank()
                {
                    Page = pages[0],
                    Person = persons[1],
                    Rank = 10
                },
                new PersonPageRank()
                {
                    Page = pages[0],
                    Person = persons[2],
                    Rank = 10
                },
                new PersonPageRank()
                {
                    Page = pages[1],
                    Person = persons[0],
                    Rank = 15
                },
                new PersonPageRank()
                {
                    Page = pages[1],
                    Person = persons[1],
                    Rank = 15
                },
                new PersonPageRank()
                {
                    Page = pages[1],
                    Person = persons[2],
                    Rank = 15
                }
            };

            return ratings;
        }

        #endregion
    }
}