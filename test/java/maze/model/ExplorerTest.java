/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.model;

import maze.model.Direction;
import maze.model.Cell;
import maze.model.Explorer;
import maze.model.Item;
import maze.model.Maze;
import maze.exceptions.InputMazeException;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class ExplorerTest {
    
    private static Maze simpleMaze, noExitMaze;
    
    public ExplorerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InputMazeException {
        File file = Paths.get("src", "main", "resources", "test", "ExampleMaze_Simple.txt").toFile();
        simpleMaze = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_NoExit.txt").toFile();
        noExitMaze = new Maze(file);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of makeStep method, of class Explorer for a maze with reachable exit.
     */
    @Test
    public void testMakeStep_SimpleMaze() {
        System.out.println("makeStep");
        Explorer instance = new Explorer(simpleMaze);
        instance.makeStep();
        
        // Path to exit has not been computed yet hence the explorer must be on the start point
        assertEquals(instance.getCurrentPosition(), simpleMaze.getStart());
        
        boolean isReachable = instance.initializePathToExit();
        assertTrue(isReachable);
        
        // Path to exit has been initialized hence the explore will eventually reach the exit after numbers of steps
        while(!instance.isOut()){
            instance.makeStep();
        }
        assertTrue(true);
    }

    /**
     * Test of makeStep method, of class Explorer for a maze with non-reachable exit
     */
    @Test
    public void testMakeStep_NoExitMaze() {
        System.out.println("makeStep");
        Explorer instance = new Explorer(noExitMaze);
        instance.makeStep();
        
        // Path to exit has not been computed yet hence the explorer must be on the start point
        assertEquals(instance.getCurrentPosition(), noExitMaze.getStart());
        
        boolean isReachable = instance.initializePathToExit();
        
        assertFalse(isReachable);
        
    }

    /**
     * Test of initializePathToExit method, of class Explorer.
     */
    @Test
    public void testInitializePathToExit() {
        System.out.println("initializePathToExit");
        Explorer instance = new Explorer(simpleMaze);
        boolean expResult = true;
        boolean result = instance.initializePathToExit();
        assertEquals(expResult, result);
        
        instance = new Explorer(noExitMaze);
        expResult = false;
        result = instance.initializePathToExit();
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Explorer.
     * Test that the explorer can step on any cell whatever his direction is, except on walls
     * @throws maze.exceptions.InputMazeException
     */
    @Test
    public void testMove() throws InputMazeException {
        Maze moveNorth, moveSouth, moveEast, moveWest;        
        Explorer north, south, east, west;
        
        System.out.println("move");
        
        // Initialize mazes
        File file = Paths.get("src", "main", "resources", "test", "ExampleMaze_moveNorth.txt").toFile();
        moveNorth = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_moveSouth.txt").toFile();
        moveSouth = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_moveEast.txt").toFile();
        moveEast = new Maze(file);
        
        file = Paths.get("src", "main", "resources", "test", "ExampleMaze_moveWest.txt").toFile();
        moveWest = new Maze(file);
        
        // Initialize explorers
        north = new Explorer(moveNorth);
        south = new Explorer(moveSouth);
        east = new Explorer(moveEast);
        west = new Explorer(moveWest);
        
        // Test move north
        assertTrue(north.getNextCell().getItem() == Item.EMPTY_SPACE); // Step on empty space
        north.move();
        assertTrue(
                north.getCurrentPosition() == moveNorth.getCellAt(2, 1) &&
                north.getCurrentPosition().getItem() == Item.EXPLORER_HEADING_NORTH
        );
        
        assertTrue(north.getNextCell().getItem() == Item.EXIT_POINT); // Step on exit point
        north.move();
        assertTrue(
                north.getCurrentPosition() == moveNorth.getCellAt(1, 1) &&
                north.getCurrentPosition().getItem() == Item.EXPLORER_HEADING_NORTH
        );
        
        // Test move south
        System.out.println("move north");
        
        // Initially the explorer heads north
        south.turn(Direction.LEFT);
        south.turn(Direction.LEFT);
        
        assertTrue(south.getNextCell().getItem() == Item.EMPTY_SPACE); // Step on empty space
        south.move();
        assertTrue(
                south.getCurrentPosition() == moveSouth.getCellAt(2, 1) &&
                south.getCurrentPosition().getItem() == Item.EXPLORER_HEADING_SOUTH
        );
        
        assertTrue(south.getNextCell().getItem() == Item.EXIT_POINT); // Step on exit point
        south.move();
        assertTrue(
                south.getCurrentPosition() == moveSouth.getCellAt(3, 1) &&
                south.getCurrentPosition().getItem() == Item.EXPLORER_HEADING_SOUTH
        );
        
        // Test move east
        System.out.println("move east");
        
        // Initially the explorer heads north
        east.turn(Direction.RIGHT);
        
        assertTrue(east.getNextCell().getItem() == Item.EMPTY_SPACE); // Step on empty space
        east.move();
        assertTrue(
                east.getCurrentPosition() == moveEast.getCellAt(1, 2) &&
                east.getCurrentPosition().getItem() == Item.EXPLORER_HEADING_EAST
        );
        
        assertTrue(east.getNextCell().getItem() == Item.EXIT_POINT); // Step on exit point
        east.move();
        assertTrue(
                east.getCurrentPosition() == moveEast.getCellAt(1, 3) &&
                east.getCurrentPosition().getItem() == Item.EXPLORER_HEADING_EAST
        );
        
        // Test move west
        System.out.println("move west");
        
        // Initially the explorer heads north
        west.turn(Direction.LEFT);
        
        assertTrue(west.getNextCell().getItem() == Item.EMPTY_SPACE); // Step on empty space
        west.move();
        assertTrue(
                west.getCurrentPosition() == moveWest.getCellAt(1, 2) &&
                west.getCurrentPosition().getItem() == Item.EXPLORER_HEADING_WEST
        );
        
        assertTrue(west.getNextCell().getItem() == Item.EXIT_POINT); // Step on exit point
        west.move();
        assertTrue(
                west.getCurrentPosition() == moveWest.getCellAt(1, 1) &&
                west.getCurrentPosition().getItem() == Item.EXPLORER_HEADING_WEST
        );
        
    }
    
    /**
     * Test of getNextCell method, of class Explorer.
     */
    @Test
    public void testGetNextCell() {
        System.out.println("getNextCell");
        Explorer instance = new Explorer(simpleMaze);
        Cell expResult = simpleMaze.getCellAt(0, 1);
        Cell result = instance.getNextCell();
        assertEquals(expResult, result);
    }

    /**
     * Test of turn method, of class Explorer.
     */
    @Test
    public void testTurn() {
        System.out.println("turn");
        Direction d = Direction.RIGHT;
        Explorer instance = new Explorer(simpleMaze);
        instance.turn(d);
        assertTrue(instance.getNextCell().getItem() == Item.EMPTY_SPACE);
        
        d = Direction.LEFT;
        instance.turn(d);
        assertTrue(instance.getNextCell().getItem() == Item.WALL);
    }

    /**
     * Test of isOut method, of class Explorer.
     */
    @Test
    public void testIsOut() {
        System.out.println("isOut");
        Explorer instance = new Explorer(simpleMaze);
        
        assertTrue(!instance.isOut());
        instance.initializePathToExit();
        while(!instance.isOut()){
            instance.makeStep();
        }
        
        assertTrue(instance.isOut());
    }

    /**
     * Test of getRecord method, of class Explorer.
     */
    @Test
    public void testGetRecord() {
        System.out.println("getRecord");
        Explorer instance = new Explorer(simpleMaze);
        List<Cell> expResult = Arrays.asList(
                simpleMaze.getCellAt(1, 2), //next(start point)
                simpleMaze.getCellAt(1, 3),
                simpleMaze.getCellAt(1, 4),
                simpleMaze.getCellAt(1, 5),
                simpleMaze.getCellAt(1, 6),
                simpleMaze.getCellAt(1, 7),
                simpleMaze.getCellAt(1, 8),
                simpleMaze.getCellAt(1, 9),
                simpleMaze.getCellAt(1, 10),
                simpleMaze.getCellAt(1, 11),
                simpleMaze.getCellAt(1, 12),
                simpleMaze.getCellAt(1, 13),
                simpleMaze.getCellAt(2, 13),
                simpleMaze.getCellAt(3, 13),
                simpleMaze.getCellAt(3, 14),
                simpleMaze.getCellAt(3, 15),
                simpleMaze.getCellAt(3, 16),
                simpleMaze.getCellAt(3, 17) // Exit point
                );
        List<Cell> result = instance.getRecord();
        assertTrue(result.isEmpty());
        
        instance.initializePathToExit();
        while(!instance.isOut()){
            instance.makeStep();
        }
        assertTrue(result.size() == expResult.size() && result.containsAll(expResult));
    }

    /**
     * Test of restart method, of class Explorer.
     */
    @Test
    public void testRestart() {
        System.out.println("restart");
        Explorer instance = new Explorer(simpleMaze);
        
        instance.initializePathToExit();
        while(!instance.isOut()){
            instance.makeStep();
        }
        
        instance.restart();
        
        // After restart, the explorer comes back to the start point + new record of where he has been
        assertTrue(instance.getCurrentPosition() == simpleMaze.getStart());
        assertTrue(!instance.isOut());        
        assertTrue(instance.getRecord().isEmpty());
        
        // The explorer changes the item on his current position hence on 'restart' the item must be reset
        assertTrue(simpleMaze.getExit().getItem() == Item.EXIT_POINT);
    }

    /**
     * Test of getCurrentPosition method, of class Explorer.
     */
    @Test
    public void testGetCurrentPosition() {
        System.out.println("getCurrentPosition");
        Explorer instance = new Explorer(simpleMaze);
        Cell expResult = simpleMaze.getStart();
        Cell result = instance.getCurrentPosition();
        assertEquals(expResult, result);
    }
    
}
