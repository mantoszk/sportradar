import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreboardImpl implements Scoreboard {

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
        match.updateScores(homeTeamScore, awayTeamScore);
    }

    public Collection<String> getSummary() {
        return matches.values()
                .stream()
                .sorted(new MatchCustomComparator().reversed())
                .map(Match::toString)
                .collect(Collectors.toList());
    }

    private String getKey(String homeTeamName, String awayTeamName) {
        return homeTeamName + awayTeamName;
    }
}
