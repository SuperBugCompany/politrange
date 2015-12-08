package com.example.nortti.politrange.intefaces;

import com.example.nortti.politrange.DateTime;
import com.example.nortti.politrange.Statistic;
import com.example.nortti.politrange.objects.Site;

public interface IStatisitcProvider {
    Statistic getCommonStatistic(Site site);
    Statistic getDailyStatistic(DateTime date1, DateTime date2, Site site);
}
