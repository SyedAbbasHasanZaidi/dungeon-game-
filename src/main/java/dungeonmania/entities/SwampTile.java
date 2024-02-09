package dungeonmania.entities;

import dungeonmania.entities.enemies.Enemy;
import dungeonmania.entities.enemies.Mercenary;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class SwampTile extends Entity {
    private int delay;

    public SwampTile(int movementDelay, Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
        this.delay = movementDelay;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {

        if (entity instanceof Enemy) {
            if (entity instanceof Mercenary) {
                if (((Mercenary) entity).isAllied()) {
                    return;
                }
            }
            // make the entity stuck
            if (((Enemy) entity).isStuck() > 0) {
                return;
            } else {
                ((Enemy) entity).setStuck(delay);
            }
        }
    }

}
