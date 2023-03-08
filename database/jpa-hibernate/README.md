# Hibernate and JPA

https://docs.jboss.org/hibernate/orm/4.3/devguide/en-US/html_single/

## flush()

Calling flush() we force hibernate to execute the SQL commands on Database. But do understand that changes are not "committed" yet.

This one can help to release memory (instead perform huge execute at the end)

```java
Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

for (int i = 0; i < 100000; i++) {
    Customer customer = new Customer(...);
    session.save(customer);
    if (i % 20 == 0) { // 20, same as the JDBC batch size
        // flush a batch of inserts and release memory:
        session.flush();
        session.clear();
    }
}

tx.commit();
session.close();
```
flush() automatically call when
- before some query executions
- from org.hibernate.Transaction.commit()
- from Session.flush()

## Cache

### Query cache
If you have queries that run over and over, with the same parameters, query caching provides performance gains

### Level 2 cahce

## Locking

### Optimistic Lock

Add column version and auto increase by @Version annotation

### Pessimistic Lock

Working with database to provide lock

Lock only release when transaction committed (even for query)

#### Transaction isolation

It's JDBC property

| Transaction Isolation Level| Transaction Isolation Level|
| ----------- | ----------- |
| read-uncommitted      | Dirty reads, non-repeatable reads, and phantom reads can occur.        |
| read-committed   | Dirty reads are prevented; non-repeatable reads and phantom reads can occur.         |
| repeatable-read   | Dirty reads and non-repeatable reads are prevented; phantom reads can occur.         |
| serializable   | Dirty reads, non-repeatable reads and phantom reads are prevented.         |

#### Lock mode

https://docs.jboss.org/hibernate/orm/3.5/api/org/hibernate/LockMode.html

- OPTIMISTIC
- OPTIMISTIC_FORCE_INCREMENT
- PESSIMISTIC_READ
- PESSIMISTIC_WRITE
- PESSIMISTIC_FORCE_INCREMENT
