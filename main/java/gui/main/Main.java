/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.main;

import gui.menu.MazeMenu;
import gui.panel.GamePanel;
import gui.panel.InfoPanel;
import gui.property.ExplorerProperty;
import gui.property.MazeProperty;
import maze.model.Maze;
import java.awt.Dimension;
import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI(); 
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame("Maze");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(350);
        
        try {
            
            File file = Paths.get("main", "resources", "ExampleMaze.txt").toFile();
            Maze maze = new Maze(file);
        
            // Ensure that all components have the same reference to the maze at any moment
            MazeProperty mazeProperty = new MazeProperty(maze);
            ExplorerProperty explorerProperty = new ExplorerProperty(mazeProperty);
            
            // Game panel
            GamePanel game = new GamePanel(mazeProperty, explorerProperty);
            
            // Info panel
            InfoPanel info = new InfoPanel(game);
            
            splitPane.add(game, 0);
            splitPane.add(info, 1);
            
            // Menu
            MazeMenu menu = new MazeMenu(game, info, mazeProperty);
            
            f.setJMenuBar(menu);
            f.add(splitPane);
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        f.setSize(new Dimension(500, 500));
//        f.pack();
        f.setResizable(true);
        f.setVisible(true);
    } 
}
