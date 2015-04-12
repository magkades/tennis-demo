package org.magkades.tests;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.magkades.dao.MatchEntity;
import org.magkades.dao.MatchStatus;
import org.magkades.hibernate.Storage;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestStorage {

    @Test
    public void testWrite() {
        // Just a write, verify id set
        MatchEntity matchEntity = new MatchEntity();
        Storage<MatchEntity> storage = new Storage<MatchEntity>(matchEntity);
        storage.beginTransaction();
        matchEntity.setPlayer1("player1");
        matchEntity.setPlayer2("player2");
        matchEntity.setStartTs(new Date());
        matchEntity.setLatestTs(new Date());
        matchEntity.setStatus(MatchStatus.ONGOING.getValue());
        matchEntity.setSetsCount1(0);
        matchEntity.setGamesCount1(0);
        matchEntity.setPointsCount1(0);
        matchEntity.setSetsCount2(0);
        matchEntity.setGamesCount2(0);
        matchEntity.setPointsCount2(0);
        assertNull(matchEntity.getId());
        storage.insert(matchEntity);
        assertNotNull(matchEntity.getId());
        storage.delete(matchEntity);
        storage.commit();
    }

//    @Test
//    public void testWriteAndRead() {
//
//        // This time write and read back
//        String overRideRole = "SuperUser";
//
//        // Write
//        Actor actor = new Actor();
//        Storage<Actor> storage = new Storage<Actor>(actor);
//        storage.beginTransaction();
//        actor.setRole(overRideRole);
//        assertNull(actor.getId());
//        storage.insert(actor);
//        assertNotNull(actor.getId());
//        Long id = actor.getId();
//        storage.commit();
//
//        // Read and verify
//        Actor actor2  = new Actor();
//        assertEquals(actor2.getRole(), Actor.DEFAULT_ROLE);
//        storage = new Storage<Actor>(actor2);
//        storage.beginTransaction();
//        actor2 = storage.getById(id);
//        assertEquals(actor2.getRole(), overRideRole);
//        storage.commit();
//    }
//
//
//    @Test
//    public void testUpdate() {
//
//        String overRideRole = "SuperUser";
//        String newOverrideRole = "GUEST";
//
//        // Insert an actor
//        Actor actor = new Actor();
//        Storage<Actor> storage = new Storage<Actor>(actor);
//        storage.beginTransaction();
//        actor.setRole(overRideRole);
//        assertNull(actor.getId());
//        storage.insert(actor);
//        assertNotNull(actor.getId());
//        Long id = actor.getId();
//        storage.commit();
//
//        // Read it back
//        Actor actor2  = new Actor();
//        assertEquals(actor2.getRole(), Actor.DEFAULT_ROLE);
//        storage.beginTransaction();
//        actor2 = storage.getById(id);
//        assertEquals(actor2.getRole(), overRideRole);
//
//        // Update it
//        actor2.setRole(newOverrideRole);
//        storage.update(actor2);
//        storage.commit();
//
//        // Read it again and verify update
//        storage.beginTransaction();
//        Actor actor3  = storage.getById(id);
//        assertEquals(actor3.getRole(), newOverrideRole);
//        storage.commit();
//    }
//
    @After
    public void emptyDatabase() {

        MatchEntity match = new MatchEntity();
        Storage<MatchEntity> storage = new Storage<MatchEntity>(match);


        // Delete an object
        storage.beginTransaction();
        long id = 1L;
        while (match != null) {
            match = storage.getById(id);
            if (match != null) {
                storage.delete(match);
            }
            id++;
        }
        storage.commit();
    }
}
