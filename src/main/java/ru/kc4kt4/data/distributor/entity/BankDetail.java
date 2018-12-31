package ru.kc4kt4.data.distributor.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class BankDetail {
    @Id
    private String bik;
    private String ks;
    private String name;
    private String nameMini;
    private String index;
    private String city;
    private String address;
    private String phone;
    private String okato;
    private String okpo;
    private String regNum;
    private String srok;
    private String dateAdd;
    private String dateChange;
}
