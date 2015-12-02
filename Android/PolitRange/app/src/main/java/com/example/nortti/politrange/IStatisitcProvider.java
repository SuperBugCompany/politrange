package com.example.nortti.politrange;

public interface IStatisitcProvider {
    Statistic GetCommonStatistic();
    Statistic GetDailyStatisitc(DateTime date1, DateTime date2);
}
