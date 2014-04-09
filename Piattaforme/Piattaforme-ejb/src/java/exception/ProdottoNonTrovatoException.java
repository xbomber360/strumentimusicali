/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exception;

/**
 *
 * @author siciliano
 */
public class ProdottoNonTrovatoException extends Exception{

    public ProdottoNonTrovatoException() {
        
    }
    
    public ProdottoNonTrovatoException(String message) {
        super(message);
    }
    
    
}
