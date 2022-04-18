import java.util.Comparator;

class MatchCustomComparator implements Comparator<Match> {

    @Override
    public int compare(Match o1, Match o2) {
        final int compare = Integer.compare(o1.getSumScore(), o2.getSumScore());
        if (compare != 0) {
            return compare;
        }
        return compareLastUpdate(o1, o2);
    }

    private int compareLastUpdate(Match o1, Match o2) {
        if (o1.getLastUpdate().isAfter(o2.getLastUpdate())) {
            return 1;
        } else if (o1.getLastUpdate().isBefore(o2.getLastUpdate())) {
            return -1;
        }
        return 0;
    }
}
