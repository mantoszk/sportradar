import java.util.Comparator;

public class MatchCustomComparator implements Comparator<Match> {
    @Override
    public int compare(Match o1, Match o2) {
        int thisSumScore = o1.getHomeTeamScore() + o1.getAwayTeamScore();
        int oSumScore = o2.getHomeTeamScore() + o2.getAwayTeamScore();

        final int compare = Integer.compare(thisSumScore, oSumScore);

        if (compare != 0) {
            return compare;
        }
        if (o1.getLastUpdate().isAfter(o2.getLastUpdate())) {
            return 1;
        } else if (o1.getLastUpdate().isBefore(o2.getLastUpdate())) {
            return -1;
        }
        return 0;
    }
}
