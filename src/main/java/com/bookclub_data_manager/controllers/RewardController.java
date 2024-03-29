package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.responses.UserRewardsResponse;
import com.bookclub_data_manager.models.Reward;
import com.bookclub_data_manager.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("app/reward")
public class RewardController {


    @Autowired
    RewardService rewardService;

    @PostMapping("/add")
    public ResponseEntity addReward(@RequestParam("name") String name,
                                    @RequestParam("reason") String reason,
                                    @RequestParam("promo") String promo,
                                    @RequestParam("image") String image,
                                    @RequestParam("tests_required") int tests_required,
                                    @RequestParam("meetings_required") int meetings_required,
                                    @RequestParam("sale") int sale) {

        String request = rewardService.add(name, reason, promo, image, tests_required, meetings_required, sale);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Награда добавлена", HttpStatus.CREATED);
    }

    @PostMapping("/deleteFromCurrent")
    public ResponseEntity deleteFromCurrent(@RequestParam("reward_id") int reward_id) {

        String request = rewardService.deleteFromCurrent(reward_id);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Награда удалена", HttpStatus.CREATED);
    }

    @PostMapping("/deleteEverywhere")
    public ResponseEntity deleteEverywhere(@RequestParam("reward_id") int reward_id) {

        String request = rewardService.deleteEverywhere(reward_id);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Награда удалена", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity updateReward(@RequestParam("name") String name,
                                       @RequestParam("reason") String reason,
                                       @RequestParam("promo") String promo,
                                       @RequestParam("image") String image,
                                       @RequestParam("tests_required") int tests_required,
                                       @RequestParam("meetings_required") int meetings_required,
                                       @RequestParam("sale") int sale,
                                       @RequestParam("reward_id")int reward_id) {

        String request = rewardService.update(name, reason, promo, image, tests_required, meetings_required, sale, reward_id);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Награда обновлена", HttpStatus.OK);
    }

    @PostMapping("/getById")
    public ResponseEntity getReward(@RequestParam("reward_id") int reward_id) {

        Reward reward = rewardService.getRewardById(reward_id);
        if(reward == null){
            return new ResponseEntity("Ошибка", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(reward, HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity getAllReward() {

        List<Reward> rewards = rewardService.getAllRewards();
        if(rewards.isEmpty()){
            return new ResponseEntity("Ошибка", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(rewards, HttpStatus.OK);
    }

    @PostMapping("/checkReward")
    public ResponseEntity checkReward(@RequestParam("user_id") int user_id) {

        List<Reward> newRewards = rewardService.newRewards(user_id);
        if (newRewards.isEmpty()){
            return new ResponseEntity("Новых наград нет", HttpStatus.OK);
        }
        return new ResponseEntity(newRewards, HttpStatus.OK);
    }

    @PostMapping("/getUserRewards")
    public ResponseEntity getUserRewards(@RequestParam("user_id") int user_id) {

        List<Reward> activeRewards = rewardService.getUserActiveRewards(user_id);
        List<Reward> passiveRewards = rewardService.getUserPassiveRewards(user_id);
        UserRewardsResponse response = new UserRewardsResponse(activeRewards, passiveRewards);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/setRewardUsed")
    public ResponseEntity getUserRewards(@RequestParam("user_id") int user_id,
                                         @RequestParam("reward_id") int reward_id) {

        rewardService.setRewardUsed(reward_id, user_id);

        return new ResponseEntity("Успешно", HttpStatus.OK);
    }

    @PostMapping("/checkPromo")
    public ResponseEntity checkPromo(@RequestParam("user_id") int user_id,
                                     @RequestParam("promo") String promo) {


        if(rewardService.checkRewardIsGotAndUsed(promo, user_id) == null){
            return new ResponseEntity("Вы уже использовали промокод", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Успешно", HttpStatus.OK);
    }
    
}
