package org.magkades;

import org.magkades.dao.MatchDao;
import org.magkades.dao.MatchDaoMysql;
import org.magkades.dao.MatchEntity;

/**
 * Created by despoina on 11/04/15.
 */
public class Tester {

    public static void main(String[] args) {
        MatchDao matchDao = new MatchDaoMysql();

        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setPlayer1("a");
        matchEntity.setPlayer2("b");

        Long matchId = matchDao.createMatch(matchEntity);

    }
}
