package algorithms;

import java.util.ArrayList;

abstract class SolvingAlgorithm extends Thread {

    protected final RubiksCube initialCube;
    protected ArrayList<String> steps;
    private float startTime;
    private float endTime;

    SolvingAlgorithm(RubiksCube cube){
        initialCube = cube;
    }

    @Override
    public void run(){
        doBefore();
        doOperation();
        doAfter();
    }

    protected abstract void doOperation();

    private void doBefore(){
        startTime = System.currentTimeMillis()/1000f;
    }

    private void doAfter(){
        endTime = System.currentTimeMillis()/1000f;
    }

    public float getExecutionTime(){
        return endTime - startTime;
    }

    public ArrayList getSteps(){
        return steps;
    }

}
