package il.ac.bgu.cs.bp.samplebpjsproject;

import il.ac.bgu.cs.bp.bpjs.model.eventselection.SimpleEventSelectionStrategy;
import lombok.Getter;
import lombok.Setter;

public class QLEventSelectionStrategy extends SimpleEventSelectionStrategy {

    @Getter @Setter
    private MazeWorld mazeWorld;

    public QLEventSelectionStrategy(long seed, MazeWorld mazeWorld) {
        super(seed);
        this.mazeWorld = mazeWorld;
    }
}
