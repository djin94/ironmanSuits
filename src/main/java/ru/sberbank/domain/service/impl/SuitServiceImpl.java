package ru.sberbank.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.dao.repository.SuitPartRepository;
import ru.sberbank.dao.repository.SuitRepository;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.domain.entity.SuitPart;
import ru.sberbank.domain.service.SuitService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuitServiceImpl implements SuitService {

    @Autowired
    private SuitRepository suitRepository;

    @Autowired
    private SuitPartRepository suitPartRepository;

    @Override
    public Suit saveSuit(Suit suit) {
        suit.getSuitParts().forEach(suitPartRepository::create);
        return suitRepository.create(suit);
    }

    @Override
    public Suit getSuitByName(String name) {
        Suit suit = suitRepository.findByName(name);
        suit.setSuitParts(getSuitPartsForSuit(suit));
        return suit;
    }

    @Override
    public Suit getSuitById(int id) {
        Suit suit = suitRepository.findById(id);
        suit.setSuitParts(getSuitPartsForSuit(suit));
        return suit;
    }

    private List<SuitPart> getSuitPartsForSuit(Suit suit) {
        return suitRepository.findSuitPartsForSuit(suit).stream().collect(ArrayList::new, (suitParts, suitPartId) -> suitParts.add(suitPartRepository.findById(suitPartId)), ArrayList::addAll);
    }
}
