package com.umut.ubank.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    @CreatedDate
    private Date createdDate = new Date();

    @CreatedBy
    private String createdBy = "Admin";

    @LastModifiedDate
    private Date LastModifiedDate = new Date();

    @LastModifiedBy
    private String lastModifiedBy = "Admin";
}
