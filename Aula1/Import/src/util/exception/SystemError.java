/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author jadir
 */
public class SystemError extends Exception {
//Ctrl espaço
    public SystemError(String message) {
        super(message);
    }

    public SystemError(String message, Throwable cause) {
        super(message, cause);
    }

}