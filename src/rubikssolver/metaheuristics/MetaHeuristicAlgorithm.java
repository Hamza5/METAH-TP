package rubikssolver.metaheuristics;

import rubikssolver.cube.RubiksCube;
import rubikssolver.cube.RubiksCubeMixer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class MetaHeuristicAlgorithm extends Thread {

    final protected RubiksCube cube;
    int maxIterations;
    ArrayList<String> solution; // Succession des rotations, ex : ["up", "left", "rightInverted", "down"]
    private long startTime;
    private long endTime;
    final int bestQuality;
    int currentIterations;

    MetaHeuristicAlgorithm(RubiksCube initialCube, ArrayList<String> initialSolution, int iterations){
        solution = initialSolution;
        cube = initialCube;
        maxIterations = iterations;
        bestQuality = quality(new RubiksCube(), new ArrayList<String>());
    }

    static HashSet<ArrayList<String>> getNeighbors(ArrayList<String> solution){ // Fonction de voisinage
        String[] methods = RubiksCubeMixer.getMethodNames();
        int solutionsCount = solution.size() * methods.length;
        HashSet<ArrayList<String>> solutions = new HashSet<>(solutionsCount - solution.size());
        for (int i = 0; i < solutionsCount; i++) {
            ArrayList<String> generatedSolution = new ArrayList<>(solution); // Construire une copie
            generatedSolution.set(i/methods.length, methods[i%methods.length]);
            if (!generatedSolution.equals(solution)) // La solution générée ne doit pas être la même que l'originale
                solutions.add(generatedSolution);
        }
        return solutions;
    }

    static int quality(RubiksCube cube, ArrayList<String> solution){
        RubiksCube generatedCube = new RubiksCube(cube.getState());
        int s = 0;
        for (String rotation : solution)
            try {
                RubiksCube.class.getMethod(rotation).invoke(generatedCube);
            } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException ignored) {}
        // Compter les carrés bien placés
        char[] topFace = generatedCube.getTopFace().toCharArray();
        char[] frontFace = generatedCube.getFrontFace().toCharArray();
        char[] leftFace = generatedCube.getLeftFace().toCharArray();
        char[] rightFace = generatedCube.getRightFace().toCharArray();
        char[] backFace = generatedCube.getBackFace().toCharArray();
        char[] bottomFace = generatedCube.getBottomFace().toCharArray();
        for (char square : topFace) if (square == topFace[4]) s++;
        for (char square : frontFace) if (square == frontFace[4]) s++;
        for (char square : leftFace) if (square == leftFace[4]) s++;
        for (char square : rightFace) if (square == rightFace[4]) s++;
        for (char square : backFace) if (square == backFace[4]) s++;
        for (char square : bottomFace) if (square == bottomFace[4]) s++;
        return s;
    }

    static ArrayList<String> getBestSolution(RubiksCube cube, HashSet<ArrayList<String>> solutions){
        int maxQuality = 0;
        ArrayList<String> bestSolution = null;
        for (ArrayList<String> solution : solutions){
            int currentQuality = quality(cube, solution);
            if (currentQuality > maxQuality){
                maxQuality = currentQuality;
                bestSolution = solution;
            }
        }
        return bestSolution;
    }

    static ArrayList<String> getRandomSolution(int size){
        RubiksCubeMixer mixer = new RubiksCubeMixer(new RubiksCube());
        mixer.mixCube(size);
        return mixer.getActions();
    }

    @Override
    public void run(){
        doBefore();
        doOperation();
        doAfter();
    }

    abstract void doOperation();

    private void doBefore(){
        startTime = System.currentTimeMillis();
    }

    private void doAfter(){
        endTime = System.currentTimeMillis();
    }

    public double getExecutionTime(){
        return (endTime - startTime)/1000.;
    }

    public ArrayList<String> getSolution(){
        return solution;
    }

    public int getIterations() {
        return currentIterations;
    }

    public boolean solutionFound(){
        return quality(cube, solution) == bestQuality;
    }
}
