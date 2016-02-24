package algorithms;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class RubiksCubeMixer {

    private final RubiksCube cube;
    private static final String[] methodNames = new String[]{"up","upInverted","left","leftInverted","right","rightInverted","back","backInverted","down","downInverted"};

    public RubiksCubeMixer(RubiksCube originalCube){
        cube = originalCube;
    }

    public void mixCube(int rotations){
        Random randomGenerator = new Random();
        for (int i=0; i<rotations; i++){
            try {
                cube.getClass().getMethod(methodNames[randomGenerator.nextInt(rotations)]).invoke(cube);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException  e) { // Should never happen
                e.printStackTrace();
            }
        }
    }

    public static String[] getMethodNames(){
        return methodNames;
    }

    public RubiksCube getCube(){
        return cube;
    }
}
