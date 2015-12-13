/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Enter coordinates and see what exists at that point
 * 
 */
public class CoordinatesPanel extends JPanel {
    
    
    public CoordinatesPanel(final FieldPanel fieldPanel){
        
        setLayout(new GridBagLayout());
        
        JLabel title = new JLabel("Coordinates: ");
        
        // Get and verify input for row and column value
        int maxDigits = 3;
        NumberFormat formatRow = NumberFormat.getNumberInstance();
        formatRow.setMaximumIntegerDigits(maxDigits);
        
        NumberFormat formatColumn = NumberFormat.getNumberInstance();
        formatColumn.setMaximumIntegerDigits(maxDigits);
        
        final JFormattedTextField textFieldRow = new JFormattedTextField(formatRow); // Row
        textFieldRow.setText("0");
        textFieldRow.setSize(50, 50);
        
        final JFormattedTextField textFieldColumn = new JFormattedTextField(formatColumn); // Column
        textFieldColumn.setText("0");
        
        // Trigger blinking
        JButton seeAtCoordinates = new JButton();
        
        seeAtCoordinates.add(new JLabel("See"));
        seeAtCoordinates.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                int row = Integer.parseInt(textFieldRow.getText());
                int column = Integer.parseInt(textFieldColumn.getText());
                CellPanel c = fieldPanel.getCellPanelAt(row, column);
                if(c != null){
                    c.blink();
                }
                
            }
        });
        
        JPanel coordinates = new JPanel();
        coordinates.setLayout(new GridLayout(1, 2));
        coordinates.add(textFieldRow);
        coordinates.add(textFieldColumn);
        
        GridBagConstraints c = new GridBagConstraints();
        
        // Title position
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(title, c);
        
        // Coordinates position
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        add(coordinates, c);
        
        // Button position
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        add(seeAtCoordinates, c);
    }
}
