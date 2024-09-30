package hk.ust.comp3021;

import hk.ust.comp3021.rank.TaskRank;

public class RiderMonthTaskCountRank implements TaskRank {
    public static RiderMonthTaskCountRank riderMonthTaskCountRank = new RiderMonthTaskCountRank();

    @Override
    public int compare(Task source, Task target) {

        if(source.getRider().getMonthTaskCount() < target.getRider().getMonthTaskCount())
            return 1;
        else
            return 0;

    }
}
