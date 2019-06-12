package ru.sberbank.dao.repository;

import ru.sberbank.domain.entity.Ammo;
import ru.sberbank.domain.entity.Weapon;

import java.util.List;

public interface AmmoRepository {
    Ammo create(Ammo ammo);

    void update(Ammo ammo);

    Ammo findById(int id);

    List<Ammo> findAll();

    void delete(Ammo ammo);

    Ammo findByWeapon(Weapon weapon);
}
