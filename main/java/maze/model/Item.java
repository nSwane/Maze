package maze.model;

public enum Item {
    START_POINT("S"), // Start point
    EXIT_POINT("F"), // Exit point
    EMPTY_SPACE(" "), // Empty space
    WALL("X"), // Wall
    
    EXPLORER_HEADING_NORTH("EN"), // Explorer
    EXPLORER_HEADING_SOUTH("ES"), // Explorer
    EXPLORER_HEADING_EAST("EE"), // Explorer
    EXPLORER_HEADING_WEST("EW"); // Explorer
    
    private final String value;

    Item(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
