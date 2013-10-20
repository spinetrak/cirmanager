delete from public.cirs;
delete from public.userroles;
delete from public.users;
delete from public.roles;
delete from public.ciids;


insert into public.ciids(name)
  values ('ciid:/my/first/ciid');

insert into public.roles(role)
  values ('approver');

insert into public.roles(role)
  values ('executor');

insert into public.users(email,password)
  values ('norole@example.com','123456');
insert into public.users(email,password)
  values ('approver@example.com','123456');
insert into public.users(email,password)
  values ('executor@example.com','123456');

insert into public.userroles(ciid,userid)
  values (1,1);
insert into public.userroles(ciid,roleid,userid)
  values (1,1,2);
insert into public.userroles(ciid,roleid,userid)
  values (1,2,3);


insert into public.cirs(ciid,createdby)
  values(1,3);



