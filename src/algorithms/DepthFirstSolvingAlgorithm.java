package algorithms;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;

public class DepthFirstSolvingAlgorithm extends SolvingAlgorithm {

    private int maxDepth;
    private HashSet<String> visited;

    public DepthFirstSolvingAlgorithm(RubiksCube cube, int limit){
        super(cube);
        maxDepth = limit;
        visited = new HashSet<>();
    }

    @Override
    protected void doOperation() {
        solveByDepthFirst(initialCube, new ArrayList<String>());
    }

    private boolean solveByDepthFirst(RubiksCube cube, ArrayList<String> pastActions){
        if (cube.isSolved()){
            steps = pastActions;
            return true;
        }
        else if (pastActions.size() == maxDepth) return false;
        else {
            String[] methods = RubiksCubeMixer.getMethodNames();
            boolean found = false;
            for (int i=0; i<methods.length && !found; i++){
                try {
                    String action = methods[i];
                    String currentState = cube.getState();
                    visited.add(currentState);
                    pastActions.add(action);
                    RubiksCube newCube = new RubiksCube(currentState);
                    RubiksCube.class.getMethod(action).invoke(newCube);
                    if (!visited.contains(newCube.getState())){
                        found = solveByDepthFirst(newCube, pastActions);
                    }
                    visited.remove(currentState);
                    if (!found) pastActions.remove(pastActions.size()-1);
                } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
                    e.printStackTrace(); // Should never happen
                }
            }
            return found;
        }
    }

}
