package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void fetchTrelloBoardTest() {
        //given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("BoardId1", "BoardName1", new ArrayList<>()));
        trelloBoardDtoList.add(new TrelloBoardDto("BoardId2", "BoardName2", new ArrayList<>()));
        trelloBoardDtoList.add(new TrelloBoardDto("BoardId3", "BoardName3", new ArrayList<>()));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //when
        int boardsSize = trelloService.fetchTrelloBoards().size();

        //then
        Assert.assertEquals(3, boardsSize);
    }

}









