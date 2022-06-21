package tech.codingclub.utility;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskManager {
    private int threadcount;
    private ExecutorService executorService;
    public TaskManager(int threadcount){
        this.threadcount=threadcount;
        this.executorService= Executors.newFixedThreadPool(threadcount);

    }
    public void waittillqueueisfreeandadd(Runnable runnable) {
        while (getQueuesize() >= threadcount) {

            try {
                System.out.println("sleeping");
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        addtask(runnable);
    }
    public void addtask(Runnable runnable){
        this.executorService.submit(runnable);
    }
    private int getQueuesize(){
        ThreadPoolExecutor executor =(ThreadPoolExecutor)executorService;
        return executor.getQueue().size();
    }
}
