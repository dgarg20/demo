package com.dgarg20.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserList {
    List<String> userIds;
}
