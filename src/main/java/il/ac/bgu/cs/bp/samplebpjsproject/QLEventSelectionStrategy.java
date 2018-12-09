package il.ac.bgu.cs.bp.samplebpjsproject;

import il.ac.bgu.cs.bp.bpjs.model.BEvent;
import il.ac.bgu.cs.bp.bpjs.model.BSyncStatement;
import il.ac.bgu.cs.bp.bpjs.model.eventselection.EventSelectionResult;
import il.ac.bgu.cs.bp.bpjs.model.eventselection.EventSelectionStrategy;
import il.ac.bgu.cs.bp.bpjs.model.eventselection.SimpleEventSelectionStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class QLEventSelectionStrategy extends SimpleEventSelectionStrategy {

    @Getter @Setter
    private MazeWorld mazeWorld;

    public QLEventSelectionStrategy(MazeWorld mazeWorld) {
        this.mazeWorld = mazeWorld;
    }

    @Override
    public Optional<EventSelectionResult> select(Set<BSyncStatement> statements, List<BEvent> externalEvents, Set<BEvent> selectableEvents) {
        if ( selectableEvents.isEmpty() ) {
            return Optional.empty();
        } else {
            if (selectableEvents.size() == 1){
                return Optional.of( new EventSelectionResult(selectableEvents.iterator().next()));
            }
            double maxValue = -Double.MAX_VALUE;
            BEvent selectedEvent = null;
            for(BEvent event : selectableEvents){
                MazeStateAction mazeStateAction = new MazeStateAction(mazeWorld.getX(), mazeWorld.getY(), event.getName());
                if (mazeWorld.getQTable().get(mazeStateAction.toString()) > maxValue){
                    maxValue = mazeWorld.getQTable().get(mazeStateAction.toString());
                    selectedEvent = event;
                }
            }
            return Optional.of( new EventSelectionResult(selectedEvent));
        }
    }
}
