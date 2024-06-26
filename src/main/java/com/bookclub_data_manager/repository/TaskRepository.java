package com.bookclub_data_manager.repository;
import com.bookclub_data_manager.models.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    @Query(value = "SELECT * FROM tasks WHERE task_id = :task_id", nativeQuery = true)
    Task getTaskById(@Param("task_id")int task_id);

    @Query(value = "SELECT * FROM tasks", nativeQuery = true)
    List<Task> getAllTasks();

    @Query(value = "SELECT * FROM tasks WHERE user_id = :user_id", nativeQuery = true)
    List<Task> getAllTasksByUser(@Param("user_id")int user_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tasks (task_name, task_text, deadline, is_done, user_id) VALUES (:task_name, :task_text, :deadline, :is_done, :user_id)", nativeQuery = true)
    void add(@Param("task_name")String task_name, @Param("task_text")String task_text, @Param("deadline") Date deadline, @Param("is_done")String is_done, @Param("user_id")int user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tasks WHERE task_id = :task_id", nativeQuery = true)
    void delete(@Param("task_id")int task_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tasks SET user_id = :user_id, task_name = :task_name, task_text = :task_text, deadline = :deadline WHERE task_id = :task_id", nativeQuery = true)
    void update(@Param("user_id")int user_id, @Param("task_name")String task_name, @Param("task_text")String task_text, @Param("deadline") Date deadline, @Param("task_id")int task_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tasks SET is_done = :is_done WHERE task_id = :task_id", nativeQuery = true)
    void markAsDone(@Param("is_done") String is_done, @Param("task_id")int task_id);

}
