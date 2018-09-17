-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
  id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  name character varying(128) COLLATE pg_catalog."default" NOT NULL,
  age numeric NOT NULL,
  address character varying(256) COLLATE pg_catalog."default",
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;
