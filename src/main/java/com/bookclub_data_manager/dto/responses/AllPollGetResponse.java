package com.bookclub_data_manager.dto.responses;

import com.bookclub_data_manager.models.Poll;


import java.util.List;

public class AllPollGetResponse {

    private List<Poll> activePolls;
    private List<Poll> archivedPolls;

    public AllPollGetResponse(List<Poll> activePolls, List<Poll> archivedPolls){
        this.activePolls = activePolls;
        this.archivedPolls = archivedPolls;
    }

    public List<Poll> getActivePolls() {
        return activePolls;
    }

    public void setActivePolls(List<Poll> activePolls) {
        this.activePolls = activePolls;
    }

    public List<Poll> getArchivedPolls() {
        return archivedPolls;
    }

    public void setArchivedPolls(List<Poll> archivedPolls) {
        this.archivedPolls = archivedPolls;
    }
}
