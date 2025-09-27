package com.apress.dao;

import com.apress.entity.State;
import com.apress.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TaskDao {
    private final List<Task> tasks = new ArrayList<Task>(
    Arrays.asList(
            new Task("Buy a ticket", State.IN_PROGRESS),
            new Task("Wipe the table", State.IN_PROGRESS),
            new Task("Fill the water barrel", State.DONE)
            )
    );

    public List<Task> findAllTasks() {
        return new ArrayList<>(tasks); //Так защищённее, ибо копия, которую можно менять без урона оригиналу
    }

    public void saveTask(Task task) {
        tasks.add(task);
    }

    public void finishTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setState(State.DONE);
                break;
            }
        }
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }
}
