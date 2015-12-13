package maze.model;

public class Cell implements Comparable<Cell>{

    // Coordinates
    private final int row, column;

    // Item
    private Item item;

    // Used to implement Dijkstra with priority queue
    private int priority;
    
    public Cell(Item item, int row, int column){
        this.item = item;
        this.row = row;
        this.column = column;
        this.priority = Integer.MAX_VALUE;
    }

    public Item getItem() {
        return item;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int compareTo(Cell o) {
        if(o.getPriority() > priority){
            return -1;
        }
        else if(o.getPriority() < priority){
            return 1;
        }
        else{
            return 0;
        }
    }

}
