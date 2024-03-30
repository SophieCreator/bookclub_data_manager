package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.responses.TestQuestionResponse;
import com.bookclub_data_manager.models.MyQuestion;
import com.bookclub_data_manager.models.Test;
import com.bookclub_data_manager.services.test.MyQuestionService;
import com.bookclub_data_manager.services.test.TestService;
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
@RequestMapping("app/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    MyQuestionService myQuestionService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestParam("test_name") String test_name,
                              @RequestParam("complexity") String complexity) {

        String request = testService.add(test_name, complexity);

        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Тест добавлен", HttpStatus.CREATED);

    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestParam("test_name") String test_name,
                                 @RequestParam("test_id") int test_id) {

        String request = testService.update(test_name, test_id);

        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Название теста обновлено", HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam("test_id") int test_id) {

        String request = testService.deleteTest(test_id);

        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Тест удалён", HttpStatus.CREATED);

    }

    @PostMapping("/getTest")
    public ResponseEntity getTest(@RequestParam("test_id") int test_id) {

        Test test = testService.getTest(test_id);

        if(test == null){
            return new ResponseEntity("Нет данных о тесте", HttpStatus.BAD_REQUEST);
        }

        List<MyQuestion> questions = myQuestionService.getQuestionsForTest(test_id);

        if (questions.isEmpty()){
            return new ResponseEntity("Нет данных по вопросам", HttpStatus.BAD_REQUEST);
        }

        TestQuestionResponse testQuestionResponse = new TestQuestionResponse(test, questions);

        if(test == null || questions.isEmpty()){
            return new ResponseEntity("Нет данных о тесте", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(testQuestionResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/getAlTests")
    public ResponseEntity getAllTests(){

        List<Test> tests = testService.getAllTests();

        if (tests.isEmpty()){
            return new ResponseEntity("Cписок пуст", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(tests, HttpStatus.OK);
    }

    @PostMapping("/addRating")
    public ResponseEntity addRating(@RequestParam("test_id") int test_id,
                                    @RequestParam("user_id") int user_id,
                                    @RequestParam("estimation") double estimation) {

        String request = testService.addRatingForUser(user_id, test_id, estimation);

        if(!Objects.equals(request, "OK")){
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Рейтинг сохранен", HttpStatus.CREATED);
    }

    @PostMapping("/getAllTestsForUser")
    public ResponseEntity getAllTestsForUser(@RequestParam("user_id") int user_id){

        testService.updateComplexity();
        List<Test> tests = testService.getAllForUser(user_id);

        if(tests.isEmpty()){
            return new ResponseEntity("Cписок пуст", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(tests, HttpStatus.OK);
    }

}
