package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.requests.AddBookRequest;
import com.bookclub_data_manager.models.Task;
import com.bookclub_data_manager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("app/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity addTask(@RequestParam("user_id") int user_id,
                                  @RequestParam("task_name") String task_name,
                                  @RequestParam("task_text") String task_text,
                                  @RequestParam("deadline") Date deadline) {

        String request = taskService.add(user_id, task_name, task_text, deadline, "0");
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Задача добавлена", HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteTask(@RequestParam("task_id") int task_id) {

        String request = taskService.delete(task_id);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Задача удалена", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity updateTask(@RequestParam("user_id") int user_id,
                                  @RequestParam("task_name") String task_name,
                                  @RequestParam("task_text") String task_text,
                                  @RequestParam("deadline") Date deadline,
                                  @RequestParam("task_id") int task_id) {

        String request = taskService.update(user_id, task_name, task_text, deadline, task_id);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Задача обновлена", HttpStatus.OK);
    }


    @PostMapping("/get")
    public ResponseEntity getTask(@RequestParam("task_id") int task_id) {

        Task task = taskService.getTaskById(task_id);
        if(task == null){
            return new ResponseEntity("Ошибка", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity getAllTask() {

        List<Task> tasks = taskService.getAllTasks();
        if(tasks.isEmpty()){
            return new ResponseEntity("Ошибка", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @PostMapping("/markAsDone")
    public ResponseEntity markAsDone(@RequestParam("task_id") int task_id) {

        taskService.markAsDone("1", task_id);
        return new ResponseEntity("Изменено", HttpStatus.OK);
    }

}
