package com.example.nortti.politrange;

public class FakeStaticProvider implements IStatisitcProvider {
    @Override
    public Statistic GetCommonStatistic() {
        return null;
    }

    @Override
    public Statistic GetDailyStatisitc(DateTime date1, DateTime date2) {
        return null;
    }
}
