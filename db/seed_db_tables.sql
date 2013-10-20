DROP TABLE if EXISTS public.cirs;
DROP TABLE if EXISTS public.userroles;
DROP TABLE if EXISTS public.users;
DROP TABLE if EXISTS public.roles;
DROP TABLE if EXISTS public.ciids;

CREATE TABLE if NOT EXISTS public.ciids
(
  ciid BIGSERIAL NOT NULL,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT PK_CIIDS PRIMARY KEY (ciid)
);

CREATE TABLE if NOT EXISTS public.roles
(
  roleid SMALLSERIAL NOT NULL,
  role VARCHAR(20) NOT NULL,
  CONSTRAINT PK_ROLES PRIMARY KEY (roleid)
);

CREATE TABLE if NOT EXISTS public.users
(
  userid BIGSERIAL NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(12) NOT NULL,
  CONSTRAINT PK_USERS PRIMARY KEY (userid)
);

CREATE TABLE if NOT EXISTS public.userroles
(
  userroleid BIGSERIAL NOT NULL,
  userid BIGINT REFERENCES users (userid),
  ciid BIGINT REFERENCES ciids(ciid),
  roleid SMALLINT REFERENCES roles(roleid),
  CONSTRAINT PK_USERROLES PRIMARY KEY (userroleid)
);

CREATE TABLE if NOT EXISTS public.cirs
(
  cirid BIGSERIAL NOT NULL,
  ciid BIGINT NOT NULL,
  summary VARCHAR(255) NOT NULL DEFAULT 'summary test',
  createdOn TIMESTAMP NOT NULL DEFAULT '1970-01-01',
  createdBy BIGINT REFERENCES users (userid),
  status VARCHAR(20) NOT NULL DEFAULT 'created',
  description TEXT NOT NULL DEFAULT 'description',
  risk varchar(20) NOT NULL DEFAULT 'low',
  submittedOn TIMESTAMP NULL,
  submittedBy bigint REFERENCES users (userid),
  startDate TIMESTAMP NULL,
  endDate TIMESTAMP NULL,
  CONSTRAINT PK_CIRS PRIMARY KEY (cirid)
);


