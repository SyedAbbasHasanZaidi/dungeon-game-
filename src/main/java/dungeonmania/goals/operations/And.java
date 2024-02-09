package dungeonmania.goals.operations;

import dungeonmania.Game;
import dungeonmania.goals.Goal;

public class And extends Goal {
    private Goal goalL;
    private Goal goalR;

    public And(Goal goalL, Goal goalR) {
        this.goalL = goalL;
        this.goalR = goalR;
    }

    public boolean achieved(Game game) {
        return goalL.achieved(game) && goalR.achieved(game);
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return "(" + goalL.toString(game) + " AND " + goalR.toString(game) + ")";
    }

}
