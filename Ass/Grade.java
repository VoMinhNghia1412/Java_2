/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ass;

/**
 *
 * @author Admin
 */
public class Grade {
    int id ;
    String masv ;
    double tienganh ;
    double tinhoc;
    double gdtc ;

    Grade(int i, String sV001, String nguyen_Van_A, double d, double d0, double d1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public double getTienganh() {
        return tienganh;
    }

    public void setTienganh(double tienganh) {
        this.tienganh = tienganh;
    }

    public double getTinhoc() {
        return tinhoc;
    }

    public void setTinhoc(double tinhoc) {
        this.tinhoc = tinhoc;
    }

    public double getGdtc() {
        return gdtc;
    }

    public void setGdtc(double gdtc) {
        this.gdtc = gdtc;
    }

    public Grade(int id, String masv, double tienganh, double tinhoc, double gdtc) {
        this.id = id;
        this.masv = masv;
        this.tienganh = tienganh;
        this.tinhoc = tinhoc;
        this.gdtc = gdtc;
    }

    public Grade() {
    }

    
}
