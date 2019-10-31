package com.example.demo.test;

public class hoop implements Runnable {
    static hoop hoop=new hoop();

    public static void main(String[] args) throws InterruptedException {
        Thread t1= new Thread(hoop);
        Thread t2= new Thread(hoop);
        t1.start();
        t2.start();
        while (t1.isAlive()&&t2.isAlive()){

        }
        System.out.println();
    }
    @Override
    public void run() {
        synchronized (this){
            System.out.println("当前线程为"+hoop);
        }
    }
}
