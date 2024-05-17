/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lad2lamlai;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class NameInputVerifier extends InputVerifier {

     @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        return text.matches("[^0-9]+");
    }
    
}
