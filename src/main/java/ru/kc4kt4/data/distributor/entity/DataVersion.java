package ru.kc4kt4.data.distributor.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class DataVersion {

    @Id
    private String dataName;
    private String version;
}
