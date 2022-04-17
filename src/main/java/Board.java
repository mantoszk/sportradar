import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Board {

    private final Map<String, Match> matches = new HashMap<>();

    public void startMatch(String homeTeam, String awayTeam) {
        final Match match = new Match(homeTeam, awayTeam);
        matches.put(getKey(homeTeam, awayTeam), match);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        matches.remove(getKey(homeTeam, awayTeam));
    }

    public void updateScore(Team home, Team away) {
        final Match match = matches.get(getKey(home.getName(), away.getName()));
        match.setHomeTeamScore(home.getScore());
        match.setAwayTeamScore(away.getScore());
    }

    public Collection<Match> getSummary(Comparator<Match> comparator) {
        final TreeSet<Match> matches = new TreeSet<>(comparator);
        matches.addAll(this.matches.values());
        return matches.descendingSet();
    }

    private String getKey(String homeTeam, String awayTeam) {
        return homeTeam + awayTeam;
    }
}
