package il.ac.bgu.cs.bp.samplebpjsproject;


import lombok.Getter;
import lombok.Setter;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MazeWorld {

    @Getter @Setter
    private MazeState mazeState;

    @Getter @Setter
    private Map<String, Double> reward;

    @Getter @Setter
    private Map<String, Double> qTable;

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
        reward.put(this.mazeState.toString(), r);
    }

    public void updateQTable(MazeStateAction lastState, String currAction){
        MazeState lastKey = new MazeState(lastState.getX(), lastState.getY());
        MazeStateAction currState = new MazeStateAction(mazeState.getX(), mazeState.getY(), currAction);
        if (!qTable.containsKey(lastState.toString())){
            qTable.put(lastState.toString(), 0.0);
        }
        if (!qTable.containsKey(currState.toString())){
            qTable.put(currState.toString(), 0.0);
        }
        if (!(lastState.getX()==currState.getX() && lastState.getY()==currState.getY())){
            double suggestedValue = qTable.get(lastState.toString()) +
                    alpha * (reward.get(lastKey.toString()) + gamma * (qTable.get(currState.toString()) - qTable.get(lastState.toString())));
            if (suggestedValue > qTable.get(lastState.toString())){
                qTable.put(lastState.toString(), suggestedValue);
            }
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

//    public static void main(String[] args) throws InterruptedException {
//
//        try {
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"));
//            try {
//                Map<String, Double> qTable = (Map<String, Double>)in.readObject();
//                in.close();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
