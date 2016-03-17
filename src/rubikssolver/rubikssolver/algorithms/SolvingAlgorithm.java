package rubikssolver.algorithms;

import rubikssolver.cube.RubiksCube;

import java.util.ArrayList;

public abstract class SolvingAlgorithm extends Thread {

    protected final RubiksCube initialCube;
    protected ArrayList<String> steps;
    private long startTime;
    private long endTime;
    protected long totalStatesCount;
    protected long abandonedStatesCount;

    SolvingAlgorithm(RubiksCube cube){
        initialCube = cube;
        steps = new ArrayList<>();
        totalStatesCount = 0;
        abandonedStatesCount = 0;
    }

    @Override
    public void run(){
        doBefore();
        doOperation();
        doAfter();
    }

    protected abstract void doOperation();

    private void doBefore(){
        startTime = System.currentTimeMillis();
    }

    private void doAfter(){
        endTime = System.currentTimeMillis();
    }

    public double getExecutionTime(){
        return (endTime - startTime)/1000.;
    }

    public ArrayList<String> getSteps(){
        return steps;
    }

    public long getTotalStatesCount() {
        return totalStatesCount;
    }

    public long getAbandonedStatesCount() {
        return abandonedStatesCount;
    }

}
