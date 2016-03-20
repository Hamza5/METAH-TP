package rubikssolver.algorithms;

import rubikssolver.cube.RubiksCube;

public class ManhattanDistanceSolvingAlgorithm extends AAsteriskSolvingAlgorithm {

    private static final class Cubie {
        char top;
        char front;
        char left;
        char right;
        char back;
        char bottom;
        Cubie(){
            top = '\0';
            front = '\0';
            left = '\0';
            right = '\0';
            back = '\0';
            bottom = '\0';
        }
    }

    public ManhattanDistanceSolvingAlgorithm(RubiksCube cube, int limit) {
        super(cube, limit);
    }

    @Override
    protected int heuristic(RubiksCube cube) {
        String topFace = cube.getTopFace();
        String frontFace = cube.getFrontFace();
        String leftFace = cube.getLeftFace();
        String rightFace = cube.getRightFace();
        String backFace = cube.getBackFace();
        String bottomFace = cube.getBottomFace();
        Cubie topLeftBackCorner = new Cubie();
        topLeftBackCorner.top = topFace.charAt(0);
        topLeftBackCorner.left = leftFace.charAt(0);
        topLeftBackCorner.back = backFace.charAt(2);
        Cubie topBackEdge = new Cubie();
        topBackEdge.top = topFace.charAt(1);
        topBackEdge.back = backFace.charAt(1);
        Cubie topRightBackCorner = new Cubie();
        topRightBackCorner.top = topFace.charAt(2);
        topRightBackCorner.right = rightFace.charAt(2);
        topRightBackCorner.back = backFace.charAt(0);
        Cubie topLeftEdge = new Cubie();
        topLeftEdge.top = topFace.charAt(3);
        topLeftEdge.left = leftFace.charAt(1);
        Cubie topRightEdge = new Cubie();
        topRightEdge.top = topFace.charAt(5);
        topRightEdge.right = rightFace.charAt(1);
        Cubie topLeftFrontCorner = new Cubie();
        topLeftFrontCorner.top = topFace.charAt(6);
        topLeftFrontCorner.left = leftFace.charAt(2);
        topLeftFrontCorner.front = frontFace.charAt(0);
        Cubie topFrontEdge = new Cubie();
        topFrontEdge.top = topFace.charAt(7);
        topFrontEdge.front = frontFace.charAt(1);
        Cubie topRightFrontCorner = new Cubie();
        topRightFrontCorner.top = topFace.charAt(8);
        topRightFrontCorner.right = rightFace.charAt(0);
        topRightFrontCorner.front = frontFace.charAt(2);
        Cubie leftBackEdge = new Cubie();
        leftBackEdge.right = rightFace.charAt(3);
        leftBackEdge.back = backFace.charAt(5);
        Cubie rightBackEdge = new Cubie();
        rightBackEdge.right = rightFace.charAt(5);
        rightBackEdge.back = backFace.charAt(3);
        Cubie leftFrontEdge = new Cubie();
        leftFrontEdge.left = leftFace.charAt(5);
        leftFrontEdge.front = frontFace.charAt(3);
        Cubie rightFrontEdge = new Cubie();
        rightFrontEdge.right = rightFace.charAt(3);
        rightFrontEdge.front = frontFace.charAt(5);
        Cubie bottomLeftBackCorner = new Cubie();
        bottomLeftBackCorner.bottom = bottomFace.charAt(2);
        bottomLeftBackCorner.left = leftFace.charAt(6);
        bottomLeftBackCorner.back = backFace.charAt(8);
        Cubie bottomBackEdge = new Cubie();
        bottomBackEdge.bottom = bottomFace.charAt(1);
        bottomBackEdge.back = backFace.charAt(7);
        Cubie bottomRightBackCorner = new Cubie();
        bottomRightBackCorner.bottom = bottomFace.charAt(0);
        bottomRightBackCorner.right = rightFace.charAt(8);
        bottomRightBackCorner.back = backFace.charAt(6);
        Cubie bottomLeftEdge = new Cubie();
        bottomLeftEdge.bottom = bottomFace.charAt(5);
        bottomLeftEdge.left = leftFace.charAt(7);
        Cubie bottomRightEdge = new Cubie();
        bottomRightEdge.bottom = bottomFace.charAt(3);
        bottomRightEdge.right = rightFace.charAt(7);
        Cubie bottomLeftFrontCorner = new Cubie();
        bottomLeftFrontCorner.bottom = bottomFace.charAt(8);
        bottomLeftFrontCorner.left = leftFace.charAt(8);
        bottomLeftFrontCorner.front = frontFace.charAt(6);
        Cubie bottomFrontEdge = new Cubie();
        bottomFrontEdge.bottom = bottomFace.charAt(7);
        bottomFrontEdge.front = frontFace.charAt(7);
        Cubie bottomRightFrontCorner = new Cubie();
        bottomRightFrontCorner.bottom = bottomFace.charAt(6);
        bottomRightFrontCorner.right = rightFace.charAt(6);
        bottomRightFrontCorner.front = frontFace.charAt(8);
        Cubie[] corners = new Cubie[]{topLeftBackCorner, topRightBackCorner, topLeftFrontCorner, topRightFrontCorner, bottomLeftBackCorner, bottomRightBackCorner, bottomLeftFrontCorner, bottomRightFrontCorner};
        int cornersSum = 0;
        for (Cubie corner : corners){
            if (corner.top != '\0'){
                if (corner.top != topFace.charAt(4) && corner.top != bottomFace.charAt(4)) cornersSum++;
                else if (corner.top == bottomFace.charAt(4)) cornersSum += 2;
            }
            if (corner.front != '\0'){
                if (corner.front != frontFace.charAt(4) && corner.front != backFace.charAt(4)) cornersSum++;
                else if (corner.front == backFace.charAt(4)) cornersSum += 2;
            }
            if (corner.left != '\0'){
                if (corner.left != leftFace.charAt(4) && corner.left != leftFace.charAt(4)) cornersSum++;
                else if (corner.left == rightFace.charAt(4)) cornersSum += 2;
            }
            if (corner.right != '\0'){
                if (corner.right != rightFace.charAt(4) && corner.right != rightFace.charAt(4)) cornersSum++;
                else if (corner.right == leftFace.charAt(4)) cornersSum += 2;
            }
            if (corner.back != '\0'){
                if (corner.back != backFace.charAt(4) && corner.back != backFace.charAt(4)) cornersSum++;
                else if (corner.back == frontFace.charAt(4)) cornersSum += 2;
            }
            if (corner.bottom != '\0'){
                if (corner.bottom != bottomFace.charAt(4) && corner.bottom != bottomFace.charAt(4)) cornersSum++;
                else if (corner.bottom == topFace.charAt(4)) cornersSum += 2;
            }
            cornersSum--;
        }
        Cubie[] edges = new Cubie[]{topBackEdge, topLeftEdge, topFrontEdge, topRightEdge, leftBackEdge, leftFrontEdge, rightBackEdge, rightFrontEdge, bottomBackEdge, bottomLeftEdge, bottomRightEdge, bottomFrontEdge};
        int edgesSum = 0;
        for (Cubie edge : edges){
            if (edge.top == bottomFace.charAt(4) || edge.bottom == topFace.charAt(4) ||
                edge.left == rightFace.charAt(4) || edge.right == leftFace.charAt(4) ||
                edge.front == backFace.charAt(4) || edge.back == frontFace.charAt(4)) edgesSum += 2;
            else {
                if ((edge.top != '\0' && edge.top != topFace.charAt(4) && edge.top != bottomFace.charAt(4)) ||
                    (edge.bottom != '\0' && edge.bottom != bottomFace.charAt(4) && edge.bottom != topFace.charAt(4)) ||
                    (edge.left != '\0' && edge.left != leftFace.charAt(4) && edge.left != rightFace.charAt(4)) ||
                    (edge.right != '\0' && edge.right != rightFace.charAt(4) && edge.right != leftFace.charAt(4)) ||
                    (edge.front != '\0' && edge.front != frontFace.charAt(4) && edge.front != backFace.charAt(4)) ||
                    (edge.back != '\0' && edge.back != backFace.charAt(4) && edge.back != frontFace.charAt(4))) edgesSum++;
            }
        }
        return cornersSum+edgesSum;
    }
}
