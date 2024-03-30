package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.MyQuestion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyQuestionRepository {

    @Query(value = "SELECT * FROM questions WHERE question_id = :question_id", nativeQuery = true)
    MyQuestion getQuestionById(@Param("question_id") int question_id);

    @Query(value = "SELECT * FROM questions WHERE question_name = :question_name", nativeQuery = true)
    Integer getQuestionIdByName(@Param("question_name")String question_name);

    @Query(value = "SELECT * FROM test_and_questions WHERE test_id = :test_id", nativeQuery = true)
    List<MyQuestion> getQuestionByTestId(@Param("test_id") int test_id);

    @Query(value = "SELECT * FROM questions", nativeQuery = true)
    List<MyQuestion> getAllQuestions();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO questions (question_name, var1, var2, var3, var4) VALUES (:test_id, :question_name, :var1, :var2, :var3, :var4)", nativeQuery = true)
    void addQuestion(@Param("question_name")String test_name, @Param("var1")String var1, @Param("var2")String var2, @Param("var3")String var3, @Param("var4")String var4);

    @Modifying
    @Transactional
    @Query(value = "UPDATE questions SET question_name = :question_name, var1 = :var1, var2 = :var2, var3 = :var3, var4 = :var4 WHERE question_id = :question_id", nativeQuery = true)
    void updateQuestion(@Param("question_name")String question_name, @Param("var1")String var1, @Param("var2")String var2, @Param("var3")String var3, @Param("var4")String var4, @Param("question_id") int question_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM questions WHERE question_id = :question_id", nativeQuery = true)
    void deleteQuestion(@Param("question_id")int question_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM test_and_question WHERE question_id = :question_id", nativeQuery = true)
    void unsetTestAndQuestion(@Param("question_id")int question_id);

}
