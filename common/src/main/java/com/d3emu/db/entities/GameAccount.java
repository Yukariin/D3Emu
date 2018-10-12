package com.d3emu.db.entities;

import com.d3emu.db.InetAddressType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.net.InetAddress;

@Entity
@Table(name = "game_accounts")
@TypeDefs(value = {
        @TypeDef(name = "inet", typeClass = InetAddressType.class)
})
public class GameAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Type(type = "inet")
    @Column(name = "last_ip", columnDefinition = "INET")
    private InetAddress lastIp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login", nullable = false)
    private Date lastLogin = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    @OneToMany(mappedBy="gameAccount", fetch = FetchType.LAZY)
    private Set<Toon> toons;

    public Integer getId() {
        return id;
    }

    public InetAddress getLastIp() {
        return lastIp;
    }

    public void setLastIp(InetAddress address) {
        lastIp = address;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date date) {
        lastLogin = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Toon> getToons() {
        return toons;
    }
}
