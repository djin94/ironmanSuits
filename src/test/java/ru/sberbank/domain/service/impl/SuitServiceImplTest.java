package ru.sberbank.domain.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.dao.repository.SuitPartRepository;
import ru.sberbank.dao.repository.SuitRepository;
import ru.sberbank.domain.entity.Ammo;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.entity.SuitPart;
import ru.sberbank.domain.entity.Weapon;
import ru.sberbank.domain.service.WeaponService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SuitServiceImplTest {

    @Mock
    private SuitRepository suitRepository;

    @Mock
    private SuitPartRepository suitPartRepository;

    @Mock
    private WeaponService weaponService;

    @InjectMocks
    private SuitServiceImpl suitService;

    private List<Suit> suits;

    private Suit suit;

    private List<Weapon> weapons;

    private Weapon weapon;

    private List<SuitPart> suitParts;

    private SuitPart suitPart;

    private List<String> requiredSuitParts;

    private List<Integer> suitPartIds;

    private Ammo ammo;

    private Map<String, Integer> requiredWeapons;

    @Before
    public void setUp() {
        ammo = new Ammo();
        ammo.setId(1);
        ammo.setName("Пуля 9мм");

        weapon = new Weapon();
        weapon.setId(1);
        weapon.setName("Пистолет");
        weapon.setCapacityAmmo(5000);

        weapons = new ArrayList<>();
        weapons.add(weapon);

        suitPart = new SuitPart();
        suitPart.setId(1);
        suitPart.setName("Резчик металлов");

        suitParts = new ArrayList<>();
        suitParts.add(suitPart);

        suitPartIds = new ArrayList<>();
        suitPartIds.add(suitPart.getId());

        suit = new Suit();
        suit.setId(1);
        suit.setName("Suit 1");
        suit.setDeveloped(true);

        suits = new ArrayList<>();
        suits.add(suit);

        requiredSuitParts = new ArrayList<>();
        requiredSuitParts.add("Резчик металлов");

        requiredWeapons = new HashMap<>();
        requiredWeapons.put("Пуля 9мм", 5000);

        when(suitRepository.findAll()).thenReturn(suits);
        when(suitRepository.findSuitPartsIdsForSuit(suit)).thenReturn(suitPartIds);
        when(suitPartRepository.findById(suitPart.getId())).thenReturn(suitPart);
        when(weaponService.findWeaponBySuit(suit)).thenReturn(weapons);
        when(weaponService.getAmmoForWeapon(weapon)).thenReturn(ammo);
        when(weaponService.computePercentFullnessAmmoInWeapon(weapon)).thenReturn(100);
        when(suitPartRepository.create(suitPart)).thenReturn(suitPart);
        when(weaponService.save(weapon)).thenReturn(weapon);
        when(suitRepository.create(suit)).thenReturn(suit);
        when(suitRepository.findByName("Suit 1")).thenReturn(suit);
        when(suitRepository.findById(suit.getId())).thenReturn(suit);
    }

    @Test
    public void whenGetAllSuits_thenReturnAllSuits() {
        List<Suit> expectedSuits = suits;

        List<Suit> actualSuits = suitService.getAll();

        assertEquals(expectedSuits, actualSuits);
    }

    @Test
    public void whenGetAllSuits_thenReturnWithWeapons() {
        List<Weapon> expectedWeapons = weapons;

        List<Weapon> actualWeapon = suitService.getAll().get(0).getWeapons();

        assertEquals(expectedWeapons, actualWeapon);
    }

    @Test
    public void whenGetAllSuits_thenReturnWithAmmo() {
        Ammo expectedAmmo = ammo;

        Ammo actualAmmo = suitService.getAll().get(0).getWeapons().get(0).getAmmo();

        assertEquals(expectedAmmo, actualAmmo);
    }

    @Test
    public void whenSaveSuit_thenSaveSuit() {
        suit.setSuitParts(suitParts);
        suit.setWeapons(weapons);

        suitService.save(suit);

        verify(suitRepository).create(suit);
    }

    @Test
    public void whenGetSuitByName_thenReturnSuit() {
        Suit expectedSuit = suit;

        Suit actualSuit = suitService.getSuitByName("Suit 1");

        assertEquals(expectedSuit, actualSuit);
    }

    @Test
    public void whenGetSuitByName_thenReturnWithWeapons() {
        List<Weapon> expectedWeapons = weapons;

        List<Weapon> actualWeapon = suitService.getSuitByName("Suit 1").getWeapons();

        assertEquals(expectedWeapons, actualWeapon);
    }

    @Test
    public void whenGetSuitByName_thenReturnWithAmmo() {
        Ammo expectedAmmo = ammo;

        Ammo actualAmmo = suitService.getSuitByName("Suit 1").getWeapons().get(0).getAmmo();

        assertEquals(expectedAmmo, actualAmmo);
    }

    @Test
    public void whenGetSuitById_thenReturnSuit() {
        Suit expectedSuit = suit;

        Suit actualSuit = suitService.getSuitById(suit.getId());

        assertEquals(expectedSuit, actualSuit);
    }

    @Test
    public void whenGetSuitById_thenReturnWithWeapons() {
        List<Weapon> expectedWeapons = weapons;

        List<Weapon> actualWeapon = suitService.getSuitById(suit.getId()).getWeapons();

        assertEquals(expectedWeapons, actualWeapon);
    }

    @Test
    public void whenGetSuitById_thenReturnWithAmmo() {
        Ammo expectedAmmo = ammo;

        Ammo actualAmmo = suitService.getSuitById(suit.getId()).getWeapons().get(0).getAmmo();

        assertEquals(expectedAmmo, actualAmmo);
    }

    @Test
    public void whenSearchBySuitPartsAndWeapons_thenReturnSuits() {
        List<Suit> expectedSuits = suits;

        List<Suit> actualSuits = suitService.searchBySuitPartsAndWeapons(requiredSuitParts, requiredWeapons);

        assertEquals(expectedSuits, actualSuits);
    }
}