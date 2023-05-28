-- Table: public.books

-- DROP TABLE IF EXISTS public.books;

CREATE TABLE IF NOT EXISTS public.books
(
    id bigint NOT NULL,
    author character varying(255) COLLATE pg_catalog."default",
    catagory character varying(255) COLLATE pg_catalog."default",
    company character varying(255) COLLATE pg_catalog."default",
    isbn character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    is_borrowed boolean,
    CONSTRAINT books_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.books
    OWNER to postgres;
	



-- Table: public.bookborrowdetails

-- DROP TABLE IF EXISTS public.bookborrowdetails;

CREATE TABLE IF NOT EXISTS public.bookborrowdetails
(
    id bigint NOT NULL,
    book_id bigint,
    from_borrowed timestamp(6) without time zone,
    to_borrowed timestamp(6) without time zone,
    user_id bigint,
    book_borrow_fine integer,
    is_returned boolean,
    CONSTRAINT bookborrowdetails_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.bookborrowdetails
    OWNER to postgres;
	


-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    mobile_no character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    roles character varying(255) COLLATE pg_catalog."default",
    user_name character varying(255) COLLATE pg_catalog."default",
    can_edit boolean,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;