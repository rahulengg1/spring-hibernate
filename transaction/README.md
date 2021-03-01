# Transaction

Transaction in database is a unit of tasks which maintain Atomicity, Consistency, Isolation, and Durability  (ACID properties)
Like transferring an amount from one bank account to another bank account, In case of failure account there should not be an amount transfer operation happened



Table used to test transaction operation using JPA
```
CREATE TABLE bank_account (
account_no int(11) NOT NULL AUTO_INCREMENT,
balance int(11) NOT NULL,
first_name varchar(255) DEFAULT NULL,
last_name varchar(255) DEFAULT NULL,
PRIMARY KEY (account_no)
);
```

