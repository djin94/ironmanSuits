package ru.sberbank.domain.service;

import ru.sberbank.domain.entity.Ammo;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.entity.Weapon;

import java.util.List;

public interface WeaponService {
    List<Weapon> findWeaponBySuit(Suit suit);

    Ammo getAmmoForWeapon(Weapon weapon);

    int computePercentFullnessAmmoInWeapon(Weapon weapon);

    Weapon save(Weapon weapon);
}
