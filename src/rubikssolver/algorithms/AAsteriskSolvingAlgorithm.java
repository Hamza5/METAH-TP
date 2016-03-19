package algorithms;

import cube.RubiksCube;
import cube.RubiksCubeMixer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class AAsteriskSolvingAlgorithm extends SolvingAlgorithm {

    int maxDepth;
    private HashMap<String, Integer> closed;
    private ArrayList<DepthFirstSolvingAlgorithm.StateAction> open;

    AAsteriskSolvingAlgorithm(RubiksCube cube, int limit) {
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
                if (isInterrupted()) throw new InterruptedException();
                int min = heuristic(new RubiksCube(open.get(0).state));
                int minIndex = 0;
                for (int i = 1; i < open.size(); i++) {
                    DepthFirstSolvingAlgorithm.StateAction selectedSA = open.get(i);
                    int f = heuristic(new RubiksCube(selectedSA.state))+selectedSA.depth;
                    if (f < min) {
                        min = f;
                        minIndex = i;
                    }
                }
                sa = open.remove(minIndex);
                if ((!closed.containsKey(sa.state) || closed.get(sa.state) > sa.depth) && sa.depth < maxDepth) {
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
        } catch (InterruptedException e) {
            System.out.println("Recherche interrompue !");
        } finally {
            totalStatesCount = closed.size();
            abandonedStatesCount = totalStatesCount - steps.size();
        }
    }

    protected abstract int heuristic(RubiksCube cube);

}
