/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu;

import gui.panel.GamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 */
public class RestartMenuItem extends JMenuItem implements ActionListener {
    
    private final GamePanel gamePanel;

    public RestartMenuItem(String label, GamePanel gamePanel){
        super(label);
        this.gamePanel = gamePanel;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.reset();
    }
}
