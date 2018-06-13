package com.d3emu.db.entities;

import com.d3emu.db.InetAddressType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
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

    @Column(name = "pass_hash", nullable = false, unique = true)
    private String passHash;

    @Type(type = "inet")
    @Column(name = "last_ip", columnDefinition = "inet")
    private InetAddress lastIp;
}
