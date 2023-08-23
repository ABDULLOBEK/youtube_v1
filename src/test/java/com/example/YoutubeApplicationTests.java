package com.example;

import com.example.dto.CardDTO;
import com.example.dto.TaskDTO;
import com.example.enums.CardStatus;
import com.example.enums.TaskStatus;
import com.example.service.CardService;
import com.example.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class YoutubeApplicationTests {
    @Autowired
    private TaskService taskService;
    @Autowired
    private CardService cardService;

    @Test
    void createTask() {
//        JWTUtil.encode()
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setStatus(TaskStatus.ACTIVE);
        taskDTO.setTitle("rarars");
        taskService.createTask(taskDTO);
    }

//    @Test
//    void getTaskList() {
//        taskService.taskList();
//    }

    @Test
    void delete() {
        taskService.delete("asdasdsda");
    }

    @Test
    void delete2() {
        taskService.delete2("dasdasdsad");
    }

    @Test
    void createCard() {
        CardDTO card = new CardDTO();
        card.setCardNumber(UUID.randomUUID().toString());
        card.setBalance(100000);
//        card.setStatus(CardStatus.ACTIVE);
//        card.setProfileId("0640c560-d3de-4977-8744-e0fc04daae23");
        cardService.create(card);
    }
}
