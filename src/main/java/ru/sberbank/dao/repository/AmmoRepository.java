package ru.sberbank.dao.repository;

import ru.sberbank.domain.entity.Ammo;
import java.util.List;

public interface AmmoRepository {
    Ammo create(Ammo ammo);

    void update(Ammo ammo);

    Ammo findById(int id);

    List<Ammo> findAll();

    void delete(Ammo ammo);

    Ammo findByWeaponId(int weaponId);
}
