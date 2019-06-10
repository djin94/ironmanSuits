package domain.entity;


import java.util.Objects;

public class Ammo {

    private int id;

    private int amount;

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
                '}';
    }
}
