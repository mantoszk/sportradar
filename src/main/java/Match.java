import java.time.LocalDateTime;
import java.util.Objects;

public class Match {
    private final Team home;
    private final Team away;
    private LocalDateTime lastUpdate;

    public Match(String home, String away) {
        this.home = new Team(home);
        this.away = new Team(away);
        this.lastUpdate = LocalDateTime.now();
    }

    public int getHomeTeamScore() {
        return home.getScore();
    }

    public int getAwayTeamScore() {
        return away.getScore();
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setHomeTeamScore(int score) {
        home.setScore(score);
        lastUpdate = LocalDateTime.now();
    }

    public void setAwayTeamScore(int score) {
        away.setScore(score);
        lastUpdate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return home.equals(match.home) && away.equals(match.away);
    }

    @Override
    public int hashCode() {
        return Objects.hash(home, away);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", home, away);
    }
}
