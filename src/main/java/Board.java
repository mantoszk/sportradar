import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Board {

    private final Map<String, Match> matches = new HashMap<>();

    public void startMatch(String homeTeamName, String awayTeamName) {
        final Match match = new Match(homeTeamName, awayTeamName);
        matches.put(getKey(homeTeamName, awayTeamName), match);
    }

    public void finishMatch(String homeTeamName, String awayTeamName) {
        matches.remove(getKey(homeTeamName, awayTeamName));
    }

    public void updateScore(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) {
        final Match match = matches.get(getKey(homeTeamName, awayTeamName));
        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);
    }

    public Collection<Match> getSummary(Comparator<Match> comparator) {
        final TreeSet<Match> matches = new TreeSet<>(comparator);
        matches.addAll(this.matches.values());
        return matches.descendingSet();
    }

    private String getKey(String homeTeamName, String awayTeamName) {
        return homeTeamName + awayTeamName;
    }
}
