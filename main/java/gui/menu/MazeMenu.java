/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu;

import gui.panel.GamePanel;
import gui.panel.InfoPanel;
import gui.property.MazeProperty;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 */
public class MazeMenu extends JMenuBar {
    
    public MazeMenu(GamePanel gamePanel, InfoPanel infoPanel, MazeProperty mazeProperty){
        
        JMenu menu = new JMenu("Game");

        JMenuItem loadMaze = new LoadMazeMenuItem("Load Maze...", mazeProperty);        
        JMenuItem restart = new RestartMenuItem("Restart", gamePanel);

        menu.add(loadMaze);
        menu.add(restart);
        add(menu);
    }
}
