package maze.model;

import maze.exceptions.InputMazeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
	
    // Max structure size
    private int maxRows, maxColumns;

    // Number of rows and columns used
    private int nRows, nColumns;

    // Number of walls and empty spaces
    private int nWalls, nEmptySpaces;

    // Field data structure
    private Cell [][] field;

    private Cell start, exit;
    
    /**
     * Initialize the maze using text file
     * 
     * @param src
     * @throws maze.exceptions.InputMazeException
     */
    public Maze(File src) throws InputMazeException {
        if(src.length() == 0){
            throw new InputMazeException("The file is empty: "+src.getName());
        }

        // Initialize data structures
        maxRows = 100;
        maxColumns = 100;

        field = new Cell[maxRows][maxColumns];

        // Read file
        try(Scanner sc = new Scanner(src)){ // Try with resources to automatically release scanner resources

            int startPoints = 0, exitPoints = 0;

            nRows = 0;
            nColumns = 0;
            nWalls = 0;
            nEmptySpaces = 0;

            // Read file
            while(sc.hasNextLine()){

                // Read line
                char [] line = sc.nextLine().toCharArray();

                if(line.length > maxColumns || nRows > maxRows){
                    throw new InputMazeException("Max size: "+maxColumns+" columns and "+maxRows+" rows");
                }

                // Initialize field structure and check line
                for(int i = 0; i < line.length; i++){
                    switch(line[i]){
                        // Start point S
                        case 'S':
                            startPoints++;
                            if(startPoints > 1){
                                throw new InputMazeException("Start point must be unique");
                            }

                            field[nRows][i] = new Cell(Item.START_POINT, nRows, i);
                            start = field[nRows][i];
                            break;

                        //Exit point F
                        case 'F':
                            exitPoints++;
                            if(exitPoints > 1){
                                throw new InputMazeException("Exit point must be unique");
                            }

                            field[nRows][i] = new Cell(Item.EXIT_POINT, nRows, i);
                            exit = field[nRows][i];
                            break;

                        // Wall
                        case 'X':
                            nWalls++;
                            field[nRows][i] = new Cell(Item.WALL, nRows, i);
                            break;

                        // Empty space
                        case ' ':
                            nEmptySpaces++;							
                            field[nRows][i] = new Cell(Item.EMPTY_SPACE, nRows, i);
                            break;
                        default:
                            throw new InputMazeException("Unknown character line "+(nRows+1)+": "+line[i]);
                    }
                }

                nRows++;
                if(nColumns == 0){
                    nColumns = line.length;
                }
                else if(nColumns != line.length){
                    throw new InputMazeException("All column size must be the same");
                }
            }

            // Check S and F
            if(startPoints == 0){
                throw new InputMazeException("There is no start point");
            }

            if(exitPoints == 0){
                throw new InputMazeException("There is no exit point");
            }
        } catch (FileNotFoundException ex) {
            throw new InputMazeException(ex.getMessage());
        }
    }

    /**
     * Print the maze
     * 
     */
    public void print(){
        // Print column indexes
        System.out.printf("%3c", ' ');
        for(int i = 0; i < nColumns; i++){
            System.out.printf("%3d", i);
        }
        System.out.println();

        for(int i = 0; i < nRows ; i++){
            // Print row indexes
            System.out.printf("%3d", i);
            for(int j = 0; j < nColumns; j++){
                Cell cell = getCellAt(i, j);
                if(cell != null){
                    System.out.printf("%3s", cell.getItem().getValue());
                }

            }
            System.out.println();
        }
    }

    public int getnWalls() {
        return nWalls;
    }

    public int getnEmptySpaces() {
        return nEmptySpaces;
    }

    public Cell getStart() {
        return start;
    }

    public Cell getExit() {
        return exit;
    }

    /**
     * Return the cell at the coordinates [row, column] or null if there is no cell
     * 
     * @param row
     * @param column
     * @return
     */
    public Cell getCellAt(int row, int column){
        if(0 <= row && row < nRows && 0 <= column && column < nColumns){
            return field[row][column];
        }
        return null;
    }
    
    /**
     * Return neighbours of the given cell - top, bottom, left and right -
     * 
     * @param cell
     * @return 
     */
    public List<Cell> neighbours(Cell cell){
        List<Cell> list = new ArrayList<>();
        Cell toAdd;
        
        // TOP
        toAdd = getCellAt(cell.getRow()-1, cell.getColumn());
        if(toAdd != null && toAdd.getItem() != Item.WALL){
            list.add(toAdd);
        }
        
        // BOTTOM
        toAdd = getCellAt(cell.getRow()+1, cell.getColumn());
        if(toAdd != null && toAdd.getItem() != Item.WALL){
            list.add(toAdd);
        }
        
        // LEFT
        toAdd = getCellAt(cell.getRow(), cell.getColumn()-1);
        if(toAdd != null && toAdd.getItem() != Item.WALL){
            list.add(toAdd);
        }
        
        // RIGHT
        toAdd = getCellAt(cell.getRow(), cell.getColumn()+1);
        if(toAdd != null && toAdd.getItem() != Item.WALL){
            list.add(toAdd);
        }
        
        return list;
    }

    public int getnRows() {
        return nRows;
    }

    public int getnColumns() {
        return nColumns;
    }

}
