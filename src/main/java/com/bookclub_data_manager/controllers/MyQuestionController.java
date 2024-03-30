package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.models.MyQuestion;
import com.bookclub_data_manager.services.test.MyQuestionService;
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
@RequestMapping("app/question")
public class MyQuestionController {

    @Autowired
    MyQuestionService myQuestionService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestParam("test_id") int test_id,
                              @RequestParam("question_name") String question_name,
                              @RequestParam("var1") String var1,
                              @RequestParam("var2") String var2,
                              @RequestParam("var3") String var3,
                              @RequestParam("var4") String var4) {

        String request = myQuestionService.add(test_id, question_name, var1, var2, var3, var4);

        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Вопрос добавлен", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestParam("question_name") String question_name,
                                 @RequestParam("var1") String var1,
                                 @RequestParam("var2") String var2,
                                 @RequestParam("var3") String var3,
                                 @RequestParam("var4") String var4,
                                 @RequestParam("question_id") int question_id) {

        String request = myQuestionService.update(question_name, var1, var2, var3, var4, question_id);

        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Вопрос обновлен", HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam("question_id") int question_id) {

        String request = myQuestionService.delete(question_id);

        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Вопрос удален", HttpStatus.CREATED);
    }

    @PostMapping("/getQuestion")
    public ResponseEntity getQuestion(@RequestParam("question_id") int question_id) {

        MyQuestion question = myQuestionService.getQuestion(question_id);

        if (question == null) {
            return new ResponseEntity("Нет данных о вопросе", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(question, HttpStatus.OK);
        }
    }

    @PostMapping("/getAllQuestions")
    public ResponseEntity getAllQuestions() {

        List<MyQuestion> questions = myQuestionService.getAllQuestions();

        if (questions.isEmpty()){
            return new ResponseEntity("Cписок пуст", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(questions, HttpStatus.OK);
    }
}
