package com.iait.executors;

import com.iait.tasks.PrestamoTask;

public class PrestamoTaskExecutor extends TaskExecutor {

    public <T> T run(PrestamoTask<T> task) {
        return super.run(task);
    }
}
