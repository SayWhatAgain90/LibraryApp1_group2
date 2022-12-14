
select count(*) as borrowed_books from users u inner join book_borrow bb
    on u.id = bb.user_id
where is_returned = 0;