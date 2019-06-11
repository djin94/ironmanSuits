package ru.sberbank.dao.repository;

import ru.sberbank.domain.entity.SuitPart;

import java.util.List;

public interface SuitPartRepository {
    SuitPart create(SuitPart suitPart);

    void update(SuitPart suitPart);

    SuitPart findById(int id);

    List<SuitPart> findAll();

    void delete(SuitPart suitPart);
}
