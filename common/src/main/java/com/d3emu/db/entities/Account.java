package com.d3emu.db.entities;

import com.d3emu.db.InetAddressType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.net.InetAddress;
import java.util.Date;
import java.util.Set;

@TypeDefs(value = {
        @TypeDef(name = "inet", typeClass = InetAddressType.class)
})
@NamedQueries({
        @NamedQuery(name = "get_account_by_ticket",
                    query = "select a from Account a where webLoginTicket = :ticket")
})
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_digest", nullable = false)
    private String passwordDigest;

    @Type(type = "inet")
    @Column(name = "last_ip", columnDefinition = "INET")
    private InetAddress lastIp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login", nullable = false)
    private Date lastLogin = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created = new Date();

    @Column(name = "web_login_ticket", length = 64)
    private String webLoginTicket;

    @OneToMany(mappedBy="account", fetch = FetchType.LAZY)
    private Set<GameAccount> gameAccounts;

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<GameAccount> getGameAccounts() {
        return gameAccounts;
    }

    public void setGameAccounts(Set<GameAccount> gameAccounts) {
        this.gameAccounts = gameAccounts;
    }
}
