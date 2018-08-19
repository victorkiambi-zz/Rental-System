package sample;

public class Houseclass {


    public Integer houses;
    public String housetype;
    public Integer rent;
    public Integer deposit;
    public Integer water;
    public Integer electricity;

    public Integer garbage;
    public Integer fee;
    public String month;

    public Houseclass(Integer houses,  Integer water, Integer electricity,Integer garbage,Integer fee,String month) {
        this.houses = houses;


        this.water = water;
        this.electricity = electricity;
        this.garbage = garbage;
        this.fee = fee;
        this.month = month;
    }

    public Houseclass(int houses, String housetype, int rent, int deposit,int water) {

        this.houses = houses;
        this.housetype = housetype;
        this.rent = rent;
        this.deposit = deposit;

    }

    public Houseclass(int houses, String housetype, int rent, int deposit, String month) {
        this.houses = houses;
        this.housetype = housetype;
        this.rent = rent;
        this.deposit = deposit;
        this.month= month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getHouses() {
        return houses;
    }

    public void setHouses(Integer houses) {
        this.houses = houses;
    }

    public String getHousetype() {
        return housetype;
    }

    public void setHousetype(String housetype) {
        this.housetype = housetype;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public Integer getElectricity() {
        return electricity;
    }

    public void setElectricity(Integer electricity) {
        this.electricity = electricity;
    }

    public Integer getGarbage() {
        return garbage;
    }

    public void setGarbage(Integer garbage) {
        this.garbage = garbage;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}
