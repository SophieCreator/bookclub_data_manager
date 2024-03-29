package com.bookclub_data_manager.services;

import com.bookclub_data_manager.models.Reward;
import com.bookclub_data_manager.repository.RewardRepository;

import com.bookclub_data_manager.services.meeting.MyRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RewardService {

    @Autowired
    RewardRepository rewardRepository;
    @Autowired
    MyRecordService myRecordService;

    public Reward getRewardById(int reward_id){
        return rewardRepository.getRewardById(reward_id);
    }

    public String add(String name, String reason, String promo, String image, int tests_required, int meetings_required, int sale){
        if (name == null && reason == null){
            return "Поля не могут быть пустыми";
        }
        if (promo != null && !promo.isEmpty() && !rewardRepository.promoIsAvailable(promo).isEmpty()){
            return "Промо не может быть уже использованным";
        }
        rewardRepository.add(name, reason, promo, image, tests_required, meetings_required, sale);
        return "OK";
    }

    public String deleteEverywhere(int reward_id){
        if (getRewardById(reward_id) == null){
            return "Нет такого идентификатора";
        }
        rewardRepository.delete(reward_id);
        rewardRepository.unsetDependency(reward_id);
        return "OK";
    }

    public String deleteFromCurrent(int reward_id){
        if (getRewardById(reward_id) == null){
            return "Нет такого идентификатора";
        }
        rewardRepository.delete(reward_id);
        rewardRepository.unsetDependency(reward_id);
        return "OK";
    }

    public String update(String name, String reason, String promo, String image, int tests_required, int meetings_required, int sale, int reward_id){
        if (getRewardById(reward_id) == null){
            return "Нет такого идентификатора";
        }
        if (name == null && reason == null){
            return "Поля не могут быть пустыми";
        }
        if (!rewardRepository.promoIsAvailable(promo).isEmpty() && !promoIsMine(promo, reward_id)){
            return "Промокод уже занят";
        }
        rewardRepository.update(name, reason, promo, image, tests_required, meetings_required, sale, reward_id);
        return "OK";
    }

    public List<Reward> getUserActiveRewards(int user_id){
        return rewardRepository.getUserActiveRewards(user_id);
    }

    public List<Reward> getUserPassiveRewards(int user_id){
        return rewardRepository.getUserPassiveRewards(user_id);
    }

    public Reward checkRewardIsGotAndUsed(String promo, int user_id){
        return rewardRepository.checkRewardIsGotAndUsed(promo, user_id);
    }

    public List<Reward> getAllRewards(){
        return rewardRepository.getAllRewards();
    }

    public List<Reward> newRewards(int user_id){
        int user_meeting_required = myRecordService.getMyRecordsByUser(user_id).size();
        int user_tests_required = 2;
        List<Reward> newRewards = new ArrayList<>();

        List<Reward> allRewards = getAllRewards();
        for (Reward reward : allRewards){
            if (user_meeting_required >= reward.getMeetings_required()
                && user_tests_required >= reward.getTests_required()
                && rewardRepository.userHasReward(user_id, reward.getReward_id()).isEmpty())
            {
                rewardRepository.setDependency(user_id, reward.getReward_id(), false);
                newRewards.add(reward);
            }
        }
        return newRewards;
    }

    public String setRewardUsed(int reward_id, int user_id){
        rewardRepository.setRewardUsed(user_id, reward_id);
        return "OK";
    }

    public boolean promoIsMine(String promo, int reward_id){
        if(!rewardRepository.promoIsMine(promo, reward_id).isEmpty()){
            return true;
        }
        return false;
    }

}
