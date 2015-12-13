/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.buttons;

import gui.panel.CommandPanel;
import gui.panel.FieldPanel;
import gui.property.ExplorerProperty;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.Timer;

/**
 *
 */
public class PlayPauseButton extends JToggleButton implements ActionListener {
    
    private final ImageIcon play, pause;
    
    private final ExplorerProperty explorerProperty;
    
    private final CommandPanel commandPanel;
    
    private final FieldPanel fieldPanel;
    
    // To animate automatic exploration to exit
    private final Timer timer;
    
    
    public PlayPauseButton(CommandPanel commandPanel){
        
        Path path;
        
        this.commandPanel = commandPanel;
        this.explorerProperty = commandPanel.getExplorerProperty();
        this.fieldPanel = commandPanel.getFieldPanel();

        path = Paths.get("src", "main", "resources", "images", "play.png");
        this.play = new ImageIcon(path.toString());
        
        path = Paths.get("src", "main", "resources", "images", "pause.png");
        this.pause = new ImageIcon(path.toString());
        
        setIcon(play);
        addActionListener(this);
        
        timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                explorerProperty.getExplorer().makeStep();
                if(explorerProperty.getExplorer().isOut()){
                    timer.stop();
                    setSelected(false);
                    setIcon(play);
                    
                }
                fieldPanel.repaint();
            }
        });
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if(isSelected()){
            commandPanel.deactivate();
            setIcon(pause);
            commandPanel.getExplorerProperty().getExplorer().initializePathToExit();
            timer.start();
        }
        else{
            commandPanel.activate();
            setIcon(play);
            timer.stop();
        }
    }
    
    /**
     * Reset play pause button to its original state.
     * 
     */
    public void reset(){
        setIcon(play);
        setSelected(false);
        timer.stop();
    }
}
