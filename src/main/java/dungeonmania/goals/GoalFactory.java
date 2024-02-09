package dungeonmania.goals;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.goals.goalTypes.BoulderGoal;
import dungeonmania.goals.goalTypes.EnemyGoal;
import dungeonmania.goals.goalTypes.ExitGoal;
import dungeonmania.goals.goalTypes.TreasureGoal;
import dungeonmania.goals.operations.And;
import dungeonmania.goals.operations.Or;

public class GoalFactory {
    public static Goal createGoal(JSONObject jsonGoal, JSONObject config) {
        JSONArray subgoals;
        switch (jsonGoal.getString("goal")) {
        case "AND":
            subgoals = jsonGoal.getJSONArray("subgoals");
            return new And(createGoal(subgoals.getJSONObject(0), config),
                    createGoal(subgoals.getJSONObject(1), config));
        case "OR":
            subgoals = jsonGoal.getJSONArray("subgoals");
            return new Or(createGoal(subgoals.getJSONObject(0), config), createGoal(subgoals.getJSONObject(1), config));
        case "exit":
            return new ExitGoal();
        case "boulders":
            return new BoulderGoal();
        case "treasure":
            int treasureGoal = config.optInt("treasure_goal", 1);
            return new TreasureGoal(treasureGoal);
        case "enemies":
            int enemiesGoal = config.optInt("enemy_goal", 1);
            return new EnemyGoal(enemiesGoal);
        default:
            return null;
        }
    }
}
