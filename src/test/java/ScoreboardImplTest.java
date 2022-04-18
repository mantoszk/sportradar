import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

//I'm assuming that person using api is not passing nulls or empty strings to the methods = invalid data
public class ScoreboardImplTest {

    @Test
    public void shouldReturnSummaryInCorrectOrder() throws InterruptedException {
        //GIVEN: (order of adding elements matter):
        //Mexico-Canada 0:5
        //Spain-Brazil 10:2
        //Germany-France 2:2
        //Uruguay-Italy 6:6
        //Argentina-Australia 3:1
        //Sleep added to simulate time difference in changing scores, junit executes too fast, which ends up
        //in matches being updated at the same time
        final Scoreboard scoreboard = new ScoreboardImpl();
        scoreboard.startMatch("Mexico","Canada");
        scoreboard.updateScore("Mexico","Canada", 0,5);
        Thread.sleep(1);
        scoreboard.startMatch("Spain","Brazil");
        scoreboard.updateScore("Spain","Brazil", 10,2);
        Thread.sleep(1);
        scoreboard.startMatch("Germany","France");
        scoreboard.updateScore("Germany","France", 2,2);
        Thread.sleep(1);
        scoreboard.startMatch("Uruguay","Italy");
        scoreboard.updateScore("Uruguay","Italy", 6,6);
        Thread.sleep(1);
        scoreboard.startMatch("Argentina","Australia");
        scoreboard.updateScore("Argentina","Australia", 3,1);

        //WHEN: getSummary from Board with GIVEN data in correct order
        final Collection<String> actual = scoreboard.getSummary();

        //THEN: expect summary:
        assertThat(actual, contains(
            "Uruguay 6 - Italy 6",
            "Spain 10 - Brazil 2",
            "Mexico 0 - Canada 5",
            "Argentina 3 - Australia 1",
            "Germany 2 - France 2"));
    }

    @Test
    void shouldReturnEmptySummaryWhenMatchIsFinished() {
        //GIVEN:
        //Spain-Portugal 0:0
        final Scoreboard scoreboard = new ScoreboardImpl();
        scoreboard.startMatch("Spain","Portugal");

        //WHEN: match is finished it is removed from scoreboard
        scoreboard.finishMatch("Spain", "Portugal");

        //THEN: scoreboard is empty
        final Collection<String> summary = scoreboard.getSummary();
        assertThat(summary, is(empty()));
    }
}