package maze.model;

import maze.exceptions.InputMazeException;
import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

class Main {

    static void menu(){
        String cmd = ""
            + "[1] Print maze\n"
            + "[2] Get number of walls and number of empty spaces\n"
            + "[3] Get item at coordinates 'row column'\n"
                
            + "[4] Turn explorer to the right\n"
            + "[5] Turn explorer to the left\n"
            + "[6] Move explorer \n"
            + "[7] Get explorer's front cell\n"
            + "[8] Explore and find exit\n";

        System.out.println(cmd);
    }
	
    public static void main(String[] args) {
        File file = Paths.get("src", "main", "resources", "ExampleMaze.txt").toFile();
        try {
            Maze maze = new Maze(file);
            Explorer explorer = new Explorer(maze);
            maze.print();

            try(Scanner sc = new Scanner(System.in)){
                while(!explorer.isOut() && sc.hasNext()){
                    menu();
                    String cmd = sc.nextLine();
                    switch(cmd){
                        case "1": // Print maze
                            maze.print();
                            break;

                        case "2": // Get number of walls and empty spaces
                            System.out.println("Number of walls: "+maze.getnWalls());
                            System.out.println("Number of empty spaces: "+maze.getnEmptySpaces());
                            break;

                        case "3": // Get item at coordinates
                            System.out.println("Enter coordinates 'row column':");
                            String [] coordinates = sc.nextLine().split("\\s");
                            Cell cell = maze.getCellAt(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                            if(cell != null){
                                System.out.println(Integer.parseInt(coordinates[0])+" "+coordinates[1]+ " --> "+cell.getItem());
                            }
                            else{
                                System.out.println("Empty cell");
                            }
                            break;
                        case "4": // Turn explorer to the right
                            explorer.turn(Direction.RIGHT);
                            break;
                            
                        case "5": // Turn explorer to the left
                            explorer.turn(Direction.LEFT);
                            break;
                            
                        case "6": // Move explorer
                            explorer.move();
                            break;
                            
                        case "7": // Explorer's front cell
                            Cell next = explorer.getNextCell();
                            System.out.println("Front cell: "+next.getItem()+"("+next.getRow()+", "+next.getColumn()+")");
                            break;
                        case "8": // Explorer and find exit
                            explorer.initializePathToExit();
                            while(!explorer.isOut()){
                                explorer.makeStep();
                            }
                            maze.print();
                            break;
                        default:
                    }

                }
                
            }


        } catch (InputMazeException | NumberFormatException e) {
            System.err.println(e);
        }

    }

}
