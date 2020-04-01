package com.iait.executors;

import com.iait.tasks.Task;

public abstract class TaskExecutor {
    
    protected <T> T run(Task<T> task) {
        return task.execute();
    }
}
