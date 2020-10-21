package com.crud.tasks.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailCreatorServiceTest {

    @Autowired
    MailCreatorService mailCreatorService;
    @Test
    public void previewMessageTest() {

        //Given
        String message = "word1 word2 word3 word4 word5 word6";

        //When
        String previevMessage = mailCreatorService.previewMessage(message);

        //Then
        Assert.assertEquals("word1 word2 word3 word4....", previevMessage);

    }
}
