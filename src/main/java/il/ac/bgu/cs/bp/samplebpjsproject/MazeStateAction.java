package il.ac.bgu.cs.bp.samplebpjsproject;

import lombok.Getter;
import lombok.Setter;

public class MazeStateAction {

    @Getter @Setter
    private int x;

    @Getter @Setter
    private int y;

    @Getter @Setter
    private String action;

    public MazeStateAction(int x, int y, String action) {
        this.x = x;
        this.y = y;
        this.action = action;
    }

    @Override
    public boolean equals(Object obj) {
        MazeStateAction qObj = (MazeStateAction)obj;
        return qObj.getX() == x && qObj.getY() == y && qObj.getAction().equals(action);
    }

    @Override
    public String toString() {
        return "MazeStateAction{" +
                "x=" + x +
                ", y=" + y +
                ", action='" + action + '\'' +
                '}';
    }
}
