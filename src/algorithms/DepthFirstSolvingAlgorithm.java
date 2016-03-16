package algorithms;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Stack;

public class DepthFirstSolvingAlgorithm extends SolvingAlgorithm {

    static class StateAction {

        String state;
        String action;
        StateAction parent;
        int depth;

        StateAction(StateAction last ,String s, String a){
            state = s;
            action = a;
            depth = last == null ? 0 : last.depth + 1;
            parent = last;
        }

    }

    private int maxDepth;
    private HashMap<String, Integer> closed;
    private Stack<StateAction> open;

    public DepthFirstSolvingAlgorithm(RubiksCube cube, int limit){
        super(cube);
        maxDepth = limit;
        open = new Stack<>();
        closed = new HashMap<>();
    }

    @Override
    protected void doOperation() {
        try {
            final String[] methodNames = RubiksCubeMixer.getMethodNames();
            StateAction sa = new StateAction(null, initialCube.getState(), "");
            open.push(sa);
            while (!open.isEmpty() && !new RubiksCube(sa.state).isSolved()) {
                sa = open.pop();
                if ((!closed.containsKey(sa.state) || closed.get(sa.state) > sa.depth) && sa.depth <= maxDepth) {
                    closed.put(sa.state, sa.depth);
                    for (String methodName : methodNames) {
                        try {
                            RubiksCube newCube = new RubiksCube(sa.state);
                            RubiksCube.class.getMethod(methodName).invoke(newCube);
                            open.push(new StateAction(sa, newCube.getState(), methodName));
                        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace(); // Should never happen
                        }
                    }
                }
            }
            if (new RubiksCube(sa.state).isSolved())
                while (sa.parent != null){
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

}
