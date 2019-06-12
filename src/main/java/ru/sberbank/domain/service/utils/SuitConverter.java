package ru.sberbank.domain.service.utils;

import org.springframework.stereotype.Component;
import ru.sberbank.domain.entity.Suit;
import ru.sberbank.web.dto.SuitDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class SuitConverter {

    public SuitDTO createDTOForSuit(Suit suit) {
        SuitDTO suitDTO = new SuitDTO();
        suitDTO.setId(suit.getId());
        suitDTO.setDeveloped(suit.isDeveloped());
        suitDTO.setName(suit.getName());
        suitDTO.setWeapon(suit.getWeapon());
        suitDTO.setSuitParts(suit.getSuitParts());
        return suitDTO;
    }

    public List<SuitDTO> createDTOsForSuits(List<Suit> suits) {
        return suits.stream().collect(ArrayList::new, (suitDTOS, suit) -> suitDTOS.add(createDTOForSuit(suit)), ArrayList::addAll);
    }
}
