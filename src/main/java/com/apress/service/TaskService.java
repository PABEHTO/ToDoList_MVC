package com.apress.service;

import com.apress.dao.TaskDao;
import com.apress.entity.State;
import com.apress.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> findAllTasks() {
        return taskDao.findAllTasks();
    }

    public void saveTask(String name) {
        if (name != null && !name.isBlank()) {
            taskDao.saveTask(new Task(name, State.IN_PROGRESS));
        }
    }

    public void finishTask(int id) {
        taskDao.finishTask(id);
    }

    public void deleteTask(int id) {
        taskDao.deleteTask(id);
    }
}
