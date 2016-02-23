import algorithms.RubiksCube;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

class RubiksCubeMixer {

    private final RubiksCube cube;
    private final String[] methodNames;

    RubiksCubeMixer(RubiksCube originalCube){
        cube = originalCube;
        methodNames = new String[]{"up","upInverted","left","leftInverted","right","rightInverted","back","backInverted","down","downInverted"};
    }

    void mixCube(int rotations){
        Random randomGenerator = new Random();
        for (int i=0; i<rotations; i++){
            try {
                cube.getClass().getMethod(methodNames[randomGenerator.nextInt(rotations)]).invoke(cube);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException  e) { // Should never happen
                e.printStackTrace();
            }
        }
    }

    RubiksCube getCube(){
        return cube;
    }
}
