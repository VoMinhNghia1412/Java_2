/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lopjava2;

/**
 *
 * @author Admin
 */
public class NoTaxProduct extends Product{

    public NoTaxProduct(String name , double price) {
        super(name,price);
    }
    
    public double getImportTax(){
    return 0;
    }
    
}
