package ru.sberbank.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.dao.repository.SuitPartRepository;
import ru.sberbank.dao.repository.SuitRepository;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.entity.SuitPart;
import ru.sberbank.domain.entity.Weapon;
import ru.sberbank.domain.service.SuitService;
import ru.sberbank.domain.service.WeaponService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SuitServiceImpl implements SuitService {

    @Autowired
    private SuitRepository suitRepository;

    @Autowired
    private SuitPartRepository suitPartRepository;

    @Autowired
    private WeaponService weaponService;

    @Override
    public List<Suit> getAll() {
        List<Suit> suits = suitRepository.findAll();
        suits.forEach(suit -> {
            suit.setSuitParts(getSuitPartsForSuit(suit));
            suit.setWeapons(getWeaponsForSuit(suit));
        });
        return suits;
    }

    @Override
    public Suit save(Suit suit) {
        suit.setSuitParts(suit.getSuitParts().stream()
                .collect(ArrayList::new, (suitParts, suitPart) -> suitParts.add(suitPartRepository.create(suitPart)), ArrayList::addAll));
        suit.getWeapons().forEach(weaponService::save);
        return suitRepository.create(suit);
    }

    @Override
    public Suit getSuitByName(String name) {
        Suit suit = suitRepository.findByName(name);
        if (suit != null) {
            suit.setSuitParts(getSuitPartsForSuit(suit));
            suit.setWeapons(getWeaponsForSuit(suit));
        }
        return suit;
    }

    @Override
    public Suit getSuitById(int id) {
        Suit suit = suitRepository.findById(id);
        if (suit != null) {
            suit.setSuitParts(getSuitPartsForSuit(suit));
            suit.setWeapons(getWeaponsForSuit(suit));
        }
        return suit;
    }

    @Override
    public List<Suit> searchBySuitPartsAndWeapons(List<String> requiredSuitParts, Map<String, Integer> requiredWeapons) {
        return getAll().stream()
                .filter(suit -> isFirstListContainsSecondList(requiredSuitParts, getSuitPartsNames(suit.getSuitParts())))
                .collect(Collectors.toList()).stream()
                .filter(suit -> isSuitContainRequiredWeapons(suit, requiredWeapons))
                .collect(Collectors.toList());
    }

    private List<SuitPart> getSuitPartsForSuit(Suit suit) {
        return suitRepository.findSuitPartsIdsForSuit(suit).stream().collect(ArrayList::new, (suitParts, suitPartId) -> suitParts.add(suitPartRepository.findById(suitPartId)), ArrayList::addAll);
    }

    private List<Weapon> getWeaponsForSuit(Suit suit) {
        List<Weapon> weapons = weaponService.findWeaponBySuit(suit);
        if (weapons != null) {
            weapons.forEach(weapon -> weapon.setAmmo(weaponService.getAmmoForWeapon(weapon)));
            weapons.forEach(weapon -> weapon.setPercentFullnessAmmo(weaponService.computePercentFullnessAmmoInWeapon(weapon)));
        }
        return weapons;
    }

    private boolean isFirstListContainsSecondList(List<String> firstList, List<String> secondList) {
        return firstList.stream()
                .allMatch(s -> secondList.stream()
                        .anyMatch(s1 -> s.toLowerCase().contains(s1.toLowerCase())));
    }

    private List<String> getSuitPartsNames(List<SuitPart> suitParts) {
        return suitParts.stream()
                .collect(ArrayList::new, (strings, suitPart) -> strings.add(suitPart.getName()), ArrayList::addAll);
    }

    private boolean isSuitContainRequiredWeapons(Suit suit, Map<String, Integer> requiredWeapons) {
        boolean isContain = requiredWeapons.keySet().stream()
                .allMatch(s -> suit.getWeapons().stream()
                        .anyMatch(weapon -> s.toLowerCase().contains(weapon.getAmmo().getName().toLowerCase())));
        if (!isContain) {
            return false;
        }

        for (String s : requiredWeapons.keySet()) {
            Weapon weapon = suit.getWeapons().stream().filter(w -> w.getAmmo().getName().toLowerCase().contains(s.toLowerCase())).findFirst().get();
            if (weapon.getCapacityAmmo() != requiredWeapons.get(s)) {
                return false;
            }
        }
        return true;
    }
}
