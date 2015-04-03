package com.mycrawler.test;


public class TestThread implements Runnable {     
    
    private String name;     
    private Object prev;     
    private Object self;     
    
    private TestThread(String name, Object prev, Object self) {     
        this.name = name;     
        this.prev = prev;     
        this.self = self;     
    }     
    
    public void run() {     
        int count = 10;     
        while (count > 0) {     
            synchronized (prev) {     
                synchronized (self) {     
                    System.out.print(name);     
                    count--;    
                    try
                    {
                        Thread.sleep(1);
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }  
                    self.notify();     
                }     
                try {     
                    prev.wait();     
                } catch (InterruptedException e) {     
                    e.printStackTrace();     
                }     
            }     
    
        }     
    }     
    
    public static void main(String[] args) throws Exception {     
        Object a = new Object();     
        Object b = new Object();     
        Object c = new Object();     
        TestThread pa = new TestThread("A", b, a);   
        TestThread pb = new TestThread("B", c, b);     
        TestThread pc = new TestThread("C", a, c);     
             
             
        new Thread(pa).start();  
        Thread.sleep(10);
        new Thread(pb).start();  

        new Thread(pc).start();    
        
//        System.exit(0);
        
    }     
}    


