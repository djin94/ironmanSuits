package ru.sberbank.dao.repository;

import ru.sberbank.domain.entity.Suit;

import java.util.List;

public interface SuitRepository {

    Suit create(Suit suit);

    void update(Suit suit);

    Suit findById(int id);

    List<Suit> findAll();

    void delete(Suit suit);

    List<Integer> findSuitPartsIdsForSuit(Suit suit);

    Suit findByName(String name);
}
