/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu;

import maze.exceptions.InputMazeException;
import gui.panel.GamePanel;
import gui.panel.InfoPanel;
import gui.property.MazeProperty;
import maze.model.Maze;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 * Load a new maze.
 */
public class LoadMazeMenuItem extends JMenuItem implements ActionListener {
    
    private JFileChooser fc;
    
    private final MazeProperty mazeProperty;
    
    public LoadMazeMenuItem(String label, MazeProperty mazeProperty){
        super(label);
        this.mazeProperty = mazeProperty;
        
        addActionListener(this);
        
        File mazeDirectory = Paths.get("src", "main", "resources").toFile();
        fc = new JFileChooser(mazeDirectory);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File maze = fc.getSelectedFile();
            if(maze != null){
                
                try {
                    mazeProperty.setMaze(new Maze(maze));
                } catch (InputMazeException ex) {
                    System.err.println("Exception: "+ex.getMessage());
                }
            }
        }
    }
}
