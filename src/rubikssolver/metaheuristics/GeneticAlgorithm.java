package rubikssolver.metaheuristics;

import rubikssolver.cube.*;

import java.util.*;

public class GeneticAlgorithm extends MetaHeuristicAlgorithm {

    private static final Random random = new Random();
    private int populationMaxSize; // Taille de la population TODO : taille kN², itérations kN
    private float maxCrossbreedingRate; // Taux maximal du croisement
    private float maxMutationRate; // Taux maximal de la mutate
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
        HashSet<ArrayList<String>> generatedSolutions = new HashSet<>(populationMaxSize);
        while (generatedSolutions.size() < populationMaxSize){ // Initialiser la population
            ArrayList<String> s = getRandomSolution(solution.size());
            generatedSolutions.add(s);
        }
        population = new ArrayList<>(generatedSolutions);
        int j;
        outer:for (j=0; j<maxIterations; j++){
            generatedSolutions.clear();
            float crossbreedingRate = random.nextFloat();
            if (crossbreedingRate > maxCrossbreedingRate) crossbreedingRate = maxCrossbreedingRate;
            int size = ((int) (population.size() * crossbreedingRate / 2));
            for (int i=0; i<size; i++){ // Appliquer le croisement
                ArrayList<String> firstSolution = population.get(random.nextInt(population.size()));
                ArrayList<String> secondSolution = population.get(random.nextInt(population.size()));
                HashSet<ArrayList<String>> subsolutions = crossover(firstSolution, secondSolution);
                generatedSolutions.addAll(subsolutions);
            }
            float mutationRate = random.nextFloat();
            if (mutationRate <= maxMutationRate){
                size = ((int) (population.size() * mutationRate));
                for (int i=0; i<size; i++){ // Appliquer les mutations
                    ArrayList<String> mutatedSolution = population.get(random.nextInt(population.size()));
                    mutate(mutatedSolution);
                    generatedSolutions.add(mutatedSolution);
                }
            }
            for (ArrayList<String> s : generatedSolutions) // Recherche de la solution dans la nouvelle génération
                if (quality(cube, s) == bestQuality){
                    solution = s;
                    break outer;
                }
            while (generatedSolutions.size() < populationMaxSize){ // Remplacements
                ArrayList<String> bestSolution = getBestSolution(cube, population);
                population.remove(bestSolution);
                generatedSolutions.add(bestSolution);
            }
            population = new ArrayList<>(generatedSolutions);
        }
        currentIterations = j;
        if (currentIterations == maxIterations) solution = getBestSolution(cube, population);
    }

    private HashSet<ArrayList<String>> crossover(ArrayList<String> first, ArrayList<String> second) {
        int size = first.size();
        ArrayList<String> solution1 = new ArrayList<>(size);
        ArrayList<String> solution2 = new ArrayList<>(size);
        int crossbreedPoint = Math.round(random.nextFloat()*(size-2)+1);
        for (int i=0; i<crossbreedPoint; i++)
            solution1.add(first.get(i));
        for (int i=crossbreedPoint; i<size; i++)
            solution1.add(second.get(i));
        for (int i=0; i<crossbreedPoint; i++)
            solution2.add(second.get(i));
        for (int i=crossbreedPoint; i<size; i++)
            solution2.add(first.get(i));
        HashSet<ArrayList<String>> solutions = new HashSet<>(2);
        solutions.add(solution1);
        solutions.add(solution2);
        return solutions;
    }

    private void mutate(ArrayList<String> solution) {
        solution.set(random.nextInt(solution.size()), RubiksCubeMixer.getMethodNames()[random.nextInt(RubiksCubeMixer.getMethodNames().length)]);
    }

}
