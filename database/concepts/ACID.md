## ACID Properties in DBMS
Source: https://www.geeksforgeeks.org/acid-properties-in-dbms/

A **transaction** is a single logical unit of work that accesses and possibly modifies the contents of a database. Transactions access data using read and write operations.
In order to maintain consistency in a database, before and after the transaction, certain properties are followed. These are called ACID properties.

![](images/ACID-Properties.jpg)

### Atomicity
Each transaction is considered as one unit and either runs to completion or is not executed at all

It involves the following two operations.

 - **Abort**: If a transaction aborts, changes made to the database are not visible.
 - **Commit**: If a transaction commits, changes made are visible. 

Atomicity is also known as the ‘All or nothing rule’. 

Example: Bank transfer between 2 account

### Consistency

Integrity constraints must be maintained so that the database is consistent before and after the transaction

Example: Bank transfer between 2 account
SUM(amount of 2 accounts) before transaction = SUM total amount after transaction

### Isolation

Ensures that multiple transactions can occur concurrently without leading to the inconsistency of the database state

Example: multi transaction execute the same time

### Durability

This property ensures that once the transaction has completed execution, the updates and modifications to the database are stored in and written to disk and they persist even if a system failure occurs. These updates now become permanent and are stored in non-volatile memory. The effects of the transaction, thus, are never lost. 