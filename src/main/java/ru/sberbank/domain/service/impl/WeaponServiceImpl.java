package ru.sberbank.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.dao.repository.AmmoRepository;
import ru.sberbank.dao.repository.WeaponRepository;
import ru.sberbank.domain.entity.Ammo;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.entity.Weapon;
import ru.sberbank.domain.service.WeaponService;

@Service
public class WeaponServiceImpl implements WeaponService {

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private AmmoRepository ammoRepository;

    @Override
    public Weapon findWeaponBySuit(Suit suit) {
        return weaponRepository.findBySuit(suit);
    }

    @Override
    public Ammo getAmmoForWeapon(Weapon weapon) {
        Ammo ammoForWeapon;
        Ammo ammo = ammoRepository.findByWeapon(weapon);
        if (ammo.getAmount() >= weapon.getCapacityAmmo()) {
            ammo.setAmount(ammo.getAmount() - weapon.getCapacityAmmo());
            ammoRepository.update(ammo);
            ammo.setAmount(weapon.getCapacityAmmo());
            ammoForWeapon = ammo;
        } else {
            ammoForWeapon = (Ammo) ammo.clone();
            ammo.setAmount(0);
            ammoRepository.update(ammo);
        }
        return ammoForWeapon;
    }

    @Override
    public int computePercentFullnessAmmoInWeapon(Weapon weapon) {
        return (int) ((((double) weapon.getAmmo().getAmount()) / weapon.getCapacityAmmo()) * 100);
    }
}
