/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panel;

import gui.property.ExplorerProperty;
import gui.utils.ItemLoader;
import maze.model.Cell;
import maze.model.Explorer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CellPanel extends JPanel {
    
    private final ItemLoader itemLoader;
    private final Cell cell;
    private final JLabel label;
    
    // Timer managing cell blinking
    private final Timer timer;
    
    private ExplorerProperty explorerProperty;
    
    public CellPanel(Cell cell, ExplorerProperty explorerProperty) {
        this.explorerProperty = explorerProperty;
        this.itemLoader = ItemLoader.getInstance();
        this.cell = cell;
        label = new JLabel();
        add(label);
        
        // Blink
        timer = new Timer(1, new BlinkCell());
        timer.setInitialDelay(0); // First blink
        timer.setDelay(1000); // blink last 1sec
        
    }
    
    /**
     * Make the cell blinking one time
     */
    public void blink(){
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        ImageIcon icon;
        try {
            icon = itemLoader.load(cell.getItem());
            label.setIcon(icon);
            
            // To avoid canceling the blink
            if(!timer.isRunning()){
                
                // Highlight explorer
                switch(cell.getItem()){
                    case EXPLORER_HEADING_NORTH:
                    case EXPLORER_HEADING_SOUTH:
                    case EXPLORER_HEADING_EAST:
                    case EXPLORER_HEADING_WEST:
                        label.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
                        break;
                    default:
                        
                        // Highlight where the explorer has been
                        Explorer explorer = explorerProperty.getExplorer();
                        
                        // All cells he visited
                        List<Cell> record = explorer.getRecord();
                        if(record.contains(cell)){
                            label.setBorder(BorderFactory.createLineBorder(Color.green));
                        }
                        else{
                            label.setBorder(BorderFactory.createEmptyBorder());
                        }
                        
                }
                
            }
        } catch (Exception ex) {
            Logger.getLogger(CellPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    class BlinkCell implements ActionListener {
        
        // Number of time the timer has been triggered
        private int nOccured;
        
        public BlinkCell(){
            nOccured = 0;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            // Stop blinking after 1 tic
            if(nOccured > 1){
                label.setBorder(BorderFactory.createEmptyBorder());
                timer.stop();
                nOccured = 0;
            }
            else{
                label.setBorder(BorderFactory.createLineBorder(Color.green, 3));
            }
            
            nOccured++;
        }
        
        
    }
}
