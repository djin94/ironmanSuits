package ru.sberbank.domain.service;

import ru.sberbank.domain.entity.Suit;

public interface SuitService {
    Suit saveSuit(Suit suit);

    Suit getSuitByName(String name);

    Suit getSuitById(int id);
}
