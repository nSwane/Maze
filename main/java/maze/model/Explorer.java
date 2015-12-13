package maze.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Explorer {

    enum State {
        HEADING_NORTH,
        HEADING_EAST,
        HEADING_SOUTH,
        HEADING_WEST;
    }
    
    private State currentState;
    
    private final Maze maze;
    
    // Save item before stepping on a new cell
    private Item itemBefore;
    
    private Cell currentPosition;
    
    private boolean exitReached;
    
    // Record of where the explorer has been
    private final List<Cell> record;
    
    // Path to exit
    List<Cell> pathToExit;
    private int currentStep;
    
    public Explorer(Maze maze){
        this.maze = maze;
        this.exitReached = false;
        this.currentState = State.HEADING_NORTH;
        
        pathToExit = new ArrayList<>();
        currentStep = 0;
        
        itemBefore = Item.START_POINT;
        currentPosition = maze.getStart();
        currentPosition.setItem(Item.EXPLORER_HEADING_NORTH);
        
        record = new ArrayList<>();
    }
    
    /**
     * Make a step i.e automatically Turn or Move.
     * Make step only if path to exit has been computed before cf method initializePathToExit()
     * 
     */
    public void makeStep(){
        if(pathToExit != null && currentStep < pathToExit.size()){
            Cell nextStep = pathToExit.get(currentStep);
            if(getNextCell() != nextStep){
                turn(Direction.RIGHT);
            }
            else{
                move();
                currentStep++;
            }
        }
        
    }
    
    /**
     * Compute path to exit before call to makeStep().
     * Return true if the exit point is reachable, false otherwise.
     * @return 
     */
    public boolean initializePathToExit(){
        // Use Dijkstra - Shortest path algorithm -
        Cell [][] previous = dijkstra();
        return buildPath(previous);
    }
    
    /**
     * Initialize pathToExit.
     * Return true if the exit point is reachable, false otherwise.
     * @param previous 
     */
    private boolean buildPath(Cell [][] previous){
        
        pathToExit.clear();
        currentStep = 0;
        
        Cell cell = maze.getExit();
        while(cell != null && cell != currentPosition){
            pathToExit.add(0, cell);
            cell = previous[cell.getRow()][cell.getColumn()];
        }
        if(cell == null){
            return false; // The exit point cannot be reached
        }
        else{
            return true;
        }
    }
    
    /**
     * Move forward
     */
    public void move() {
        if(updatePosition(getNextCell())){
            // Save position each time the explorer moves to see where he has been
            if(!record.contains(currentPosition)){
                record.add(currentPosition);
            }
            
            if(currentPosition == maze.getExit()){
                exitReached = true;
            }
        }
    }

    /**
     * Get the cell up ahead
     * 
     * @return 
     */
    public Cell getNextCell(){
        Cell next = null;
        switch(currentState){
            case HEADING_NORTH:
                next = maze.getCellAt(currentPosition.getRow()-1, currentPosition.getColumn());
                break;
            case HEADING_EAST:
                next = maze.getCellAt(currentPosition.getRow(), currentPosition.getColumn()+1);
                break;
            case HEADING_SOUTH:
                next = maze.getCellAt(currentPosition.getRow()+1, currentPosition.getColumn());
                break;
            case HEADING_WEST:
                next = maze.getCellAt(currentPosition.getRow(), currentPosition.getColumn()-1);
                break;
            default:
                // NOT POSSIBLE
        }
        
        return next;
    }
    
    /**
     * Update explorer's position - from 'currentPosition' to 'next' -
     * Return true if the position changed, false otherwise
     * @param next 
     */
    private boolean updatePosition(Cell next){
        
        // Cannot walk on walls
        if(next != null && next.getItem() != Item.WALL){
            
            // Reset cell's item since the explorer is moving from 'currentPosition' to 'next'
            Item tmp = next.getItem();
            
            next.setItem(currentPosition.getItem());
            currentPosition.setItem(itemBefore);
            itemBefore = tmp;
            
            currentPosition = next;
            return true;
        }
        
        return false;
    }
    
    /**
     * Change explorer's direction
     * @param d
     */
    public void turn(Direction d) {
        
        switch(currentState){
            case HEADING_NORTH:
                if(d == Direction.LEFT){
                    currentState = State.HEADING_WEST;
                    currentPosition.setItem(Item.EXPLORER_HEADING_WEST);
                }
                else{
                    currentState = State.HEADING_EAST;
                    currentPosition.setItem(Item.EXPLORER_HEADING_EAST);
                }
                break;
                
            case HEADING_EAST:
                if(d == Direction.LEFT){
                    currentState = State.HEADING_NORTH;
                    currentPosition.setItem(Item.EXPLORER_HEADING_NORTH);
                }
                else{
                    currentState = State.HEADING_SOUTH;
                    currentPosition.setItem(Item.EXPLORER_HEADING_SOUTH);
                }
                break;
                
            case HEADING_SOUTH:
                if(d == Direction.LEFT){
                    currentState = State.HEADING_EAST;
                    currentPosition.setItem(Item.EXPLORER_HEADING_EAST);
                }
                else{
                    currentState = State.HEADING_WEST;
                    currentPosition.setItem(Item.EXPLORER_HEADING_WEST);
                }
                break;
            case HEADING_WEST:
                if(d == Direction.LEFT){
                    currentState = State.HEADING_SOUTH;
                    currentPosition.setItem(Item.EXPLORER_HEADING_SOUTH);
                }
                else{
                    currentState = State.HEADING_NORTH;
                    currentPosition.setItem(Item.EXPLORER_HEADING_NORTH);
                }
                break;
            default:
        }
        
    }

    /**
     * Return true if the explorer reached the exit, false otherwise
     * @return 
     */
    public boolean isOut(){
        return exitReached;
    }
    
    /**
     * Get record of where the explorer has been
     * @return 
     */
    public List<Cell> getRecord(){
        return record;
    }
    
    /**
     * Dijkstra algorithm using the start point as initial point.
     * Return matrix representation of the shortest path.
     * 
     * @return 
     */
    private Cell [][] dijkstra(){
        int nRows = maze.getnRows();
        int nColumns = maze.getnColumns();
        Cell source = currentPosition;
        
        // Initialize distance to each cell from the start point
        // + initialize path to each cell
        int [][] distances = new int[nRows][nColumns]; // distance[i][j] is the shortest path from 'source' to the cell at coordinates [i,j]
        Cell [][] previous = new Cell[nRows][nColumns]; // previous[i][j] is the cell to step on before reaching coordinates[i,j]
        for(int i = 0; i < nRows; i++){
            for(int j = 0; j < nColumns; j++){
                distances[i][j] = Integer.MAX_VALUE;
                previous[i][j] = null;
            }
        }        
        distances[source.getRow()][source.getColumn()] = 1;
        
        PriorityQueue<Cell> queue = new PriorityQueue<>();
        source.setPriority(0);
        queue.add(source); // Source
        while(!queue.isEmpty()){
            Cell x = queue.poll();
            
            for(Cell y: maze.neighbours(x)){
                int distanceXY = distances[x.getRow()][x.getColumn()] + 1;
                if(distanceXY < distances[y.getRow()][y.getColumn()]){
                    distances[y.getRow()][y.getColumn()] = distanceXY; // Shortest path to 'y' found
                    previous[y.getRow()][y.getColumn()] = x;
                    
                    y.setPriority(distanceXY);
                    queue.add(y);
                }
                
            }
        }
        
        return previous;
    }
    
    /**
     * Reset explorer to its original position i.e start point
     */
    public void restart(){
        this.exitReached = false;
        this.currentState = State.HEADING_NORTH;
        
        pathToExit.clear();
        currentStep = 0;
        
        currentPosition.setItem(itemBefore);
        itemBefore = Item.START_POINT;
        currentPosition = maze.getStart();
        currentPosition.setItem(Item.EXPLORER_HEADING_NORTH);
        
        record.clear();
    }
    
    public Cell getCurrentPosition(){
        return currentPosition;
    }
}
