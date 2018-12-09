package il.ac.bgu.cs.bp.samplebpjsproject;

import lombok.Getter;
import lombok.Setter;

public class MazeState {

    @Getter @Setter
    private int x;

    @Getter @Setter
    private int y;

    public MazeState() {
    }

    public MazeState(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "MazeState{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        MazeState mObj = (MazeState)obj;
        return mObj.getX() == x && mObj.getY() == y;
    }
}
