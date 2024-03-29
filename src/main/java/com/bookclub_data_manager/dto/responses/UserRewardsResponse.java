package com.bookclub_data_manager.dto.responses;

import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.Book;
import com.bookclub_data_manager.models.Genre;
import com.bookclub_data_manager.models.Reward;

import java.util.List;

public class UserRewardsResponse {

    private List<Reward> activeRewards;
    private List<Reward> passiveRewards;

    public UserRewardsResponse(List<Reward> activeRewards, List<Reward> passiveRewards){
        this.activeRewards = activeRewards;
        this.passiveRewards = passiveRewards;
    }

    public List<Reward> getActiveRewards() {
        return activeRewards;
    }

    public void setActiveRewards(List<Reward> activeRewards) {
        this.activeRewards = activeRewards;
    }

    public List<Reward> getPassiveRewards() {
        return passiveRewards;
    }

    public void setPassiveRewards(List<Reward> passiveRewards) {
        this.passiveRewards = passiveRewards;
    }
}
