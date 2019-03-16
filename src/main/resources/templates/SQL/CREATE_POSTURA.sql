-- Table: public.postura

-- DROP TABLE public.postura;

CREATE TABLE public.postura
(
    codigo_postura bigint,
    comentario character varying(100),
    dataentrada character varying(10),
    datasaida character varying(10),
    maximo_aves integer NOT NULL,
	quantidade integer NOT NULL,
	tipo_ave character varying(25) NOT NULL,
	PRIMARY KEY (codigo_postura),
	FOREIGN KEY (tipo_ave) REFERENCES tipo_ave (nome_ave)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.postura
    OWNER to vpmngamaiuvqjh;