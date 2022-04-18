import java.util.Collection;

public interface Scoreboard {

    void startMatch(String homeTeamName, String awayTeamName);

    void finishMatch(String homeTeamName, String awayTeamName);

    void updateScore(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore);

    Collection<String> getSummary();
}
