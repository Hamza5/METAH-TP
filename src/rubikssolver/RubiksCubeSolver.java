package rubikssolver;

import rubikssolver.algorithms.*;
import rubikssolver.cube.*;
import rubikssolver.metaheuristics.GeneticAlgorithm;
import rubikssolver.metaheuristics.LocalSearchAlgorithm;
import rubikssolver.metaheuristics.MetaHeuristicAlgorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class RubiksCubeSolver {

    private static RubiksCube cube;
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private static final String generateCubeLetter = "G";
    private static final String showCubeRepresentationLetter = "A";
    private static final String mixCubeLetter = "M";
    private static final String solveByDepthFirstLetter = "P";
    private static final String solveByBroadFirstLetter = "L";
    private static final String quitLetter = "Q";
    private static final String aAsteriskLetter = "H";
    private static final String placedSquaresLetter = "C";
    private static final String placedcubiesLetter = "M";
    private static final String manhattanDistanceLetter = "T";
    private static final String solvedCubeLetter = "R";
    private static final String saveFileLetter = "S";
    private static final String localSerachLetters = "LS";
    private static final String geneticAlgorithmLetters = "GA";

    static {

    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 1) {
            try {
                if (checkFile(args[0])) cube = new RubiksCube(readFile(args[0]));
            } catch (IOException ex) {
                System.exit(1);
            }
        }
        String choice;
        try {
            do {
                choice = displayMenu();
                switch (choice) {
                    case solvedCubeLetter :
                        cube = new RubiksCube();
                        break;
                    case generateCubeLetter :
                        requestCubeFilePath();
                        break;
                    case showCubeRepresentationLetter :
                        System.out.println("Cube courant :");
                        System.out.println(cube);
                        input.readLine(); // Wait
                        break;
                    case mixCubeLetter :
                        requestMixCount();
                        break;
                    case solveByDepthFirstLetter :
                        short depth = requestDepthLimit();
                        if (depth == 0) break;
                        DepthFirstSolvingAlgorithm depthFirst = new DepthFirstSolvingAlgorithm(cube, depth);
                        startAndWaitThenShowExecutionTime(depthFirst);
                        break;
                    case solveByBroadFirstLetter :
                        LargeurAlgorithm breadthFirst  = new LargeurAlgorithm(cube);
                        startAndWaitThenShowExecutionTime(breadthFirst);
                        break;
                    case aAsteriskLetter :
                        depth = requestDepthLimit();
                        if (depth == 0) break;
                        System.out.println("La fonction heuristique :");
                        System.out.printf("   (%s) Nombre de carrés mal placés%n", placedSquaresLetter);
                        System.out.printf("   (%s) Nombre de cubes mal placés%n", placedcubiesLetter);
                        System.out.printf("   (%s) Distance de Manhattan%n> ", manhattanDistanceLetter);
                        String function = input.readLine().toUpperCase();
                        AAsteriskSolvingAlgorithm AAsteriskSolvingAlgorithm;
                        switch (function){
                            case placedSquaresLetter :
                                AAsteriskSolvingAlgorithm = new MisplacedSquaresSolvingAlgorithm(cube, depth);
                                startAndWaitThenShowExecutionTime(AAsteriskSolvingAlgorithm);
                                break;
                            case placedcubiesLetter :
                                AAsteriskSolvingAlgorithm = new MisplacedCubiesSolvingAlgorithme(cube, depth);
                                startAndWaitThenShowExecutionTime(AAsteriskSolvingAlgorithm);
                                break;
                            case manhattanDistanceLetter :
                                AAsteriskSolvingAlgorithm = new ManhattanDistanceSolvingAlgorithm(cube, depth);
                                startAndWaitThenShowExecutionTime(AAsteriskSolvingAlgorithm);
                                break;
                            default :
                                System.err.println("Choix invalide !");
                                input.readLine();
                        }
                        break;
                    case localSerachLetters :
                        ArrayList<String> s = getRandomSolution();
                        if (s != null){
                            LocalSearchAlgorithm localSearch = new LocalSearchAlgorithm(cube, s, 10000);
                            startMetaHeuristicAndWait(localSearch);
                        }
                        input.readLine();
                        break;
                    case geneticAlgorithmLetters :
                        s = getRandomSolution();
                        if (s != null){
                            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(cube, s, 10000, 100, 0.8f, 0.15f);
                            startMetaHeuristicAndWait(geneticAlgorithm);
                        }
                        input.readLine();
                        break;
                    case saveFileLetter :
                        saveToFile();
                        break;
                }
            } while (!choice.equals(quitLetter));
        } catch (IOException e) { // Should not happen
            e.printStackTrace();
        }
    }

    private static String displayMenu() throws IOException {
        String choice;
        HashSet<String> acceptableChoices = new HashSet<>();
        acceptableChoices.add(generateCubeLetter);
        acceptableChoices.add(showCubeRepresentationLetter);
        acceptableChoices.add(mixCubeLetter);
        acceptableChoices.add(solveByBroadFirstLetter);
        acceptableChoices.add(solveByDepthFirstLetter);
        acceptableChoices.add(quitLetter);
        acceptableChoices.add(aAsteriskLetter);
        acceptableChoices.add(solvedCubeLetter);
        acceptableChoices.add(saveFileLetter);
        acceptableChoices.add(localSerachLetters);
        acceptableChoices.add(geneticAlgorithmLetters);
        boolean valid = false;
        do {
            System.out.println("                                          o          o          |         ");
            System.out.println("   ,-.-.,---.,---..   .,---.    ,---.,---..,---.,---..,---.,---.|    ,---.");
            System.out.println("   | | ||---'|   ||   ||---'    |   ||    ||   ||    ||   |,---||    |---'");
            System.out.println("   ` ' '`---'`   '`---'`---'    |---'`    ``   '`---'`|---'`---^`---'`---'");
            System.out.println("                                |                     |                   ");
            System.out.printf(" (%s) Générer un cube lisse%n", solvedCubeLetter);
            System.out.printf(" (%s) Générer un cube à partir d'un fichier%n", generateCubeLetter);
            if (cube != null) {
                System.out.printf(" (%s) Afficher une représentation du cube%n", showCubeRepresentationLetter);
                System.out.printf(" (%s) Mixer le cube%n", mixCubeLetter);
                System.out.printf(" (%s) Sauvegarder la représentation dans un fichier%n", saveFileLetter);
                System.out.printf(" (%s) Résoudre le cube par l'algorithme largeur d'abord%n", solveByBroadFirstLetter);
                System.out.printf(" (%s) Résoudre le cube par l'algorithme profondeur d'abord%n", solveByDepthFirstLetter);
                System.out.printf(" (%s) Résoudre par l'algorithme A*%n", aAsteriskLetter);
                System.out.printf(" (%s) Résoudre par la métaheuristique de la recherche locale%n", localSerachLetters);
                System.out.printf(" (%s) Résoudre par l'algorithme génétique%n", geneticAlgorithmLetters);
            }
            System.out.printf(" (%s) Quitter%n", quitLetter);
            System.out.print("\n\nLettre : ");
            choice = input.readLine().toUpperCase();
            if (!acceptableChoices.contains(choice)) {
                System.err.printf("Les choix autorisés sont : %s !%n", acceptableChoices);
                input.readLine();
            } else valid = true;
        } while (!valid || (cube == null && (!choice.equals(quitLetter) && !choice.equals(generateCubeLetter) && !choice.equals(solvedCubeLetter))));
        return choice;
    }

    private static void requestCubeFilePath() throws IOException {
        System.out.printf("Chemin du fichier :%n> ");
        String filePath = input.readLine();
        if (checkFile(filePath)) {
            try {
                cube = new RubiksCube(readFile(filePath));
            } catch (IOException ex) {
                System.err.printf("Impossible de lire le fichier %s !%n", filePath);
            } catch (IllegalArgumentException ex){
                System.err.printf("Le fichier %s est invalide !%n", filePath);
                input.readLine(); // Wait
            }
        }
    }

    private static void requestMixCount() throws IOException {
        System.out.printf("Nombre de rotations :%n> ");
        short rotations;
        try {
            rotations = Short.valueOf(input.readLine());
            RubiksCubeMixer mixer = new RubiksCubeMixer(cube);
            mixer.mixCube(Math.abs(rotations));
            System.out.printf("Rotations appliquées : %s%n", mixer.getActions());
        } catch (NumberFormatException e){
            System.err.println("Vous devez spécifier un nombre valide !");
        }
        input.readLine(); // Wait
    }

    private static short requestDepthLimit() throws IOException {
        System.out.printf("Limite de profondeur :%n> ");
        short limit = 0;
        try {
            limit = Short.valueOf(input.readLine());
        } catch (NumberFormatException e){
            System.err.println("Vous devez spécifier une nombre valide !");
            input.readLine(); // Wait
        }
        return limit;
    }

    private static boolean checkFile(String filePath) throws IOException {
        File file = new File(filePath);
        boolean canBeUsed = true;
        if (!file.exists()) {
            System.err.printf("Le fichier %s n'existe pas !%n", filePath);
            canBeUsed = false;
        } else if (!file.isFile()) {
            System.err.printf("Le fichier %s n'est pas un fichier régulié !%n", filePath);
            canBeUsed = false;
        } else if (!file.canRead()) {
            System.err.printf("Le fichier %s ne peut pas être lu !%n", filePath);
            canBeUsed = false;
        }
        if (!canBeUsed) input.readLine(); // Wait
        return canBeUsed;
    }

    private static String readFile(String path) throws IOException, IllegalArgumentException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder representation = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            if (line.matches("[wbrogy]{3}")) representation.append(line);
            else throw new IllegalArgumentException();
        }
        reader.close();
        return representation.toString();
    }

    private static void startAndWaitThenShowExecutionTime(SolvingAlgorithm algorithm) throws IOException {
        try {
            System.out.printf("Temps maximal (en secondes) :%n> ");
            int timeLimit = Integer.parseInt(input.readLine());
            algorithm.start();
            System.out.println("Recherche en cours ...");
            algorithm.join(timeLimit*1000);
            if (algorithm.isAlive()) algorithm.interrupt();
            algorithm.join();
            ArrayList<String> solution = algorithm.getSteps();
            System.out.printf(solution.isEmpty() ? "Aucune solution trouvée%n" : "Solution trouvé : %s%n", solution);
            System.out.printf("Temps d'exécution : %.3f s | Nombre d'états visités : %d | Nombre d'états abandonés : %d%n", algorithm.getExecutionTime(), algorithm.getTotalStatesCount(), algorithm.getAbandonedStatesCount());
        } catch (InterruptedException e) { // Should not happen
            e.printStackTrace();
        } catch (NumberFormatException e){
            System.err.println("Vous devez spécifier un nombre valide !");
            input.readLine(); // Wait
        }
        input.readLine();
    }

    private static void saveToFile() throws IOException {
        try {
            System.out.printf("Nom du nouveau fichier :%n> ");
            String filePath = input.readLine();
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath));
            fileWriter.write("# Rubik's cube representation");
            fileWriter.newLine();
            String state = cube.getState();
            fileWriter.write("# Up");
            fileWriter.newLine();
            for (int i = 0; i < 9; i+=3){
                fileWriter.write(state.substring(i, i+3));
                fileWriter.newLine();
            }
            fileWriter.write("# Front");
            fileWriter.newLine();
            for (int i = 9; i < 18; i+=3){
                fileWriter.write(state.substring(i, i+3));
                fileWriter.newLine();
            }
            fileWriter.write("# Left");
            fileWriter.newLine();
            for (int i = 18; i < 27; i+=3){
                fileWriter.write(state.substring(i, i+3));
                fileWriter.newLine();
            }
            fileWriter.write("# Right");
            fileWriter.newLine();
            for (int i = 27; i < 36; i+=3){
                fileWriter.write(state.substring(i, i+3));
                fileWriter.newLine();
            }
            fileWriter.write("# Back");
            fileWriter.newLine();
            for (int i = 36; i < 45; i+=3){
                fileWriter.write(state.substring(i, i+3));
                fileWriter.newLine();
            }
            fileWriter.write("# Down");
            fileWriter.newLine();
            for (int i = 45; i < 54; i+=3){
                fileWriter.write(state.substring(i, i+3));
                fileWriter.newLine();
            }
            fileWriter.close();
        } catch (IOException e){
            System.err.println("Impossible d'écrire dans le fchier !");
            input.readLine();
        }
    }

    private static ArrayList<String> getRandomSolution() throws IOException {
        try {
            System.out.printf("Taille de la solution :%n> ");
            int solutionSize = Integer.parseInt(input.readLine());
            RubiksCubeMixer mixer = new RubiksCubeMixer(new RubiksCube());
            mixer.mixCube(solutionSize);
            return mixer.getActions();
        } catch (NumberFormatException e){
            System.err.println("Vous devez spécifier un nombre valide !");
            return null;
        }
    }

    private static void startMetaHeuristicAndWait(MetaHeuristicAlgorithm algorithm) {
        try {
            algorithm.start();
            algorithm.join();
            System.out.printf("%s :%n%s, Qualité : %.2f%%%nTemps d'exécution : %.3f s | Nombre d'itérations : %d%n", (algorithm.solutionFound() ? "Solution trouvée" : "Aucune solution touvée, la meilleure"), algorithm.getSolution(), algorithm.getQuality()*100,algorithm.getExecutionTime(), algorithm.getIterations());
        } catch (InterruptedException e) { // Should not happen
            e.printStackTrace();
        }
    }

}
