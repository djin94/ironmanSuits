package ru.sberbank.domain.entity;

import java.util.Objects;

public class Weapon {

    private int id;

    private String name;

    private int capacityAmmo;

    private Ammo ammo;

    private int percentFullnessAmmo;

    private int suitId;

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

    public int getCapacityAmmo() {
        return capacityAmmo;
    }

    public void setCapacityAmmo(int capacityAmmo) {
        this.capacityAmmo = capacityAmmo;
    }

    public Ammo getAmmo() {
        return ammo;
    }

    public void setAmmo(Ammo ammo) {
        this.ammo = ammo;
    }

    public int getPercentFullnessAmmo() {
        return percentFullnessAmmo;
    }

    public void setPercentFullnessAmmo(int percentFullnessAmmo) {
        this.percentFullnessAmmo = percentFullnessAmmo;
    }

    public int getSuitId() {
        return suitId;
    }

    public void setSuitId(int suitId) {
        this.suitId = suitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return id == weapon.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacityAmmo=" + capacityAmmo +
                ", percentFullnessAmmo=" + percentFullnessAmmo +
                '}';
    }
}
