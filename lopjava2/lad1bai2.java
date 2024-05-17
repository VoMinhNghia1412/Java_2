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
public class lad1bai2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Product[] products = new Product[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Nhập sản phẩm thứ  " + (i+1)+";");
            System.out.println("Nhập tên sản phẩm");
            String name = scanner.nextLine();
            System.out.println("Nhập giá sản phẩm ");
            double price = Double.parseDouble(scanner.nextLine());
            products[i]= new Product(name,price);
            
           
        }
        System.out.println("\n Thông tin của sản phẩm");
        for (int i = 0; i < 5; i++) {
            System.out.println("Sản phẩm thứ"+(i+1)+" ");
            System.out.println("Tên của sản phẩm " + products[i].getName());
            System.out.println("Giá của sản phẩm " +products[i].getPrice());
            System.out.println("Thuế của sản phẩm là"+ products[i].getImportTax());
            products[i].insert();
            products[i].update();
            products[i].select();
            products[i].delete();
        }
        
        
    }
}
