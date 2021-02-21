# Inheritance Mapping using JPA

### There are three strategies to implement inheritance using JPA in database

1. SINGLE_TABLE
2. TABLE_PER_CLASS
3. JOINED


1. SINGLE_TABLE

For single_table strategies Payment table is used to show an example

```
CREATE TABLE `payment` (
`pmode` varchar(2) NOT NULL,
`id` int(11) NOT NULL,
`amount` bigint(20) NOT NULL,
`cheque_number` varchar(255) DEFAULT NULL,
`card_number` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
);
```

2. TABLE_PER_CLASS


```
CREATE TABLE `online_customer` (
`id` int(11) NOT NULL,
`discount` bigint(20) NOT NULL,
`name` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `walking_customer` (
  `id` int(11) NOT NULL,
  `discount` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
```

3. JOINED


```
CREATE TABLE `user` (
`id` int(11) NOT NULL,
`discount` bigint(20) NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `agent` (
`name` varchar(255) DEFAULT NULL,
`id` int(11) NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `FK_USER_AGENT_ID` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

CREATE TABLE `employees` (
`name` varchar(255) DEFAULT NULL,
`id` int(11) NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `FK_USER_EMPLOYEE_ID` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);
```






