/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panel;

import gui.property.ExplorerProperty;
import gui.property.MazeProperty;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * Game panel i.e graphics + control
 * 
 */
public class GamePanel extends JPanel {
    private final FieldPanel field;
    private final CommandPanel commands;
    
    private final MazeProperty maze;
    private final ExplorerProperty explorerProperty;
    
    public GamePanel(MazeProperty maze, ExplorerProperty explorerProperty) {
        
        this.maze = maze;
        this.explorerProperty = explorerProperty;
        
        setLayout(new BorderLayout());

        // Field panel
        field = new FieldPanel(maze, explorerProperty);

        // Commands panel
        commands = new CommandPanel(field, explorerProperty);

        add(field, BorderLayout.CENTER);
        add(commands, BorderLayout.SOUTH);
        
    }
    
    public MazeProperty getMazeProperty(){
        return maze;
    }
    
    public ExplorerProperty getExplorerProperty(){
        return explorerProperty;
    }
    
    public CommandPanel getCommandPanel(){
        return commands;
    }
    
    public FieldPanel getFieldPanel(){
        return field;
    }
    
    /**
     * Reset game panel to its original state.
     */
    public void reset(){
        explorerProperty.getExplorer().restart();
        commands.reset();
        field.repaint();
    }

}
