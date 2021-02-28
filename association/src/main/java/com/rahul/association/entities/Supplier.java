package com.rahul.association.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<PhoneNumber> phoneNumberSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPhoneNumberSet(PhoneNumber phoneNumber) {
        if (phoneNumber != null) {
            if (phoneNumberSet == null) {
                phoneNumberSet = new HashSet<>();
            }
            phoneNumber.setSupplier(this);
            phoneNumberSet.add(phoneNumber);
        }
    }

    public Set<PhoneNumber> getPhoneNumberSet() {
        return phoneNumberSet;
    }

    public void setPhoneNumberSet(Set<PhoneNumber> phoneNumberSet) {
        this.phoneNumberSet = phoneNumberSet;
    }

}
