package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.omg.CORBA.INTERNAL;

public class Tenant {

    public SimpleIntegerProperty houseno;
    public SimpleIntegerProperty id;
    public SimpleStringProperty tenant_name;
    public SimpleStringProperty status;

    public SimpleIntegerProperty contact;


    public SimpleIntegerProperty people;
    public SimpleStringProperty vehicle;
    public SimpleStringProperty work;
    public SimpleIntegerProperty employercontact;
    public SimpleStringProperty address;
    public SimpleStringProperty emergencyname;
    public SimpleIntegerProperty emergencyid;
    public SimpleStringProperty box;
    public SimpleIntegerProperty emergencycontact;
    public SimpleStringProperty months;

    public Tenant(Integer houseno, Integer id_no, String tenantname,Integer contact) {

        this.houseno = new SimpleIntegerProperty(houseno);
        this.id = new SimpleIntegerProperty(id_no);
        this.tenant_name = new SimpleStringProperty(tenantname);
        this.contact= new SimpleIntegerProperty(contact);



    }

    public Tenant(int houseno, int id_no, String tenantname, int contact, String month) {
        this.houseno = new SimpleIntegerProperty(houseno);
        this.id = new SimpleIntegerProperty(id_no);
        this.tenant_name = new SimpleStringProperty(tenantname);
        this.contact= new SimpleIntegerProperty(contact);
        this.months = new SimpleStringProperty(month);
    }

    public String getWork() {
        return work.get();
    }

    public SimpleStringProperty workProperty() {
        return work;
    }

    public void setWork(String work) {
        this.work.set(work);
    }

    public int getEmployercontact() {
        return employercontact.get();
    }

    public SimpleIntegerProperty employercontactProperty() {
        return employercontact;
    }

    public void setEmployercontact(int employercontact) {
        this.employercontact.set(employercontact);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getEmergencyname() {
        return emergencyname.get();
    }

    public SimpleStringProperty emergencynameProperty() {
        return emergencyname;
    }

    public void setEmergencyname(String emergencyname) {
        this.emergencyname.set(emergencyname);
    }

    public int getEmergencyid() {
        return emergencyid.get();
    }

    public SimpleIntegerProperty emergencyidProperty() {
        return emergencyid;
    }

    public void setEmergencyid(int emergencyid) {
        this.emergencyid.set(emergencyid);
    }

    public String getBox() {
        return box.get();
    }

    public SimpleStringProperty boxProperty() {
        return box;
    }

    public void setBox(String box) {
        this.box.set(box);
    }

    public int getEmergencycontact() {
        return emergencycontact.get();
    }

    public SimpleIntegerProperty emergencycontactProperty() {
        return emergencycontact;
    }

    public void setEmergencycontact(int emergencycontact) {
        this.emergencycontact.set(emergencycontact);
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

    public String getTenant_name() {
        return tenant_name.get();
    }

    public SimpleStringProperty tenant_nameProperty() {
        return tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        this.tenant_name.set(tenant_name);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }



    public int getHouseno() {
        return houseno.get();
    }

    public SimpleIntegerProperty housenoProperty() {
        return houseno;
    }

    public void setHouseno(int houseno) {
        this.houseno.set(houseno);
    }






    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public int getPeople() {
        return people.get();
    }

    public SimpleIntegerProperty peopleProperty() {
        return people;
    }

    public void setPeople(int people) {
        this.people.set(people);
    }

    public String getVehicle() {
        return vehicle.get();
    }

    public SimpleStringProperty vehicleProperty() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle.set(vehicle);
    }

    public int getContact() {
        return contact.get();
    }

    public SimpleIntegerProperty contactProperty() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact.set(contact);
    }





 }


