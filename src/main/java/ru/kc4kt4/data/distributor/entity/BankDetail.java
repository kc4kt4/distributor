package ru.kc4kt4.data.distributor.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "bank_detail")
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
