package ru.sberbank.domain.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.dao.repository.AmmoRepository;
import ru.sberbank.dao.repository.WeaponRepository;
import ru.sberbank.domain.entity.Ammo;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.entity.Weapon;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class WeaponServiceImplTest {

    @Mock
    private WeaponRepository weaponRepository;

    @Mock
    private AmmoRepository ammoRepository;

    @InjectMocks
    private WeaponServiceImpl weaponService;

    private List<Weapon> weapons;

    private Weapon weapon;

    private Ammo ammo;

    private Suit suit;

    @Before
    public void setUp() throws Exception {
        ammo = new Ammo();
        ammo.setId(1);
        ammo.setName("Пуля 9мм");
        ammo.setAmount(5000);

        weapon = new Weapon();
        weapon.setId(1);
        weapon.setName("Пистолет");
        weapon.setCapacityAmmo(5000);
        weapon.setAmmo(ammo);

        weapons = new ArrayList<>();
        weapons.add(weapon);

        suit = new Suit();
        suit.setId(1);
        suit.setName("Suit 1");
        suit.setDeveloped(true);

        when(weaponRepository.findBySuit(suit)).thenReturn(weapons);
        when(ammoRepository.findByWeapon(weapon)).thenReturn(ammo);
        doNothing().when(ammoRepository).update(ammo);
        when(ammoRepository.create(ammo)).thenReturn(ammo);
        when(weaponRepository.create(weapon)).thenReturn(weapon);
    }

    @Test
    public void whenFindWeaponBySuit_thenReturnListWeapon() {
        List<Weapon> expectedWeapons = weapons;

        List<Weapon> actualWeapon = weaponService.findWeaponBySuit(suit);

        assertEquals(expectedWeapons, actualWeapon);
    }

    @Test
    public void whenGetAmmoForWeapon_thenReturnAmmo() {
        Ammo expectedAmmo = ammo;

        Ammo actualAmmo = weaponService.getAmmoForWeapon(weapon);

        assertEquals(expectedAmmo, actualAmmo);
    }

    @Test
    public void whenComputePercentFullnessAmmoInWeapon_thenReturnPercentFullnessAmmoInWeapon() {
        int expectedPercent = 100;

        int actualPercent = weaponService.computePercentFullnessAmmoInWeapon(weapon);

        assertEquals(expectedPercent, actualPercent);
    }

    @Test
    public void whenSaveWeapon_thenSaveWeaponAndReturnWeapon() {
        Weapon expectedWeapon = weapon;

        Weapon actualWeapon = weaponService.save(weapon);

        assertEquals(expectedWeapon, actualWeapon);
    }
}