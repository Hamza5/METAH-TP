package algorithms;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class RubiksCubeMixer {

    private final RubiksCube cube;
    static final String[] methodNames = new String[]{"up","upInverted","left","leftInverted","right","rightInverted","back","backInverted","down","downInverted","front","frontInverted"};

    public RubiksCubeMixer(RubiksCube originalCube){
        cube = originalCube;
    }

    public void mixCube(int rotations){
        Random randomGenerator = new Random();
        for(int i=0; i<rotations; i++){
        	try {            	
        		cube.getClass().getMethod(methodNames[randomGenerator.nextInt(methodNames.length)]).invoke(cube);
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

    public static String[] getMethodNames(){
        return methodNames;
    }

    public RubiksCube getCube(){
        return cube;
    }
}
