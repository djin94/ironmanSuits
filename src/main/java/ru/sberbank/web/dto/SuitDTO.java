package ru.sberbank.web.dto;

import ru.sberbank.domain.entity.SuitPart;
import ru.sberbank.domain.entity.Weapon;

import java.util.List;
import java.util.Objects;

public class SuitDTO {
    private int id;

    private String name;

    private boolean isDeveloped;

    private List<SuitPart> suitParts;

    private List<Weapon> weapons;

    private int percentFullnessAmmo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeveloped() {
        return isDeveloped;
    }

    public void setDeveloped(boolean developed) {
        isDeveloped = developed;
    }

    public List<SuitPart> getSuitParts() {
        return suitParts;
    }

    public void setSuitParts(List<SuitPart> suitParts) {
        this.suitParts = suitParts;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public int getPercentFullnessAmmo() {
        return percentFullnessAmmo;
    }

    public void setPercentFullnessAmmo(int percentFullnessAmmo) {
        this.percentFullnessAmmo = percentFullnessAmmo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuitDTO suitDTO = (SuitDTO) o;
        return id == suitDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Suit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeveloped=" + isDeveloped +
                '}';
    }
}
