package hk.ust.comp3021;

import hk.ust.comp3021.rank.TaskRank;

import java.util.Objects;

public class RiderMonthTaskCountRank implements TaskRank {
    public static RiderMonthTaskCountRank riderMonthTaskCountRank = new RiderMonthTaskCountRank();

    @Override
    public int compare(Task source, Task target) {
        if(Objects.equals(source.getRider().getMonthTaskCount(), target.getRider().getMonthTaskCount()))
            return 0;
        else if(source.getRider().getMonthTaskCount() < target.getRider().getMonthTaskCount())
            return 1;
        else
            return -1;

    }
}
