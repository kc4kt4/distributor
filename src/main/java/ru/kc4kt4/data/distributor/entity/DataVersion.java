package ru.kc4kt4.data.distributor.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "data_version")
public class DataVersion {

    @Id
    @Column(name = "data_name")
    private String dataName;
    private String version;
}
