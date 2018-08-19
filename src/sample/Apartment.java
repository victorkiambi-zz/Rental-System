package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

public class Apartment extends TableColumn<Apartment, Object> {



    public SimpleIntegerProperty house_no;
    public SimpleStringProperty tenant_name;
    public SimpleIntegerProperty id_no;

    public SimpleIntegerProperty rent;
    public SimpleIntegerProperty deposit;

    public SimpleIntegerProperty water;
    public SimpleIntegerProperty electricity;
    public SimpleIntegerProperty garbage;

    public SimpleIntegerProperty fees;

    public SimpleStringProperty months;
    public SimpleIntegerProperty balance;

    public Apartment(Integer houseno, String tname, Integer id, Integer rent, Integer deposit, Integer waterpay, Integer elec, Integer waste, Integer fees, Integer balance) {


    }

    public Apartment(int houseno, String tname, int id, int rent, int deposit, int waterpay, int elec, int waste, int fees,int balance) {

        this.house_no = new SimpleIntegerProperty(houseno);
        this.tenant_name = new SimpleStringProperty(tname);
        this.id_no = new SimpleIntegerProperty(id);
        this.rent = new SimpleIntegerProperty(rent);
        this.deposit = new SimpleIntegerProperty(deposit);
        this.water = new SimpleIntegerProperty(waterpay);
        this.electricity = new SimpleIntegerProperty(elec);
        this.garbage = new SimpleIntegerProperty(waste);

        this.fees = new SimpleIntegerProperty(fees);
        this.balance = new SimpleIntegerProperty(balance);

    }

    public Apartment(int houseno, String tname, int id, String months) {
        this.house_no = new SimpleIntegerProperty(houseno);
        this.tenant_name = new SimpleStringProperty(tname);
        this.id_no = new SimpleIntegerProperty(id);
        this.months = new SimpleStringProperty(months);
    }



    public int getDeposit() {
        return deposit.get();
    }

    public SimpleIntegerProperty depositProperty() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit.set(deposit);
    }

    public int getBalance() {
        return balance.get();
    }

    public SimpleIntegerProperty balanceProperty() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance.set(balance);
    }

    public int getFees() {
        return fees.get();
    }

    public SimpleIntegerProperty feesProperty() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees.set(fees);
    }

    public Apartment(Integer houseno, String tname, Integer id, Integer rent, Integer waterpay, Integer elec, Integer waste, Integer fees,String months) {

        this.tenant_name = new SimpleStringProperty(tname);
        this.id_no = new SimpleIntegerProperty(id);
        this.house_no = new SimpleIntegerProperty(houseno);


        this.rent = new SimpleIntegerProperty(rent);
        this.water = new SimpleIntegerProperty(waterpay);
        this.electricity = new SimpleIntegerProperty(elec);
        this.garbage = new SimpleIntegerProperty(waste);

        this.fees = new SimpleIntegerProperty(fees);
        this.months = new SimpleStringProperty(months);
    }


    public String getTenant_name() {
        return tenant_name.get();
    }

    public int getRent() {
        return rent.get();
    }

    public SimpleIntegerProperty rentProperty() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent.set(rent);
    }

    public SimpleStringProperty tenant_nameProperty() {
        return tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        this.tenant_name.set(tenant_name);
    }

    public int getId_no() {
        return id_no.get();
    }

    public SimpleIntegerProperty id_noProperty() {
        return id_no;
    }

    public void setId_no(int id_no) {
        this.id_no.set(id_no);
    }

    public int getHouse_no() {
        return house_no.get();
    }

    public SimpleIntegerProperty house_noProperty() {
        return house_no;
    }

    public void setHouse_no(int house_no) {
        this.house_no.set(house_no);
    }



    public int getWater() {
        return water.get();
    }

    public SimpleIntegerProperty waterProperty() {
        return water;
    }

    public void setWater(int water) {
        this.water.set(water);
    }

    public int getElectricity() {
        return electricity.get();
    }

    public SimpleIntegerProperty electricityProperty() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity.set(electricity);
    }

    public int getGarbage() {
        return garbage.get();
    }

    public SimpleIntegerProperty garbageProperty() {
        return garbage;
    }

    public void setGarbage(int garbage) {
        this.garbage.set(garbage);
    }

    public String getMonths() {
        return months.get();
    }

    public SimpleStringProperty monthsProperty() {
        return months;
    }

    public void setMonths(String months) {
        this.months.set(months);
    }
}