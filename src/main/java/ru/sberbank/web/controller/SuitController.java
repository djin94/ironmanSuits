package ru.sberbank.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.service.SuitService;
import ru.sberbank.domain.service.WeaponService;
import ru.sberbank.domain.service.utils.SuitConverter;
import ru.sberbank.web.dto.SuitDTO;

@RestController
@RequestMapping(SuitController.URL)
public class SuitController {
    public static final String URL = "/suits";

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private SuitService suitService;

    @Autowired
    private SuitConverter suitConverter;

    @GetMapping("/name/{name}")
    @ApiOperation(value = "Get suit by name")
    public SuitDTO getSuitByName(@PathVariable String name) {
        Suit suit = suitService.getSuitByName(name);
        suit.setWeapon(weaponService.findWeaponBySuit(suit));
        suit.getWeapon().setAmmo(weaponService.getAmmoForWeapon(suit.getWeapon()));
        SuitDTO suitDTO = suitConverter.createDTOForSuit(suit);
        suitDTO.setPercentFullnessAmmo(weaponService.computePercentFullnessAmmoInWeapon(suitDTO.getWeapon()));
        return suitDTO;
    }

    @GetMapping("/id/{id}")
    @ApiOperation(value = "Get suit by id")
    public SuitDTO getSuitById(@PathVariable int id) {
        Suit suit = suitService.getSuitById(1);
        suit.setWeapon(weaponService.findWeaponBySuit(suit));
        suit.getWeapon().setAmmo(weaponService.getAmmoForWeapon(suit.getWeapon()));
        SuitDTO suitDTO = suitConverter.createDTOForSuit(suit);
        suitDTO.setPercentFullnessAmmo(weaponService.computePercentFullnessAmmoInWeapon(suitDTO.getWeapon()));
        return suitDTO;
    }
}
