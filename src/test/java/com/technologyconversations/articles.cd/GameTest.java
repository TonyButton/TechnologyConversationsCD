package com.technologyconversations.articles.cd;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Implement a simple tennis game

**Rules:**

* Scores from zero to three points are described as "love", "fifteen", "thirty", and "forty" respectively.
* If at least three points have been scored by each side and a player has one more point than his opponent, the score of the game is "advantage" for the player in the lead.
* If at least three points have been scored by each player, and the scores are equal, the score is "deuce".
* A game is won by the first player to have won at least four points in total and at least two points more than the opponent.
*/
public class GameTest {

    Player victor;
    Player sarah;
    Game game;

    @Before
    public void beforeGameTest() {
        victor = new Player("Victor");
        sarah = new Player("Sarah");
        game = new Game(victor, sarah);
    }

    @Test
    public void loveShouldBeDescriptionForScore0() {
        Game game = new Game(victor, sarah);
        assertThat(game, hasProperty("score", is("love, love")));
    }

    @Test
    public void fifteenShouldBeDescriptionForScore1() {
        sarah.winBall();
        assertThat(game, hasProperty("score", is("love, fifteen")));
    }

    @Test
    public void thirtyShouldBeDescriptionForScore2() {
        victor.winBall();
        victor.winBall();
        sarah.winBall();
        assertThat(game, hasProperty("score", is("thirty, fifteen")));
    }

    @Test
    public void fortyShouldBeDescriptionForScore3() {
        for (int index = 1; index <= 3; index++) {
                victor.winBall();
        }
        assertThat(game, hasProperty("score", is("forty, love")));
    }

    @Test
    public void advantageShouldBeDescriptionWhenLeastThreePointsHaveNeenScoredByEachSideAndPlayerHasOnePointMoreThanHisOpponent() {
        for (int index = 1; index <= 3; index++) {
            victor.winBall();
        }
        for (int index = 1; index <= 4; index++) {
            sarah.winBall();
        }
        assertThat(game, hasProperty("score", is("advantage Sarah")));
    }

    @Test
    public void deuceShouldBeDescriptionWhenAtLeastThreePointsHaveBeenScoredByEachPlayerAndTheScoresAreEqual() {
        for (int index = 1; index <= 3; index++) {
            victor.winBall();
        }
        for (int index = 1; index <= 3; index++) {
            sarah.winBall();
        }
        assertThat(game, hasProperty("score", is("deuce")));
        victor.winBall();
        assertThat(game, hasProperty("score", is(not("deuce"))));
        sarah.winBall();
        assertThat(game, hasProperty("score", is("deuce")));
    }

    @Test
    public void gameShouldBeWonByTheFirstPlayerToHaveWonAtLeastFourPointsInTotalAndWithAtLeastTwoPointsMoreThanTheOpponent() {
        for (int index = 1; index <= 4; index++) {
            victor.winBall();
        }
        for (int index = 1; index <= 3; index++) {
            sarah.winBall();
        }
        assertThat(game, hasProperty("score", is(not("Victor won"))));
        assertThat(game, hasProperty("score", is(not("Sarah won"))));
        victor.winBall();
        assertThat(game, hasProperty("score", is("Victor won")));
    }

}
