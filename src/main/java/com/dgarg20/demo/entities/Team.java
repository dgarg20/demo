package com.dgarg20.demo.entities;

import com.google.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Deepanshu Garg on 26/11/20.
 */

@Entity
@Table(name = "team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "name")
    private String name;

    @ManyToMany()
    @JoinColumn(name = "user_id")
    private List<User> users;

    public Team(String teamId, String name){
        this.name = name;
        this.teamId = teamId;
    }

}
