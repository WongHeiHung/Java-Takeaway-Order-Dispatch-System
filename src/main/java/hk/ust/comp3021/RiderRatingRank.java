package hk.ust.comp3021;

import hk.ust.comp3021.rank.TaskRank;

public class RiderRatingRank implements TaskRank {
    public static RiderRatingRank riderRatingRank = new RiderRatingRank();

    @Override
    public int compare(Task source, Task target) {

        if(source.getRider().getUserRating() > target.getRider().getUserRating())
            return 1;
        else
            return 0;

    }
}

