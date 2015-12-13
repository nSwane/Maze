/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.model;

import maze.model.Cell;
import maze.model.Maze;
import maze.exceptions.InputMazeException;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 */
public class MazeTest {
    
    private static Maze maze;
    
    public MazeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws InputMazeException {
        File file = Paths.get("src", "main", "resources", "test", "ExampleMaze_NoExit.txt").toFile();
        maze = new Maze(file);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test wrong maze inputs - Not regular column size -
     */
    @Rule
     public ExpectedException expectedException = ExpectedException.none();

    @Test()
    public void testConstructor_ColumnSize() throws InputMazeException{
        File file;
        Maze maze;
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_ColumnSize.txt").toFile();        
        expectedException.expect(InputMazeException.class);
        expectedException.expectMessage("All column size must be the same");
        maze = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_Empty.txt").toFile();        
        expectedException.expect(InputMazeException.class);
        expectedException.expectMessage("The file is empty: "+file.getName());
        maze = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_ExitPoints.txt").toFile();        
        expectedException.expect(InputMazeException.class);
        expectedException.expectMessage("Exit point must be unique");
        maze = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_StartPoints.txt").toFile();        
        expectedException.expect(InputMazeException.class);
        expectedException.expectMessage("Start point must be unique");
        maze = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_UnknownCharacter.txt").toFile();        
        expectedException.expect(InputMazeException.class);
        expectedException.expectMessage("Unknown character line 5: P");
        maze = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_ZeroExitPoint.txt").toFile();        
        expectedException.expect(InputMazeException.class);
        expectedException.expectMessage("There is no exit point");
        maze = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_ZeroStartPoint.txt").toFile();        
        expectedException.expect(InputMazeException.class);
        expectedException.expectMessage("There is no start point");
        maze = new Maze(file);
    }
    
    /**
     * Test of print method, of class Maze.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        // print the maze on standard output...
    }

    /**
     * Test of getnWalls method, of class Maze.
     */
    @Test
    public void testGetnWalls() {
        System.out.println("getnWalls");
        int expResult = 32;
        int result = maze.getnWalls();
        assertEquals(expResult, result);
    }

    /**
     * Test of getnEmptySpaces method, of class Maze.
     */
    @Test
    public void testGetnEmptySpaces() {
        System.out.println("getnEmptySpaces");
        int expResult = 11;
        int result = maze.getnEmptySpaces();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStart method, of class Maze.
     */
    @Test
    public void testGetStart() {
        System.out.println("getStart");
        Cell expResult = maze.getCellAt(1, 1);
        Cell result = maze.getStart();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExit method, of class Maze.
     */
    @Test
    public void testGetExit() {
        System.out.println("getExit");
        Cell expResult = maze.getCellAt(1, 14);
        Cell result = maze.getExit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCellAt method, of class Maze.
     */
    @Test
    public void testGetCellAt() {
        System.out.println("getCellAt");
        int row = 1;
        int column = 14;
        Cell expResult = maze.getExit();
        Cell result = maze.getCellAt(row, column);
        assertEquals(expResult, result);
        assertTrue(maze.getCellAt(50, 50) == null); // out of bounds
    }

    /**
     * Test of neighbours method, of class Maze.
     * Test of all 4 possible neighbours of a cell.
     * @throws maze.exceptions.InputMazeException
     */
    @Test
    public void testNeighbours() throws InputMazeException {
        Maze moveNorth, moveSouth, moveEast, moveWest;        
        Cell cell;
        List<Cell> neighbours;
        
        System.out.println("neighbours");
        
        // Initialize mazes
        File file = Paths.get("src", "main", "resources", "test", "ExampleMaze_moveNorth.txt").toFile();
        moveNorth = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_moveSouth.txt").toFile();
        moveSouth = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_moveEast.txt").toFile();
        moveEast = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_moveWest.txt").toFile();
        moveWest = new Maze(file);
        
        // Test neighbours
        
        // North neighbour
        cell = moveNorth.getStart();
        neighbours = moveNorth.neighbours(cell);
        assertTrue(neighbours.size() == 1 && neighbours.contains(moveNorth.getCellAt(2, 1)));
        
        // South neighbour
        cell = moveNorth.getStart();
        neighbours = moveSouth.neighbours(cell);
        assertTrue(neighbours.size() == 1 && neighbours.contains(moveSouth.getCellAt(2, 1)));

        
        // East neighbour
        cell = moveEast.getStart();
        neighbours = moveEast.neighbours(cell);
        assertTrue(neighbours.size() == 1 && neighbours.contains(moveEast.getCellAt(1, 2)));

        // West neighbour
        cell = moveWest.getStart();
        neighbours = moveWest.neighbours(cell);
        assertTrue(neighbours.size() == 1 && neighbours.contains(moveWest.getCellAt(1, 2)));

    }

    /**
     * Test of getnRows method, of class Maze.
     */
    @Test
    public void testGetnRows() {
        System.out.println("getnRows");
        int expResult = 3;
        int result = maze.getnRows();
        assertEquals(expResult, result);
    }

    /**
     * Test of getnColumns method, of class Maze.
     */
    @Test
    public void testGetnColumns() {
        System.out.println("getnColumns");
        int expResult = 15;
        int result = maze.getnColumns();
        assertEquals(expResult, result);
    }
    
}
