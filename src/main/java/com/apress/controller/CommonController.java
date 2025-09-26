package com.apress.controller;

import com.apress.entity.State;
import com.apress.entity.Task;
import com.apress.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CommonController {
    private final TaskService taskService;

    @Autowired
    public CommonController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getMainPage(Model model) {
        List<Task> tasks =  taskService.findAllTasks();
        int doneTasksQuantity = (int)tasks.stream().filter(t -> t.getState().equals(State.DONE)).count();
        int activeTasksQuantity = (int)tasks.stream().filter(t -> t.getState().equals(State.IN_PROGRESS)).count();
        model.addAttribute("numberOfDoneTasks", doneTasksQuantity);
        model.addAttribute("numberOfActiveTasks", activeTasksQuantity);
        return "main-page";
    }
}
