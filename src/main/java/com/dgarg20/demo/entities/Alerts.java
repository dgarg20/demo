package com.dgarg20.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */


@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Alerts {
    @Id
    private long id;

    private String description;

    private long teamId;

    private String status;
}
