package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class TrelloValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    public void ValidateCard(TrelloCard trelloCard) {
        if (trelloCard.getName().contains("test")) {
            LOGGER.info("Someone is testing my application!");
        } else {
            LOGGER.info("Someone is using my app in proper way.");
        }
    }

    public List<TrelloBoard> validateTrelloBoards(List<TrelloBoard> trelloBoards) {
        LOGGER.info("Starting filtering boards...");
        List<TrelloBoard> filteredBoards = trelloBoards.stream()
                .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("Filtering boards completed. Current boards size: " + filteredBoards.size());
        return filteredBoards;
    }

}
