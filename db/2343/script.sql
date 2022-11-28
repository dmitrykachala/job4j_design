create or replace procedure delete_data(i_count integer)
language 'plpgsql'
as $$
BEGIN
delete from products * where count = i_count;
END
$$;

create or replace function f_delete_data(u_id integer)
returns void
language 'plpgsql'
as
$$
begin
delete from products * where id = u_id;
end;
$$;