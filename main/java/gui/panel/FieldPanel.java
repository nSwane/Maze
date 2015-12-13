/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panel;

import gui.property.ExplorerProperty;
import gui.property.MazeProperty;
import maze.model.Maze;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * Graphical represention of the maze
 */
public class FieldPanel extends JPanel implements Observer {
    // Current maze
    private Maze maze;
    
    private MazeProperty mazeProperty;
    private ExplorerProperty explorerProperty;
    
    private CellPanel [][] cellPanels;
    
    public FieldPanel(MazeProperty mazeProperty, ExplorerProperty explorerProperty) {
        this.maze = mazeProperty.getMaze();
        this.mazeProperty = mazeProperty;
        this.explorerProperty = explorerProperty;
        
        initializeCellPanels();
        
        mazeProperty.addObserver(this);
        
    }
    
    private void initializeCellPanels(){
        GridLayout gridL = new GridLayout(maze.getnRows(), maze.getnColumns(), 0, 0);
        setLayout(gridL);

        cellPanels = new CellPanel[maze.getnRows()][maze.getnColumns()];

        for(int i = 0; i < maze.getnRows(); i++){
            for(int j = 0; j < maze.getnColumns(); j++){
                CellPanel cell = new CellPanel(maze.getCellAt(i, j), explorerProperty);
                cellPanels[i][j] = cell;
                add(cell);
            }
        }
    }
    
    public CellPanel getCellPanelAt(int row, int column){
        if(0 <= row && row < maze.getnRows() && 0 <= column && column < maze.getnColumns()){
            return cellPanels[row][column];
        }
        
        return null;
    }

    /**
     * Reset the field panel to a new value.
     * 
     * @param newMaze 
     */
    public void reset(Maze newMaze) {
        
        for(int i = 0; i < maze.getnRows(); i++){
            for(int j = 0; j < maze.getnColumns(); j++){
                remove(cellPanels[i][j]);
            }
        }
        
        this.maze = newMaze;
        initializeCellPanels();
        revalidate(); // Because cells have been deleted
        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        reset((Maze) arg);
    }
    
    public MazeProperty getMazeProperty(){
        return mazeProperty;
    }
    
}
