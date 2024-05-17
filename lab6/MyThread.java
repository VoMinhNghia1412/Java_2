/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab6;

/**
 *
 * @author Admin
 */
public class MyThread implements Runnable{
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Thread: " + Thread.currentThread().getName() + " - Count: " + i);
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        Thread thread1 = new Thread((Runnable) myThread);
        thread1.setName("Thread1");
        thread1.setPriority(Thread.MAX_PRIORITY);

        Thread thread2 = new Thread((Runnable) myThread);
        thread2.setName("Thread2");
        thread2.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
    }
}
