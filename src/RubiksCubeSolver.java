import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RubiksCubeSolver {

    private static String readFile(String path) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder representation = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            if (line.trim().startsWith("#")) continue;
            representation.append(line);
        }
        return representation.toString();
    }

    public static void main(String[] args) {
        if (args.length == 1){
            try {
                String cube_content = readFile(args[0]);
                RubiksCube cube = new RubiksCube(cube_content);
                cube.up();
                System.out.println(cube);
            } catch (IOException ex){
                System.err.println("Impossible de lire le fichier : "+args[0]);
            }
        } else System.err.println("Usage : RubiksCubeSolver <fichier_cube>");
    }

}
