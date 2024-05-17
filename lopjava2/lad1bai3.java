/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lopjava2;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class lad1bai3 {

    private static int i;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Product[] products = new Product[3];
        for (int i = 0; i < 2; i++) {
            System.out.println("Hãy nhập thông sản phẩm thứ "+(i+1));
            System.out.println("Thông tin sản phẩm ");
            String name = scanner.nextLine();
            System.out.println("Nhập giá sản phẩm");
            double price =  Double.parseDouble(scanner.nextLine());
            
           products[i] = new Product(name,price);
            
        }
        
        System.out.println("Nhập sản phẩm không thuế");
        String name = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm");
        double price = Double.parseDouble(scanner.nextLine());
            products[2]= new NoTaxProduct(name,price);
            
            //xuất//
            
            System.out.println(" Thông tin sản phẩm");
            System.out.println("Sản phẩm thứ"+( i+1)+"");
            System.out.println("Tên: " + products[i].getName());
            System.out.println("Thuế nhập khẩu: " + products[i].getImportTax());
            System.out.println();
        
    }
    

    
}
