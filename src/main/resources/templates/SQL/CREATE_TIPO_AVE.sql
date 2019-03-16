-- Table: public.tipo_ave

-- DROP TABLE public.tipo_ave;

CREATE TABLE public.tipo_ave
(
    nome_ave character varying(25) NOT NULL,
    tempo_chocagem integer NOT NULL,
	PRIMARY KEY (nome_ave)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tipo_ave
    OWNER to vpmngamaiuvqjh;