package com.rahul.association;

import com.rahul.association.entities.PhoneNumber;
import com.rahul.association.entities.Supplier;
import com.rahul.association.entities.repo.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AssociationOneToManyTests {


    @Autowired
    SupplierRepository supplierRepository;

    @Test
    @Transactional
    public void testAssociation() {
        Supplier supplier = new Supplier();
        supplier.setName("test");

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("100000");
        phoneNumber.setType("mobile phone");

        PhoneNumber landline = new PhoneNumber();
        landline.setPhoneNumber("9999999");
        landline.setType("landline phone");

        supplier.addPhoneNumberSet(phoneNumber);
        supplier.addPhoneNumberSet(landline);

        supplierRepository.save(supplier);

        List<Supplier> savedSupplier = supplierRepository.findAll();
        assertTrue(savedSupplier.size() == 1);
        assertEquals("test", savedSupplier.get(0).getName());

        Set<PhoneNumber> phoneNumberSet = savedSupplier.get(0).getPhoneNumberSet();
        assertTrue(phoneNumberSet.size() == 2);

        // Print the phoneNumber record for reference
        for(PhoneNumber phoneNumberRecord: phoneNumberSet)
        {
            System.out.println(String.format("Phone Number : %s, type : %s", phoneNumberRecord.getPhoneNumber(), phoneNumberRecord.getType()));
        }

    }


    @Test
    @Transactional
    public void delete() {

        Supplier supplier = new Supplier();
        supplier.setName("test");

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("100000");
        phoneNumber.setType("mobile phone");


        PhoneNumber landline = new PhoneNumber();
        landline.setPhoneNumber("9999999");
        landline.setType("landline phone");

        supplier.addPhoneNumberSet(phoneNumber);
        supplier.addPhoneNumberSet(landline);

        supplierRepository.save(supplier);

        List<Supplier> savedSupplier = supplierRepository.findAll();
        assertTrue(savedSupplier.size() == 1);
        assertEquals("test", savedSupplier.get(0).getName());

        //Deleting supplier record also delete the child records in phone_number table recursively, check the logs for this.
        supplierRepository.deleteById(savedSupplier.get(0).getId());

        List<Supplier> supplierRecord = supplierRepository.findAll();
        assertTrue(supplierRecord.size() == 0);
    }


}
