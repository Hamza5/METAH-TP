package rubikssolver.algorithms;

import rubikssolver.cube.RubiksCube;
import rubikssolver.cube.RubiksCubeMixer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class HeuristicSolvingAlgorithm extends SolvingAlgorithm {

    int maxDepth;
    private HashMap<String, Integer> closed;
    private ArrayList<DepthFirstSolvingAlgorithm.StateAction> open;

    HeuristicSolvingAlgorithm(RubiksCube cube, int limit) {
        super(cube);
        maxDepth = limit;
        closed = new HashMap<>();
        open = new ArrayList<>();
    }

    @Override
    protected void doOperation() {
        try {
            final String[] methodNames = RubiksCubeMixer.getMethodNames();
            DepthFirstSolvingAlgorithm.StateAction sa = new DepthFirstSolvingAlgorithm.StateAction(null, initialCube.getState(), "");
            open.add(sa);
            while (!open.isEmpty() && !new RubiksCube(sa.state).isSolved()) {
                int max = heuristic(new RubiksCube(open.get(0).state));
                int maxIndex = 0;
                for (int i = 1; i < open.size(); i++) {
                    int h = heuristic(new RubiksCube(open.get(i).state));
                    if (h > max) {
                        max = h;
                        maxIndex = i;
                    }
                }
                sa = open.remove(maxIndex);
                if ((!closed.containsKey(sa.state) || closed.get(sa.state) > sa.depth) && sa.depth <= maxDepth) {
                    closed.put(sa.state, sa.depth);
                    for (String methodName : methodNames) {
                        try {
                            RubiksCube newCube = new RubiksCube(sa.state);
                            RubiksCube.class.getMethod(methodName).invoke(newCube);
                            open.add(new DepthFirstSolvingAlgorithm.StateAction(sa, newCube.getState(), methodName));
                        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace(); // Should never happen
                        }
                    }
                }
            }
            if (new RubiksCube(sa.state).isSolved())
                while (sa.parent != null) {
                    steps.add(0, sa.action);
                    sa = sa.parent;
                }
        } catch (OutOfMemoryError e) {
            System.err.println("Mémoire insuffisante ! impossible de continuer.");
        } finally {
            totalStatesCount = closed.size();
            abandonedStatesCount = totalStatesCount - steps.size();
        }
    }

    protected abstract int heuristic(RubiksCube cube);

}
