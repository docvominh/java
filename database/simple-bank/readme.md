https://docs.jboss.org/hibernate/core/3.3/reference/en/html/best-practices.html

## SQL and Hibernate
Question -> How SQL pronunciation?

Why hibernate over SQL? 
- Quick setup
- Object mapping
- Database independent
- Lazy loading and more...


## Hibernate join

### unidirectional vs bidirectional
Employee-Customer

Avoid use bidirectional (stackoverflow problem)

### Cascade Types
@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

### @OneToOne

OneToOne association should always share the Primary Key with the parent table.

PK and FK columns are most often indexed, so sharing the PK can reduce the index footprint by half, which is desirable since you want to store all your indexes into memory to speed up index scanning.

### @OneToMany

### @ManyToOne and @JoinColumn

### @ManyToMany and @JoinTable

## N+1 problem
Hibernate N+1 problem occurs when you use FetchType.LAZY for your entity associations. If you perform a query to select n-entities and if you try to call any access method of your entity's lazy association, Hibernate will perform n-additional queries to load lazily fetched objects.

AccountEntity example, comment transaction for easy track
## Testing

### Unit test

### Integration test

1970
SQL
SEQUEL -> Structured English QUEry Language