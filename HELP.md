```bash
docker run -p 5432:5432 -e POSTGRES_PASSWORD=123456 postgres run
```

```sql
create table person (
    id int primary key,
    first_name varchar(255),
    last_name varchar(255)
);

insert into person(id, first_name, last_name)
values
    (1, 'Ivan', 'Ivanov'),
    (2, 'Petr', 'Petrov');
```
