package com.example.nortti.politrange.objects;

public class Day {
    private int foundDateTime;

    private int rank;

    public Day(int foundDateTime, int rank) {
        this.foundDateTime = foundDateTime;

        this.rank = rank;
    }

    public int getFoundDateTime() {
        return foundDateTime;
    }

    public void setFoundDateTime(int foundDateTime) {
        this.foundDateTime = foundDateTime;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
