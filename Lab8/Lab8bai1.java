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
public class Lab8bai1 {

    public static void main(String[] args) {

        ArrayList<Object> arrayList = new ArrayList<>();

        arrayList.add(10);
        arrayList.add(3.14);
        arrayList.add(true);
        arrayList.add("Hello");

        for (Object obj : arrayList) {
            System.out.println(obj);
        }
    }
}
