package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloMapper {
    public List<TrelloBoard> mapToBoard(List<TrelloBoardDto> trelloBoardDto) {
        return trelloBoardDto.stream()
                .map(t -> new TrelloBoard(t.getId(), t.getName(), mapToList(t.getLists())))
                .collect(toList());
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(t -> new TrelloList(t.getId(), t.getName(), t.isClosed()))
                .collect(toList());
    }

    public List<TrelloBoardDto> mapToBoardDto(List<TrelloBoard> trelloBoard) {
        return trelloBoard.stream()
                .map(t -> new TrelloBoardDto(t.getId(), t.getName(), mapToListDto(t.getLists())))
                .collect(toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloList) {
        return trelloList.stream()
                .map(t -> new TrelloListDto(t.getId(), t.getName(), t.isClosed()))
                .collect(toList());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto){
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(),trelloCardDto.getPos(),trelloCardDto.getListId());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard){
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(),trelloCard.getPos(),trelloCard.getListId());
    }
}
