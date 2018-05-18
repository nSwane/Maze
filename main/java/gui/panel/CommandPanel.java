/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panel;

import gui.buttons.PlayPauseButton;
import gui.property.ExplorerProperty;
import maze.model.Cell;
import maze.model.Direction;
import maze.model.Item;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Swing component to manage actions on the Explorer
 */
public class CommandPanel extends JPanel implements ActionListener, Observer {
    
    private final FieldPanel field;
    
    private final ExplorerProperty explorerProperty;
    
    private final JButton turnLeft, turnRight, moveForward;
    
    // Automatically head to the exit
    private final PlayPauseButton playPause;
    
    public CommandPanel(FieldPanel field, ExplorerProperty explorerProperty){
        this.field = field;
        
        this.explorerProperty = explorerProperty;
        this.explorerProperty.addObserver((Observer) this);
        
        setLayout(new GridBagLayout());
        
        // Turn left button
        Path tl = Paths.get("main", "resources", "images", "turn-left.png");
        Icon iconTl = new ImageIcon(tl.toString());        
        turnLeft = new JButton(iconTl);
        turnLeft.setActionCommand("TURN_LEFT");
        turnLeft.addActionListener(this);
        
        // Turn right button
        Path tr = Paths.get("main", "resources", "images", "turn-right.png");
        Icon iconTr = new ImageIcon(tr.toString());        
        turnRight = new JButton(iconTr);
        turnRight.setActionCommand("TURN_RIGHT");
        turnRight.addActionListener(this);
        
        // move forward button
        Path mf = Paths.get("main", "resources", "images", "move.png");
        Icon iconMf = new ImageIcon(mf.toString());        
        moveForward = new JButton(iconMf);
        moveForward.setActionCommand("MOVE_FORWARD");
        moveForward.addActionListener(this);
        
        // Play / Pause Button
        playPause = new PlayPauseButton(this);
        
        GridBagConstraints c = new GridBagConstraints();
        
        // Move forward position
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        add(moveForward, c);
        
        // Turn left position
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        add(turnLeft, c);
        
        // play pause position
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        add(playPause, c);
        
        // Turn right position
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        add(turnRight, c);
        
        updateMove();
        
    }

    /**
     * Deactivate controls
     */
    public void deactivate(){
        turnLeft.setEnabled(false);
        turnRight.setEnabled(false);
        moveForward.setEnabled(false);
    }
    
    /**
     * Activate controls
     */
    public void activate(){
        turnLeft.setEnabled(true);
        turnRight.setEnabled(true);
        updateMove();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "MOVE_FORWARD":
                explorerProperty.getExplorer().move();
                if(explorerProperty.getExplorer().isOut()){
                    deactivate();
                }
                break;
                
            case "TURN_LEFT":
                explorerProperty.getExplorer().turn(Direction.LEFT);
                break;
                
            case "TURN_RIGHT":
                explorerProperty.getExplorer().turn(Direction.RIGHT);
                break;
            default:
                
        }
        updateMove();
        field.repaint();
        
        
    }
    
    /**
     * Disable / enable buttons if necessary
     * 
     */
    private void updateMove(){
        Cell cell = explorerProperty.getExplorer().getNextCell();
        if(cell == null || cell.getItem() == Item.WALL){
            moveForward.setEnabled(false);
        }
        else{
            moveForward.setEnabled(true);
        }
    }
    
    public ExplorerProperty getExplorerProperty(){
        return explorerProperty;
    }
    
    public FieldPanel getFieldPanel(){
        return field;
    }
    
    /**
     * Reset commands to their initial values
     */
    public void reset(){
        activate();
        playPause.reset();
    }

    /**
     * Be notified when the maze changes.
     * 
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        reset();
    }

}
