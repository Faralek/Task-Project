package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {

    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test board", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test board", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoard(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //When

        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then


        Assert.assertEquals(trelloLists,trelloBoards.get(0).getLists());
        Assert.assertNotNull(trelloBoardDtos);
        Assert.assertEquals(1, trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            Assert.assertEquals("1", trelloBoardDto.getId());
            Assert.assertEquals("test board", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                Assert.assertEquals("1", trelloListDto.getId());
                Assert.assertEquals("test list", trelloListDto.getName());
                Assert.assertEquals(false, trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void shouldCreateCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test desc", "test pos", "test id");
        TrelloCard trelloCard = new TrelloCard("test name", "test desc", "test pos", "test id");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("test id", "test name", "test pos");

        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto createdTrelloCardDto2 = trelloFacade.createCard(trelloCardDto);

        //Then
        assertThat(createdTrelloCardDto).isEqualToComparingFieldByField(createdTrelloCardDto2);
    }
}
