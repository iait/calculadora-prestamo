package com.iait.executor;

import com.iait.task.Task;

public class TaskExecutor {

    public <T> T run(Task<T> task) {
        return task.execute();
    }
}
