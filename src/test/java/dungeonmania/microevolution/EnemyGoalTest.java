package dungeonmania.microevolution;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class EnemyGoalTest {

    // Pass Test 1: Create a EnemyGoal and check existence see on response
    @Test
    @DisplayName("Create a EnemyGoal and check existence see on response")
    public void testCreateEnemyGoal() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("enemyGoal/d_enemyGoalTest_testPass", "enemyGoal/c_enemyGoalTest_testPass");
        assertTrue(TestUtils.getGoals(res).contains("enemies"));
    }

    // Pass Test 2: Create a EnemyGoal and target is reached and no more spawners
    @Test
    @DisplayName("Create a EnemyGoal and target is reached and no more spawners")
    public void testPassEnemyGoal() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        // map must have the correct number of enemies and spawners
        DungeonResponse res = dmc.newGame("enemyGoal/d_enemyGoalTest_testPass", "enemyGoal/c_enemyGoalTest_testPass");
        assertEquals(1, TestUtils.getEntities(res, "zombie_toast_spawner").size());
        String spawnerId = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();
        assertTrue(TestUtils.countEntityOfType(res.getEntities(), "zombie") > 0);

        // Zombie slain
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        // cardinally adjacent: true, has sword: true thus has destroyed
        res = assertDoesNotThrow(() -> dmc.interact(spawnerId));
        assertEquals(0, TestUtils.countType(res, "zombie_toast_spawner"));

        // no goals left
        assertEquals("", TestUtils.getGoals(res));
    }

    // Pass Test 3: Create a EnemyGoal and No enemies or spawners on creating with target being 0
    @Test
    @DisplayName("Create a EnemyGoal and no enemies or spawners on creating with target equal to 0")
    public void testNoEnemiesAndSpawners() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        // map must have the correct number of enemies and spawners
        DungeonResponse res = dmc.newGame("enemyGoal/d_enemyGoalTest_goalZero", "enemyGoal/c_enemyGoalTest_goalZero");
        assertEquals(0, TestUtils.countType(res, "zombie_toast_spawner"));
        assertEquals(0, TestUtils.countEntityOfType(res.getEntities(), "zombie"));

        assertEquals("", TestUtils.getGoals(res));
        return;
    }

    @Test
    @DisplayName("Not enough enemies killed and spawners exist")
    public void testNotEnoughEnemiesAndSpawnersExist() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        // map must have the correct number of enemies and spawners
        DungeonResponse res = dmc.newGame("enemyGoal/d_enemyGoalTest_notEnoughEnemiesSpawnerExist",
                "enemyGoal/c_enemyGoalTest_notEnoughEnemiesSpawnerExist");
        dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);

        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "zombie_toast"));
        assertEquals(1, TestUtils.countType(res, "zombie_toast_spawner"));
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        return;
    }

    @Test
    @DisplayName("Not enough enemies killed")
    public void testNotEnoughEnemiesKilled() {

        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        // map must have the correct number of enemies and spawners
        DungeonResponse res = dmc.newGame("enemyGoal/d_enemyGoalTest_notEnoughEnemiesKilled",
                "enemyGoal/c_enemyGoalTest_notEnoughEnemiesKilled");
        dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);

        assertEquals(2, TestUtils.countEntityOfType(res.getEntities(), "zombie_toast"));
        assertEquals(0, TestUtils.countType(res, "zombie_toast_spawner"));
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        return;
    }

    @Test
    @DisplayName("Spawners still exist")
    public void testSpawnersExist() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        // map must have the correct number of enemies and spawners
        DungeonResponse res = dmc.newGame("enemyGoal/d_enemyGoalTest_testPass", "enemyGoal/c_enemyGoalTest_testPass");
        assertEquals(1, TestUtils.getEntities(res, "zombie_toast_spawner").size());
        TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();
        assertTrue(TestUtils.countEntityOfType(res.getEntities(), "zombie") > 0);

        // Zombie slain
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        // cardinally adjacent: true, has sword: true thus has destroyed
        assertEquals(0, TestUtils.countEntityOfType(res.getEntities(), "zombie_toast"));
        assertEquals(1, TestUtils.countType(res, "zombie_toast_spawner"));

        // no goals left
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
    }

}
