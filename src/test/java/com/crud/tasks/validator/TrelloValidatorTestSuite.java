package com.crud.tasks.validator;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void shouldValidateCard() {

        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "lists Id");
        //When
        trelloValidator.ValidateCard(trelloCard);
    }

    @Test
    public void shouldValidateBoard() {

        //Given
        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));
        //When

        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(mappedTrelloBoards);

        //Then
        Assert.assertEquals(0,validatedBoards.size());

    }
}
