package dungeonmania.mvp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SwampTest {

    // p0: can create a swamp tile
    @Test
    @DisplayName("can create a swamp tile")
    public void spawnTile() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTest_spawnTile", "c_swampTest_spawnTile");
        assertEquals(1, TestUtils.countEntityOfType(res.getEntities(), "swamp_tile"));
        return;
    }

    // p1: player walks over the swamp and nothing happens
    @Test
    @DisplayName("player walks over a swamp tile")
    public void playerWalksSwamp() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTest_playerWalks", "c_swampTest_playerWalks");
        EntityResponse actualPlayer = TestUtils.getPlayer(res).get();

        DungeonResponse res2 = dmc.tick(Direction.DOWN);
        actualPlayer = TestUtils.getPlayer(res2).get();

        DungeonResponse res3 = dmc.tick(Direction.DOWN);
        EntityResponse player = TestUtils.getPlayer(res3).get();

        // expected result
        EntityResponse expectedPlayer = new EntityResponse(player.getId(), player.getType(), new Position(0, 2), false);
        actualPlayer = TestUtils.getPlayer(res3).get();
        assertTrue(TestUtils.entityResponsesEqual(expectedPlayer, actualPlayer));
    }

    // p2: zombie walks over swamp and gets stuck
    @Test
    @DisplayName("zombie walks over test")
    public void zombieWalk() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_swampTest_zombieWalks", "c_swampTest_playerWalks");
        Position zomPosition = TestUtils.getEntities(res, "zombie_toast").get(0).getPosition();
        // TestUtils.getEntityAtPos(res, null, zomPosition);
        int j = 0;
        Position tilePosition = TestUtils.getEntities(res, "swamp_tile").get(0).getPosition();

        res = dmc.tick(Direction.LEFT);
        zomPosition = TestUtils.getEntities(res, "zombie_toast").get(0).getPosition();
        j++;

        res = dmc.tick(Direction.LEFT);
        zomPosition = TestUtils.getEntities(res, "zombie_toast").get(0).getPosition();
        j++;

        assertTrue(TestUtils.countType(res, "swamp_tile") == 1);
    }
    // p3: mercenary walks over and gets stuck

    // p4: allied mercernary walks over and nothing happens
}
