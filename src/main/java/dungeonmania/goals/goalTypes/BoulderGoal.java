package dungeonmania.goals.goalTypes;

import dungeonmania.Game;
import dungeonmania.entities.Switch;
import dungeonmania.goals.Goal;

public class BoulderGoal extends Goal {
    public BoulderGoal() {
        return;
    }

    public boolean achieved(Game game) {
        return game.getMap().getEntities(Switch.class).stream().allMatch(s -> s.isActivated());
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return ":boulders";
    }

}
