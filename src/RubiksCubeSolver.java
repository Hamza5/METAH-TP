import algorithms.DepthFirstSolvingAlgorithm;
import algorithms.RubiksCube;
import algorithms.RubiksCubeMixer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RubiksCubeSolver {

    private static String readFile(String path) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder representation = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            representation.append(line);
        }
        return representation.toString();
    }

    public static void main(String[] args) {
        if (args.length == 1){
            try {
                String cube_content = readFile(args[0]);
                RubiksCube cube = new RubiksCube(cube_content);
                RubiksCubeMixer mixer = new RubiksCubeMixer(cube);
                System.out.println(cube);
                mixer.mixCube(5);
                System.out.println(mixer.getActions());
                System.out.println(cube);
                DepthFirstSolvingAlgorithm depthFirst = new DepthFirstSolvingAlgorithm(cube, 4);
                depthFirst.start();
                depthFirst.join();
                if (depthFirst.getSteps() != null){
                    System.out.println("Solution trouvée :");
                    System.out.println(depthFirst.getSteps());
                } else System.out.println("Aucune solution trouvée !");
            } catch (IOException ex){
                System.err.println("Impossible de lire le fichier : " + args[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else System.err.println("Usage : RubiksCubeSolver <fichier_cube>");
    }

}
