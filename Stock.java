/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDate;

/*
 * @author matth
 */
public class Stock {

    private int id;
    private String customer;
    private int quanity;
    private String position;
    private LocalDate date;
    private String notes;
    

    public Stock(int inId, String inCustomer, int inQuanity, String inPosition, LocalDate inDate, String inNotes) {
        id = inId;
        customer = inCustomer;
        quanity = inQuanity;
        position = inPosition;
        date = inDate;
        notes = inNotes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        String ret = id + ";" + customer + ";" + quanity + ";" + position + ";" + date + ";" + notes;
        return ret;
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public int getQuanity() {
        return quanity;
    }

    public String getPosition() {
        return position;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }
}
