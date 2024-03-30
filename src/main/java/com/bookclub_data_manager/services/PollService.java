package com.bookclub_data_manager.services;

import com.bookclub_data_manager.models.Poll;
import com.bookclub_data_manager.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {
    
    @Autowired
    PollRepository pollRepository;
    

    public String add(String question, String var1, String var2,String var3, String var4){
        if (question == null || var1 == null || var2 == null || var3 == null || var4 == null){
            return "Поле не может быть пустым";
        }
        if (var1.equals(var2) || var1.equals(var3) || var1.equals(var4) || var2.equals(var3) || var2.equals(var4) || var3.equals(var4)){
            return "Варианты ответа должны быть разными";
        }
        pollRepository.add(question, var1, var2, var3, var4, 0, 0, 0, 0, 0,  0, 0, 0, true);
        return "OK";
    }

    public String delete(int poll_id){
        if (pollRepository.getPollById(poll_id) == null){
            return "Не существует такого идентификатора";
        }
        pollRepository.delete(poll_id);
        return "OK";
    }

    public String archive(int poll_id){
        if (pollRepository.getPollById(poll_id) == null){
            return "Не существует такого идентификатора";
        }
        pollRepository.archive(poll_id);
        return "OK";
    }

    public String correct(String question, String var1, String var2,String var3, String var4, int poll_id){
        if (question == null || var1 == null || var2 == null || var3 == null || var4 == null){
            return "Поле не может быть пустым";
        }
        if (pollRepository.getPollById(poll_id) == null){
            return "Не существует такого идентификатора";
        }
        pollRepository.correct(question, var1, var2, var3, var4, poll_id);
        return "OK";
    }

    public String updateByOld(int counter1, int counter2, int counter3, int counter4, int poll_id){
        if (counter1 == 0 && counter2 == 0 && counter3 == 0 && counter4 == 0){
            return "Нужно выбрать хотя бы один вариант";
        }
        if (pollRepository.getPollById(poll_id) == null){
            return "Не существует такого идентификатора";
        }
        pollRepository.updateByOld(counter1, counter2, counter3, counter4, poll_id);
        return "OK";
    }

    public String updateByNew(int counterN1, int counterN2, int counterN3, int counterN4, int poll_id){
        if (counterN1 == 0 && counterN2 == 0 && counterN3 == 0 && counterN4 == 0){
            return "Нужно выбрать хотя бы один вариант";
        }
        if (pollRepository.getPollById(poll_id) == null){
            return "Не существует такого идентификатора";
        }
        pollRepository.updateByNew(counterN1, counterN2, counterN3, counterN4, poll_id);
        return "OK";
    }

    public List<Poll> getAllActivePolls(){
        return pollRepository.getAllActivePolls();
    }

    public List<Poll> getAllArchivedPolls(){
        return pollRepository.getAllArchivedPolls();
    }

    public List<Poll> getAllUserPolls(int user_id){
        return pollRepository.getAllUserPolls(user_id);
    }

    public Poll getPollById(int id){
        return pollRepository.getPollById(id);
    }

    public String setDependency(int user_id, int poll_id){
        if (pollRepository.getPollById(poll_id) == null){
            return "Не существует такого идентификатора";
        }
        pollRepository.setDependency(user_id, poll_id);
        return "OK";
    }
    
}
