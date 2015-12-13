/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.exceptions;

/**
 * Exception when the input file is not valid
 */
public class InputMazeException extends Exception{
    
    public InputMazeException(String message){
        super(message);
    }
}
