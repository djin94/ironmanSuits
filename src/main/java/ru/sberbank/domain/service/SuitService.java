package ru.sberbank.domain.service;

import ru.sberbank.domain.entity.Suit;

import java.util.List;
import java.util.Map;

public interface SuitService {
    List<Suit> getAll();

    Suit saveSuit(Suit suit);

    Suit getSuitByName(String name);

    Suit getSuitById(int id);

    List<Suit> searchBySuitPartsAndWeapons(List<String> requiredSuitParts, Map<String, Integer> requiredWeapons);
}
