package com.d3emu.db.entities;

import com.d3emu.db.InetAddressType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.net.InetAddress;

@Entity
@Table(name = "accounts")
@TypeDefs(value = {
        @TypeDef(name = "inet", typeClass = InetAddressType.class)
})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_digest", nullable = false)
    private String passwordDigest;

    @Type(type = "inet")
    @Column(name = "last_ip", columnDefinition = "inet")
    private InetAddress lastIp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login", nullable = false)
    private Date lastLogin = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created = new Date();

    public Long getId() {
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
}
