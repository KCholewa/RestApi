package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void testMapToBoardsAndMapToBoardsDto() {
        //given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("BoardId1", "BoardName1", trelloListDtos));

        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("BoardId1", "BoardName1", trelloLists));

        //when
        List<TrelloBoard> trelloBoardLists = trelloMapper.mapToBoards(trelloBoardDtoList);
        List<TrelloBoardDto> trelloBoardDtoLists = trelloMapper.mapToBoardsDto(trelloBoards);
        //then
        assertEquals(1, trelloBoards.size());
        assertEquals("BoardId1", trelloBoardLists.get(0).getId());
        assertEquals(1, trelloBoardDtoList.size());
        assertEquals("BoardId1", trelloBoardDtoLists.get(0).getId());
    }

    @Test
    public void testMapToList() {
        //given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("ListId1", "ListName1", true));
        trelloListDtoList.add(new TrelloListDto("ListId2", "ListName2", true));
        trelloListDtoList.add(new TrelloListDto("ListId3", "ListName3", true));

        //when
        List<TrelloList> trelloListList = trelloMapper.mapToList(trelloListDtoList);

        //then
        assertEquals(3, trelloListList.size());
        assertTrue(trelloListList.get(2).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //given
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(new TrelloList("ListId1", "ListName1", true));
        trelloListList.add(new TrelloList("ListId2", "ListName2", true));
        trelloListList.add(new TrelloList("ListId3", "ListName3", true));

        //when
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListList);

        //then
        assertEquals(3, trelloListDtoList.size());
        assertTrue(trelloListList.get(2).isClosed());
    }

    @Test
    public void testMaptoCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "pos", "listId");

        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //then
        assertEquals("name", trelloCard.getName());
    }

    @Test
    public void testMaptoCardDto() {
        //given
        TrelloCard trelloCard = new TrelloCard("name", "desc", "pos", "listId");

        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //then
        assertEquals("name", trelloCardDto.getName());
    }

    @Test
    public void testMapToTask(){
        //given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        //when
        Task task = taskMapper.mapToTask(taskDto);

        //then
        assertEquals("title", task.getTitle());
    }

    @Test
    public void testMapToTaskDto(){
        //given
        Task task = new Task(1L, "title", "content");

        //when
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //then
        assertEquals("title", taskDto.getTitle());
    }

    @Test
    public void testMapToTaskDtoList() {
        //given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "title1", "content1"));
        tasks.add(new Task(2L, "title2", "content2"));
        tasks.add(new Task(3L, "title3", "content3"));

        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);

        //then
        assertEquals("title1", taskDtoList.get(0).getTitle());
        assertEquals(3, taskDtoList.size());
    }
}
