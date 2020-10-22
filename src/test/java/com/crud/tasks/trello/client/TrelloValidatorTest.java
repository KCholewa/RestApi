package com.crud.tasks.trello.client;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import ch.qos.logback.classic.Logger;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {


    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void validateCardTest() {
        //given
        TrelloCard trelloCard = new TrelloCard("name", "desc", "pos", "listId");
        Logger validateCardLogger = (Logger) LoggerFactory.getLogger(TrelloValidator.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        validateCardLogger.addAppender(listAppender);

        //when
        List<ILoggingEvent> logsList = listAppender.list;
        trelloValidator.validateCard(trelloCard);

        //then
        assertEquals("Seems that my application is used in proper way.", logsList.get(0).getMessage());
    }

    @Test
    public void validateTrelloBoardsTest() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("BoardId1", "BoardName1", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("BoardId2", "BoardName2", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("BoardId3", "Test", new ArrayList<>()));

        //When
        int boardsSize = trelloValidator.validateTrelloBoards(trelloBoardList).size();

        //Then
        assertEquals(2, boardsSize);
    }

}
