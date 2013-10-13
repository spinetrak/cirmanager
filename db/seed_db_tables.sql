CREATE TABLE if not exists public.ciids
(
  ciid BIGSERIAL NOT NULL,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT PK_CIIDS PRIMARY KEY (ciid)
);

CREATE TABLE if not exists public.cirs
(
  cirid BIGSERIAL NOT NULL,
  ciid BIGINT NOT NULL,
  summary VARCHAR(255) not null default 'summary test',
  createdOn DATE not null default '1970-01-01',
  createdBy VARCHAR(30) not null default 'user@example.com',
  status VARCHAR(20) not null default 'created',
  CONSTRAINT PK_CIRS PRIMARY KEY (cirid)
);

insert into public.ciids(name)
  values ('ciid:/my/first/ciid');

insert into public.cirs(ciid,summary,createdBy)
  values (1,'summary of first CIR','cirmanager@spinetrak.net');
