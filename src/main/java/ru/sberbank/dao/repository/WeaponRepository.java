package ru.sberbank.dao.repository;

import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.entity.Weapon;

import java.util.List;

public interface WeaponRepository {
    Weapon create(Weapon weapon);

    void update(Weapon weapon);

    Weapon findById(int id);

    List<Weapon> findAll();

    void delete(Weapon weapon);

    List<Weapon> findBySuit(Suit suit);
}
