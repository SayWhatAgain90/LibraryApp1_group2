select id, name, isbn, author, added_date from books
where name = 'Clean Code' and author='Robert C.Martin'
order by id desc;

select id, name, isbn, author, added_date from books
where name = 'Clean Code'
order by id desc;