package com.jta.transaction.h2.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "TRA", name = "COLLEGE_STUDENT")
public class Team {

    @Id
    private String teamName;

    private String gameName;

    @ElementCollection
    @CollectionTable(name = "TEAM_PLAYERS", schema = "TRA")
    @Column(name = "PLAYER_NAME")
    private List<String> players;

}
