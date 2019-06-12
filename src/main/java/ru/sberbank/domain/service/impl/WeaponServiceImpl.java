package ru.sberbank.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.dao.repository.AmmoRepository;
import ru.sberbank.dao.repository.WeaponRepository;
import ru.sberbank.domain.entity.Ammo;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.entity.Weapon;
import ru.sberbank.domain.service.WeaponService;

import java.util.List;

@Service
public class WeaponServiceImpl implements WeaponService {

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private AmmoRepository ammoRepository;

    @Override
    public List<Weapon> findWeaponBySuit(Suit suit) {
        return weaponRepository.findBySuit(suit);
    }

    @Override
    public Ammo getAmmoForWeapon(Weapon weapon) {
        Ammo ammoForWeapon = null;
        Ammo ammo = ammoRepository.findByWeapon(weapon);
        if (ammo != null) {
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
        }
        return ammoForWeapon;
    }

    @Override
    public int computePercentFullnessAmmoInWeapon(Weapon weapon) {
        return (int) ((((double) weapon.getAmmo().getAmount()) / weapon.getCapacityAmmo()) * 100);
    }

    @Override
    public Weapon save(Weapon weapon) {
        weapon.getAmmo().setAmount(0);
        if (weapon.getAmmo() != null) {
            ammoRepository.create(weapon.getAmmo());
        }
        return weaponRepository.create(weapon);
    }
}
