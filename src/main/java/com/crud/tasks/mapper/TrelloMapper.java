package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Component
public class TrelloMapper {


    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDto) {
        return trelloBoardDto.stream()
                .map(this::getTrelloBoard)
                .collect(toList());
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(this::getTrelloList)
                .collect(toList());
    }

    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(this::getTrelloBoardDto)
                .collect(toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(this::getTrelloListDto)
                .collect(toList());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(),
                trelloCard.getPos(), trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(),
                trelloCardDto.getPos(), trelloCardDto.getListId());
    }

    public TrelloBoard getTrelloBoard(TrelloBoardDto trelloBoardDto) {
        return new TrelloBoard(trelloBoardDto.getId(), trelloBoardDto.getName(), mapToList(trelloBoardDto.getLists()));
    }

    public TrelloList getTrelloList(TrelloListDto trelloListDto) {
        return new TrelloList(trelloListDto.getId(), trelloListDto.getName(), trelloListDto.isClosed());
    }

    public TrelloBoardDto getTrelloBoardDto(TrelloBoard trelloBoard) {
        return new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToListDto(trelloBoard.getLists()));
    }

    public TrelloListDto getTrelloListDto(TrelloList trelloList) {
        return new TrelloListDto(trelloList.getId(), trelloList.getName(), trelloList.isClosed());
    }


}
