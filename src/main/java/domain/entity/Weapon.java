package domain.entity;

import java.util.Objects;

public class Weapon {

    private int id;

    private int capacityAmmo;

    private Ammo ammo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", capacityAmmo=" + capacityAmmo +
                '}';
    }
}
