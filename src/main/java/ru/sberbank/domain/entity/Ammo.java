package ru.sberbank.domain.entity;


import java.util.Objects;

public class Ammo implements Cloneable {

    private int id;

    private int amount;

    private String name;

    private int weaponId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ammo ammo = (Ammo) o;
        return id == ammo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        return "Ammo{" +
                "id=" + id +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Object clone() {
        Ammo ammo = new Ammo();
        ammo.id = this.id;
        ammo.name = this.name;
        ammo.amount = this.amount;
        ammo.weaponId = this.weaponId;
        return ammo;
    }
}
