import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;

public class BoardTest {

    @Test
    public void shouldReturnSummaryInCorrectOrder() throws InterruptedException {
        //GIVEN: actual scoreboard (order of adding elements matter):
        //Mexico-Canada 0:5
        //Spain-Brazil 10:2
        //Germany-France 2:2
        //Uruguay-Italy 6:6
        //Argentina-Australia 3:1
        //Sleep added to simulate time difference in changing scores, junit executes too fast, which ends up
        //in matches being updated at the same time
        final Board board = new Board();
        board.startMatch("Mexico","Canada");
        board.updateScore(new Team("Mexico",0), new Team("Canada", 5));
        Thread.sleep(1);
        board.startMatch("Spain","Brazil");
        board.updateScore(new Team("Spain",10), new Team("Brazil", 2));
        Thread.sleep(1);
        board.startMatch("Germany","France");
        board.updateScore(new Team("Germany",2), new Team("France", 2));
        Thread.sleep(1);
        board.startMatch("Uruguay","Italy");
        board.updateScore(new Team("Uruguay",6), new Team("Italy", 6));
        Thread.sleep(1);
        board.startMatch("Argentina","Australia");
        board.updateScore(new Team("Argentina",3), new Team("Australia", 1));

        //WHEN: getSummary from Board with GIVEN data in correct order
        final Collection<Match> actual = board.getSummary(new MatchCustomComparator());

        //THEN: expect summary:
        //Uruguay-Italy 6:6
        //Spain-Brazil 10:2
        //Mexico-Canada 0:5
        //Argentina-Australia 3:1
        //Germany-France 2:2
        final Match ui = new Match("Uruguay", "Italy");
        ui.setHomeTeamScore(6);
        ui.setAwayTeamScore(6);
        final Match sb = new Match("Spain", "Brazil");
        sb.setHomeTeamScore(10);
        sb.setAwayTeamScore(2);
        final Match mc = new Match("Mexico", "Canada");
        mc.setHomeTeamScore(0);
        mc.setAwayTeamScore(5);
        final Match aa = new Match("Argentina", "Australia");
        aa.setHomeTeamScore(3);
        aa.setAwayTeamScore(1);
        final Match gf = new Match("Germany", "France");
        gf.setHomeTeamScore(2);
        gf.setAwayTeamScore(2);

        assertThat(actual, contains(ui, sb, mc, aa, gf));
    }

    @Test
    void shouldFinishMatch() {
        //GIVEN: scoreboard:
        //Spain-Portugal 0:0
        final Board board = new Board();
        board.startMatch("Spain","Portugal");

        //WHEN: match is finished it is removed from scoreboard
        board.finishMatch("Spain", "Portugal");

        //THEN: scoreboard is empty
        final Collection<Match> summary = board.getSummary(new MatchCustomComparator());
        assertThat(summary, is(empty()));
    }
}