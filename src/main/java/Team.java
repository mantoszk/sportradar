import java.util.Objects;

public class Team {
    private final String name;
    private int score;

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %d", name, score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return score == team.score && name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }
}
