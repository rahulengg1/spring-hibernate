package com.rahul.inheritance;

import com.rahul.inheritance.single.table.entities.Cheque;
import com.rahul.inheritance.single.table.entities.CreditCard;
import com.rahul.inheritance.single.table.entities.Payment;
import com.rahul.inheritance.single.table.entities.repository.PaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InheritanceSingleTableTests {

    @Autowired
    PaymentRepository paymentRepository;

    @Test
    public void testCreditCard()
    {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("12345");
        creditCard.setId(1);
        creditCard.setAmount(100);
        paymentRepository.save(creditCard);

        List<Payment> savedPaymentInfo = paymentRepository.findAll();
        assertEquals(1, savedPaymentInfo.size());
        assertEquals("12345", ((CreditCard)savedPaymentInfo.get(0)).getCardNumber());
    }

    @Test
    public void testCheque()
    {
        Cheque cheque = new Cheque();
        cheque.setChequeNumber("54321");
        cheque.setId(2);
        cheque.setAmount(100);
        paymentRepository.save(cheque);

        List<Payment> savedPaymentInfo = paymentRepository.findAll();
        assertEquals(1, savedPaymentInfo.size());
        assertEquals("54321", ((Cheque)savedPaymentInfo.get(0)).getChequeNumber());
    }


    @AfterEach
    public void clearDb()
    {
        paymentRepository.deleteAll();
    }
}
