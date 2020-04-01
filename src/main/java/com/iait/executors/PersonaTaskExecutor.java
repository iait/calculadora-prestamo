package com.iait.executors;

import com.iait.tasks.PersonaTask;

public class PersonaTaskExecutor extends TaskExecutor {

    public <T> T run(PersonaTask<T> task) {
        return super.run(task);
    }
}
