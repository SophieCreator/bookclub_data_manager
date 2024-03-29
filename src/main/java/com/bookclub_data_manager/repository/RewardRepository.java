package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Genre;
import com.bookclub_data_manager.models.Reward;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface RewardRepository extends CrudRepository<Reward, Integer> {

    @Query(value = "SELECT * FROM rewards WHERE reward_id = :reward_id", nativeQuery = true)
    Reward getRewardById(@Param("reward_id")int reward_id);

    @Query(value = "SELECT * FROM rewards", nativeQuery = true)
    List<Reward> getAllRewards();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO rewards (name, reason, promo, image, tests_required, meetings_required, sale) VALUES (:name, :reason, :promo, :image, :tests_required, :meetings_required, :sale)", nativeQuery = true)
    void add(@Param("name")String name, @Param("reason")String reason, @Param("promo")String promo, @Param("image") String image, @Param("tests_required")int tests_required, @Param("meetings_required")int meetings_required, @Param("sale")int sale);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM rewards WHERE reward_id = :reward_id", nativeQuery = true)
    void delete(@Param("reward_id")int reward_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE rewards SET name = :name, reason = :reason, promo = :promo, image = :image, tests_required = :tests_required, meetings_required = :meetings_required, sale = :sale WHERE reward_id = :reward_id", nativeQuery = true)
    void update(@Param("name")String name, @Param("reason")String reason, @Param("promo")String promo, @Param("image") String image, @Param("tests_required")int tests_required, @Param("meetings_required")int meetings_required, @Param("sale")int sale, @Param("reward_id")int reward_id);

    @Query(value = "SELECT * FROM rewards WHERE reward_id IN (SELECT reward_id FROM user_and_reward WHERE user_id = :user_id AND is_used = false)", nativeQuery = true)
    List<Reward> getUserActiveRewards(@Param("user_id")int user_id);

    @Query(value = "SELECT * FROM rewards WHERE reward_id IN (SELECT reward_id FROM user_and_reward WHERE user_id = :user_id AND is_used = true)", nativeQuery = true)
    List<Reward> getUserPassiveRewards(@Param("user_id")int user_id);

    @Query(value = "SELECT * FROM rewards WHERE promo = :promo AND reward_id IN (SELECT reward_id FROM user_and_reward WHERE user_id = :user_id AND is_used = false)", nativeQuery = true)
    Reward checkRewardIsGotAndUsed(@Param("promo")String promo, @Param("user_id")int user_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_and_reward (user_id, reward_id, is_used) VALUES (:user_id, :reward_id, :is_used)", nativeQuery = true)
    void setDependency(@Param("user_id")int user_id, @Param("reward_id")int reward_id, @Param("is_used")boolean is_used);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_and_reward WHERE reward_id = :reward_id", nativeQuery = true)
    void unsetDependency(@Param("reward_id")int reward_id);

    @Query(value = "SELECT promo FROM rewards WHERE promo = :promo", nativeQuery = true)
    List<String> promoIsAvailable(@Param("promo")String promo);

    @Query(value = "SELECT promo FROM rewards WHERE promo = :promo AND reward_id = :reward_id", nativeQuery = true)
    List<String> promoIsMine(@Param("promo")String promo, @Param("reward_id")int reward_id);

    @Query(value = "SELECT user_id FROM user_and_reward WHERE user_id = :user_id AND reward_id = :reward_id", nativeQuery = true)
    List<Integer> userHasReward(@Param("user_id")int user_id, @Param("reward_id")int reward_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE rewards SET is_used = true WHERE user_id = :user_id AND reward_id = :reward_id", nativeQuery = true)
    void setRewardUsed(@Param("user_id")int user_id, @Param("reward_id")int reward_id);

}
