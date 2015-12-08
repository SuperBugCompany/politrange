package com.example.nortti.politrange;

import com.example.nortti.politrange.intefaces.IStatisitcProvider;
import com.example.nortti.politrange.objects.Site;

public class FakeStaticProvider implements IStatisitcProvider {

    @Override
    public Statistic getCommonStatistic(Site site) {

        return null;
    }

    @Override
    public Statistic getDailyStatistic(DateTime date1, DateTime date2, Site site) {
        return null;
    }
}
