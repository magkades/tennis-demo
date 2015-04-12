package org.magkades.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScoreAdjusterTest {
    private static final String PLAYER_1 = "nadal";
    private static final String PLAYER_2 = "federer";

    ScoreAdjuster scoreAdjuster;

    @Before
    public void setUp() throws Exception {
        scoreAdjuster = new ScoreAdjuster();
    }

    @Test
    public void shouldAdjustOnlyPoints() {
        // given
        MatchEntity match = new MatchEntity();
        match.initialise();
        match.setPlayer1(PLAYER_1);
        match.setPlayer2(PLAYER_2);
        match.setPointsCount1(4);
        match.setPointsCount2(4);

        // when
        match = scoreAdjuster.adjustMatch(match, PLAYER_1);

        // then
        Assert.assertEquals(new Integer(5), match.getPointsCount1());
        Assert.assertEquals(new Integer(4), match.getPointsCount2());
        Assert.assertEquals(new Integer(0), match.getGamesCount1());
        Assert.assertEquals(new Integer(0), match.getGamesCount2());
    }

    @Test
    public void shouldAdjustGamesWhenPointsOverflow() {
        // given
        MatchEntity match = new MatchEntity();
        match.initialise();
        match.setPlayer1(PLAYER_1);
        match.setPlayer2(PLAYER_2);
        match.setPointsCount1(5);
        match.setPointsCount2(4);

        // when
        match = scoreAdjuster.adjustMatch(match, PLAYER_1);

        // then
        Assert.assertEquals(new Integer(0), match.getPointsCount1());
        Assert.assertEquals(new Integer(0), match.getPointsCount2());
        Assert.assertEquals(new Integer(1), match.getGamesCount1());
        Assert.assertEquals(new Integer(0), match.getGamesCount2());
    }

    @Test
    public void shouldAdjustSetsWhenGamesOverflow() {
        // given
        MatchEntity match = new MatchEntity();
        match.initialise();
        match.setPlayer1(PLAYER_1);
        match.setPlayer2(PLAYER_2);
        match.setPointsCount1(5);
        match.setPointsCount2(4);
        match.setGamesCount1(7);
        match.setGamesCount2(6);

        // when
        match = scoreAdjuster.adjustMatch(match, PLAYER_1);

        // then
        Assert.assertEquals(new Integer(0), match.getPointsCount1());
        Assert.assertEquals(new Integer(0), match.getPointsCount2());
        Assert.assertEquals(new Integer(0), match.getGamesCount1());
        Assert.assertEquals(new Integer(0), match.getGamesCount2());
        Assert.assertEquals(new Integer(1), match.getSetsCount1());
        Assert.assertEquals(new Integer(0), match.getSetsCount2());
    }

    @Test
    public void shouldAdjustStatus() {
        // given
        MatchEntity match = new MatchEntity();
        match.initialise();
        match.setPlayer1(PLAYER_1);
        match.setPlayer2(PLAYER_2);
        match.setPointsCount1(5);
        match.setPointsCount2(4);
        match.setGamesCount1(7);
        match.setGamesCount2(6);
        match.setSetsCount1(1);
        match.setSetsCount2(1);

        // when
        match = scoreAdjuster.adjustMatch(match, PLAYER_1);

        // then
        Assert.assertEquals(new Integer(0), match.getPointsCount1());
        Assert.assertEquals(new Integer(0), match.getPointsCount2());
        Assert.assertEquals(new Integer(0), match.getGamesCount1());
        Assert.assertEquals(new Integer(0), match.getGamesCount2());
        Assert.assertEquals(new Integer(2), match.getSetsCount1());
        Assert.assertEquals(new Integer(1), match.getSetsCount2());
        Assert.assertEquals(match.getStatus(), MatchStatus.COMPLETE.getValue());
    }
}
