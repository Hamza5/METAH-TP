package algorithms;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

public class LargeurAlgorithm extends SolvingAlgorithm {

	static final String[] methodNames = new String[]{"up","upInverted","left","leftInverted","right","rightInverted","back","backInverted","down","downInverted","front","frontInverted"};
	 String initialCubeTester=new String(initialCube.getState());
	 
	 RubiksCube cube=initialCube;
	 public void rotateCube(int rotation){
	    try {
	    	cube.getClass().getMethod(methodNames[rotation]).invoke(cube);
         } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException  e) { // Should never happen
             e.printStackTrace();
         }
 }
  
	public LargeurAlgorithm(RubiksCube cube) {
		super(cube);
	}

    boolean exists(int rotations){
    	int [] rotationTable= new int[8];
    	int rotation = 0,nbrRotation=0;
    	
    	while(rotations!=0){
    		rotationTable[rotation++]= rotations & 0x000F;
    		rotations= rotations>>4;
    		nbrRotation++;
    	}
    	for(int i=0;i<nbrRotation-1;i++){
    		if((rotationTable[i]-1)%2==0){
    			if(rotationTable[i]==(rotationTable[i+1]-1)){
    				return false;
    			}
    		}else{
    			if(rotationTable[i]==rotationTable[i+1]+1){
    				return false;
    			}
    		}
    		int k=i;
    		while(rotationTable[k]-1==rotationTable[k+1]-1){k++;}
    		if((k-i)>3){return false;}
    	}
    	
		return false;
    }
    boolean ResulteState(int rotations){
    	int [] rotationTable= new int[8];
    	int rotation = 0,nbrRotation=0;
  
    	while(rotations!=0){
    		rotationTable[rotation++]= rotations & 0x000F;
    		rotations= rotations>>4;
    		nbrRotation++;
    	}
    	for(int i=0;i<nbrRotation;i++){
    		rotateCube(rotationTable[i]-1);
    		}
    	if(cube.equals("wwwwwwwwwbbbbbbbbbrrrrrrrrrooooooooogggggggggyyyyyyyyy")){ System.out.println("Before \n"+cube.getState());}
    	if(cube.isSolved()){
    		return true;
			}else{
				cube.setState(initialCubeTester);
				return false;}
		
    }
	protected void doOperation() {
		LinkedList<Integer> BigFile= new LinkedList<Integer>();
		for(int i=1;i <= 12;i++ ){
			BigFile.add(i);
		}
		boolean Trouvé= false;
		int currentState = 0;
		int nbr=0;
		while(!BigFile.isEmpty() && !Trouvé   ){
			currentState=BigFile.removeFirst();
			if(!exists(currentState)){
				if(ResulteState(currentState)){
					Trouvé=true;
				}else{	
					int TobeAdd;
					for(int i=1;i <= 12;i++ ){
						TobeAdd=currentState<<4 ;
						TobeAdd=TobeAdd + i ;
						BigFile.add(TobeAdd);
					}
				}
			}
		}
		System.out.println("Solution: ");
    	while(currentState!=0){
    		System.out.println(RubiksCubeMixer.methodNames[(currentState & 0x000F)-1]);
    		currentState= currentState>>4;
			}
	}

}
