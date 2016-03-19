package algorithms;

import cube.RubiksCube;

public class MisplacedCubiesSolvingAlgorithme extends AAsteriskSolvingAlgorithm {

	public MisplacedCubiesSolvingAlgorithme(RubiksCube cube, int limit) {
		super(cube, limit);
	}
	String CubeState;
	String Test=new String();
	@Override
	protected int heuristic(RubiksCube cube) {
		int heuristique=52;
		StringBuilder changedState = new StringBuilder(cube.getState());
		
		if(changedState.charAt(0)=='w' && changedState.charAt(38)=='g' && changedState.charAt(27)=='r'){heuristique-=2;}
		if(changedState.charAt(0)=='r' && changedState.charAt(38)=='g' && changedState.charAt(27)=='w'){heuristique-=1;}
		if(changedState.charAt(0)=='w' && changedState.charAt(38)=='r' && changedState.charAt(27)=='g'){heuristique-=1;}
		if(changedState.charAt(0)=='r' && changedState.charAt(38)=='w' && changedState.charAt(27)=='g'){heuristique-=1;}
		if(changedState.charAt(0)=='g' && changedState.charAt(38)=='w' && changedState.charAt(27)=='r'){heuristique-=1;}
		if(changedState.charAt(0)=='g' && changedState.charAt(38)=='r' && changedState.charAt(27)=='w'){heuristique-=1;}
		
		if(changedState.charAt(2)=='w' && changedState.charAt(36)=='g' && changedState.charAt(29)=='o'){heuristique-=2;}
		if(changedState.charAt(2)=='o' && changedState.charAt(36)=='g' && changedState.charAt(29)=='w'){heuristique-=1;}
		if(changedState.charAt(2)=='w' && changedState.charAt(36)=='o' && changedState.charAt(29)=='g'){heuristique-=1;}
		if(changedState.charAt(2)=='o' && changedState.charAt(36)=='w' && changedState.charAt(29)=='g'){heuristique-=1;}
		if(changedState.charAt(2)=='g' && changedState.charAt(36)=='w' && changedState.charAt(29)=='o'){heuristique-=1;}
		if(changedState.charAt(2)=='g' && changedState.charAt(36)=='o' && changedState.charAt(29)=='w'){heuristique-=1;}
		
		if(changedState.charAt(6)=='w' && changedState.charAt(9)=='b' && changedState.charAt(20)=='r'){heuristique-=2;}
		if(changedState.charAt(6)=='r' && changedState.charAt(9)=='b' && changedState.charAt(20)=='w'){heuristique-=1;}
		if(changedState.charAt(6)=='w' && changedState.charAt(9)=='r' && changedState.charAt(20)=='b'){heuristique-=1;}
		if(changedState.charAt(6)=='r' && changedState.charAt(9)=='w' && changedState.charAt(20)=='b'){heuristique-=1;}
		if(changedState.charAt(6)=='b' && changedState.charAt(9)=='w' && changedState.charAt(20)=='r'){heuristique-=1;}
		if(changedState.charAt(6)=='b' && changedState.charAt(9)=='r' && changedState.charAt(20)=='w'){heuristique-=1;}
		
		if(changedState.charAt(6)=='w' && changedState.charAt(9)=='b' && changedState.charAt(20)=='o'){heuristique-=2;}
		if(changedState.charAt(6)=='o' && changedState.charAt(9)=='b' && changedState.charAt(20)=='w'){heuristique-=1;}
		if(changedState.charAt(6)=='w' && changedState.charAt(9)=='o' && changedState.charAt(20)=='b'){heuristique-=1;}
		if(changedState.charAt(6)=='o' && changedState.charAt(9)=='w' && changedState.charAt(20)=='b'){heuristique-=1;}
		if(changedState.charAt(6)=='b' && changedState.charAt(9)=='w' && changedState.charAt(20)=='o'){heuristique-=1;}
		if(changedState.charAt(6)=='b' && changedState.charAt(9)=='o' && changedState.charAt(20)=='w'){heuristique-=1;}
		
		if(changedState.charAt(15)=='b' && changedState.charAt(26)=='r' && changedState.charAt(53)=='y'){heuristique-=2;}
		if(changedState.charAt(15)=='y' && changedState.charAt(26)=='r' && changedState.charAt(53)=='b'){heuristique-=1;}
		if(changedState.charAt(15)=='b' && changedState.charAt(26)=='y' && changedState.charAt(53)=='r'){heuristique-=1;}
		if(changedState.charAt(15)=='y' && changedState.charAt(26)=='b' && changedState.charAt(53)=='r'){heuristique-=1;}
		if(changedState.charAt(15)=='r' && changedState.charAt(26)=='b' && changedState.charAt(53)=='y'){heuristique-=1;}
		if(changedState.charAt(15)=='r' && changedState.charAt(26)=='y' && changedState.charAt(53)=='b'){heuristique-=1;}
		
		if(changedState.charAt(17)=='b' && changedState.charAt(33)=='o' && changedState.charAt(51)=='y'){heuristique-=2;}
		if(changedState.charAt(17)=='y' && changedState.charAt(33)=='o' && changedState.charAt(51)=='b'){heuristique-=1;}
		if(changedState.charAt(17)=='b' && changedState.charAt(33)=='y' && changedState.charAt(51)=='o'){heuristique-=1;}
		if(changedState.charAt(17)=='y' && changedState.charAt(33)=='b' && changedState.charAt(51)=='o'){heuristique-=1;}
		if(changedState.charAt(17)=='o' && changedState.charAt(33)=='b' && changedState.charAt(51)=='y'){heuristique-=1;}
		if(changedState.charAt(17)=='o' && changedState.charAt(33)=='y' && changedState.charAt(51)=='b'){heuristique-=1;}
		
		if(changedState.charAt(24)=='r' && changedState.charAt(44)=='g' && changedState.charAt(47)=='y'){heuristique-=2;}
		if(changedState.charAt(24)=='y' && changedState.charAt(44)=='g' && changedState.charAt(47)=='r'){heuristique-=1;}
		if(changedState.charAt(24)=='r' && changedState.charAt(44)=='y' && changedState.charAt(47)=='g'){heuristique-=1;}
		if(changedState.charAt(24)=='y' && changedState.charAt(44)=='r' && changedState.charAt(47)=='g'){heuristique-=1;}
		if(changedState.charAt(24)=='g' && changedState.charAt(44)=='r' && changedState.charAt(47)=='y'){heuristique-=1;}
		if(changedState.charAt(24)=='g' && changedState.charAt(44)=='y' && changedState.charAt(47)=='r'){heuristique-=1;}
		
		if(changedState.charAt(42)=='g' && changedState.charAt(35)=='o' && changedState.charAt(45)=='y'){heuristique-=2;}
		if(changedState.charAt(42)=='y' && changedState.charAt(35)=='o' && changedState.charAt(45)=='g'){heuristique-=1;}
		if(changedState.charAt(42)=='g' && changedState.charAt(35)=='y' && changedState.charAt(45)=='o'){heuristique-=1;}
		if(changedState.charAt(42)=='y' && changedState.charAt(35)=='g' && changedState.charAt(45)=='o'){heuristique-=1;}
		if(changedState.charAt(42)=='o' && changedState.charAt(35)=='g' && changedState.charAt(45)=='y'){heuristique-=1;}
		if(changedState.charAt(42)=='o' && changedState.charAt(35)=='y' && changedState.charAt(45)=='g'){heuristique-=1;}
		
		if(changedState.charAt(1)=='w' && changedState.charAt(37)=='g'){heuristique-=2;}
		if(changedState.charAt(1)=='g' && changedState.charAt(37)=='w'){heuristique-=1;}
		
		if(changedState.charAt(3)=='w' && changedState.charAt(19)=='r'){heuristique-=2;}
		if(changedState.charAt(3)=='r' && changedState.charAt(19)=='w'){heuristique-=1;}
		
		if(changedState.charAt(7)=='w' && changedState.charAt(10)=='b'){heuristique-=2;}
		if(changedState.charAt(7)=='b' && changedState.charAt(10)=='w'){heuristique-=1;}
		
		if(changedState.charAt(5)=='w' && changedState.charAt(28)=='o'){heuristique-=2;}
		if(changedState.charAt(5)=='o' && changedState.charAt(28)=='w'){heuristique-=1;}
		
		
		
		if(changedState.charAt(12)=='b' && changedState.charAt(23)=='r'){heuristique-=2;}
		if(changedState.charAt(12)=='r' && changedState.charAt(23)=='b'){heuristique-=1;}
		
		if(changedState.charAt(14)=='b' && changedState.charAt(30)=='o'){heuristique-=2;}
		if(changedState.charAt(14)=='o' && changedState.charAt(30)=='b'){heuristique-=1;}
		
		if(changedState.charAt(32)=='o' && changedState.charAt(39)=='g'){heuristique-=2;}
		if(changedState.charAt(32)=='g' && changedState.charAt(39)=='o'){heuristique-=1;}
		
		if(changedState.charAt(41)=='g' && changedState.charAt(21)=='r'){heuristique-=2;}
		if(changedState.charAt(41)=='r' && changedState.charAt(21)=='g'){heuristique-=1;}
		
		
		
		if(changedState.charAt(16)=='b' && changedState.charAt(52)=='y'){heuristique-=2;}
		if(changedState.charAt(16)=='y' && changedState.charAt(52)=='b'){heuristique-=1;}
		
		if(changedState.charAt(25)=='r' && changedState.charAt(50)=='y'){heuristique-=2;}
		if(changedState.charAt(25)=='y' && changedState.charAt(50)=='r'){heuristique-=1;}
		
		if(changedState.charAt(43)=='g' && changedState.charAt(46)=='y'){heuristique-=2;}
		if(changedState.charAt(43)=='y' && changedState.charAt(46)=='g'){heuristique-=1;}
		
		if(changedState.charAt(34)=='o' && changedState.charAt(48)=='y'){heuristique-=2;}
		if(changedState.charAt(34)=='y' && changedState.charAt(48)=='o'){heuristique-=1;}
		
		return heuristique;
	}

}
