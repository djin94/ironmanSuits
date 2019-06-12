package ru.sberbank.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.service.SuitService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(SuitController.URL)
public class SuitController {
    public static final String URL = "/suits";

    @Autowired
    private SuitService suitService;

    @GetMapping("/name/{name}")
    @ApiOperation(value = "Get suit by name")
    public Suit getSuitByName(@PathVariable String name) {
        Suit suit = suitService.getSuitByName(name);
        return suit;
    }

    @GetMapping("/id/{id}")
    @ApiOperation(value = "Get suit by id")
    public Suit getSuitById(@PathVariable int id) {
        Suit suit = suitService.getSuitById(id);
        return suit;
    }

    @PostMapping("/search")
    @ApiOperation(value = "Search suit by suit's parts and weapons")
    public List<Suit> searchBySuitPartsAndWeapons(@RequestBody List<String> requiredSuitParts, @RequestBody Map<String, Integer> requiredWeapons) {
        return suitService.searchBySuitPartsAndWeapons(requiredSuitParts, requiredWeapons);
    }
}
