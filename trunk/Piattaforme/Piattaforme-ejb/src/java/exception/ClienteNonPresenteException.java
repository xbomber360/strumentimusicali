/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exception;

/**
 *
 * @author maidenfp
 */
public class ClienteNonPresenteException extends Exception{

    public ClienteNonPresenteException() {
    }

    public ClienteNonPresenteException(String message) {
        super(message);
    }
    
}
