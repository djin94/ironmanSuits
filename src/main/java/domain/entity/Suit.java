package domain.entity;

import java.util.List;
import java.util.Objects;

public class Suit {

    private int id;

    private String name;

    private boolean isDeveloped;

    private List<SuitPart> suitParts;

    private Weapon weapon;

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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public List<SuitPart> getSuitParts() {
        return suitParts;
    }

    public void setSuitParts(List<SuitPart> suitParts) {
        this.suitParts = suitParts;
    }

    public void add(SuitPart suitPart) {
        suitParts.add(suitPart);
    }

    public void remove(SuitPart suitPart) {
        suitParts.remove(suitPart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suit suit = (Suit) o;
        return id == suit.id;
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
