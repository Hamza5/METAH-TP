package rubikssolver.metaheuristics;

import rubikssolver.cube.RubiksCube;
import rubikssolver.cube.RubiksCubeMixer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class GeneticAlgorithm extends MetaHeuristicAlgorithm {

    private static final Random random = new Random();
    private int populationMaxSize; // Taille de la population TODO : taille kN², itérations kN
    private float maxCrossbreedingRate; // Taux maximal du croisement
    private float maxMutationRate; // Taux maximal de la mutate
    private LinkedList<ArrayList<String>> population;

    public GeneticAlgorithm(RubiksCube initialCube, ArrayList<String> initialSolution, int iterations, int populationSize, float crossbreedingRate, float mutationRate) {
        super(initialCube, initialSolution, iterations);
        population = new LinkedList<>();
        populationMaxSize = populationSize;
        maxCrossbreedingRate = crossbreedingRate;
        maxMutationRate = mutationRate;
    }

    @Override
    void doOperation() {
        int j;
        outer:for (j=0; j<maxIterations; j++){
            while (population.size() < populationMaxSize){ // Compléter la population (initialiser pour la première itération)
                ArrayList<String> s = getRandomSolution(solution.size());
                if (!population.contains(s)) population.add(s);
            }
            float crossbreedingRate = random.nextFloat();
            if (crossbreedingRate > maxCrossbreedingRate) crossbreedingRate = maxCrossbreedingRate;
            HashSet<ArrayList<String>> generatedSolutions = new HashSet<>(population.size());
            int size = ((int) (population.size() * crossbreedingRate));
            for (int i=0; i<size; i++){ // Appliquer le croisement
                ArrayList<String> firstSolution = population.get(random.nextInt(size)); // Sélection aléatoire
                ArrayList<String> secondSolution = population.get(random.nextInt(size)); // Sélection aléatoire
                generatedSolutions.addAll(crossover(firstSolution, secondSolution));
            }
            population.addAll(generatedSolutions);
            float mutationRate = random.nextFloat();
            if (mutationRate <= maxMutationRate){
                size = ((int) (population.size() * mutationRate));
                for (int i=0; i<size; i++){ // Appliquer les mutations
                    mutate(population.get(random.nextInt(solution.size())));
                }
            }
            for (ArrayList<String> s : population) // Recherche de la solution
                if (quality(cube, s) == bestQuality){
                    solution = s;
                    break outer;
                }
            doReplacements();
        }
        currentIterations = j;
        if (currentIterations == maxIterations) solution = getBestSolution(cube, population);
    }

    private HashSet<ArrayList<String>> crossover(ArrayList<String> first, ArrayList<String> second) {
        int size = first.size();
        ArrayList<String> solution1 = new ArrayList<>(size);
        ArrayList<String> solution2 = new ArrayList<>(size);
        int crossbreedPoint = size/4;// Math.round(random.nextFloat()*(size-2)+1);
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

    private void doReplacements() {
        while (population.size()>populationMaxSize){ // Elimination des solutions les plus anciennes
            population.removeFirst();
        }
    }

}
