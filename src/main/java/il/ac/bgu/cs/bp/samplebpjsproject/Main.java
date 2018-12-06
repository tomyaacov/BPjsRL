package il.ac.bgu.cs.bp.samplebpjsproject;

import il.ac.bgu.cs.bp.bpjs.execution.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.SingleResourceBProgram;
import il.ac.bgu.cs.bp.bpjs.model.eventselection.SimpleEventSelectionStrategy;
import org.mozilla.javascript.Scriptable;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MazeWorld mazeWorld = new MazeWorld(0.5, 0.5);
        MazeEventListener mazeEventListener = new MazeEventListener(mazeWorld);
        // This will load the program file  <Project>/src/main/resources/HelloBPjsWorld.js
        final SingleResourceBProgram bprog = new SingleResourceBProgram("maze.js", "maze.js", new SimpleEventSelectionStrategy()){
            protected void setupProgramScope(Scriptable scope) {
                putInGlobalScope("mazeWorld", mazeWorld);
                super.setupProgramScope(scope);
            }
        };
        BProgramRunner rnr = new BProgramRunner(bprog);

        // Print program events to the console
        rnr.addListener( new PrintBProgramRunnerListener() );
        rnr.addListener( mazeEventListener );

        // go!
        rnr.run();
        System.out.println(mazeWorld.getQTable());
    }
}
