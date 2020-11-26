package com.dgarg20.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddTeamRequest {
    private String teamId;
    private String teamName;
}
