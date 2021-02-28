package com.rahul.association;

import com.rahul.association.onetoone.entities.License;
import com.rahul.association.onetoone.entities.Person;
import com.rahul.association.onetoone.entities.repo.LicenseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AssociationOneToOneTests {


    @Autowired
    LicenseRepository licenseRepository;


    @Test
    public void testOneToOneAssociation() {

        License license = new License();
        license.setType("CAR");
        license.setValidFrom(new Date());
        license.setValidTo(new Date());

        // To Test whether we can assocaite or not lets add two records, for OneToOne association last record override existing records
        Person person1 = new Person();
        person1.setAge(45);
        person1.setFirstName("test");
        person1.setLastName("user 1");

        Person person2 = new Person();
        person2.setAge(49);
        person2.setFirstName("test");
        person2.setLastName("user 2");
        license.setPerson(person2);

        license.setPerson(person1);
        license.setPerson(person2);

        licenseRepository.save(license);

        final List<License> all = licenseRepository.findAll();
        Assertions.assertTrue(all.size() == 1);

        Person person = all.get(0).getPerson();

        Assertions.assertEquals("user 2", person.getLastName());
        Assertions.assertEquals("test", person.getFirstName());
        Assertions.assertEquals(49, person.getAge());

    }



}
