package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {


    @Autowired
    DbService dbService;

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void getAllTasksAndSaveTaskTest() {

        //Given
        Task task = new Task(1L,"Test Task", "Test Description");

        //When
        List<Task> taskListBeforeUpdate = dbService.getAllTasks();
        dbService.saveTask(task);
        List<Task> taskListAfterUpdate = dbService.getAllTasks();

        //Then
        Assert.assertEquals((taskListBeforeUpdate.size()+1), taskListAfterUpdate.size());

    }

    @Test
    public void getByIdTest() {

        //Given
        Task task = new Task(1L,"Test getById","Test getById");
        dbService.saveTask(task);
        List<Task> tasksList = dbService.getAllTasks();
        int tasksListQty = tasksList.size();
        long idNumber = tasksList.get(tasksListQty-1).getId();

        //When
        Optional<Task> taskByid = dbService.getTaskByID(idNumber);
        String title = taskByid.get().getTitle();

        //Then
        Assert.assertEquals("Test getById", title);

    }

    @Test
    public void deleteByIdTest() {

        //Given
        List<Task> tasksList = dbService.getAllTasks();
        int tasksListQty = tasksList.size();
        long idNumber = tasksList.get(tasksListQty-1).getId();

        //When
        dbService.deleteById(idNumber);
        List<Task> taskListAfterDelete = dbService.getAllTasks();

        //Then
        Assert.assertEquals((tasksListQty-1), taskListAfterDelete.size());
    }

}
