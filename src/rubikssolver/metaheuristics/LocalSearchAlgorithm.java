package rubikssolver.metaheuristics;

import rubikssolver.cube.RubiksCube;

import java.util.ArrayList;
import java.util.HashSet;

public class LocalSearchAlgorithm extends MetaHeuristicAlgorithm {

    private HashSet<ArrayList<String>> tabu;

    public LocalSearchAlgorithm(RubiksCube initialCube, ArrayList<String> initialSolution, int iterations) {
        super(initialCube, initialSolution, iterations);
        tabu = new HashSet<>();
    }

    @Override
    protected void doOperation() {
        int i;
        for (i = 0; i < maxIterations; i++){
            if (quality(cube, solution) == bestQuality) break; // Solution trouvée
            HashSet<ArrayList<String>> neighbors = getNeighbors(solution); // Tous les voisins
            HashSet<ArrayList<String>> nonTabuNeighbors = new HashSet<>(neighbors);
            nonTabuNeighbors.removeAll(tabu); // Supprimer les voisins qui sont dans la liste tabou
            if (!nonTabuNeighbors.isEmpty()){ // Intensifier
                solution = getBestSolution(cube, nonTabuNeighbors); // Recherche en voisinage en anneau
                tabu.add(solution);
            } else { // Diversifier
                ArrayList<String> tabuBestSolution = getBestSolution(cube, tabu);
                tabu.remove(tabuBestSolution);
                solution = tabuBestSolution;
            }
        }
        currentIterations = i;
        if (currentIterations == maxIterations) solution = getBestSolution(cube, tabu); // Retourner la meilleure solution si on a atteint le max
    }

}
