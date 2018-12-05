package il.ac.bgu.cs.bp.samplebpjsproject;

import il.ac.bgu.cs.bp.bpjs.model.eventselection.SimpleEventSelectionStrategy;
import lombok.Getter;
import lombok.Setter;

public class QLEventSelectionStrategy extends SimpleEventSelectionStrategy {

    @Getter @Setter
    private MazeState mazeState;

    public QLEventSelectionStrategy(long seed, MazeState mazeState) {
        super(seed);
        this.mazeState = mazeState;
    }
}
