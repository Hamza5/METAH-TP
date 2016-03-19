

import rubikssolver.algorithms.*;
import rubikssolver.cube.RubiksCube;
import rubikssolver.cube.RubiksCubeMixer;

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
    private static final String heuristicLetter = "H";
    private static final String placedSquaresLetter = "C";

    static {

    }

    public static void main(String[] args) {
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
                    case heuristicLetter :
                        depth = requestDepthLimit();
                        if (depth == 0) break;
                        System.out.println("La fonction heuristique :");
                        System.out.printf("   (%s) Nombre de carrés bien placés%n> ", placedSquaresLetter);
                        String function = input.readLine().toUpperCase();
                        HeuristicSolvingAlgorithm heuristicSolvingAlgorithm;
                        switch (function){
                            case placedSquaresLetter :
                                heuristicSolvingAlgorithm = new PlacedSquaresHeuristicSolvingAlgorithm(cube, depth);
                                startAndWaitThenShowExecutionTime(heuristicSolvingAlgorithm);
                                break;
                            default :
                                System.err.println("Choix invalide !");
                                input.readLine();
                        }
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
        acceptableChoices.add(heuristicLetter);
        boolean valid = false;
        do {
            System.out.println("                                          o          o          |         ");
            System.out.println("   ,-.-.,---.,---..   .,---.    ,---.,---..,---.,---..,---.,---.|    ,---.");
            System.out.println("   | | ||---'|   ||   ||---'    |   ||    ||   ||    ||   |,---||    |---'");
            System.out.println("   ` ' '`---'`   '`---'`---'    |---'`    ``   '`---'`|---'`---^`---'`---'");
            System.out.println("                                |                     |                   ");
            System.out.printf(" (%s) Générer un cube à partir d'un fichier%n", generateCubeLetter);
            if (cube != null) {
                System.out.printf(" (%s) Afficher une représentation du cube%n", showCubeRepresentationLetter);
                System.out.printf(" (%s) Mixer le cube%n", mixCubeLetter);
                System.out.printf(" (%s) Résoudre le cube par l'algorithme largeur d'abord%n", solveByBroadFirstLetter);
                System.out.printf(" (%s) Résoudre le cube par l'algorithme profondeur d'abord%n", solveByDepthFirstLetter);
                System.out.printf(" (%s) Résoudre par une algorithme avec heuristique%n", heuristicLetter);
            }
            System.out.printf(" (%s) Quitter%n", quitLetter);
            System.out.print("\n\nLettre : ");
            choice = input.readLine().toUpperCase();
            if (!acceptableChoices.contains(choice)) {
                System.err.printf("Les choix autorisés sont : %s !%n", acceptableChoices);
                input.readLine();
            } else valid = true;
        } while (!valid || (cube == null && (!choice.equals(quitLetter) && !choice.equals(generateCubeLetter))));
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
        return representation.toString();
    }

    private static void startAndWaitThenShowExecutionTime(SolvingAlgorithm algorithm) throws IOException {
        algorithm.start();
        System.out.println("Recherche en cours ...");
        try {
            algorithm.join();
            ArrayList<String> solution = algorithm.getSteps();
            System.out.printf(solution.isEmpty() ? "Aucune solution trouvée%n" : "Solution trouvé : %s%n", solution);
            System.out.printf("Temps d'exécution : %.3f s | Nombre d'états visités : %d | Nombre d'états abandonés : %d%n", algorithm.getExecutionTime(), algorithm.getTotalStatesCount(), algorithm.getAbandonedStatesCount());
        } catch (InterruptedException e) { // Should not happen
            e.printStackTrace();
        }
        input.readLine();
    }

}
