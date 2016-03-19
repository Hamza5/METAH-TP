package cube;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class RubiksCubeMixer {

    private final RubiksCube cube;
    public static final String[] methodNames = new String[]{"up","upInverted","left","leftInverted","right","rightInverted","back","backInverted","down","downInverted","front","frontInverted"};
    private ArrayList<String> actions;

    public RubiksCubeMixer(RubiksCube originalCube){
        cube = originalCube;
        actions = new ArrayList<>();
    }

    public void mixCube(int rotations){
        Random randomGenerator = new Random();
        for(int i=0; i<rotations; i++){
        	try {
                String methodName = methodNames[randomGenerator.nextInt(methodNames.length)];
                actions.add(methodName);
        		cube.getClass().getMethod(methodName).invoke(cube);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException  e) { // Should never happen
                e.printStackTrace();
            }
        }
        
    }

    public void rotateCube(int rotation){
          try {
              cube.getClass().getMethod(methodNames[rotation]).invoke(cube);
          } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException  e) { // Should never happen
              e.printStackTrace();
          }
      
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public static String[] getMethodNames(){
        return methodNames;
    }

}
