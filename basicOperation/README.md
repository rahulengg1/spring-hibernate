# Basic Operation
1. READ
2. WRITE
3. UPDATE
4. DELETE
5. PAGINATION
6. SORTING
7. JPQL QUERY
7. NATIVE QUERY
8. NAMED QUERY

All the operations can be found in test class EntitiesTest and tests are self-explanatory



DB schema for Employee table used in example

```
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_of_birth` datetime(6) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
```
