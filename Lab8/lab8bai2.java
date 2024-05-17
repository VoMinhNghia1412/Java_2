/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab8;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class lab8bai2 {

    public static void main(String[] args) {

        ArrayList<Integer> myArray = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            myArray.add(i);
        }

        for (Integer num : myArray) {
            System.out.print(num + " ");
        }
    }
}
