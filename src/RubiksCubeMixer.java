import java.lang.reflect.InvocationTargetException;
import java.util.Random;

class RubiksCubeMixer {

    private final RubiksCube cube;
    private final String[] methodNames;

    RubiksCubeMixer(RubiksCube originalCube){
        cube = originalCube;
        methodNames = new String[]{"up","upInverted","left","leftInverted","right","rightInverted","back","backInverted","down","downInverted"};
    }

    RubiksCube mixCube(int rotations){
        Random randomGenerator = new Random();
        for (int i=0; i<rotations; i++){
            try {
                int methodNumber = randomGenerator.nextInt(rotations);
                System.out.println(methodNames[methodNumber]);
                cube.getClass().getMethod(methodNames[methodNumber]).invoke(cube);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException  e) { // Should never happen
                e.printStackTrace();
            }
        }
        return cube;
    }

    RubiksCube getCube(){
        return cube;
    }
}
