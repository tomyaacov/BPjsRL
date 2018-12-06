package il.ac.bgu.cs.bp.samplebpjsproject;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class MazeWorld {

    @Getter @Setter
    private MazeState mazeState;

    @Getter @Setter
    private Map<MazeState, Double> reward;

    @Getter @Setter
    private Map<MazeStateAction, Double> qTable;

    @Getter @Setter
    private double alpha;

    @Getter @Setter
    private double gamma;


    public MazeWorld(double alpha, double gamma) {
        this.reward = new HashMap<>();
        this.qTable = new HashMap<>();
        this.mazeState = new MazeState();
        this.alpha = alpha;
        this.gamma = gamma;
    }

    @Override
    public String toString() {
        return mazeState.toString();
    }

    public void insertReward(double r){
        reward.put(this.mazeState, r);
    }

    public void updateQTable(MazeStateAction lastState, String currAction){
        MazeState lastKey = new MazeState(lastState.getX(), lastState.getY());
        MazeStateAction currState = new MazeStateAction(mazeState.getX(), mazeState.getY(), currAction);
        if (!qTable.containsKey(lastState)){
            qTable.put(lastState, 0.0);
        }
        if (!qTable.containsKey(currState)){
            qTable.put(currState, 0.0);
        }
        System.out.println(lastKey);
        System.out.println(reward.get(lastKey));
        double suggestedValue = qTable.get(lastState) + alpha * (reward.get(lastKey) + gamma * (qTable.get(currState) - qTable.get(lastState)));
        if (suggestedValue > qTable.get(lastState)){
            qTable.put(lastState, suggestedValue);
        }
    }

    public int getX(){
        return mazeState.getX();
    }

    public int getY(){
        return mazeState.getY();
    }

    public void setX(int x){
        mazeState.setX(x);
    }

    public void setY(int y){
        mazeState.setY(y);
    }
}
