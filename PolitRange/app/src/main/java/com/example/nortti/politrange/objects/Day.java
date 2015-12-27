package com.example.nortti.politrange.objects;

public class Day {
    private String foundDateTime;

    private int rank;

    public Day(String foundDateTime, int rank) {
        this.foundDateTime = foundDateTime;

        this.rank = rank;
    }

    public String getFoundDateTime() {
        return foundDateTime;
    }

    public void setFoundDateTime(String foundDateTime) {
        this.foundDateTime = foundDateTime;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
