
## Transaction Isolation Levels in DBMS
Source: https://www.geeksforgeeks.org/transaction-isolation-levels-dbms/

 - **Dirty Read** – A Dirty read is a situation when a transaction reads data that has not yet been committed. For example, Let’s say transaction 1 updates a row and leaves it uncommitted, meanwhile, Transaction 2 reads the updated row. If transaction 1 rolls back the change, transaction 2 will have read data that is considered never to have existed.
 - **Non Repeatable** read – Non Repeatable read occurs when a transaction reads the same row twice and gets a different value each time. For example, suppose transaction T1 reads data. Due to concurrency, another transaction T2 updates the same data and commit, Now if transaction T1 rereads the same data, it will retrieve a different value.
 - **Phantom Read** – Phantom Read occurs when two same queries are executed, but the rows retrieved by the two, are different. For example, suppose transaction T1 retrieves a set of rows that satisfy some search criteria. Now, Transaction T2 generates some new rows that match the search criteria for transaction T1. If transaction T1 re-executes the statement that reads the rows, it gets a different set of rows this time.



### The SQL standard defines four isolation levels :



 - Read Uncommitted – Read Uncommitted is the lowest isolation level. In this level, one transaction may read not yet committed changes made by other transactions, thereby allowing dirty reads. At this level, transactions are not isolated from each other.


 - Read Committed – This isolation level guarantees that any data read is committed at the moment it is read. Thus it does not allow dirty read. The transaction holds a read or write lock on the current row, and thus prevents other transactions from reading, updating, or deleting it.


 - Repeatable Read – This is the most restrictive isolation level. The transaction holds read locks on all rows it references and writes locks on referenced rows for update and delete actions. Since other transactions cannot read, update or delete these rows, consequently it avoids non-repeatable read.


 - Serializable – This is the highest isolation level. A serializable execution is guaranteed to be serializable. Serializable execution is defined to be an execution of operations in which concurrently executing transactions appears to be serially executing.

<table aria-label="Table 1" class="table table-sm">
<thead>
<tr>
<th>Transaction isolation level</th>
<th>Dirty reads</th>
<th>Nonrepeatable reads</th>
<th>Phantoms</th>
</tr>
</thead>
<tbody>
<tr>
<td>Read uncommitted</td>
<td>X</td>
<td>X</td>
<td>X</td>
</tr>
<tr>
<td>Read committed</td>
<td>--</td>
<td>X</td>
<td>X</td>
</tr>
<tr>
<td>Repeatable read</td>
<td>--</td>
<td>--</td>
<td>X</td>
</tr>
<tr>
<td>Serializable</td>
<td>--</td>
<td>--</td>
<td>--</td>
</tr>
</tbody>
</table>