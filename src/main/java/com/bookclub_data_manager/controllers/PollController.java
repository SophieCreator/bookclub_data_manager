package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.responses.AllPollGetResponse;
import com.bookclub_data_manager.models.Poll;
import com.bookclub_data_manager.services.PollService;
import com.bookclub_data_manager.services.meeting.MyRecordService;
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
@RequestMapping("app/poll")
public class PollController {

    @Autowired
    PollService pollService;
    @Autowired
    MyRecordService myRecordService;


    @PostMapping("/add")
    public ResponseEntity addPoll(@RequestParam("question") String question,
                                    @RequestParam("var1") String var1,
                                    @RequestParam("var2") String var2,
                                    @RequestParam("var3") String var3,
                                    @RequestParam("var4") String var4) {

        String request = pollService.add(question, var1, var2, var3, var4);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Опрос добавлен", HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity deletePollById(@RequestParam("poll_id") int poll_id) {

        String request = pollService.delete(poll_id);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Опрос удалён", HttpStatus.CREATED);
    }

    @PostMapping("/archive")
    public ResponseEntity archivePollById(@RequestParam("poll_id") int poll_id) {

        String request = pollService.archive(poll_id);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Опрос архивирован", HttpStatus.CREATED);
    }

    @PostMapping("/correct")
    public ResponseEntity correctPoll(@RequestParam("question") String question,
                                      @RequestParam("var1") String var1,
                                      @RequestParam("var2") String var2,
                                      @RequestParam("var3") String var3,
                                      @RequestParam("var4") String var4,
                                      @RequestParam("poll_id") int poll_id) {

        String request = pollService.correct(question, var1, var2, var3, var4, poll_id);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Опрос архивирован", HttpStatus.CREATED);
    }

    @PostMapping("/vote")
    public ResponseEntity vote(@RequestParam("counter1") int counter1,
                               @RequestParam("counter2") int counter2,
                               @RequestParam("counter3") int counter3,
                               @RequestParam("counter4") int counter4,
                               @RequestParam("user_id") int user_id,
                               @RequestParam("poll_id") int poll_id) {
        String request;
        if (myRecordService.getMyRecordsByUser(user_id).isEmpty()){
            request = pollService.updateByNew(counter1, counter2, counter3, counter4, poll_id);
        } else {
            request = pollService.updateByOld(counter1, counter2, counter3, counter4, poll_id);
        }
        pollService.setDependency(user_id, poll_id);
        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Вы проголосовали", HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity getAll() {

        List<Poll> activePolls = pollService.getAllActivePolls();
        List<Poll> archivedPolls = pollService.getAllArchivedPolls();

        AllPollGetResponse allPollGetResponse = new AllPollGetResponse(activePolls, archivedPolls);

        return new ResponseEntity(allPollGetResponse, HttpStatus.OK);
    }


    @PostMapping("/getAllForUser")
    public ResponseEntity getAllForUser(@RequestParam("user_id") int user_id) {
        List<Poll> polls = pollService.getAllUserPolls(user_id);
        if(polls.isEmpty()){
            return new ResponseEntity("Cписок пуст", HttpStatus.OK);
        }
        return new ResponseEntity(polls, HttpStatus.OK);
    }

}
