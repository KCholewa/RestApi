package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void ShouldGetTasks() throws Exception{
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "title1", "content1"));
        tasks.add(new Task(2L, "title2", "content2"));
        tasks.add(new Task(3L, "title3", "content3"));

        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "title1", "content1"));
        taskDtoList.add(new TaskDto(2L, "title2", "content2"));
        taskDtoList.add(new TaskDto(3L, "title3", "content3"));

        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtoList);
        when(service.getAllTasks()).thenReturn(tasks);

        //When & Then
        mockMvc.perform(get("/v1/tasks/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title1")));

    }

    @Test
    public void ShouldGetTask() throws Exception {
        //Given
        Task task = new Task(1L, "title1", "content1");
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Optional<Task> optionalTask = Optional.of(task);

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(service.getTaskByID(1L)).thenReturn(optionalTask);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title1")))
                .andExpect(jsonPath("$.content", is("content1")));   ;

    }

    @Test
    public void ShouldDeleteTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");

        //When & Then
        mockMvc.perform(delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void ShouldUpdateTasks() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        TaskDto taskAfterUpdate = new TaskDto(1L, "title1", "content1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskAfterUpdate);

        //When & Then
        mockMvc.perform(put("/v1/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title1")))
                .andExpect(jsonPath("$.content", is("content1")));

   }

    @Test
    public void ShouldCreateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Task task = new Task(1L, "title1", "content1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        //When & Then
        mockMvc.perform(post("/v1/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }
}
