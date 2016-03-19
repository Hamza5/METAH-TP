package algorithms;

import cube.RubiksCube;
import cube.RubiksCubeMixer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class LargeurAlgorithm extends SolvingAlgorithm {

	static final String[] methodNames = new String[]{"up","upInverted","left","leftInverted","right","rightInverted","back","backInverted","down","downInverted","front","frontInverted"};
	 String initialCubeTester=initialCube.getState();
	 int etatsGenerés,etatsabondoné,niveau=0; 
	 RubiksCube cube=initialCube;
	 
	 private static int lastPercent;
	 
	 public static void updatePercentageBar(float progress) {
	     int percent = Math.round(progress * 100);
	     if (Math.abs(percent - lastPercent) >= 1) {
	         StringBuilder template = new StringBuilder("\r[");
	         for (int i = 0; i < 50; i++) {
	             if (i < percent * .5) {
	                 template.append("=");
	             } else if (i == percent * .5) {
	                 template.append(">");
	             } else {
	                 template.append(" ");
	             }
	         }
	         template.append("] %s   ");
	         if (percent >= 100) {
	             template.append("%n");
	         }
	         System.out.printf(template.toString(), percent + "%");
	         lastPercent = percent;
	     }
	 }
	 
	 
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


    		 boolean exists(long rotations){
    		    	long [] rotationTable= new long[8];
    		    	int rotation = 0,nbrRotation=0;
    		    	
    		    	while(rotations!=0){
    		    		rotationTable[rotation++]= rotations & 0x000F;
    		    		rotations= rotations>>4;
    		    		nbrRotation++;
    		    	}
    		    	for(int i=0;i<nbrRotation-1;i++){
    		    		if((rotationTable[i]-1)%2==0){
    		    			if(rotationTable[i]==(rotationTable[i+1]-1)){
    		    				etatsabondoné++;return false;
    		    			}
    		    		}else{
    		    			if(rotationTable[i]==rotationTable[i+1]+1){
    		    				etatsabondoné++;return false;
    		    			}
    		    		}
    		    		int k=i;
    		    		while(rotationTable[k]-1==rotationTable[k+1]-1){k++;}
    		    		if((k-i)>3){etatsabondoné++;return false;}
    		    	}
    		    	
    				return false;
    		    }
   
    boolean ResulteState(long rotations){
    	long [] rotationTable= new long[8];
    	int rotation = 0,nbrRotation=0;
  
    	while(rotations!=0){
    		rotationTable[rotation++]= rotations & 0x000F;
    		rotations= rotations>>4;
    		nbrRotation++;
    	}
    	for(int i=0;i<nbrRotation;i++){
    		rotateCube((int)(rotationTable[i]-1));
    		}
    	if(cube.isSolved()){
    		return true;
			}else{
				cube.setState(initialCubeTester);
				return false;}
		
    }
    
	protected void doOperation() {
	     try {
		ArrayList<Long> BigFile= new ArrayList<>();
		for(long i=1;i <= 12;i++ ){
			BigFile.add(i);
		}
		niveau++;
		boolean Trouvé= false;
		long currentState = 0;
		int niveuCompteur=0;
		int indiceNiveau=12;
		float currentPourcentage=0;
		while(!BigFile.isEmpty() && !Trouvé ){
			niveuCompteur++;
			currentState=BigFile.remove(0);
			if(!exists(currentState)){
				if(ResulteState(currentState)){	Trouvé=true;
				}else{	
					long TobeAdd;
					etatsGenerés++;
					if(niveuCompteur>indiceNiveau){
						niveau++;
						indiceNiveau=BigFile.size();
						niveuCompteur=0;
						System.out.println("\nniveau "+niveau);
					}
					if((int)( niveuCompteur/(float)(indiceNiveau+1)*100) !=(int) currentPourcentage)
					{
						updatePercentageBar((niveuCompteur/(float)(indiceNiveau)));
					
					//System.out.print((int) (niveuCompteur/(float)(indiceNiveau)*100)+"% ");
					currentPourcentage=niveuCompteur/(float)(indiceNiveau)*100;}
					for(int i=1;i <= 12;i++ ){
						TobeAdd=currentState<<4 ;
						TobeAdd=TobeAdd + i ;
						BigFile.add(TobeAdd);
					}
				}
			}
		}
    	while(currentState!=0){
    		steps.add(RubiksCubeMixer.methodNames[(int)(currentState & 0x0000000F)-1]);
    		currentState= currentState>>4;
			}
    	BigFile.clear();
	     } catch (OutOfMemoryError e) {
	            System.err.println("Mémoire insuffisante ! impossible de continuer.");
	        } finally {
	        	totalStatesCount=etatsGenerés;
	        	abandonedStatesCount=etatsabondoné;
	        	System.out.println("\nniveau "+niveau);
	        }
    	
	}

}
