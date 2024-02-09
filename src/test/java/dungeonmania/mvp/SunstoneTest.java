package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SunstoneTest {
    @Test
    @Tag("4-1")
    @DisplayName("Test player can pick up Sunstone and add it into inventory")
    public void pickupSunStone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_SunstoneTest_pickupSunstone", "c_SunstoneTest_pickupSunstone");

        assertEquals(1, TestUtils.getEntities(res, "sunstone").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // pick up sunstone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(0, TestUtils.getEntities(res, "sunstone").size());
    }

    @Test
    @Tag("4-3")
    @DisplayName("Test player can use a Sunstone to open and walk through a door")
    public void useSunstoneWalkThroughOpenDoor() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_SunstoneTest_useSunstoneWalkThroughOpenDoor",
                "c_SunstoneTest_useSunstoneWalkThroughOpenDoor");
        //assuming no sunstone upon creation
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());
        // pick up sunstone
        res = dmc.tick(Direction.RIGHT);
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        // walk through door and check sunstone exists in inventory.
        res = dmc.tick(Direction.LEFT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @Tag("4-4")
    @DisplayName("Test player can pickup two sunstones at the same time")
    public void canPickupTwoSunstones() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_SunstoneTest_canPickupTwoSunstone", "c_SunstoneTest_canPickupTwoSunstone");

        // System.out.println(TestUtils.getEntities(res, "sunstone").size());
        assertEquals(2, TestUtils.getEntities(res, "sunstone").size());

        // pick up Sunstone_1
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(1, TestUtils.getEntities(res, "sunstone").size());

        // pick up Sunstone_2
        res = dmc.tick(Direction.DOWN);
        assertEquals(2, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(0, TestUtils.getEntities(res, "sunstone").size());

    }
}
