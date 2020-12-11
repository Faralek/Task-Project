package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MapperTestSuite {
    @InjectMocks
    TrelloMapper trelloMapper;
    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void testTaskMapper() {
        //Given
        Task task = new Task(1L, "test title", "test content");
        Task task1 = new Task(2L, "another test title", "another test content");

        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        TaskDto taskDto1 = new TaskDto(2L, "another test title", "another test content");

        List<Task> tasks = new ArrayList<>();
        List<TaskDto> tasksDto = new ArrayList<>();

        //When
        tasksDto.add(taskDto);
        tasksDto.add(taskDto1);

        tasks.add(task);
        tasks.add(task1);

        //Then

        assertThat(taskDto).isEqualToComparingFieldByField(taskMapper.mapToTask(taskDto));
        assertThat(taskDto1).isEqualToComparingFieldByField(taskMapper.mapToTask(taskDto1));

        assertThat(task).isEqualToComparingFieldByField(taskMapper.mapToTaskDto(task));
        assertThat(task1).isEqualToComparingFieldByField(taskMapper.mapToTaskDto(task1));

        Assert.assertEquals(tasks.size(), tasksDto.size());
    }

    @Test
    public void testTrelloMapper() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test", "test", "test", "test");
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test", "test", "test");
        TrelloBoard trelloBoard = new TrelloBoard("test", "test", new ArrayList<>());
        TrelloListDto trelloListDto = new TrelloListDto("test", "test", true);

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();

        //When
        trelloListDtoList.add(trelloListDto);
        trelloBoardList.add(trelloBoard);

        trelloLists = trelloMapper.mapToList(trelloListDtoList);
        trelloListDtoList = trelloMapper.mapToListDto(trelloLists);
        trelloBoardDtoList = trelloMapper.mapToBoardDto(trelloBoardList);
        trelloBoardList = trelloMapper.mapToBoard(trelloBoardDtoList);

        //Then
        Assert.assertEquals(1,trelloLists.size());
        Assert.assertEquals(1,trelloBoardList.size());

        Assert.assertEquals(trelloBoardList.get(0).getId(),trelloBoardDtoList.get(0).getId());
        Assert.assertEquals(trelloBoardList.get(0).getName(),trelloBoardDtoList.get(0).getName());
        Assert.assertEquals(trelloBoardList.get(0).getLists(),trelloBoardDtoList.get(0).getLists());

        Assert.assertEquals(trelloLists.get(0).getId(),trelloListDtoList.get(0).getId());
        Assert.assertEquals(trelloLists.get(0).getName(),trelloListDtoList.get(0).getName());
        Assert.assertEquals(trelloLists.get(0).isClosed(),trelloListDtoList.get(0).isClosed());

        assertThat(trelloCard).isEqualToComparingFieldByField(trelloMapper.mapToCardDto(trelloCard));
        assertThat(trelloCardDto).isEqualToComparingFieldByField(trelloMapper.mapToCard(trelloCardDto));
    }
}
