/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab6;

/**
 *
 * @author Admin
 */
public class TestThread {
    public static void main(String[] args) {
        OddThread oddThread = new OddThread();
        EvenThread evenThread = new EvenThread();
        
        
        oddThread.start();
        try {
            oddThread.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        evenThread.start();
    }
}
