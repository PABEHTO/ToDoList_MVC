package com.apress.controller;

import com.apress.entity.State;
import com.apress.entity.Task;
import com.apress.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("tasks", tasks);
        model.addAttribute("numberOfDoneTasks", doneTasksQuantity);
        model.addAttribute("numberOfActiveTasks", activeTasksQuantity);
        return "main-page";
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public String addTask(@RequestParam String name) {
        taskService.saveTask(name);
        return "redirect:/home";
    }

    @RequestMapping("/")
    public String goToHome() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/finish-task", method = RequestMethod.POST)
    public String finishTask(@RequestParam int id) {
        taskService.finishTask(id);
        return "redirect:/home";
    }

    @RequestMapping(value="/delete-task", method = RequestMethod.POST)
    public String deleteTask(@RequestParam int id) {
        taskService.deleteTask(id);
        return "redirect:/home";
    }
}
