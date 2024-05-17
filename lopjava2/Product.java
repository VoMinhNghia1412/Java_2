/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lopjava2;

/**
 *
 * @author Admin
 */
public class Product implements DAO{
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
        name="";
        price=0;
    }
    public double getImportTax(){
        return  price*0.1;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void insert() {
        System.out.println("Thêm mới thành công");
    }

    @Override
    public void update() {
        System.out.println("Update thành công");
    }

    @Override
    public void delete() {
       System.out.println("Xóa thành công");
    }

    @Override
    public void select() {
        System.out.println("Truy vấn thành công");
    }
    
}
        