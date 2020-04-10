package com.lfy.threadPool;

import java.util.Vector;

/**
 * 线程池
 */
public class ThreadPoolManager {

    private int maxSize;
    private Vector<SimpleThread> pool;

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    // 初始化线程池
    public ThreadPoolManager(int maxSize) {
        setMaxSize(maxSize);
        System.out.println("Start Pool ...");
        pool = new Vector();
        for (int i = 1; i <= maxSize; i++) {
            SimpleThread thread = new SimpleThread(i);
            pool.addElement(thread);
            thread.start();
        }
    }

    //管理线程池
    public void process(String argument) {
        for (int i = 0; i < pool.size(); i++) {
            SimpleThread thread = pool.elementAt(i);
            if (!thread.isRun()) {
                System.out.println("Thread "+(i+1)+" is running argument["+argument+"].");
                thread.setArgument(argument);
                thread.setRunning(true);
            }
            if (i == pool.size()) {
                System.out.println("pool is full...");
            }
        }
    }

}
