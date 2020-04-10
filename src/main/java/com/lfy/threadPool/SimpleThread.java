package com.lfy.threadPool;

/**
 * 简单的工作线程类
 */
public class SimpleThread extends Thread{

    private String argument;
    private boolean runFlag;

    public String getArgument() {
        return argument;
    }
    public void setArgument(String argument) {
        this.argument = argument;
    }
    public boolean isRun() {
        return runFlag;
    }
    public synchronized void setRunning(boolean runFlag) {
        this.runFlag = runFlag;
        if (runFlag) {
            this.notify();
        }
    }

    public SimpleThread(int threadNumber) {
        runFlag = false;
        System.out.println("Thread:["+threadNumber+"] start....");
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                if (!runFlag) {
                    this.wait();
                } else {
                    System.out.println("process "+getArgument()+" done...");
                    sleep(5000);
                    System.out.println("Thread is sleeping...");
                    setRunning(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
