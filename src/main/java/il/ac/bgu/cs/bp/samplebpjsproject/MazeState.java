package il.ac.bgu.cs.bp.samplebpjsproject;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class MazeState {

    @Getter @Setter
    private int x;

    @Getter @Setter
    private int y;

    @Getter @Setter
    private Map<int[], Double> reward;

    @Getter @Setter
    private Map<MazeStateAction, Double> qTable;

    public MazeState() {
        this.reward = new HashMap<>();
    }

    @Override
    public String toString() {
        return "MazeState{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void insertReward(double r){
        int[] key = {x, y};
        reward.put(key, r);
    }
}
