package algorithms;

import cube.RubiksCube;

public class MisplacedSquaresSolvingAlgorithm extends AAsteriskSolvingAlgorithm {

    public MisplacedSquaresSolvingAlgorithm(RubiksCube cube, int limit) {
        super(cube, limit);
    }

    protected int heuristic(RubiksCube cube){
        int s = 0;
        for (char square : cube.getTopFace().toCharArray()) if (square != 'w') s++;
        for (char square : cube.getFrontFace().toCharArray()) if (square != 'b') s++;
        for (char square : cube.getLeftFace().toCharArray()) if (square != 'r') s++;
        for (char square : cube.getRightFace().toCharArray()) if (square != 'o') s++;
        for (char square : cube.getBackFace().toCharArray()) if (square != 'g') s++;
        for (char square : cube.getBottomFace().toCharArray()) if (square != 'y') s++;
        return s;
    }
}
