package rubikssolver.algorithms;

import rubikssolver.cube.RubiksCube;

public class MisplacedSquaresSolvingAlgorithm extends AAsteriskSolvingAlgorithm {

    public MisplacedSquaresSolvingAlgorithm(RubiksCube cube, int limit) {
        super(cube, limit);
    }

    protected int heuristic(RubiksCube cube){
        int s = 0;
        char[] topFace = cube.getTopFace().toCharArray();
        char[] frontFace = cube.getFrontFace().toCharArray();
        char[] leftFace = cube.getLeftFace().toCharArray();
        char[] rightFace = cube.getRightFace().toCharArray();
        char[] backFace = cube.getBackFace().toCharArray();
        char[] bottomFace = cube.getBottomFace().toCharArray();
        for (char square : topFace) if (square != topFace[4]) s++;
        for (char square : frontFace) if (square != frontFace[4]) s++;
        for (char square : leftFace) if (square != leftFace[4]) s++;
        for (char square : rightFace) if (square != rightFace[4]) s++;
        for (char square : backFace) if (square != backFace[4]) s++;
        for (char square : bottomFace) if (square != bottomFace[4]) s++;
        return s;
    }
}
