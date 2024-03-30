package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Poll;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends CrudRepository<Poll, Integer> {

    @Query(value = "SELECT * FROM polls WHERE poll_id = :poll_id", nativeQuery = true)
    Poll getPollById(@Param("poll_id")int poll_id);

    @Query(value = "SELECT * FROM polls WHERE is_active = true", nativeQuery = true)
    List<Poll> getAllActivePolls();

    @Query(value = "SELECT * FROM polls WHERE is_active = false", nativeQuery = true)
    List<Poll> getAllArchivedPolls();

    @Query(value = "SELECT * FROM polls WHERE is_active = true AND poll_id NOT IN (SELECT poll_id FROM user_and_poll WHERE user_id = :user_id)", nativeQuery = true)
    List<Poll> getAllUserPolls(@Param("user_id")int user_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO polls (question, var1, var2, var3, var4, counter1, counter2, counter3, counter4, counterN1, counterN2, counterN3, counterN4, is_active) VALUES (:question, :var1, :var2, :var3, :var4, :counter1, :counter2, :counter3, :counter4, :counterN1, :counterN2, :counterN3, :counterN4, :is_active)", nativeQuery = true)
    void add(@Param("question")String question, @Param("var1")String var1, @Param("var2") String var2, @Param("var3")String var3, @Param("var4")String var4, @Param("counter1")int counter1, @Param("counter2") int counter2, @Param("counter3")int counter3, @Param("counter4")int counter4,  @Param("counterN1")int counterN1, @Param("counterN2") int counterN2, @Param("counterN3")int counterN3, @Param("counterN4")int counterN4, @Param("is_active")boolean is_active);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM polls WHERE poll_id = :poll_id", nativeQuery = true)
    void delete(@Param("poll_id")int poll_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE polls SET is_active = false WHERE poll_id = :poll_id", nativeQuery = true)
    void archive(@Param("poll_id")int poll_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE polls SET question = :question, var1 = :var1, var2 = :var2, var3 = :var3, var4 = :var4 WHERE poll_id = :poll_id", nativeQuery = true)
    void correct(@Param("question")String question, @Param("var1")String var1, @Param("var2") String var2, @Param("var3")String var3, @Param("var4")String var4, @Param("poll_id")int poll_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE polls SET counter1 = counter1 + :counter1, counter2 = counter2 + :counter2, counter3 = counter3 + :counter3, counter4 = counter4 + :counter4 WHERE poll_id = :poll_id", nativeQuery = true)
    void updateByOld(@Param("counter1")int counter1, @Param("counter2") int counter2, @Param("counter3")int counter3, @Param("counter4")int counter4, @Param("poll_id")int poll_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE polls SET counterN1 = counterN1 + :counterN1, counterN2 = counterN2 + :counterN2, counterN3 = counterN3 + :counterN3, counterN4 = counterN4 + :counterN4 WHERE poll_id = :poll_id", nativeQuery = true)
    void updateByNew(@Param("counterN1")int counterN1, @Param("counterN2") int counterN2, @Param("counterN3")int counterN3, @Param("counterN4")int counterN4, @Param("poll_id")int poll_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_and_poll (user_id, poll_id) VALUES (:user_id, :poll_id)", nativeQuery = true)
    void setDependency(@Param("user_id")int user_id, @Param("poll_id")int poll_id);

}
