CREATE TABLE public.customers
(
    id integer,
    name character(500),
    phonenumber character(50),
    partysize integer,
    "num_kids" integer,
    status character(10),
    createtime timestamp with time zone,
    updatetime timestamp with time zone,
    reservationtime timestamp with time zone,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.customers
    OWNER to "seatMe";

ALTER TABLE public.customers
    ADD COLUMN id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 999999999 CACHE 1 );