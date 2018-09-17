-- Table: public.mails

-- DROP TABLE public.mails;

CREATE TABLE public.mails
(
  id bigint NOT NULL DEFAULT nextval('mails_id_seq'::regclass),
  sender bigint,
  recipient bigint,
  mail_topic character varying(64) COLLATE pg_catalog."default" NOT NULL,
  mail_body character varying(256) COLLATE pg_catalog."default",
  sent_date timestamp without time zone NOT NULL,
  delivery_date timestamp without time zone,
  status character varying(16) COLLATE pg_catalog."default" NOT NULL,
  CONSTRAINT mails_pkey PRIMARY KEY (id),
  CONSTRAINT mails_recipient_foreign_key FOREIGN KEY (recipient)
  REFERENCES public.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT mails_sender_foreign_key FOREIGN KEY (sender)
  REFERENCES public.users (id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;
