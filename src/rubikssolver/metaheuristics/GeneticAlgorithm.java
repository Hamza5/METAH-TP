package rubikssolver.metaheuristics;

import rubikssolver.cube.RubiksCube;
import rubikssolver.cube.RubiksCubeMixer;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm extends MetaHeuristicAlgorithm {

    private int populationMaxSize = 100; // Taille de la population
    private float maxCrossbreedingRate; // Taux maximal du croisement
    private float maxMutationRate; // Taux maximal de la mutation
    private ArrayList<ArrayList<String>> population;

    public GeneticAlgorithm(RubiksCube initialCube, ArrayList<String> initialSolution, int iterations, int populationSize, float crossbreedingRate, float mutationRate) {
        super(initialCube, initialSolution, iterations);
        population = new ArrayList<>(populationSize);
        populationMaxSize = populationSize;
        maxCrossbreedingRate = crossbreedingRate;
        maxMutationRate = mutationRate;
    }

    @Override
    void doOperation() {
        for (int i = 0; i< populationMaxSize; i++) // Initialisation de la population
            population.add(getRandomSolution(solution.size()));
        Random random = new Random();
        int j;
        outer:for (j=0; j<maxIterations; j++){
            for (ArrayList<String> s : population) // Recherche de la solution
                if (quality(cube, s) == bestQuality){
                    solution = s;
                    break outer;
                }
            float crossbreedingRate = random.nextFloat();
            if (crossbreedingRate > maxCrossbreedingRate) crossbreedingRate = maxCrossbreedingRate;
            int size = ((int) (population.size() * crossbreedingRate));
            for (int i=0; i<size; i++) { // Appliquer le croisement
                ArrayList<String> firstSolution = population.get(random.nextInt(size)); // Sélection aléatoire
                ArrayList<String> secondSolution = population.get(random.nextInt(size)); // Sélection aléatoire
                population.add(crossbreed(firstSolution, secondSolution));
            }
            float mutationRate = random.nextFloat();
            if (mutationRate <= maxMutationRate){
                size = ((int) (population.size() * mutationRate));
                for (int i=0; i<size; i++) { // Appliquer les mutations
                    population.get(random.nextInt(population.size())).set(random.nextInt(solution.size()), RubiksCubeMixer.getMethodNames()[random.nextInt(RubiksCubeMixer.getMethodNames().length)]);
                }
            }
            for (int i = population.size(); i> populationMaxSize; i--) { // Remplacement aléatoire
                population.remove(random.nextInt(population.size()));
            }
        }
        currentIterations = j;
    }

    private ArrayList<String> crossbreed(ArrayList<String> first, ArrayList<String> second) {
        for (int i=first.size()/2; i<first.size(); i++){ // 50%-50%
            first.set(i, second.get(i));
        }
        return first;
    }

}
