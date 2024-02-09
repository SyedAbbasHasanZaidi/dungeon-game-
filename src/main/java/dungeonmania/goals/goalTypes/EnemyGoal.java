package dungeonmania.goals.goalTypes;

import dungeonmania.Game;
import dungeonmania.entities.enemies.ZombieToastSpawner;
import dungeonmania.goals.Goal;

public class EnemyGoal extends Goal {
    private int enemyGoal;

    public EnemyGoal(int enemyGoal) {
        this.enemyGoal = enemyGoal;
        return;
    }

    public boolean achieved(Game game) {
        int spawnerCount = game.getMap().getEntities(ZombieToastSpawner.class).size();
        return (game.getPlayer().getSlain() >= enemyGoal) && (spawnerCount == 0);
    }

    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return ":enemies";
    }
}
