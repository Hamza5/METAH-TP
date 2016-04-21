package rubikssolver.metaheuristics;

import rubikssolver.cube.RubiksCube;

import java.util.ArrayList;
import java.util.HashSet;

public class LocalSearchAlgorithm extends MetaHeuristicAlgorithm {

    public LocalSearchAlgorithm(RubiksCube initialCube, ArrayList<String> initialSolution, int iterations) {
        super(initialCube, initialSolution, iterations);
    }

    @Override
    protected void doOperation() { // Recherche avec voisinage étoile
        int i;
        outer:for (i = 0; i < maxIterations; i++) {
            ArrayList<String> partialSolution = new ArrayList<>();
            for (int j=0; j<solution.size(); j++) {
                partialSolution.add(solution.get(j));
                if (quality(cube, partialSolution) == bestQuality) {
                    solution = partialSolution;
                    break outer;
                }
            }
            if (quality(cube, solution) == bestQuality) break;
            HashSet<ArrayList<String>> neighbors = getNeighbors(solution);
            ArrayList<String> bestNeighbor = getBestSolution(cube, neighbors);
            if (quality(cube, bestNeighbor) <= quality(cube ,solution)) break;
            else solution = bestNeighbor;
        }
        currentIterations = i+1;
    }

}
