/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.utils;

import maze.exceptions.ItemException;
import maze.model.Item;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;

/**
 * Load items once and for all.
 * 
 */
public class ItemLoader {
    
    private static ImageIcon START_POINT, EXIT_POINT, WALL;
    private static ImageIcon EXPLORER_HEADING_NORTH, EXPLORER_HEADING_SOUTH, EXPLORER_HEADING_EAST, EXPLORER_HEADING_WEST;
    
    private static ItemLoader loader = null;
    
    ItemLoader(){
        
        Path path;
        
        path = Paths.get("main", "resources", "images", "s.png");
        START_POINT = new ImageIcon(path.toString());
        
        path = Paths.get("main", "resources", "images", "f.png");
        EXIT_POINT = new ImageIcon(path.toString());
        
        path = Paths.get("main", "resources", "images", "wall.png");
        WALL = new ImageIcon(path.toString());
        
        path = Paths.get("main", "resources", "images", "explorer-north.png");
        EXPLORER_HEADING_NORTH = new ImageIcon(path.toString());
        
        path = Paths.get("main", "resources", "images", "explorer-south.png");
        EXPLORER_HEADING_SOUTH = new ImageIcon(path.toString());
        
        path = Paths.get("main", "resources", "images", "explorer-east.png");
        EXPLORER_HEADING_EAST = new ImageIcon(path.toString());
        
        path = Paths.get("main", "resources", "images", "explorer-west.png");
        EXPLORER_HEADING_WEST = new ImageIcon(path.toString());
        
    }
    
    public static ItemLoader getInstance(){
        if(loader == null){
            loader = new ItemLoader();
        }
        return loader;
    }
    
    public ImageIcon load(Item item) throws ItemException{
        ImageIcon icon = null;
        
        switch(item){
            case START_POINT:
                icon = START_POINT;
                break;
                
            case EXIT_POINT:
                icon = EXIT_POINT;
                break;
                
            case WALL:
                icon = WALL;
                break;
                
            case EMPTY_SPACE:
                break;
                
            case EXPLORER_HEADING_NORTH:
                icon = EXPLORER_HEADING_NORTH;
                break;
            
            case EXPLORER_HEADING_SOUTH:
                icon = EXPLORER_HEADING_SOUTH;
                break;

            case EXPLORER_HEADING_EAST:
                icon = EXPLORER_HEADING_EAST;
                break;
                    
            case EXPLORER_HEADING_WEST:
                icon = EXPLORER_HEADING_WEST;
                break;
                
            default:
                throw new ItemException("Unmanaged item");
        }
        
        return icon;
    }
    
}
