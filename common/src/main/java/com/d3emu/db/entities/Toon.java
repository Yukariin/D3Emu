package com.d3emu.db.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "toons")
public class Toon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="game_account_id", nullable=false)
    private GameAccount gameAccount;

    @Column(name = "name")
    private String name;
    
    @Column(name = "gbid_class", nullable=false)
    private Integer gbidClass;

    @Column(name = "level", nullable=false)
    private Integer level = 1;
    
    @Column(name = "last_played_act", nullable=false)
    private Integer lastPlayedAct = 0;

    @Column(name = "highest_unlocked_act", nullable=false)
    private Integer highestUnlockedAct = 0;

    @Column(name = "last_played_quest", nullable=false)
    private Integer lastPlayedQuest;

    @Column(name = "last_played_quest_step", nullable=false)
    private Integer lastPlayedQuestStep;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_played", nullable = false)
    private Date lastPlayed = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created = new Date();

    public Integer getId() {
        return id;
    }

    public GameAccount getGameAccount() {
        return gameAccount;
    }

    public void setGameAccount(GameAccount gameAccount) {
        this.gameAccount = gameAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGbidClass() {
        return gbidClass;
    }

    public void setGbidClass(Integer gbidClass) {
        this.gbidClass = gbidClass;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLastPlayedAct() {
        return lastPlayedAct;
    }

    public void setLastPlayedAct(Integer lastPlayedAct) {
        this.lastPlayedAct = lastPlayedAct;
    }
    
    public Integer getHighestUnlockedAct() {
        return highestUnlockedAct;
    }

    public void setHighestUnlockedAct(Integer highestUnlockedAct) {
        this.highestUnlockedAct = highestUnlockedAct;
    }

    public Integer getLastPlayedQuest() {
        return lastPlayedQuest;
    }

    public void setLastPlayedQuest(Integer lastPlayedQuest) {
        this.lastPlayedQuest = lastPlayedQuest;
    }

    public Integer getLastPlayedQuestStep() {
        return lastPlayedQuestStep;
    }

    public void setLastPlayedQuestStep(Integer lastPlayedQuestStep) {
        this.lastPlayedQuestStep = lastPlayedQuestStep;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date date) {
        lastPlayed = date;
    }
}
