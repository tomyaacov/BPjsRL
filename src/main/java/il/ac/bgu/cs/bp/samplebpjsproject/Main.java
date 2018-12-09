package il.ac.bgu.cs.bp.samplebpjsproject;

import il.ac.bgu.cs.bp.bpjs.execution.BProgramRunner;
import il.ac.bgu.cs.bp.bpjs.execution.listeners.PrintBProgramRunnerListener;
import il.ac.bgu.cs.bp.bpjs.model.SingleResourceBProgram;
import il.ac.bgu.cs.bp.bpjs.model.eventselection.SimpleEventSelectionStrategy;
import org.mozilla.javascript.Scriptable;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Learning parameters:
        double alpha = 0.5;
        double gamma = 0.9;
        double learning_iterations = 5000;

        MazeWorld mazeWorld = new MazeWorld(alpha, gamma);
        MazeEventListener mazeEventListener = new MazeEventListener(mazeWorld);



        for (int i = 0; i < learning_iterations; i++){
            final SingleResourceBProgram bprog = new SingleResourceBProgram("maze.js", "maze.js", new SimpleEventSelectionStrategy(i)){
                protected void setupProgramScope(Scriptable scope) {
                    putInGlobalScope("mazeWorld", mazeWorld);
                    super.setupProgramScope(scope);
                }
            };
            BProgramRunner rnr = new BProgramRunner(bprog);
            rnr.addListener( new PrintBProgramRunnerListener() );
            rnr.addListener( mazeEventListener );
            rnr.run();
        }



        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));
            out.writeObject(mazeWorld.getQTable());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final SingleResourceBProgram bprog = new SingleResourceBProgram("maze.js", "maze.js", new QLEventSelectionStrategy(mazeWorld)){
            protected void setupProgramScope(Scriptable scope) {
                putInGlobalScope("mazeWorld", mazeWorld);
                super.setupProgramScope(scope);
            }
        };
        BProgramRunner rnr = new BProgramRunner(bprog);
        rnr.addListener( new PrintBProgramRunnerListener() );
        rnr.addListener( mazeEventListener );
        rnr.run();

    }
}
