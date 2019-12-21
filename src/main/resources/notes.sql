CREATE TABLE public.notes
(
    id integer,
    customer_id integer,
    notes text,
    createtime timestamp with time zone,
    updatetime timestamp with time zone,
    PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

ALTER TABLE public.notes
    OWNER to "seatMe";

ALTER TABLE public.notes
    ADD COLUMN id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 999999999 CACHE 1 );