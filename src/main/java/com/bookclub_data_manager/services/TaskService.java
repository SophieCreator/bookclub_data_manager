package com.bookclub_data_manager.services;

import com.bookclub_data_manager.models.Task;
import com.bookclub_data_manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public String add(int user_id, String task_name, String task_text, Date deadline, String is_done){
        if (task_name == null){
            return "Название задачи пустое";
        }
        taskRepository.add(user_id, task_name, task_text, deadline, is_done);
        return "OK";
    }

    public String delete(int task_id){
        if (taskRepository.getTaskById(task_id) == null){
            return "Не существует такого идентификатора";
        }
        taskRepository.delete(task_id);
        return "OK";
    }

    public String update(int user_id, String task_name, String task_text, Date deadline, int task_id){
        if (task_name == null){
            return "Название задачи пустое";
        }
        if (taskRepository.getTaskById(task_id) == null){
            return "Не существует такого идентификатора";
        }
        taskRepository.update(user_id, task_name, task_text, deadline, task_id);
        return "OK";
    }

    public List<Task> getAllTasks(){
        return taskRepository.getAllTasks();
    }

    public Task getTaskById(int id){
        return taskRepository.getTaskById(id);
    }

    public String markAsDone(String is_done, int task_id){
        if (taskRepository.getTaskById(task_id) == null){
            return "Не существует такого идентификатора";
        }
        taskRepository.markAsDone(is_done, task_id);
        return "OK";
    }

}
