package il.ac.bgu.cs.bp.samplebpjsproject;

import il.ac.bgu.cs.bp.bpjs.execution.listeners.BProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.BEvent;
import il.ac.bgu.cs.bp.bpjs.model.BProgram;
import il.ac.bgu.cs.bp.bpjs.model.BThreadSyncSnapshot;
import il.ac.bgu.cs.bp.bpjs.model.FailedAssertion;
import lombok.Getter;
import lombok.Setter;

public class MazeEventListener implements BProgramRunnerListener {

    @Getter @Setter
    private MazeWorld mazeWorld;

    @Getter @Setter
    private MazeStateAction lastState;

    @Getter @Setter
    private String currAction;

    @Getter @Setter
    private boolean firstTime;

    public MazeEventListener(MazeWorld mazeWorld) {
        this.mazeWorld = mazeWorld;
        this.firstTime = true;

    }

    @Override
    public void starting(BProgram bProgram) {

    }

    @Override
    public void started(BProgram bProgram) {

    }

    @Override
    public void superstepDone(BProgram bProgram) {

    }

    @Override
    public void ended(BProgram bProgram) {

    }

    @Override
    public void assertionFailed(BProgram bProgram, FailedAssertion failedAssertion) {

    }

    @Override
    public void bthreadAdded(BProgram bProgram, BThreadSyncSnapshot bThreadSyncSnapshot) {

    }

    @Override
    public void bthreadRemoved(BProgram bProgram, BThreadSyncSnapshot bThreadSyncSnapshot) {

    }

    @Override
    public void bthreadDone(BProgram bProgram, BThreadSyncSnapshot bThreadSyncSnapshot) {

    }

    @Override
    public void eventSelected(BProgram bProgram, BEvent bEvent) {
        if (bEvent.getName().equals("updateDone")){
            System.out.println(mazeWorld);
        } else {
            currAction = bEvent.getName();
            if(firstTime){
                lastState = new MazeStateAction(mazeWorld.getX(), mazeWorld.getY(), currAction);
                firstTime = false;
            } else {
                mazeWorld.updateQTable(lastState, currAction);
                lastState = new MazeStateAction(mazeWorld.getX(), mazeWorld.getY(), currAction);
            }
        }
    }

    @Override
    public void halted(BProgram bProgram) {

    }
}
