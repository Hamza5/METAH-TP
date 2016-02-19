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
//                System.out.println("Up");
//                cube.up();
//                System.out.println(cube);
//                System.out.println("Up inverted");
//                cube.upInverted();
//                System.out.println(cube);
//                System.out.println("Left");
//                cube.left();
//                System.out.println(cube);
//                System.out.println("Left inverted");
//                cube.leftInverted();
                System.out.println(cube);
//                cube.BackLeft();
//                System.out.println(cube);
//                cube.BackRight();
//                System.out.println(cube);
//                System.out.println("Right");
//                cube.right();
//                System.out.println(cube);
//                System.out.println("Right inverted");
//                cube.rightInverted();
//                System.out.println(cube);
            } catch (IOException ex){
                System.err.println("Impossible de lire le fichier : "+args[0]);
            }
        } else System.err.println("Usage : RubiksCubeSolver <fichier_cube>");
    }

}
