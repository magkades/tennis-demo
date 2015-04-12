package org.magkades.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Match entity 
 *
 */
@Entity
@Table(name="matches")
public class MatchEntity implements Serializable {

    private static final long serialVersionUID = -8039686696076337053L;

    /** id of the match */
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="player1")
    private String player1;

    @Column(name="player2")

    private String player2;

    @Column(name="status")
    private String status;

    @Column(name="start_ts")
    private Date startTs;

    @Column(name="latest_ts")
    private Date latestTs;

    @Column(name="sets_count_1")
    private Integer setsCount1;

    @Column(name="games_count_1")
    private Integer gamesCount1;

    @Column(name="points_count_1")
    private Integer pointsCount1;

    @Column(name="sets_count_2")
    private Integer setsCount2;

    @Column(name="games_count_2")
    private Integer gamesCount2;

    @Column(name="points_count_2")
    private Integer pointsCount2;

    public MatchEntity(){}

    public MatchEntity(String player1, String player2, String status, Date startTs, Date latestTs,
                       Integer setsCount1, Integer gamesCount1, Integer pointsCount1,
                       Integer setsCount2, Integer gamesCount2, Integer pointsCount2) {
        this.player1 = player1;
        this.player2 = player2;
        this.status = status;
        this.startTs = startTs;
        this.latestTs = latestTs;
        this.setsCount1 = setsCount1;
        this.gamesCount1 = gamesCount1;
        this.pointsCount1 = pointsCount1;
        this.setsCount2 = setsCount2;
        this.gamesCount2 = gamesCount2;
        this.pointsCount2 = pointsCount2;
    }

    public void initialise() {
        this.setStartTs(new Date());
        this.setLatestTs(new Date());
        this.setStatus(MatchStatus.ONGOING.getValue());
        this.setSetsCount1(0);
        this.setGamesCount1(0);
        this.setPointsCount1(0);
        this.setSetsCount2(0);
        this.setGamesCount2(0);
        this.setPointsCount2(0);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTs() {
        return startTs;
    }

    public void setStartTs(Date startTs) {
        this.startTs = startTs;
    }

    public Date getLatestTs() {
        return latestTs;
    }

    public void setLatestTs(Date latestTs) {
        this.latestTs = latestTs;
    }

    public Integer getSetsCount1() {
        return setsCount1;
    }

    public void setSetsCount1(Integer setsCount1) {
        this.setsCount1 = setsCount1;
    }

    public Integer getGamesCount1() {
        return gamesCount1;
    }

    public void setGamesCount1(Integer gamesCount1) {
        this.gamesCount1 = gamesCount1;
    }

    public Integer getPointsCount1() {
        return pointsCount1;
    }

    public void setPointsCount1(Integer pointsCount1) {
        this.pointsCount1 = pointsCount1;
    }

    public Integer getSetsCount2() {
        return setsCount2;
    }

    public void setSetsCount2(Integer setsCount2) {
        this.setsCount2 = setsCount2;
    }

    public Integer getGamesCount2() {
        return gamesCount2;
    }

    public void setGamesCount2(Integer gamesCount2) {
        this.gamesCount2 = gamesCount2;
    }

    public Integer getPointsCount2() {
        return pointsCount2;
    }

    public void setPointsCount2(Integer pointsCount2) {
        this.pointsCount2 = pointsCount2;
    }
}
