import java.time.LocalDateTime;
import java.util.Objects;

public class Match {

    private final String homeTeamName;

    private final String awayTeamName;

    private int homeTeamScore;

    private int awayTeamScore;

    private LocalDateTime lastUpdate;

    public Match(String homeTeamName, String awayTeamName) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.lastUpdate = LocalDateTime.now();
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
        lastUpdate = LocalDateTime.now();
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
        lastUpdate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return homeTeamScore == match.homeTeamScore && awayTeamScore == match.awayTeamScore
                && homeTeamName.equals(match.homeTeamName) && awayTeamName.equals(match.awayTeamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeamName, awayTeamName, homeTeamScore, awayTeamScore);
    }

    @Override
    public String toString() {
        return String.format("%s %d - %s %d", homeTeamName, homeTeamScore, awayTeamName, awayTeamScore);
    }
}
