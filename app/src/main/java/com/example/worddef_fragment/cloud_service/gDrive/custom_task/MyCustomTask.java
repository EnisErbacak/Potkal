package com.example.worddef_fragment.cloud_service.gDrive.custom_task;

import java.util.concurrent.Callable;

public class MyCustomTask implements Callable {

    public MyCustomTask(String wasd) {
    }

    @Override
    public Long call() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("---THREAD_NAME: "+Thread.currentThread().getId());
        System.out.println("--------------------------------------------------------------------------------------------------\n" +
                "--------------------------------------------------------------------------------------------------");
        Thread.sleep(3000);
        System.out.println("CHILD FINISHED");

        return System.currentTimeMillis();
    }
}
