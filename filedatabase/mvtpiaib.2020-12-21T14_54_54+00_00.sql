--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5 (Ubuntu 11.5-3.pgdg18.04+1)
-- Dumped by pg_dump version 11.5 (Ubuntu 11.5-3.pgdg18.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: btree_gin; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS btree_gin WITH SCHEMA public;


--
-- Name: EXTENSION btree_gin; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION btree_gin IS 'support for indexing common datatypes in GIN';


--
-- Name: btree_gist; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS btree_gist WITH SCHEMA public;


--
-- Name: EXTENSION btree_gist; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION btree_gist IS 'support for indexing common datatypes in GiST';


--
-- Name: citext; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS citext WITH SCHEMA public;


--
-- Name: EXTENSION citext; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION citext IS 'data type for case-insensitive character strings';


--
-- Name: cube; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS cube WITH SCHEMA public;


--
-- Name: EXTENSION cube; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION cube IS 'data type for multidimensional cubes';


--
-- Name: dblink; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dblink WITH SCHEMA public;


--
-- Name: EXTENSION dblink; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dblink IS 'connect to other PostgreSQL databases from within a database';


--
-- Name: dict_int; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dict_int WITH SCHEMA public;


--
-- Name: EXTENSION dict_int; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dict_int IS 'text search dictionary template for integers';


--
-- Name: dict_xsyn; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dict_xsyn WITH SCHEMA public;


--
-- Name: EXTENSION dict_xsyn; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dict_xsyn IS 'text search dictionary template for extended synonym processing';


--
-- Name: earthdistance; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS earthdistance WITH SCHEMA public;


--
-- Name: EXTENSION earthdistance; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION earthdistance IS 'calculate great-circle distances on the surface of the Earth';


--
-- Name: fuzzystrmatch; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS fuzzystrmatch WITH SCHEMA public;


--
-- Name: EXTENSION fuzzystrmatch; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION fuzzystrmatch IS 'determine similarities and distance between strings';


--
-- Name: hstore; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS hstore WITH SCHEMA public;


--
-- Name: EXTENSION hstore; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION hstore IS 'data type for storing sets of (key, value) pairs';


--
-- Name: intarray; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS intarray WITH SCHEMA public;


--
-- Name: EXTENSION intarray; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION intarray IS 'functions, operators, and index support for 1-D arrays of integers';


--
-- Name: ltree; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS ltree WITH SCHEMA public;


--
-- Name: EXTENSION ltree; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION ltree IS 'data type for hierarchical tree-like structures';


--
-- Name: pg_stat_statements; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pg_stat_statements WITH SCHEMA public;


--
-- Name: EXTENSION pg_stat_statements; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pg_stat_statements IS 'track execution statistics of all SQL statements executed';


--
-- Name: pg_trgm; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pg_trgm WITH SCHEMA public;


--
-- Name: EXTENSION pg_trgm; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pg_trgm IS 'text similarity measurement and index searching based on trigrams';


--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


--
-- Name: pgrowlocks; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgrowlocks WITH SCHEMA public;


--
-- Name: EXTENSION pgrowlocks; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgrowlocks IS 'show row-level locking information';


--
-- Name: pgstattuple; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgstattuple WITH SCHEMA public;


--
-- Name: EXTENSION pgstattuple; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgstattuple IS 'show tuple-level statistics';


--
-- Name: tablefunc; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS tablefunc WITH SCHEMA public;


--
-- Name: EXTENSION tablefunc; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION tablefunc IS 'functions that manipulate whole tables, including crosstab';


--
-- Name: unaccent; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA public;


--
-- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


--
-- Name: xml2; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS xml2 WITH SCHEMA public;


--
-- Name: EXTENSION xml2; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION xml2 IS 'XPath querying and XSLT';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO mvtpiaib;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO mvtpiaib;

--
-- Name: jhi_persistent_audit_event; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(50) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_event OWNER TO mvtpiaib;

--
-- Name: jhi_persistent_audit_evt_data; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.jhi_persistent_audit_evt_data (
    event_id bigint NOT NULL,
    name character varying(150) NOT NULL,
    value character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_evt_data OWNER TO mvtpiaib;

--
-- Name: sequence_generator; Type: SEQUENCE; Schema: public; Owner: mvtpiaib
--

CREATE SEQUENCE public.sequence_generator
    START WITH 1050
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sequence_generator OWNER TO mvtpiaib;

--
-- Name: tbaccountbank; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.tbaccountbank (
    id_accountbank integer NOT NULL,
    id_workspace integer,
    ds_accountbank character varying(50),
    initial_value numeric(30,2),
    cod_conta character varying(30),
    digito character varying(10)
);


ALTER TABLE public.tbaccountbank OWNER TO mvtpiaib;

--
-- Name: tbaccountbank_id_accountbank_seq; Type: SEQUENCE; Schema: public; Owner: mvtpiaib
--

CREATE SEQUENCE public.tbaccountbank_id_accountbank_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbaccountbank_id_accountbank_seq OWNER TO mvtpiaib;

--
-- Name: tbaccountbank_id_accountbank_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mvtpiaib
--

ALTER SEQUENCE public.tbaccountbank_id_accountbank_seq OWNED BY public.tbaccountbank.id_accountbank;


--
-- Name: tbconfigextrato; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.tbconfigextrato (
    idworkspace integer NOT NULL,
    tokenextrato character varying(100) NOT NULL,
    idnatureplan integer,
    idconfigextrato integer NOT NULL,
    id_accountank integer,
    ind_not_imp character varying,
    CONSTRAINT fck_configext_ind CHECK (((ind_not_imp)::text = 'S'::text))
);


ALTER TABLE public.tbconfigextrato OWNER TO mvtpiaib;

--
-- Name: tbconfigextrato_idconfigextrato_seq; Type: SEQUENCE; Schema: public; Owner: mvtpiaib
--

CREATE SEQUENCE public.tbconfigextrato_idconfigextrato_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbconfigextrato_idconfigextrato_seq OWNER TO mvtpiaib;

--
-- Name: tbconfigextrato_idconfigextrato_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mvtpiaib
--

ALTER SEQUENCE public.tbconfigextrato_idconfigextrato_seq OWNED BY public.tbconfigextrato.idconfigextrato;


--
-- Name: tbloginworkspace; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.tbloginworkspace (
    idworklogin information_schema.cardinal_number,
    login character varying(100),
    idworkspace integer
);


ALTER TABLE public.tbloginworkspace OWNER TO mvtpiaib;

--
-- Name: tbmovementbank; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.tbmovementbank (
    idmovementbank integer NOT NULL,
    id_accountbank integer,
    id_natureplan integer,
    movementdate date NOT NULL,
    movementvalue numeric(30,2),
    history character varying(200),
    number_doc character varying(100),
    accid character varying(100)
);


ALTER TABLE public.tbmovementbank OWNER TO mvtpiaib;

--
-- Name: tbmovementbank_idmovementbank_seq; Type: SEQUENCE; Schema: public; Owner: mvtpiaib
--

CREATE SEQUENCE public.tbmovementbank_idmovementbank_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbmovementbank_idmovementbank_seq OWNER TO mvtpiaib;

--
-- Name: tbmovementbank_idmovementbank_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mvtpiaib
--

ALTER SEQUENCE public.tbmovementbank_idmovementbank_seq OWNED BY public.tbmovementbank.idmovementbank;


--
-- Name: tbnatureplan; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.tbnatureplan (
    id_natureplan integer NOT NULL,
    id_workspace integer NOT NULL,
    ds_natureplan character varying(50),
    id_natureplan_father integer,
    ind_type character varying(1) NOT NULL
);


ALTER TABLE public.tbnatureplan OWNER TO mvtpiaib;

--
-- Name: tbnatureplan_id_natureplan_seq; Type: SEQUENCE; Schema: public; Owner: mvtpiaib
--

CREATE SEQUENCE public.tbnatureplan_id_natureplan_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbnatureplan_id_natureplan_seq OWNER TO mvtpiaib;

--
-- Name: tbnatureplan_id_natureplan_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mvtpiaib
--

ALTER SEQUENCE public.tbnatureplan_id_natureplan_seq OWNED BY public.tbnatureplan.id_natureplan;


--
-- Name: tbprojeto; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.tbprojeto (
    id bigint NOT NULL,
    data_final timestamp without time zone,
    data_inicial timestamp without time zone,
    nome_projeto character varying(255),
    id_workspace bigint
);


ALTER TABLE public.tbprojeto OWNER TO mvtpiaib;

--
-- Name: tbprojetonatureza; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.tbprojetonatureza (
    id bigint NOT NULL,
    valor_previsto double precision,
    valor_realizado double precision,
    id_natureplan bigint,
    id_projeto bigint
);


ALTER TABLE public.tbprojetonatureza OWNER TO mvtpiaib;

--
-- Name: tbuser; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.tbuser (
    login character varying(100) NOT NULL,
    password character varying(10),
    email character varying(50)
);


ALTER TABLE public.tbuser OWNER TO mvtpiaib;

--
-- Name: tbworkspace; Type: TABLE; Schema: public; Owner: mvtpiaib
--

CREATE TABLE public.tbworkspace (
    id_workspace integer NOT NULL,
    ds_workspace character varying(50)
);


ALTER TABLE public.tbworkspace OWNER TO mvtpiaib;

--
-- Name: tbworkspace_id_workspace_seq; Type: SEQUENCE; Schema: public; Owner: mvtpiaib
--

CREATE SEQUENCE public.tbworkspace_id_workspace_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbworkspace_id_workspace_seq OWNER TO mvtpiaib;

--
-- Name: tbworkspace_id_workspace_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mvtpiaib
--

ALTER SEQUENCE public.tbworkspace_id_workspace_seq OWNED BY public.tbworkspace.id_workspace;


--
-- Name: vw_alimentacao_curva_s; Type: VIEW; Schema: public; Owner: mvtpiaib
--

CREATE VIEW public.vw_alimentacao_curva_s AS
 SELECT ger.mes,
    ger.movementdate,
    sum(
        CASE
            WHEN (ger.tipo = 'Previsto'::text) THEN ger.valor
            ELSE (0)::numeric
        END) AS valor_previsto,
    sum(
        CASE
            WHEN (ger.tipo = 'Realizado'::text) THEN ger.valor
            ELSE (0)::numeric
        END) AS valor_realizado,
    sum(
        CASE
            WHEN (ger.tipo = 'Realizado'::text) THEN ger.valor
            ELSE (ger.valor * ('-1'::integer)::numeric)
        END) AS diferenca
   FROM ( SELECT date_trunc('month'::text, (tbmovementbank.movementdate)::timestamp with time zone) AS mes,
            'Realizado'::text AS tipo,
            tbmovementbank.movementdate,
            (( SELECT sum(mk.movementvalue) AS sum
                   FROM public.tbmovementbank mk
                  WHERE ((mk.id_natureplan = 6) AND (mk.movementdate >= date_trunc('month'::text, (tbmovementbank.movementdate)::timestamp with time zone)) AND (mk.movementdate <= tbmovementbank.movementdate))) * ('-1'::integer)::numeric) AS valor
           FROM ((public.tbmovementbank tbmovementbank
             LEFT JOIN public.tbnatureplan tbnatureplan ON ((tbmovementbank.id_natureplan = tbnatureplan.id_natureplan)))
             JOIN public.tbaccountbank tbaccountbank ON ((tbmovementbank.id_accountbank = tbaccountbank.id_accountbank)))
          WHERE (tbnatureplan.id_natureplan = 6)
          GROUP BY tbmovementbank.movementdate
        UNION
         SELECT date_trunc('month'::text, (tbmovementbank.movementdate)::timestamp with time zone) AS mes,
            'Previsto'::text AS tipo,
            tbmovementbank.movementdate,
            (to_number(to_char((date_trunc('hour'::text, (tbmovementbank.movementdate)::timestamp with time zone) - date_trunc('month'::text, (tbmovementbank.movementdate)::timestamp with time zone)), 'DD'::text), '999'::text) * ((2500 / 30))::numeric) AS valor
           FROM (public.tbmovementbank tbmovementbank
             LEFT JOIN public.tbnatureplan tbnatureplan ON ((tbmovementbank.id_natureplan = tbnatureplan.id_natureplan))),
            public.tbaccountbank tbaccountbank
          WHERE ((tbmovementbank.id_accountbank = tbaccountbank.id_accountbank) AND (tbnatureplan.id_natureplan = 6))
          GROUP BY tbmovementbank.movementdate) ger
  GROUP BY ger.mes, ger.movementdate
  ORDER BY ger.mes, ger.movementdate;


ALTER TABLE public.vw_alimentacao_curva_s OWNER TO mvtpiaib;

--
-- Name: vw_saldo_total; Type: VIEW; Schema: public; Owner: mvtpiaib
--

CREATE VIEW public.vw_saldo_total AS
 SELECT sum((x.movim + x.inicial)) AS total
   FROM ( SELECT tbmovementbank.id_accountbank,
            sum(tbmovementbank.movementvalue) AS movim,
            COALESCE(tbaccountbank.initial_value, (0)::numeric) AS inicial
           FROM public.tbmovementbank tbmovementbank,
            public.tbaccountbank tbaccountbank
          WHERE (tbmovementbank.id_accountbank = tbaccountbank.id_accountbank)
          GROUP BY tbmovementbank.id_accountbank, COALESCE(tbaccountbank.initial_value, (0)::numeric)) x;


ALTER TABLE public.vw_saldo_total OWNER TO mvtpiaib;

--
-- Name: vw_supermarket_outubro; Type: VIEW; Schema: public; Owner: mvtpiaib
--

CREATE VIEW public.vw_supermarket_outubro AS
 SELECT 'Realizado'::text AS tipo,
    tbmovementbank.movementdate,
    (( SELECT sum(mk.movementvalue) AS sum
           FROM public.tbmovementbank mk
          WHERE ((mk.id_natureplan = 6) AND (mk.movementdate >= to_date('24-09-20'::text, 'dd-mm-yy'::text)) AND (mk.movementdate <= tbmovementbank.movementdate))) * ('-1'::integer)::numeric) AS valor
   FROM ((public.tbmovementbank tbmovementbank
     LEFT JOIN public.tbnatureplan tbnatureplan ON ((tbmovementbank.id_natureplan = tbnatureplan.id_natureplan)))
     JOIN public.tbaccountbank tbaccountbank ON ((tbmovementbank.id_accountbank = tbaccountbank.id_accountbank)))
  WHERE ((tbnatureplan.id_natureplan = 6) AND (tbmovementbank.movementdate >= to_date('24-09-20'::text, 'dd-mm-yy'::text)))
  GROUP BY tbmovementbank.movementdate
UNION
 SELECT 'Previsto'::text AS tipo,
    (tbmovementbank.movementdate)::date AS movementdate,
    ((to_number(to_char((date_trunc('hour'::text, (tbmovementbank.movementdate)::timestamp with time zone) - (to_date('24-09-20'::text, 'dd-mm-yy'::text))::timestamp with time zone), 'DD'::text), '999'::text) + (1)::numeric) * ((2000 / 30))::numeric) AS valor
   FROM ( SELECT generate_series('2020-09-24 00:00:00'::timestamp without time zone, '2020-10-24 00:00:00'::timestamp without time zone, '1 day'::interval) AS movementdate) tbmovementbank
  GROUP BY tbmovementbank.movementdate
  ORDER BY 1, 2;


ALTER TABLE public.vw_supermarket_outubro OWNER TO mvtpiaib;

--
-- Name: workspace_seq; Type: SEQUENCE; Schema: public; Owner: mvtpiaib
--

CREATE SEQUENCE public.workspace_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.workspace_seq OWNER TO mvtpiaib;

--
-- Name: tbaccountbank id_accountbank; Type: DEFAULT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbaccountbank ALTER COLUMN id_accountbank SET DEFAULT nextval('public.tbaccountbank_id_accountbank_seq'::regclass);


--
-- Name: tbconfigextrato idconfigextrato; Type: DEFAULT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbconfigextrato ALTER COLUMN idconfigextrato SET DEFAULT nextval('public.tbconfigextrato_idconfigextrato_seq'::regclass);


--
-- Name: tbmovementbank idmovementbank; Type: DEFAULT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbmovementbank ALTER COLUMN idmovementbank SET DEFAULT nextval('public.tbmovementbank_idmovementbank_seq'::regclass);


--
-- Name: tbnatureplan id_natureplan; Type: DEFAULT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbnatureplan ALTER COLUMN id_natureplan SET DEFAULT nextval('public.tbnatureplan_id_natureplan_seq'::regclass);


--
-- Name: tbworkspace id_workspace; Type: DEFAULT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbworkspace ALTER COLUMN id_workspace SET DEFAULT nextval('public.tbworkspace_id_workspace_seq'::regclass);


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
00000000000000	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2020-11-15 01:46:19.752976	1	EXECUTED	8:b8c27d9dc8db18b5de87cdb8c38a416b	createSequence sequenceName=sequence_generator		\N	3.9.0	\N	\N	5415579515
00000000000001	jhipster	config/liquibase/changelog/00000000000000_initial_schema.xml	2020-11-15 01:46:20.526862	2	EXECUTED	8:3c2fb2f2606417392082d55679e608bd	createTable tableName=jhi_persistent_audit_event; createTable tableName=jhi_persistent_audit_evt_data; addPrimaryKey tableName=jhi_persistent_audit_evt_data; createIndex indexName=idx_persistent_audit_event, tableName=jhi_persistent_audit_event; c...		\N	3.9.0	\N	\N	5415579515
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: jhi_persistent_audit_event; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
\.


--
-- Data for Name: jhi_persistent_audit_evt_data; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.jhi_persistent_audit_evt_data (event_id, name, value) FROM stdin;
\.


--
-- Data for Name: tbaccountbank; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.tbaccountbank (id_accountbank, id_workspace, ds_accountbank, initial_value, cod_conta, digito) FROM stdin;
3	1	Fisica santander	5984.30	000010047224	\N
1	1	Jurídica Santander	447.00	4538130039651	0
4	1	Carteira	350.00	\N	\N
5	1	Fisica Bradesco	124.85	\N	0
6	1	Conta Itau	0.00	1598455028	\N
\.


--
-- Data for Name: tbconfigextrato; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.tbconfigextrato (idworkspace, tokenextrato, idnatureplan, idconfigextrato, id_accountank, ind_not_imp) FROM stdin;
1	TARIFA MANUTENCAO	13	2	\N	\N
1	RECUPERACAO CREDITO	7	3	\N	\N
1	RESGATE AUT CONTAMAX EMPRESARIAL	\N	4	1	S
1	TAR REG TIT COB RAPIDA SIMP-ELETR	13	6	\N	\N
1	TARIFA MANUTENCAO TIT VENCIDO	13	7	\N	\N
1	OMENA ALIMENTOS	6	9	\N	\N
1	FARMACIA	14	10	\N	\N
1	BOMPRECO	6	11	\N	\N
1	TARIFA SAQUE 	13	12	\N	\N
1	RESTAURANTE	6	13	\N	\N
1	CONTA DE TELEFONE	15	14	\N	\N
1	TARIFA MENSALIDADE	13	15	\N	\N
1	LEROY MACEIO	9	16	\N	\N
1	PREST. DE EMPREST. FINANCIAMENTO	7	17	\N	\N
1	F.T. BOA VISTA	14	18	\N	\N
1	ATACADAO	6	19	\N	\N
1	EMPORIO MERCADI	6	21	\N	\N
1	BEATRIZ CONCEIC	6	22	\N	\N
1	PAGAMENTO DE TITULOS - BCE	7	23	\N	\N
1	ESCOLA GERACAO KIDS	16	26	\N	\N
1	POSTO SARANDI	8	24	\N	\N
1	JOSELIA DE ANDR	6	27	\N	\N
1	FARMACIA PERMAN	14	28	\N	\N
1	GALETO SAO LUIZ	6	29	\N	\N
1	AndersonCle	6	31	\N	\N
1	CASA VIEIRA	9	33	\N	\N
1	BISTEKAS CASA	6	34	\N	\N
1	MERCADOPAGO .MA	6	35	\N	\N
1	APLICACAO AUT CONTAMAX EMPRESARIAL	\N	36	1	S
1	MERCADINHO AKI	6	37	\N	\N
1	MENSALIDADE DE SEGURO	13	38	\N	\N
1	MariaDoCarm	6	39	\N	\N
1	RECARGA TELEFONE CELULAR	12	40	\N	\N
1	CENTER CARNES	6	41	\N	\N
1	MARIA ISABELE	6	42	\N	\N
\.


--
-- Data for Name: tbloginworkspace; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.tbloginworkspace (idworklogin, login, idworkspace) FROM stdin;
\.


--
-- Data for Name: tbmovementbank; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.tbmovementbank (idmovementbank, id_accountbank, id_natureplan, movementdate, movementvalue, history, number_doc, accid) FROM stdin;
662	1	6	2020-08-03	-37.00	COMPRA CARTAO MAESTRO              01/08 KOMMO	70879	\N
692	3	7	2020-08-07	-540.61	PREST. DE EMPREST. FINANCIAMENTO   PARC 002/060 320000193710	193710	\N
693	3	7	2020-08-10	-152.42	PAGAMENTO DE TITULOS - BCE         27.351.731/0001-38	0	\N
697	3	6	2020-08-10	-23.00	COMPRA CARTAO MAESTRO              08/08 PAG.MariaDoCarm	45575	\N
698	3	6	2020-08-10	-7.00	COMPRA CARTAO MAESTRO              08/08 EMPORIO MERCADI	121675	\N
471	3	15	2020-07-02	-179.43	PGTO CONTA DE TELEFONE EM CANAIS   INTERNET VIVO FIXO NAC	0	\N
472	3	13	2020-07-03	-47.70	TARIFA MENSALIDADE PACOTE SERVICOS  JUNHO / 2020	0	\N
473	3	9	2020-07-06	-718.21	COMPRA CARTAO MAESTRO              05/07 LEROY MACEIO	400275	\N
474	3	6	2020-07-06	-139.44	COMPRA CARTAO MAESTRO              05/07 BOMPRECO HIPER	202975	\N
475	3	7	2020-07-07	-540.61	PREST. DE EMPREST. FINANCIAMENTO   PARC 001/060 320000193710	193710	\N
476	3	6	2020-07-07	-12.00	COMPRA CARTAO MAESTRO              07/07 OMENA ALIMENTOS	244375	\N
477	3	14	2020-07-07	-11.00	COMPRA CARTAO MAESTRO              07/07 F.T. BOA VISTA	71975	\N
478	3	6	2020-07-07	-18.50	COMPRA CARTAO MAESTRO              07/07 MERCADOPAGO .MA	474875	\N
479	3	6	2020-07-07	-479.34	COMPRA CARTAO MAESTRO              07/07 ATACADAO 217 AS	360075	\N
480	3	6	2020-07-07	-492.16	COMPRA CARTAO MAESTRO              07/07 ATACADAO 217 AS	403375	\N
481	3	6	2020-07-07	-265.56	COMPRA CARTAO MAESTRO              07/07 ATACADAO 217 AS	434275	\N
482	3	6	2020-07-08	-5.50	COMPRA CARTAO MAESTRO              08/07 OMENA ALIMENTOS	30175	\N
483	3	6	2020-07-08	-2.50	COMPRA CARTAO MAESTRO              08/07 OMENA ALIMENTOS	85275	\N
484	3	6	2020-07-09	-8.50	COMPRA CARTAO MAESTRO              09/07 OMENA ALIMENTOS	284275	\N
485	3	6	2020-07-09	-8.25	COMPRA CARTAO MAESTRO              09/07 EMPORIO MERCADI	352575	\N
487	3	6	2020-07-10	-14.99	COMPRA CARTAO MAESTRO              10/07 BOMPRECO B21 PA	495675	\N
489	3	6	2020-07-10	-6.15	COMPRA CARTAO MAESTRO              10/07 BEATRIZ CONCEIC	75775	\N
490	3	7	2020-07-10	-160.77	PAGAMENTO DE TITULOS - BCE         14.792.379/0001-24	0	\N
491	3	6	2020-07-10	-8.75	COMPRA CARTAO MAESTRO              10/07 JOSELIA DE ANDR	335875	\N
492	3	6	2020-07-10	-6.00	COMPRA CARTAO MAESTRO              10/07 JOSELIA DE ANDR	343475	\N
493	3	8	2020-07-15	-100.00	COMPRA CARTAO MAESTRO              15/07 POSTO SARANDI	141475	\N
494	3	16	2020-07-15	-623.00	PGTO TITULO OUTRO BCO - INTERNET   ESCOLA GERACAO KIDS	0	\N
495	3	14	2020-07-15	-26.82	COMPRA CARTAO MAESTRO              15/07 FARMACIA PERMAN	394975	\N
486	3	6	2020-07-10	-15.50	COMPRA CARTAO MAESTRO              10/07 MARIA ISABELE C	353275	\N
488	3	6	2020-07-10	-160.00	COMPRA CARTAO MAESTRO              10/07 MERCADOPAGO .ES	421975	\N
694	3	9	2020-08-10	-73.55	COMPRA CARTAO MAESTRO              08/08 LOJAS IMPERADOR	190175	\N
695	3	9	2020-08-10	-340.00	COMPRA CARTAO MAESTRO              08/08 PAG.AdmirMenese	460375	\N
696	3	6	2020-08-10	-71.42	COMPRA CARTAO MAESTRO              08/08 CENTER CARNES	191575	\N
699	3	6	2020-08-10	-28.00	COMPRA CARTAO MAESTRO              09/08 CHURRASCARIA DO	272875	\N
497	3	6	2020-07-17	-22.00	COMPRA CARTAO MAESTRO              17/07 GALETO SAO LUIZ	591275	\N
498	3	6	2020-07-17	-3.00	COMPRA CARTAO MAESTRO              17/07 EMPORIO MERCADI	434875	\N
500	3	6	2020-07-17	-15.00	COMPRA CARTAO MAESTRO              17/07 BEATRIZ CONCEIC	502575	\N
501	3	6	2020-07-17	-18.00	COMPRA CARTAO MAESTRO              17/07 PAG.AndersonCle	373975	\N
504	3	6	2020-07-20	-56.00	COMPRA CARTAO MAESTRO              18/07 GALETO SAO LUIZ	445675	\N
505	3	9	2020-07-20	-92.99	COMPRA CARTAO MAESTRO              18/07 CASA VIEIRA CEN	384952	\N
506	3	6	2020-07-20	-10.00	COMPRA CARTAO MAESTRO              19/07 OMENA ALIMENTOS	511275	\N
507	3	6	2020-07-20	-56.00	COMPRA CARTAO MAESTRO              19/07 GALETO SAO LUIZ	143875	\N
509	3	6	2020-07-20	-9.00	COMPRA CARTAO MAESTRO              19/07 EMPORIO MERCADI	442175	\N
510	3	6	2020-07-20	-22.00	COMPRA CARTAO MAESTRO              20/07 GALETO SAO LUIZ	334275	\N
511	3	14	2020-07-20	-14.00	COMPRA CARTAO MAESTRO              20/07 FARMACIA PERMAN	354675	\N
512	3	6	2020-07-20	-18.50	COMPRA CARTAO MAESTRO              20/07 MERCADOPAGO .MA	50275	\N
513	3	6	2020-07-21	-25.64	COMPRA CARTAO MAESTRO              21/07 BISTEKAS CASA D	502075	\N
514	3	6	2020-07-22	-16.73	COMPRA CARTAO MAESTRO              22/07 BISTEKAS CASA D	220475	\N
516	3	6	2020-07-22	-21.00	COMPRA CARTAO MAESTRO              22/07 JOSELIA DE ANDR	425875	\N
518	1	6	2020-07-13	-7.00	COMPRA CARTAO MAESTRO              11/07 BEATRIZ CONCEIC	505979	\N
519	1	9	2020-07-13	-185.66	COMPRA CARTAO MAESTRO              11/07 LEROY MACEIO	82679	\N
520	1	6	2020-07-13	-9.25	COMPRA CARTAO MAESTRO              11/07 OMENA ALIMENTOS	360879	\N
521	1	6	2020-07-13	-7.25	COMPRA CARTAO MAESTRO              11/07 JOSELIA DE ANDR	470379	\N
522	1	6	2020-07-13	-7.00	COMPRA CARTAO MAESTRO              12/07 BEATRIZ CONCEIC	115279	\N
523	1	14	2020-07-13	-27.73	COMPRA CARTAO MAESTRO              13/07 FARMACIA PERMAN	190579	\N
524	1	6	2020-07-13	-3.00	COMPRA CARTAO MAESTRO              13/07 BEATRIZ CONCEIC	83779	\N
525	1	6	2020-07-14	-21.00	COMPRA CARTAO MAESTRO              14/07 GALETO SAO LUIZ	14979	\N
526	1	6	2020-07-14	-13.00	COMPRA CARTAO MAESTRO              14/07 OMENA ALIMENTOS	321379	\N
527	1	14	2020-07-14	-14.00	COMPRA CARTAO MAESTRO              14/07 F.T. BOA VISTA	430979	\N
528	1	6	2020-07-14	-3.00	COMPRA CARTAO MAESTRO              14/07 BEATRIZ CONCEIC	591979	\N
529	1	13	2020-07-14	-5.94	TARIFA MANUTENCAO TIT VENCIDO	194631	\N
530	1	13	2020-07-14	-6.54	TAR REG TIT COB RAPIDA SIMP-ELETR	194631	\N
531	1	6	2020-07-15	-20.28	COMPRA CARTAO MAESTRO              15/07 OMENA ALIMENTOS	392179	\N
532	1	6	2020-07-15	-6.50	COMPRA CARTAO MAESTRO              15/07 BEATRIZ CONCEIC	495479	\N
533	1	14	2020-07-15	-22.92	COMPRA CARTAO MAESTRO              15/07 FARMACIA PERMAN	270279	\N
534	1	6	2020-07-16	-21.00	COMPRA CARTAO MAESTRO              16/07 GALETO SAO LUIZ	360479	\N
535	1	13	2020-07-16	-5.94	TARIFA MANUTENCAO TIT VENCIDO	194631	\N
661	1	6	2020-08-03	-14.23	COMPRA CARTAO MAESTRO              01/08 BOMPRECO B21 PA	474279	\N
539	1	9	2020-07-27	-59.36	COMPRA CARTAO MAESTRO              25/07 CASA VIEIRA PAT	303279	\N
544	3	6	2020-07-23	-13.20	COMPRA CARTAO MAESTRO              23/07 BISTEKAS CASA D	80475	\N
545	3	6	2020-07-23	-1.75	COMPRA CARTAO MAESTRO              23/07 BEATRIZ CONCEIC	545875	\N
546	3	6	2020-07-24	-15.94	COMPRA CARTAO MAESTRO              24/07 BISTEKAS CASA D	155675	\N
550	3	6	2020-07-24	-7.50	COMPRA CARTAO MAESTRO              24/07 BEATRIZ CONCEIC	284575	\N
551	3	6	2020-07-24	-17.50	COMPRA CARTAO MAESTRO              24/07 EMPORIO MERCADI	272875	\N
553	3	6	2020-07-27	-7.00	COMPRA CARTAO MAESTRO              25/07 JOSELIA DE ANDR	570075	\N
554	3	6	2020-07-27	-42.00	COMPRA CARTAO MAESTRO              26/07 GALETO SAO LUIZ	33575	\N
555	3	6	2020-07-27	-29.23	COMPRA CARTAO MAESTRO              26/07 BOMPRECO B21 PA	183475	\N
556	3	6	2020-07-27	-6.00	COMPRA CARTAO MAESTRO              27/07 BEATRIZ CONCEIC	395775	\N
557	3	6	2020-07-27	-18.50	COMPRA CARTAO MAESTRO              27/07 MERCADOPAGO .MA	285475	\N
559	3	6	2020-07-27	-18.75	COMPRA CARTAO MAESTRO              27/07 EMPORIO MERCADI	331175	\N
560	3	6	2020-07-28	-15.00	COMPRA CARTAO MAESTRO              28/07 BISTEKAS CASA D	400575	\N
561	3	6	2020-07-28	-2.00	COMPRA CARTAO MAESTRO              28/07 EMPORIO MERCADI	594475	\N
663	1	6	2020-08-03	-10.00	COMPRA CARTAO MAESTRO              01/08 JOSELIA DE ANDR	90179	\N
664	1	6	2020-08-03	-26.30	COMPRA CARTAO MAESTRO              01/08 BEATRIZ CONCEIC	172379	\N
567	1	6	2020-07-29	-24.89	COMPRA CARTAO MAESTRO              29/07 BISTEKAS CASA D	490879	\N
566	1	9	2020-07-28	-74.46	COMPRA CARTAO MAESTRO              28/07 CENTRAL DOS PAR	180579	\N
565	1	9	2020-07-28	-80.00	COMPRA CARTAO MAESTRO              28/07 MERCADOPAGO .ES	455479	\N
558	3	6	2020-07-27	-11.50	COMPRA CARTAO MAESTRO              27/07 MERCADINHO AKI	593375	\N
552	3	6	2020-07-27	-29.00	COMPRA CARTAO MAESTRO              25/07 PAG.MariaDoCarm	503775	\N
549	3	7	2020-07-24	-550.00	SAQUE BANCO 24HS	117110	\N
548	3	6	2020-07-24	-3.00	COMPRA CARTAO MAESTRO              24/07 ZP .GERIVALDO T	215975	\N
547	3	6	2020-07-24	-8.90	COMPRA CARTAO MAESTRO              24/07 MARIA ISABELE C	523575	\N
543	1	7	2020-07-27	-571.53	DEBITO DE DIVIDA/ACORDO EM ATRASO	322707	\N
542	1	6	2020-07-27	-7.00	COMPRA CARTAO MAESTRO              25/07 P S ADMINISTRAD	23079	\N
541	1	8	2020-07-27	-100.00	COMPRA CARTAO MAESTRO              25/07 AUTO POSTO MANG	253979	\N
540	1	6	2020-07-27	-8.00	COMPRA CARTAO MAESTRO              25/07 PB ADMINISTRADO	411279	\N
537	1	9	2020-07-27	-499.00	COMPRA CARTAO MAESTRO              25/07 CITY BOX	400479	\N
496	3	6	2020-07-16	-20.00	COMPRA CARTAO MAESTRO              16/07 PAG.EditeSoares	571975	\N
499	3	6	2020-07-17	-10.00	COMPRA CARTAO MAESTRO              17/07 PAG.MariaGedalv	485775	\N
502	3	6	2020-07-20	-150.00	SAQUE BANCO 24HS	536124	\N
503	3	6	2020-07-20	-80.00	COMPRA CARTAO MAESTRO              18/07 MERCADOPAGO .ES	344152	\N
508	3	6	2020-07-20	-26.00	COMPRA CARTAO MAESTRO              19/07 PAG.MariaDoCarm	370075	\N
515	3	6	2020-07-22	-13.00	COMPRA CARTAO MAESTRO              22/07 GERIVALDO TENOR	441275	\N
517	1	6	2020-07-13	-60.00	COMPRA CARTAO MAESTRO              11/07 SAO MIGUEL GAS	323479	\N
568	1	6	2020-07-29	-7.60	COMPRA CARTAO MAESTRO              29/07 BEATRIZ CONCEIC	404579	\N
569	1	6	2020-07-29	-16.00	COMPRA CARTAO MAESTRO              29/07 BOMPRECO B21 PA	285779	\N
570	1	6	2020-07-29	-9.25	COMPRA CARTAO MAESTRO              29/07 EMPORIO MERCADI	264179	\N
538	1	6	2020-07-27	-72.00	COMPRA CARTAO MAESTRO              25/07 CABOCLO DO SERT	271779	\N
665	1	6	2020-08-03	-31.00	COMPRA CARTAO MAESTRO              01/08 PAG.MariaDoCarm	270179	\N
666	1	6	2020-08-03	-39.20	COMPRA CARTAO MAESTRO              02/08 BEATRIZ CONCEIC	565879	\N
667	1	6	2020-08-03	-5.50	COMPRA CARTAO MAESTRO              02/08 BEATRIZ CONCEIC	590079	\N
668	1	6	2020-08-03	-19.75	COMPRA CARTAO MAESTRO              02/08 EMPORIO MERCADI	454379	\N
573	1	4	2020-07-27	2285.56	CR COB DINHEIRO CONF RECEBIMENTO   4538/008496463	22907	\N
576	3	7	2020-07-27	-436.47	PAGAMENTO DE TITULOS - BCE	\N	\N
577	1	6	2020-07-30	-15.11	COMPRA CARTAO MAESTRO              30/07 BISTEKAS CASA D	342779	\N
580	1	14	2020-07-30	-39.98	COMPRA CARTAO MAESTRO              30/07 FARMACIA PERMAN	220279	\N
581	1	6	2020-07-31	-6.00	COMPRA CARTAO MAESTRO              31/07 BEATRIZ CONCEIC	534579	\N
582	1	6	2020-07-31	-15.80	COMPRA CARTAO MAESTRO              31/07 BISTEKAS CASA D	45179	\N
583	1	6	2020-07-31	-3.00	COMPRA CARTAO MAESTRO              31/07 BEATRIZ CONCEIC	143179	\N
669	1	13	2020-08-03	-7.23	MENSALIDADE DE SEGURO              Parc 006/012 AP	157211	\N
670	1	6	2020-08-03	-6.70	COMPRA CARTAO MAESTRO              03/08 BEATRIZ CONCEIC	111979	\N
671	1	6	2020-08-03	-5.49	COMPRA CARTAO MAESTRO              03/08 BOMPRECO B21 PA	400179	\N
673	1	13	2020-08-07	-79.50	TARIFA MENSALIDADE PACOTE SERVICOS  JULHO / 2020	160808	\N
674	1	8	2020-08-07	-100.00	COMPRA CARTAO MAESTRO              07/08 POSTO SARANDI	594179	\N
676	3	13	2020-08-05	-47.70	TARIFA MENSALIDADE PACOTE SERVICOS  JULHO / 2020	0	\N
679	3	7	2020-08-06	-270.00	PAGAMENTO DE TITULOS - BCE         08.561.701/0001-01	0	\N
680	3	6	2020-08-07	-16.00	COMPRA CARTAO MAESTRO              07/08 BOMPRECO B21 PA	104475	\N
681	3	14	2020-08-07	-42.69	COMPRA CARTAO MAESTRO              07/08 FARMACIA PERMAN	124475	\N
682	3	6	2020-08-07	-21.32	COMPRA CARTAO MAESTRO              07/08 BOMPRECO B21 PA	22175	\N
578	1	6	2020-07-30	-1.50	COMPRA CARTAO MAESTRO              30/07 ESFERA .MARCIO	435779	\N
579	1	6	2020-07-30	-28.00	COMPRA CARTAO MAESTRO              30/07 PAG.MariaDoCarm	524379	\N
595	4	6	2020-08-03	-150.00	Bistekas Carnes - compras para 7 dias 	\N	\N
672	1	6	2020-08-05	-85.01	COMPRA CARTAO MAESTRO              05/08 PAG.RNINFORMATI	34879	\N
675	3	3	2020-08-04	5954.00	LIQUIDO DE VENCIMENTO              CNPJ 031409011000153	10804	\N
677	3	12	2020-08-06	-60.00	RECARGA TELEFONE CELULAR           IB 82 *****5028 TIM BRASI	0	\N
678	3	12	2020-08-06	-20.00	RECARGA TELEFONE CELULAR           IB 82 *****5028 TIM BRASI	0	\N
700	3	6	2020-08-10	-5.00	COMPRA CARTAO MAESTRO              09/08 BEATRIZ CONCEIC	355575	\N
701	3	6	2020-08-10	-28.75	COMPRA CARTAO MAESTRO              09/08 JOSELIA DE ANDR	14375	\N
702	3	6	2020-08-10	-5.10	COMPRA CARTAO MAESTRO              09/08 BEATRIZ CONCEIC	264675	\N
704	3	6	2020-08-10	-7.40	COMPRA CARTAO MAESTRO              09/08 JOSELIA DE ANDR	154875	\N
703	3	18	2020-08-10	-56.00	COMPRA CARTAO MAESTRO              09/08 PAPEL PAUTADO	314475	\N
705	4	6	2020-08-10	-200.00	GASTOS DIVERSOS	\N	\N
708	3	6	2020-08-10	-20.00	COMPRA CARTAO MAESTRO              10/08 MERCADOPAGO .MA	241675	\N
709	3	6	2020-08-10	-10.60	COMPRA CARTAO MAESTRO              10/08 BEATRIZ CONCEIC	494475	\N
710	3	6	2020-08-10	-15.27	COMPRA CARTAO MAESTRO              10/08 BOMPRECO B21 PA	33775	\N
711	3	6	2020-08-10	-11.50	COMPRA CARTAO MAESTRO              10/08 JOSELIA DE ANDR	3375	\N
707	3	17	2020-08-10	-300.00	PAGAMENTO DE TITULOS - BCE         08.561.701/0001-01	0	\N
713	3	6	2020-08-12	-3.75	COMPRA CARTAO MAESTRO              12/08 OMENA ALIMENTOS	294475	\N
714	3	12	2020-08-12	-10.00	RECARGA TELEFONE CELULAR           IB 82 *****6999 OI ALAGOA	0	\N
715	3	6	2020-08-12	-15.97	COMPRA CARTAO MAESTRO              12/08 BOMPRECO B21 PA	171175	\N
717	3	6	2020-08-12	-7.75	COMPRA CARTAO MAESTRO              12/08 JOSELIA DE ANDR	451175	\N
718	3	6	2020-08-12	-6.25	COMPRA CARTAO MAESTRO              12/08 JOSELIA DE ANDR	222775	\N
719	3	6	2020-08-13	-28.08	COMPRA CARTAO MAESTRO              13/08 BOMPRECO B21 PA	354252	\N
720	3	6	2020-08-13	-17.70	COMPRA CARTAO MAESTRO              13/08 EMPORIO MERCADI	451552	\N
721	3	6	2020-08-13	-5.90	COMPRA CARTAO MAESTRO              13/08 BEATRIZ CONCEIC	155352	\N
716	3	17	2020-08-12	-101.63	PGTO TITULO OUTRO BCO - INTERNET   B2W . COMPANHIA DIGITAL	0	\N
712	3	6	2020-08-12	-25.00	COMPRA CARTAO MAESTRO              12/08 PAG.EditeSoares	593975	\N
724	5	\N	2020-08-13	-100.00	transferencia	\N	\N
722	5	6	2020-08-13	0.00	cesta de alimentos	\N	\N
727	3	6	2020-08-14	-28.73	COMPRA CARTAO MAESTRO              14/08 BOMPRECO B21 PA	200652	\N
728	3	6	2020-08-14	-10.30	COMPRA CARTAO MAESTRO              14/08 BEATRIZ CONCEIC	551352	\N
729	3	6	2020-08-14	-8.00	COMPRA CARTAO MAESTRO              14/08 PAG.AndersonCle	332452	\N
733	3	6	2020-08-17	-15.30	COMPRA CARTAO MAESTRO              15/08 BEATRIZ CONCEIC	274352	\N
734	3	6	2020-08-17	-7.00	COMPRA CARTAO MAESTRO              15/08 EMPORIO MERCADI	232052	\N
738	3	6	2020-08-17	-13.25	COMPRA CARTAO MAESTRO              16/08 EMPORIO MERCADINHO	581552	\N
739	3	6	2020-08-17	-8.10	COMPRA CARTAO MAESTRO              16/08 BEATRIZ CONCEICAO D	73852	\N
737	3	6	2020-08-17	-145.43	COMPRA CARTAO MAESTRO              16/08 CENTER CARNES	65052	\N
735	3	20	2020-08-17	-56.00	COMPRA CARTAO MAESTRO              15/08 POIT DO ACAI	311152	\N
732	3	6	2020-08-17	-52.00	COMPRA CARTAO MAESTRO              15/08 KOMMO	275452	\N
731	3	21	2020-08-17	-89.90	COMPRA CARTAO MAESTRO              15/08 ABY S SPORTS	562852	\N
730	3	20	2020-08-17	-74.70	COMPRA CARTAO MAESTRO              15/08 LOJAS IMPERADOR	442552	\N
726	3	6	2020-08-14	-20.00	COMPRA CARTAO MAESTRO              14/08 BARBEARIA PAI E	383552	\N
736	3	20	2020-08-17	-9.75	COMPRA CARTAO MAESTRO              15/08 EMPORIO MERCADI	454052	\N
725	4	\N	2020-08-13	100.00	trasferencia	3	\N
742	1	6	2020-08-17	-14.00	COMPRA CARTAO MAESTRO              15/08 GALETO SAO LUIZ	314879	\N
743	1	13	2020-08-13	-6.54	TARIFA MANUTENCAO TIT VENCIDO	\N	\N
741	1	6	2020-08-17	-20.00	COMPRA CARTAO MAESTRO              15/08 BIKE CLUB	222779	\N
740	3	20	2020-08-17	-22.92	COMPRA CARTAO MAESTRO              16/08 FARMACIA PERMANENTE	294252	\N
744	4	20	2020-08-17	-100.00	EMPRESTIMO MATHEUS	\N	\N
750	3	6	2020-08-17	-14.40	COMPRA CARTAO MAESTRO              17/08 BEATRIZ CONCEICAO D	210552	\N
751	3	6	2020-08-17	-25.00	COMPRA CARTAO MAESTRO              17/08 EMPORIO MERCADINHO	272352	\N
752	3	16	2020-08-17	-623.00	PGTO TITULO OUTRO BCO - INTERNET   ESCOLA GERACAO KIDS	0	\N
745	3	20	2020-08-17	95.00	TED RECEBIDA DIF TITULARIDADE STR  David Matheus de Gusmao V	0	\N
746	3	20	2020-08-17	34.00	TED RECEBIDA DIF TITULARIDADE STR  David Matheus de Gusmao V	0	\N
747	3	20	2020-08-17	10.00	TED RECEBIDA DIF TITULARIDADE STR  David Matheus de Gusm o V	0	\N
748	3	20	2020-08-17	20.00	TED RECEBIDA DIF TITULARIDADE STR  David Matheus de Gusm o V	0	\N
749	3	20	2020-08-17	119.00	TED RECEBIDA DIF TITULARIDADE STR  David Matheus de Gusmao V	0	\N
753	3	20	2020-08-18	-180.00	SAQUE BANCO 24HS	429271	\N
754	3	6	2020-08-18	-6.80	COMPRA CARTAO MAESTRO              18/08 BEATRIZ CONCEICAO D	162952	\N
756	3	6	2020-08-18	-20.00	COMPRA CARTAO MAESTRO              18/08 MERCADOPAGO .MANDUB	93652	\N
757	3	6	2020-08-18	-20.45	COMPRA CARTAO MAESTRO              18/08 BOMPRECO B21 PAJUCA	13952	\N
759	3	6	2020-08-19	-6.50	COMPRA CARTAO MAESTRO              19/08 JOSELIA DE ANDRADE	580752	\N
760	3	6	2020-08-19	-6.25	COMPRA CARTAO MAESTRO              19/08 JOSELIA DE ANDRADE	354552	\N
758	3	6	2020-08-19	-43.60	COMPRA CARTAO MAESTRO              19/08 FELICIA	444452	\N
755	3	6	2020-08-18	-65.00	COMPRA CARTAO MAESTRO              18/08 SAO MIGUEL GAS	285652	\N
761	4	20	2020-08-19	40.00	pagamento matheus	\N	\N
765	3	6	2020-08-20	-18.20	COMPRA CARTAO MAESTRO              20/08 JOSELIA DE ANDRADE	414352	\N
766	3	6	2020-08-20	-67.50	COMPRA CARTAO MAESTRO              20/08 PAG.MariaDoCarmo	220852	\N
767	3	6	2020-08-21	-4.50	COMPRA CARTAO MAESTRO              21/08 BEATRIZ CONCEICAO D	275652	\N
768	3	6	2020-08-21	-16.00	COMPRA CARTAO MAESTRO              21/08 BOMPRECO B21 PAJUCA	283852	\N
770	3	6	2020-08-24	-40.00	COMPRA CARTAO MAESTRO              22/08 SUMUP  .RESTAURANTE	234752	\N
771	3	8	2020-08-24	-100.00	COMPRA CARTAO MAESTRO              22/08 POSTO VIA EXPRESSA	322852	\N
769	3	22	2020-08-24	-1246.00	COMPRA CARTAO MAESTRO              22/08 PLADIM	571752	\N
772	3	20	2020-08-24	-33.00	COMPRA CARTAO MAESTRO              22/08 PAG.MariaDoCarmo	292552	\N
764	3	6	2020-08-20	-14.90	COMPRA CARTAO MAESTRO              20/08 MARIA ISABELE C A G	275652	\N
762	3	7	2020-08-19	-12.80	DEBITO AUT. FAT.CARTAO MASTER CARD FINAL 3705	0	\N
763	3	6	2020-08-20	-26.00	COMPRA CARTAO MAESTRO              20/08 PAG.EditeSoaresDa	573252	\N
773	1	8	2020-08-19	-30.00	AUTO POSTO GUIMARAES	\N	\N
774	3	6	2020-08-24	-56.00	COMPRA CARTAO MAESTRO              23/08 GALETO SAO LUIZ	282652	\N
775	3	6	2020-08-24	-11.60	COMPRA CARTAO MAESTRO              23/08 BEATRIZ CONCEICAO D	334752	\N
776	3	6	2020-08-24	-31.75	COMPRA CARTAO MAESTRO              23/08 BOMPRECO B21 PAJUCA	343152	\N
779	3	6	2020-08-24	-9.45	COMPRA CARTAO MAESTRO              24/08 BEATRIZ CONCEICAO D	520252	\N
777	3	6	2020-08-24	-105.40	COMPRA CARTAO MAESTRO              23/08 EXTRA 1363 MACEIO	21852	\N
778	3	20	2020-08-24	69.00	TED RECEBIDA DIF TITULARIDADE STR  David Matheus de Gusmao V	0	\N
780	3	6	2020-08-24	-7.75	COMPRA CARTAO MAESTRO              24/08 OMENA ALIMENTOS	411252	\N
781	3	6	2020-08-24	-11.00	COMPRA CARTAO MAESTRO              24/08 EMPORIO MERCADINHO	460552	\N
782	3	6	2020-08-25	-13.00	COMPRA CARTAO MAESTRO              25/08 MARIA ISABELE C A G	252	\N
783	3	6	2020-08-25	-9.74	COMPRA CARTAO MAESTRO              25/08 BOMPRECO B21 PAJUCA	143352	\N
784	3	6	2020-08-25	-7.80	COMPRA CARTAO MAESTRO              25/08 BEATRIZ CONCEICAO D	315652	\N
785	3	6	2020-08-25	-16.25	COMPRA CARTAO MAESTRO              25/08 EMPORIO MERCADINHO	405452	\N
786	3	6	2020-08-25	-5.30	COMPRA CARTAO MAESTRO              25/08 JOSELIA DE ANDRADE	512952	\N
787	1	20	2020-08-24	-50.00	SAQUE TERMINAL INTER AG	600186	\N
788	1	7	2020-08-25	-50.34	DEBITO DE DIVIDA/ACORDO EM ATRASO	42608	\N
789	1	4	2020-08-26	3226.36	CR COB DINHEIRO CONF RECEBIMENTO   4538/008496463	62608	\N
790	1	7	2020-08-26	-521.19	DEBITO DE DIVIDA/ACORDO EM ATRASO	72608	\N
792	3	6	2020-08-26	-10.90	COMPRA CARTAO MAESTRO              26/08 BEATRIZ CONCEICAO D	152852	\N
793	3	14	2020-08-26	-46.86	COMPRA CARTAO MAESTRO              26/08 FARMACIA PERMANENTE	314752	\N
794	3	6	2020-08-26	-13.30	COMPRA CARTAO MAESTRO              26/08 JOSELIA DE ANDRADE	194952	\N
791	3	6	2020-08-26	-109.90	COMPRA CARTAO MAESTRO              26/08 CENTER CARNES	533652	\N
795	4	6	2020-08-26	-40.00	ajuste saldo carteira	\N	\N
796	3	6	2020-08-27	-28.73	COMPRA CARTAO MAESTRO              27/08 OMENA ALIMENTOS	343952	\N
797	3	15	2020-08-28	-211.56	PGTO CONTA DE TELEFONE EM CANAIS   INTERNET VIVO FIXO NAC	0	\N
803	1	6	2020-08-27	-7.60	COMPRA CARTAO MAESTRO              27/08 JOSELIA DE ANDRADE	125479	\N
802	1	6	2020-08-27	-5.40	COMPRA CARTAO MAESTRO              27/08 MARIA ISABELE C A G	63479	\N
801	1	6	2020-08-27	-14.50	COMPRA CARTAO MAESTRO              27/08 MARIA ISABELE C A G	484379	\N
800	1	13	2020-08-26	-2.00	TAR LIQ COB COM REG VIA PAGFOR	194631	\N
804	1	6	2020-08-28	-14.08	COMPRA CARTAO MAESTRO              28/08 BOMPRECO B21 PAJUCA	55279	\N
805	1	6	2020-08-28	-20.00	COMPRA CARTAO MAESTRO              28/08 MERCADOPAGO .MANDUB	171679	\N
806	1	6	2020-08-28	-7.25	COMPRA CARTAO MAESTRO              28/08 JOSELIA DE ANDRADE	274079	\N
807	1	6	2020-08-28	-6.00	COMPRA CARTAO MAESTRO              28/08 PAG.MariaDoCarmo	514979	\N
808	1	6	2020-08-28	-7.00	COMPRA CARTAO MAESTRO              28/08 JOSELIA DE ANDRADE	592479	\N
810	1	6	2020-08-31	-21.96	COMPRA CARTAO MAESTRO              29/08 ILAHUI	364479	\N
809	1	6	2020-08-31	-86.00	COMPRA CARTAO MAESTRO              29/08 CASA LEA	251579	\N
812	1	9	2020-08-31	-15.23	COMPRA CARTAO MAESTRO              29/08 CASA VIEIRA PATIO	210879	\N
815	1	6	2020-08-31	-29.00	COMPRA CARTAO MAESTRO              29/08 PAG.MariaDoCarmo	472579	\N
817	1	14	2020-08-31	-13.99	COMPRA CARTAO MAESTRO              30/08 FARMACIA DO TRABALH	550379	\N
814	1	6	2020-08-31	-34.20	COMPRA CARTAO MAESTRO              29/08 EXTRA 1363 MACEIO	191779	\N
813	1	6	2020-08-31	-8.00	COMPRA CARTAO MAESTRO              29/08 PB ADMINISTRADORA D	240779	\N
811	1	6	2020-08-31	-19.99	COMPRA CARTAO MAESTRO              29/08 ILAHUI	40479	\N
816	1	6	2020-08-31	-50.00	SAQUE BANCO 24HS	530273	\N
819	1	6	2020-08-31	-8.45	omena	\N	\N
820	1	6	2020-08-31	-8.45	COMPRA CARTAO MAESTRO              30/08 OMENA ALIMENTOS	1979	\N
821	1	6	2020-08-31	-26.77	COMPRA CARTAO MAESTRO              30/08 BOMPRECO B21 PAJUCA	520479	\N
823	1	6	2020-08-31	-16.79	COMPRA CARTAO MAESTRO              31/08 BISTEKAS CASA DE CA	300579	\N
824	1	6	2020-08-31	-11.70	COMPRA CARTAO MAESTRO              31/08 OMENA ALIMENTOS	485879	\N
826	1	6	2020-08-31	-12.25	COMPRA CARTAO MAESTRO              31/08 JOSELIA DE ANDRADE	250279	\N
828	1	6	2020-09-01	-30.69	COMPRA CARTAO MAESTRO              01/09 BISTEKAS CASA DE CA	421679	\N
827	1	13	2020-08-31	-11.80	TAR BAIXA COB SIMPLES-ELETRONICA	194631	\N
825	1	20	2020-08-31	-100.00	SAQUE BANCO 24HS	43273	\N
822	1	6	2020-08-31	-99.55	COMPRA CARTAO MAESTRO              30/08 EXTRA 1363 MACEIO	230179	\N
829	1	6	2020-09-01	-17.55	COMPRA CARTAO MAESTRO              01/09 OMENA ALIMENTOS	295579	\N
830	1	14	2020-09-01	-28.00	COMPRA CARTAO MAESTRO              01/09 F.T. BOA VISTA F1	215379	\N
831	1	6	2020-09-01	-4.50	COMPRA CARTAO MAESTRO              01/09 JOSELIA DE ANDRADE	321479	\N
832	1	6	2020-09-01	-12.00	COMPRA CARTAO MAESTRO              01/09 PAG.MariaDoCarmo	475979	\N
833	1	13	2020-09-02	-9.00	TARIFA SAQUE ATM/BANCO 24HS	80309	\N
834	1	6	2020-09-02	-19.57	COMPRA CARTAO MAESTRO              02/09 BOMPRECO B21 PAJUCA	181979	\N
835	1	8	2020-09-02	-100.00	COMPRA CARTAO MAESTRO              02/09 POSTO SARANDI	412279	\N
836	1	6	2020-09-02	-12.50	COMPRA CARTAO MAESTRO              02/09 JOSELIA DE ANDRADE	533379	\N
837	1	6	2020-09-02	-6.00	COMPRA CARTAO MAESTRO              02/09 JOSELIA DE ANDRADE	282179	\N
838	1	13	2020-09-03	-7.23	MENSALIDADE DE SEGURO              Parc 007/012 AP	157211	\N
839	1	6	2020-09-03	-16.00	COMPRA CARTAO MAESTRO              03/09 BISTEKAS CASA DE CA	403779	\N
840	1	6	2020-09-03	-22.90	COMPRA CARTAO MAESTRO              03/09 MARIA ISABELE C A G	482179	\N
841	1	6	2020-09-03	-5.25	COMPRA CARTAO MAESTRO              03/09 JOSELIA DE ANDRADE	330279	\N
842	1	6	2020-09-03	-11.69	COMPRA CARTAO MAESTRO              03/09 OMENA ALIMENTOS	375279	\N
843	1	6	2020-09-03	-44.63	COMPRA CARTAO MAESTRO              03/09 CESTA DE ALIMENTOS	315179	\N
844	3	3	2020-09-03	2090.00	Salário Ofm 	\N	\N
845	3	9	2020-09-03	-720.00	compra da cadeira de escritório	\N	\N
846	1	6	2020-09-04	-7.17	COMPRA CARTAO MAESTRO              04/09 OMENA ALIMENTOS	524179	\N
847	1	6	2020-09-04	-20.00	COMPRA CARTAO MAESTRO              04/09 BOMPRECO B21 PAJUCA	383979	\N
848	1	6	2020-09-04	-22.00	COMPRA CARTAO MAESTRO              04/09 GALETO SAO LUIZ	311079	\N
849	3	13	2020-09-03	-49.40	TARIFA MENSALIDADE PACOTE SERVICOS  AGOSTO / 2020	0	\N
861	3	12	2020-09-08	-40.00	RECARGA TELEFONE CELULAR           IB 82 *****5028 TIM BRASI	0	\N
862	3	12	2020-09-08	-20.00	RECARGA TELEFONE CELULAR           IB 82 *****5028 TIM BRASI	0	\N
863	1	6	2020-09-04	-6.25	COMPRA CARTAO MAESTRO              04/09 JOSELIA DE ANDRADE	312979	\N
864	1	6	2020-09-08	-44.49	COMPRA CARTAO MAESTRO              05/09 CENTER CARNES	595879	\N
865	1	6	2020-09-08	-28.70	COMPRA CARTAO MAESTRO              05/09 OMENA ALIMENTOS	164779	\N
866	1	6	2020-09-08	-40.00	COMPRA CARTAO MAESTRO              05/09 PAG.MariaDoCarmo	322779	\N
867	1	6	2020-09-08	-6.25	COMPRA CARTAO MAESTRO              05/09 JOSELIA DE ANDRADE	465579	\N
868	1	6	2020-09-08	-7.00	COMPRA CARTAO MAESTRO              05/09 JOSELIA DE ANDRADE	523579	\N
853	3	3	2020-09-04	4727.00	CREDITO DE SALARIO                 CNPJ 031409011000153	10904	\N
852	3	9	2020-09-04	-183.70	COMPRA CARTAO MAESTRO              04/09 MAX CASA	455852	\N
854	3	23	2020-09-08	-113.19	PGTO TRIBUTO ESTADUAL EM CANAIS    INTERNET SEFAZ AL DAR	0	\N
855	3	23	2020-09-08	-5.23	PAGAMENTO DPVAT EM CANAIS          INTERNET SEGURADORA LIDER	0	\N
856	3	6	2020-09-08	-80.00	COMPRA CARTAO MAESTRO              05/09 BIO LEVE DISTRIBUID	231552	\N
857	3	23	2020-09-08	-132.77	PGTO TRIBUTO ESTADUAL EM CANAIS    INTERNET SEFAZ AL DAR	0	\N
858	3	23	2020-09-08	-132.50	PGTO TRIBUTO ESTADUAL EM CANAIS    INTERNET SEFAZ AL DAR	0	\N
859	3	23	2020-09-08	-125.35	PGTO TRIBUTO ESTADUAL EM CANAIS    INTERNET SEFAZ AL DAR	0	\N
860	3	23	2020-09-08	-116.43	PGTO TRIBUTO ESTADUAL EM CANAIS    INTERNET SEFAZ AL DAR	0	\N
869	1	14	2020-09-08	-100.00	COMPRA CARTAO MAESTRO              05/09 CAO GATAO	501479	\N
870	1	9	2020-09-08	-378.50	COMPRA CARTAO MAESTRO              05/09 EXTRA 1363 MACEIO	474179	\N
871	1	6	2020-09-08	-16.00	COMPRA CARTAO MAESTRO              05/09 EXTRA 1363 MACEIO	483679	\N
872	1	8	2020-09-08	-60.00	COMPRA CARTAO MAESTRO              06/09 AUTO POSTO MANGABEI	115279	\N
873	1	6	2020-09-08	-30.00	COMPRA CARTAO MAESTRO              06/09 MP .MERCADOP	480779	\N
874	1	6	2020-09-08	-20.00	COMPRA CARTAO MAESTRO              06/09 MANUEL BELO DA SILV	61479	\N
1171	3	14	2020-11-14	-28.31	boa vista	\N	\N
1173	6	14	2020-11-13	-24.00	farmacia boa vista	\N	\N
1174	4	25	2020-11-15	-79.00	despesas estacionamento 	\N	\N
1172	6	31	2020-11-09	-65.50	big tids	\N	\N
875	1	6	2020-09-08	-46.80	COMPRA CARTAO MAESTRO              06/09 IDEAL SUPERMERCADO	403079	\N
876	1	6	2020-09-08	-15.09	COMPRA CARTAO MAESTRO              06/09 IDEAL SUPERMERCADO	435079	\N
915	3	6	2020-09-11	-20.00	COMPRA CARTAO MAESTRO              11/09 MP .MANDUBEB	462052	\N
877	1	18	2020-09-08	-47.00	COMPRA CARTAO MAESTRO              06/09 MP .PONTODOB	33079	\N
878	1	6	2020-09-08	-12.78	COMPRA CARTAO MAESTRO              06/09 DAVID ROGERIO LIMA	65979	\N
879	1	6	2020-09-08	-23.00	COMPRA CARTAO MAESTRO              06/09 MP .MERCADOP	205179	\N
918	3	6	2020-09-14	-70.00	COMPRA CARTAO MAESTRO              12/09 SAO MIGUEL GAS	332552	\N
889	3	7	2020-09-08	-540.61	PREST. DE EMPREST. FINANCIAMENTO   PARC 003/060 320000193710	193710	\N
898	3	6	2020-09-09	-12.00	COMPRA CARTAO MAESTRO              09/09 PAG.MariaDoCarmo	583252	\N
902	1	13	2020-09-08	-88.00	TARIFA MENSALIDADE PACOTE SERVICOS  AGOSTO / 2020	210909	\N
891	3	6	2020-09-08	-7.00	COMPRA CARTAO MAESTRO              08/09 POLO COMERCIAL ESTA	92552	\N
899	1	6	2020-09-08	-121.00	COMPRA CARTAO MAESTRO              07/09 BODE ASSADO DO LUCI	553579	\N
900	1	18	2020-09-08	-39.50	COMPRA CARTAO MAESTRO              07/09 STELO S.A..PAN TROP	174379	\N
901	1	8	2020-09-08	-117.49	COMPRA CARTAO MAESTRO              07/09 J P ALVES PEREIRA C	414279	\N
903	1	6	2020-09-08	-50.43	COMPRA CARTAO MAESTRO              08/09 DAVID ROGERIO LIMA	143379	\N
908	1	20	2020-09-08	-126.00	COMPRA CARTAO MAESTRO              08/09 MSS MODAS	444379	\N
907	1	20	2020-09-08	-39.99	COMPRA CARTAO MAESTRO              08/09 ROTA DO MAR POLO	360579	\N
905	1	21	2020-09-08	-48.00	COMPRA CARTAO MAESTRO              08/09 EUMAR FIRFOR	3579	\N
909	1	21	2020-09-08	-39.99	COMPRA CARTAO MAESTRO              08/09 ROTA DO MAR POLO	532279	\N
890	3	21	2020-09-08	-227.00	COMPRA CARTAO MAESTRO              08/09 PAG.PRModas	24052	\N
892	3	6	2020-09-08	-6.00	COMPRA CARTAO MAESTRO              08/09 PANIFICADORA BOA ES	350352	\N
894	3	21	2020-09-09	-190.00	COMPRA CARTAO MAESTRO              09/09 STAR MODAS	493152	\N
895	3	21	2020-09-09	-68.00	COMPRA CARTAO MAESTRO              09/09 PAG.PRModas	555052	\N
896	3	6	2020-09-09	-7.00	COMPRA CARTAO MAESTRO              09/09 POLO COMERCIAL ESTA	594452	\N
897	3	6	2020-09-09	-39.00	COMPRA CARTAO MAESTRO              09/09 TABUA DE SABORES	80552	\N
893	3	21	2020-09-09	-76.00	COMPRA CARTAO MAESTRO              09/09 ACQIO  .KYLLS MODAS	330552	\N
906	1	20	2020-09-08	-35.00	COMPRA CARTAO MAESTRO              08/09 MARANDS	103979	\N
910	3	6	2020-09-09	-15.25	COMPRA CARTAO MAESTRO 09/09 JOSELIA DE ANDRADE	\N	\N
904	1	9	2020-09-08	-36.00	COMPRA CARTAO MAESTRO              08/09 PAG.JoseRonaldoDeAm	194979	\N
912	3	6	2020-09-10	-41.69	COMPRA CARTAO MAESTRO              10/09 BOMPRECO B21 PAJUCA	521152	\N
913	3	6	2020-09-10	-19.90	COMPRA CARTAO MAESTRO              10/09 OMENA ALIMENTOS	201552	\N
914	3	7	2020-09-11	-100.00	PAGAMENTO DE TITULOS - BCE         08.561.701/0001-01	0	\N
916	3	6	2020-09-14	-34.59	COMPRA CARTAO MAESTRO              12/09 BISTEKAS CASA DE CA	575952	\N
917	3	6	2020-09-14	-6.20	COMPRA CARTAO MAESTRO              12/09 BEATRIZ CONCEICAO D	80652	\N
919	3	6	2020-09-14	-59.94	COMPRA CARTAO MAESTRO              13/09 BOMPRECO B21 PAJUCA	103452	\N
922	3	9	2020-09-14	-50.26	COMPRA CARTAO MAESTRO              13/09 CASA VIEIRA PRAIA	285952	\N
924	3	6	2020-09-14	-13.75	COMPRA CARTAO MAESTRO              13/09 JOSELIA DE ANDRADE	214552	\N
925	3	6	2020-09-14	-28.12	COMPRA CARTAO MAESTRO              14/09 BOMPRECO B21 PAJUCA	294352	\N
926	3	6	2020-09-14	-4.50	COMPRA CARTAO MAESTRO              14/09 JOSELIA DE ANDRADE	1452	\N
927	3	14	2020-09-14	-42.83	COMPRA CARTAO MAESTRO              14/09 FARMACIA PERMANENTE	570752	\N
928	3	6	2020-09-15	-108.64	COMPRA CARTAO MAESTRO              15/09 BOMPRECO B21 PAJUCA	500152	\N
920	3	8	2020-09-14	-100.00	COMPRA CARTAO MAESTRO              13/09 POSTO PONTA VERDE	164352	\N
923	3	6	2020-09-14	-155.50	COMPRA CARTAO MAESTRO              13/09 PIZ PIRATAS DO PICU	590652	\N
921	3	7	2020-09-14	-50.00	SAQUE BANCO 24HS	712178	\N
929	3	6	2020-09-15	-18.50	COMPRA CARTAO MAESTRO              15/09 JOSELIA DE ANDRADE	453452	\N
930	3	6	2020-09-16	-63.87	COMPRA CARTAO MAESTRO              16/09 CENTER CARNES	243352	\N
931	3	6	2020-09-16	-19.62	COMPRA CARTAO MAESTRO              16/09 OMENA ALIMENTOS	341752	\N
935	3	6	2020-09-17	-30.75	COMPRA CARTAO MAESTRO              17/09 JOSELIA DE ANDRADE	320952	\N
936	3	6	2020-09-18	-40.35	COMPRA CARTAO MAESTRO              18/09 OMENA ALIMENTOS	21552	\N
937	3	9	2020-09-18	-67.38	COMPRA CARTAO MAESTRO              18/09 CASA VIEIRA CENTRO	273952	\N
941	3	6	2020-09-21	-22.78	COMPRA CARTAO MAESTRO              19/09 BOMPRECO B21 PAJUCA	52152	\N
944	3	6	2020-09-21	-27.34	COMPRA CARTAO MAESTRO              19/09 BOMPRECO B21 PAJUCA	283652	\N
945	3	6	2020-09-21	-24.70	COMPRA CARTAO MAESTRO              19/09 PAG.AndersonCleyton	192352	\N
942	3	6	2020-09-21	-20.00	COMPRA CARTAO MAESTRO              19/09 MP .MANDUBEB	402452	\N
967	1	6	2020-09-25	-150.00	SAQUE BANCO 24HS	130114	\N
940	3	6	2020-09-18	-68.74	COMPRA CARTAO MAESTRO              19/09 SUPERMERCADO PALATO	51652	\N
939	3	6	2020-09-18	-13.00	COMPRA CARTAO MAESTRO              19/09 MP .MERCADOP	73452	\N
938	3	6	2020-09-18	-5.00	COMPRA CARTAO MAESTRO              18/09 YANNA PRESENTES	452652	\N
932	3	6	2020-09-16	-18.00	COMPRA CARTAO MAESTRO              16/09 FRUTOS DE GOIAIS MA	571352	\N
933	3	6	2020-09-16	-5.00	COMPRA CARTAO MAESTRO              16/09 FRUTOS DE GOIAIS MA	101952	\N
934	3	9	2020-09-17	-49.90	COMPRA CARTAO MAESTRO              17/09 MAX CASA	530352	\N
946	3	6	2020-09-21	-22.00	COMPRA CARTAO MAESTRO              20/09 GALETO SAO LUIZ	172052	\N
947	3	14	2020-09-21	-27.50	COMPRA CARTAO MAESTRO              20/09 F.T. BOA VISTA F1	415552	\N
949	3	6	2020-09-21	-29.00	COMPRA CARTAO MAESTRO              20/09 PAG.MariaDoCarmo	464752	\N
950	3	7	2020-09-21	-923.26	PAGAMENTO DE TITULOS - BCE         27.351.731/0001-38	0	\N
951	3	6	2020-09-21	-34.79	COMPRA CARTAO MAESTRO              21/09 BOMPRECO B21 PAJUCA	592852	\N
953	3	14	2020-09-21	-60.62	COMPRA CARTAO MAESTRO              21/09 FARMACIA DO TRABALH	275652	\N
948	3	8	2020-09-21	-100.00	COMPRA CARTAO MAESTRO              20/09 AUTO POSTO COMENDAD	423652	\N
952	3	23	2020-09-21	-107.80	PGTO TRIBUTO ESTADUAL EM CANAIS    INTERNET SEFAZ AL DAR	0	\N
956	3	6	2020-09-22	-71.59	COMPRA CARTAO MAESTRO              22/09 OMENA ALIMENTOS	543252	\N
954	3	6	2020-09-22	-100.00	SAQUE BANCO 24HS	955001	\N
955	3	9	2020-09-22	-14.50	COMPRA CARTAO MAESTRO              22/09 QUEIROZ ALUMINIO	95952	\N
957	3	6	2020-09-22	-15.90	COMPRA CARTAO MAESTRO              22/09 MARIA ISABELE C A G	590852	\N
959	3	6	2020-09-23	-146.39	COMPRA CARTAO MAESTRO              23/09 CENTER CARNES	201752	\N
958	3	20	2020-09-23	200.00	TED RECEBIDA DIF TITULARIDADE STR  David Matheus de Gusmao V	0	\N
960	3	6	2020-09-23	-39.00	COMPRA CARTAO MAESTRO              23/09 BODEGA DO ALAGOANO	533552	\N
963	1	9	2020-09-24	-93.66	COMPRA CARTAO MAESTRO              24/09 CASA VIEIRA PRAIA	155879	\N
966	1	6	2020-09-25	-23.12	COMPRA CARTAO MAESTRO              25/09 OMENA ALIMENTOS	241179	\N
961	1	7	2020-09-23	-29.20	DEBITO DE DIVIDA/ACORDO EM ATRASO	22509	\N
964	1	7	2020-09-24	-542.33	DEBITO DE DIVIDA/ACORDO EM ATRASO	62509	\N
965	1	13	2020-09-24	-2.30	TAR LIQ COB COM REG VIA PAGFOR	194631	\N
962	1	4	2020-09-24	3312.20	CR COB DINHEIRO CONF RECEBIMENTO   4538/008496463	42509	\N
968	1	6	2020-09-25	-29.00	COMPRA CARTAO MAESTRO              25/09 PAG.MariaDoCarmo	34479	\N
969	1	6	2020-09-25	-3.00	COMPRA CARTAO MAESTRO              25/09 PAG.MariaDoCarmo	250679	\N
970	1	6	2020-09-25	-10.00	COMPRA CARTAO MAESTRO              25/09 PAG.MariaDoCarmo	210179	\N
971	1	6	2020-09-28	-15.00	COMPRA CARTAO MAESTRO              26/09 JOSELIA DE ANDRADE	405679	\N
975	1	14	2020-09-28	-10.60	COMPRA CARTAO MAESTRO              27/09 F.T. BOA VISTA F1	434179	\N
976	1	14	2020-09-28	-12.50	COMPRA CARTAO MAESTRO              27/09 F.T. BOA VISTA F1	525079	\N
980	1	6	2020-09-28	-15.00	COMPRA CARTAO MAESTRO              28/09 PAG.MariaDoCarmo	241179	\N
981	1	13	2020-09-29	-4.50	TARIFA SAQUE ATM/BANCO 24HS        25/09/2020	192909	\N
985	1	6	2020-09-29	-12.00	COMPRA CARTAO MAESTRO              29/09 PAG.MariaDoCarmo	272079	\N
984	1	6	2020-09-29	-46.00	COMPRA CARTAO MAESTRO              29/09 GH LAVA JATO EIRELI	461679	\N
982	1	6	2020-09-29	-25.10	COMPRA CARTAO MAESTRO              29/09 PAG.Soteca	412779	\N
972	1	6	2020-09-28	-37.00	COMPRA CARTAO MAESTRO              26/09 PIZZA AMERICANA	445479	\N
973	1	6	2020-09-28	-8.00	COMPRA CARTAO MAESTRO              26/09 PB ADMINISTRADORA D	321579	\N
974	1	6	2020-09-28	-12.17	COMPRA CARTAO MAESTRO              26/09 EXTRA 1363 MACEIO	180379	\N
979	1	6	2020-09-28	-65.77	COMPRA CARTAO MAESTRO              28/09 ERVA DOCE	285079	\N
977	1	20	2020-09-28	-150.00	SAQUE BANCO 24HS	908117	\N
983	1	6	2020-09-29	-18.50	COMPRA CARTAO MAESTRO              29/09 PAG.BERTULINAVARIED	264779	\N
978	1	6	2020-09-28	-75.95	COMPRA CARTAO MAESTRO              27/09 RRA PINHEIRO EIRELI	292379	\N
986	1	13	2020-09-29	-4.50	TARIFA SAQUE ATM/BANCO 24HS        25/09/2020	130310	\N
987	1	13	2020-09-30	-4.50	TARIFA SAQUE ATM/BANCO 24HS        27/09/2020	190310	\N
988	1	6	2020-09-30	-70.85	COMPRA CARTAO MAESTRO              30/09 BOMPRECO B21 PAJUCA	423279	\N
990	1	14	2020-09-30	-7.98	COMPRA CARTAO MAESTRO              30/09 FARMACIA PERMANENTE	150779	\N
991	1	6	2020-09-30	-38.00	COMPRA CARTAO MAESTRO              30/09 PAG.MariaDoCarmo	312479	\N
989	1	8	2020-09-30	-137.51	COMPRA CARTAO MAESTRO              30/09 AUTO POSTO MANGABEI	385479	\N
992	1	6	2020-10-01	-66.00	COMPRA CARTAO MAESTRO              01/10 GH LAVA JATO EIRELI	574379	\N
993	1	6	2020-10-01	-20.00	COMPRA CARTAO MAESTRO              01/10 BARBEARIA PAI E FIL	502879	\N
994	1	6	2020-10-02	-58.00	COMPRA CARTAO MAESTRO              02/10 GH LAVA JATO EIRELI	190079	\N
995	1	6	2020-10-02	-13.00	COMPRA CARTAO MAESTRO              02/10 BISTRO DO SERTAO	165579	\N
996	1	6	2020-10-02	-45.00	COMPRA CARTAO MAESTRO              02/10 BODEGA DO ALAGOANO	215479	\N
1002	1	14	2020-10-05	-7.50	COMPRA CARTAO MAESTRO              03/10 SUMUP  .FARMACIAPOP	475979	\N
1009	1	13	2020-10-05	-7.23	MENSALIDADE DE SEGURO              Parc 008/012 AP	157211	\N
1010	1	6	2020-10-05	-22.40	COMPRA CARTAO MAESTRO              05/10 SNACK RESTAURANTE	344679	\N
998	1	6	2020-10-05	-59.00	COMPRA CARTAO MAESTRO              03/10 TABUA DE SABORES	483679	\N
997	1	6	2020-10-05	-58.80	COMPRA CARTAO MAESTRO              03/10 PAG.ReiDog	114479	\N
999	1	6	2020-10-05	-80.00	COMPRA CARTAO MAESTRO              03/10 MP .UNIVERSO	235679	\N
1000	1	8	2020-10-05	-112.68	COMPRA CARTAO MAESTRO              03/10 AUTO POSTO FORENE	53879	\N
1001	1	6	2020-10-05	-74.88	COMPRA CARTAO MAESTRO              03/10 SUPERMERCADO FENIX	391779	\N
1003	1	6	2020-10-05	-19.00	COMPRA CARTAO MAESTRO              03/10 POSTO DE MED SANTA	521079	\N
1004	1	6	2020-10-05	-111.20	COMPRA CARTAO MAESTRO              04/10 SUPERMERCADO FENIX	534479	\N
1005	1	6	2020-10-05	-19.99	COMPRA CARTAO MAESTRO              04/10 CENTAURO CE 53	111379	\N
1006	1	20	2020-10-05	-314.00	COMPRA CARTAO MAESTRO              04/10 SUELDOS	462879	\N
1007	1	6	2020-10-05	-9.00	COMPRA CARTAO MAESTRO              04/10 RECIFE PARKING	32779	\N
1008	1	8	2020-10-05	-161.00	COMPRA CARTAO MAESTRO              04/10 POSTO SUL	123279	\N
1011	1	6	2020-10-05	-78.40	COMPRA CARTAO MAESTRO              05/10 AC CENTRAL MACEIO	310079	\N
1012	1	13	2020-10-07	-88.00	TARIFA MENSALIDADE PACOTE SERVICOS  SETEMBRO / 2020	21210	\N
1013	1	6	2020-10-07	-85.35	COMPRA CARTAO MAESTRO              07/10 BISTEKAS CASA DE CA	30779	\N
1015	1	6	2020-10-07	-25.50	COMPRA CARTAO MAESTRO              07/10 PAG.AndersonCleyton	403379	\N
1016	1	6	2020-10-08	-40.00	COMPRA CARTAO MAESTRO              08/10 MP .MANDUBEB	564579	\N
1017	1	6	2020-10-08	-69.00	COMPRA CARTAO MAESTRO              08/10 POIT DO ACAI	94279	\N
1014	1	6	2020-10-07	-40.00	SAQUE TERMINAL INTER AG	603192	\N
1018	3	13	2020-10-05	-49.40	TARIFA MENSALIDADE PACOTE SERVICOS  SETEMBRO / 2020	0	\N
1019	3	12	2020-10-06	-60.00	RECARGA TELEFONE CELULAR           IB 82 *****5028 TIM BRASI	0	\N
1020	3	7	2020-10-07	-540.61	PREST. DE EMPREST. FINANCIAMENTO   PARC 004/060 320000193710	193710	\N
1022	3	6	2020-10-09	-12.00	COMPRA CARTAO MAESTRO              09/10 OMENA ALIMENTOS	581952	\N
1025	3	6	2020-10-13	-29.00	COMPRA CARTAO MAESTRO              11/10 PAG.MariaDoCarmo	180152	\N
1027	3	6	2020-10-13	-6.50	COMPRA CARTAO MAESTRO              11/10 PAG.AndersonCleyton	282252	\N
1021	3	6	2020-10-09	-60.00	SAQUE TERMINAL INTER AG	4538	\N
1023	3	8	2020-10-13	-165.84	COMPRA CARTAO MAESTRO              10/10 POSTO VIA MARE	510852	\N
1024	3	6	2020-10-13	-8.90	COMPRA CARTAO MAESTRO              11/10 BR MANIA	230652	\N
1026	3	6	2020-10-13	-102.82	COMPRA CARTAO MAESTRO              11/10 SUPERMERCADO PALATO	160352	\N
1029	3	9	2020-10-13	-34.97	COMPRA CARTAO MAESTRO              12/10 CASA VIEIRA PRAIA	424952	\N
1035	3	6	2020-10-15	-18.50	COMPRA CARTAO MAESTRO              15/10 BOMPRECO B21 PAJUCA	81352	\N
1034	3	14	2020-10-14	-17.14	COMPRA CARTAO MAESTRO              14/10 FCIA PAGUE MENOS 15	582152	\N
1033	3	8	2020-10-14	-50.00	COMPRA CARTAO MAESTRO              14/10 POSTO PAJUCARA	474652	\N
1156	1	6	2020-11-11	-14.00	COMPRA CARTAO MAESTRO              11/11 R C RACOES LTDA ME	211879	\N
1028	3	6	2020-10-13	-93.34	COMPRA CARTAO MAESTRO              12/10 EXTRA 1363 MACEIO	81452	\N
1032	3	24	2020-10-14	-1578.30	PAGAMENTO DE TITULOS - BCE         62.307.848/0001-15	0	\N
1031	3	25	2020-10-14	-30.00	SAQUE TERMINAL INTER AG	3192	\N
1155	1	13	2020-11-10	-6.54	TAR REG TIT COB RAPIDA SIMP-ELETR	194631	\N
1157	1	6	2020-11-12	-22.79	COMPRA CARTAO MAESTRO              12/11 BOMPRECO B21 PAJUCA	383879	\N
1159	1	14	2020-11-13	-27.00	COMPRA CARTAO MAESTRO              13/11 F.T. BOA VISTA F1	21479	\N
1160	1	6	2020-11-13	-29.27	COMPRA CARTAO MAESTRO              13/11 BOMPRECO B21 PAJUCA	582379	\N
1165	1	6	2020-11-16	-143.60	COMPRA CARTAO MAESTRO              14/11 PAG.RaimundoAntonio	483879	\N
1161	1	6	2020-11-16	-54.00	COMPRA CARTAO MAESTRO              14/11 GH LAVA JATO EIRELI	421079	\N
1163	1	6	2020-11-16	-42.50	COMPRA CARTAO MAESTRO              14/11 POSTO MILLENIUM	493079	\N
1162	1	6	2020-11-16	-40.00	COMPRA CARTAO MAESTRO              14/11 REIS ALIMENTOS	555879	\N
1164	1	6	2020-11-16	-20.00	COMPRA CARTAO MAESTRO              14/11 BARBEARIA PAI E FIL	471779	\N
1158	1	31	2020-11-12	-11.00	COMPRA CARTAO MAESTRO              12/11 MARIA ISABELE C A G	411579	\N
1030	3	31	2020-10-13	-206.30	COMPRA CARTAO MAESTRO              12/10 PIZ PIRATAS DO PICU	474852	\N
1036	3	16	2020-10-15	-623.00	PGTO TITULO OUTRO BCO - INTERNET   ESCOLA GERACAO KIDS	0	\N
1038	3	6	2020-10-16	-26.96	COMPRA CARTAO MAESTRO              16/10 BOMPRECO B21 PAJUCA	415152	\N
1039	3	6	2020-10-16	-16.00	COMPRA CARTAO MAESTRO              16/10 BOMPRECO B21 PAJUCA	50352	\N
1073	3	6	2020-10-26	-33.00	COMPRA CARTAO MAESTRO              24/10 SUMUP  .MACEIOCELL	101952	\N
1040	3	25	2020-10-16	-30.00	SAQUE TERMINAL INTER AG	3192	\N
1041	3	6	2020-10-16	-50.00	SAQUE TERMINAL INTER AG	186	\N
1077	3	8	2020-10-26	-100.00	COMPRA CARTAO MAESTRO              25/10 POSTO MILLENIUM	20552	\N
1042	3	25	2020-10-16	-20.00	SAQUE TERMINAL INTER AG	\N	\N
1037	3	6	2020-10-15	-55.00	BID KIDS	281952	\N
1045	3	9	2020-10-19	-234.90	COMPRA CARTAO MAESTRO              17/10 LEROY MACEIO	440352	\N
1048	3	9	2020-10-19	-95.64	COMPRA CARTAO MAESTRO              18/10 CASA VIEIRA PRAIA	595852	\N
1043	3	17	2020-10-19	-700.00	COMPRA CARTAO MAESTRO              17/10 J C INSTRUMENTOS MU	495152	\N
1046	3	8	2020-10-19	-100.00	COMPRA CARTAO MAESTRO              17/10 AUTO POSTO MANGABEI	582252	\N
1047	3	6	2020-10-19	-20.00	COMPRA CARTAO MAESTRO              17/10 MP .MANDUBEB	182852	\N
1049	3	6	2020-10-19	-86.52	COMPRA CARTAO MAESTRO              18/10 SUPERMERCADO PALATO	293852	\N
1050	3	6	2020-10-19	-300.00	SAQUE TERMINAL INTER AG	186	\N
1054	3	9	2020-10-20	-127.89	COMPRA CARTAO MAESTRO              20/10 LEROY MACEIO	30352	\N
1055	3	6	2020-10-21	-2.78	COMPRA CARTAO MAESTRO              21/10 BOMPRECO B21 PAJUCA	2952	\N
1056	3	6	2020-10-21	-20.00	COMPRA CARTAO MAESTRO              21/10 BOMPRECO B21 PAJUCA	14752	\N
1057	3	6	2020-10-21	-23.00	COMPRA CARTAO MAESTRO              21/10 GALETO SAO LUIZ	581852	\N
1058	3	9	2020-10-21	-19.80	COMPRA CARTAO MAESTRO              21/10 LEROY MACEIO	465252	\N
1060	3	14	2020-10-21	-47.27	COMPRA CARTAO MAESTRO              21/10 FARMACIA PERMANENTE	111552	\N
1061	3	6	2020-10-22	-18.50	COMPRA CARTAO MAESTRO              22/10 BOMPRECO B21 PAJUCA	492475	\N
1071	3	6	2020-10-23	-26.00	COMPRA CARTAO MAESTRO              23/10 PAG.MariaDoCarmo	55775	\N
1051	3	6	2020-10-20	-17.50	COMPRA CARTAO MAESTRO              20/10 STELO S.A..CASA D A	390852	\N
1052	3	25	2020-10-20	-26.90	COMPRA CARTAO MAESTRO              20/10 MC DONALDS MIG	240752	\N
1059	3	8	2020-10-21	-100.00	COMPRA CARTAO MAESTRO              21/10 AUTO POSTO MANGABEI	574852	\N
1062	3	25	2020-10-22	-34.16	COMPRA CARTAO MAESTRO              22/10 MARIAH	292152	\N
1053	3	6	2020-10-20	-7.00	COMPRA CARTAO MAESTRO              20/10 P S ADMINISTRADORA	502852	\N
1063	3	6	2020-10-22	-78.95	COMPRA CARTAO MAESTRO              22/10 KRC DOS SANTOS	242552	\N
1064	3	6	2020-10-22	-26.97	COMPRA CARTAO MAESTRO              22/10 KRC DOS SANTOS	421752	\N
1065	3	6	2020-10-23	-5.00	COMPRA CARTAO MAESTRO              23/10 MP .ESTACION	62152	\N
1066	3	9	2020-10-23	-499.99	COMPRA CARTAO MAESTRO              23/10 EXTRA 1363 MACEIO	543652	\N
1068	3	14	2020-10-23	-300.00	SAQUE BANCO 24HS	324123	\N
1069	3	14	2020-10-23	-37.59	COMPRA CARTAO MAESTRO              23/10 3151DROGASIL	424152	\N
1070	3	14	2020-10-23	-36.60	COMPRA CARTAO MAESTRO              23/10 MARIA ISABELE C A G	512952	\N
1072	3	6	2020-10-26	-66.93	COMPRA CARTAO MAESTRO              24/10 OMENA ALIMENTOS	162975	\N
1074	3	6	2020-10-26	-15.00	COMPRA CARTAO MAESTRO              25/10 PAG.AndersonCleyton	274452	\N
1075	3	6	2020-10-26	-9.28	COMPRA CARTAO MAESTRO              25/10 BOMPRECO B21 PAJUCA	21875	\N
1079	3	6	2020-10-26	-27.09	COMPRA CARTAO MAESTRO              26/10 BOMPRECO B21 PAJUCA	232975	\N
1076	3	6	2020-10-26	-131.60	COMPRA CARTAO MAESTRO              25/10 PAG.RaimundoAntonio	381152	\N
1080	3	6	2020-10-26	-10.50	COMPRA CARTAO MAESTRO              26/10 MP .TEPESTIS	514675	\N
1081	3	6	2020-10-26	-8.64	DEBITO AUT. FAT.CARTAO MASTER CARD FINAL 3705	0	\N
1082	3	10	2020-10-27	-534.98	PAGAMENTO CONTA LUZ EM CANAIS      INTERNET CEAL ALAGOAS	0	\N
1078	3	28	2020-10-26	-200.00	PAGAMENTO DE TITULOS - BCE         08.561.701/0001-01	0	\N
1170	3	6	2020-11-12	-16.00	andersonclayton	\N	\N
1067	3	31	2020-10-23	-49.00	COMPRA CARTAO MAESTRO              23/10 GH LAVA JATO EIRELI	531552	\N
1044	3	31	2020-10-19	-42.00	COMPRA CARTAO MAESTRO              17/10 GH LAVA JATO EIRELI	524352	\N
1083	3	15	2020-10-27	-138.94	PGTO CONTA DE TELEFONE EM CANAIS   INTERNET VIVO FIXO NAC	0	\N
1086	3	6	2020-10-27	-25.60	COMPRA CARTAO MAESTRO              27/10 OMENA ALIMENTOS	145252	\N
1087	3	6	2020-10-27	-15.50	COMPRA CARTAO MAESTRO              28/10 PAG.AndersonCleyton	460852	\N
1088	3	6	2020-10-28	-42.00	COMPRA CARTAO MAESTRO              28/10 OMENA ALIMENTOS	194875	\N
1089	3	6	2020-10-29	-20.75	COMPRA CARTAO MAESTRO              29/10 BOMPRECO B21 PAJUCA	451975	\N
1090	3	6	2020-10-29	-8.90	COMPRA CARTAO MAESTRO              29/10 PAG.TACIDODAVIDALEN	353275	\N
1085	3	6	2020-10-27	-20.00	COMPRA CARTAO MAESTRO              27/10 MP .MANDUBEB	94152	\N
1091	1	4	2020-10-28	3406.87	CR COB DINHEIRO CONF RECEBIMENTO   4538/008496463	23010	\N
1092	1	9	2020-10-28	-80.00	COMPRA CARTAO MAESTRO              28/10 MADEIRAS DO BRASIL	342879	\N
1093	1	25	2020-10-28	-18.00	COMPRA CARTAO MAESTRO              28/10 MARIA ISABELE C A G	43479	\N
1094	1	6	2020-10-28	-40.70	COMPRA CARTAO MAESTRO              28/10 ARACAJN PETISCARIA	352379	\N
1095	1	6	2020-10-28	-5.00	COMPRA CARTAO MAESTRO              28/10 EXTRA 1363 MACEIO	30579	\N
1096	1	13	2020-10-28	-2.30	TAR LIQ COB COM REG VIA PAGFOR	194631	\N
1097	1	25	2020-10-29	-38.10	COMPRA CARTAO MAESTRO              29/10 UVA DOCE RESTAURANT	12879	\N
1098	1	8	2020-10-29	-49.93	COMPRA CARTAO MAESTRO              29/10 POSTO TIGRE CAMBONA	32679	\N
1099	1	14	2020-10-29	-31.86	COMPRA CARTAO MAESTRO              29/10 MC PONTA VERDE 2	364879	\N
1100	4	30	2020-10-27	2408.00	Recebimento fgts e seguro desemprego	\N	\N
1104	3	7	2020-10-30	-14.00	PAGAMENTO DE TITULOS - BCE         08.561.701/0001-01	0	\N
1105	3	9	2020-10-30	-151.59	COMPRA CARTAO MAESTRO              30/10 LEROY MACEIO	60452	\N
1103	3	20	2020-10-30	310.00	TED RECEBIDA DIF TITULARIDADE STR  David Matheus de Gusmao V	0	\N
1102	3	6	2020-10-30	-31.00	COMPRA CARTAO MAESTRO              30/10 LANCHONETE CHIC	520252	\N
1107	1	6	2020-10-30	-15.40	COMPRA CARTAO MAESTRO              30/10 OMENA ALIMENTOS	143479	\N
1109	1	6	2020-10-30	-7.55	COMPRA CARTAO MAESTRO              30/10 BOMPRECO B21 PAJUCA	74279	\N
1110	1	6	2020-10-30	-30.00	COMPRA CARTAO MAESTRO              30/10 GALETO SAO LUIZ	382979	\N
1111	1	14	2020-10-30	-33.00	COMPRA CARTAO MAESTRO              30/10 F.T. BOA VISTA F1	502479	\N
1112	1	6	2020-10-30	-45.00	COMPRA CARTAO MAESTRO              30/10 PAG.MariaDoCarmo	295079	\N
1106	1	6	2020-10-30	-21.00	COMPRA CARTAO MAESTRO              30/10 MARIA ISABELE C A G	479	\N
1108	1	14	2020-10-30	-8.79	COMPRA CARTAO MAESTRO              30/10 3151DROGASIL	553279	\N
1113	1	6	2020-11-03	-144.76	COMPRA CARTAO MAESTRO              31/10 PAG.RaimundoAntonio	172779	\N
1114	1	9	2020-11-03	-93.44	COMPRA CARTAO MAESTRO              31/10 EXTRA 1363 MACEIO	480879	\N
1115	1	20	2020-11-03	-15.00	COMPRA CARTAO MAESTRO              31/10 EXTRA 1363 MACEIO	485779	\N
1101	3	20	2020-10-30	-281.87	PAGAMENTO DE TITULOS - BCE         27.351.731/0001-38	0	\N
1116	4	25	2020-10-30	-20.00	pagamento diversos estacionamento	\N	\N
1117	1	6	2020-11-03	-13.00	COMPRA CARTAO MAESTRO              01/11 PAG.MariaDoCarmo	373379	\N
1122	1	6	2020-11-03	-192.84	COMPRA CARTAO MAESTRO              02/11 CENTER CARNES	331379	\N
1123	1	6	2020-11-03	-156.66	COMPRA CARTAO MAESTRO              02/11 OMENA ALIMENTOS	15079	\N
1125	1	9	2020-11-03	-25.46	COMPRA CARTAO MAESTRO              02/11 CASA VIEIRA PRAIA	292979	\N
1126	1	6	2020-11-03	-48.00	COMPRA CARTAO MAESTRO              02/11 PAG.MariaDoCarmo	100079	\N
1127	1	13	2020-11-03	-7.23	MENSALIDADE DE SEGURO              Parc 009/012 AP	157211	\N
1128	1	14	2020-11-03	-32.00	COMPRA CARTAO MAESTRO              03/11 FARMACIA DO TRABALH	473679	\N
1131	1	6	2020-11-05	-35.00	COMPRA CARTAO MAESTRO              05/11 PAG.MariaDoCarmo	13979	\N
1132	1	6	2020-11-05	-5.00	COMPRA CARTAO MAESTRO              05/11 PAG.MariaDoCarmo	541379	\N
1119	1	8	2020-11-03	-171.25	COMPRA CARTAO MAESTRO              01/11 POSTO MELO	592579	\N
1084	3	31	2020-10-27	-52.00	COMPRA CARTAO MAESTRO              27/10 GH LAVA JATO EIRELI	164252	\N
1120	1	6	2020-11-03	-12.98	COMPRA CARTAO MAESTRO              02/11 POSTO MILLENIUM	452879	\N
1121	1	8	2020-11-03	-26.00	COMPRA CARTAO MAESTRO              02/11 POSTO MILLENIUM	540479	\N
1129	1	6	2020-11-03	-29.90	COMPRA CARTAO MAESTRO              03/11 MARIA ISABELE C A G	91179	\N
1130	1	6	2020-11-05	-65.00	COMPRA CARTAO MAESTRO              05/11 SAO MIGUEL GAS	375379	\N
1133	3	30	2020-10-29	11727.75	recebimento recisão fgts	\N	\N
1134	3	13	2020-11-03	-49.40	TARIFA MENSALIDADE PACOTE SERVICOS  OUTUBRO / 2020	0	\N
1136	3	9	2020-11-04	-107.18	COMPRA CARTAO MAESTRO              04/11 LEROY MACEIO	115552	\N
1135	3	25	2020-11-03	-11.00	COMPRA CARTAO MAESTRO              03/11 PAG.EdlaneAlvesDaSi	585752	\N
1145	4	25	2020-11-06	-14.00	pagamento estacionamento	\N	\N
1137	3	17	2020-11-05	-49.90	COMPRA CARTAO MAESTRO              05/11 MAX CASA	202452	\N
1138	4	25	2020-11-05	-140.00	despesas trabalho	\N	\N
1139	6	3	2020-11-01	427.00	vale refeição	\N	\N
1140	1	6	2020-11-06	-19.19	COMPRA CARTAO MAESTRO              06/11 OMENA ALIMENTOS	444579	\N
1142	1	14	2020-11-09	-21.99	COMPRA CARTAO MAESTRO              07/11 FARMACIA PERMANENTE	55579	\N
1143	1	14	2020-11-09	-53.45	COMPRA CARTAO MAESTRO              07/11 F.T. BOA VISTA F1	373279	\N
1124	1	31	2020-11-03	-129.70	COMPRA CARTAO MAESTRO              02/11 CASA DE MAINHA	555079	\N
1144	4	6	2020-11-06	-400.00	pagamento niida feira	\N	\N
1146	6	3	2020-11-06	4625.13	recebimento salário 	\N	\N
1147	6	25	2020-11-06	-103.55	compra wireless	\N	\N
1148	3	7	2020-11-09	-540.61	PREST. DE EMPREST. FINANCIAMENTO PARC 005/060 320000193710	\N	\N
1151	1	6	2020-11-09	-22.00	COMPRA CARTAO MAESTRO              09/11 BOMPRECO B21 PAJUCA	21279	\N
1152	1	6	2020-11-10	-33.96	COMPRA CARTAO MAESTRO              10/11 OMENA ALIMENTOS	35179	\N
1149	1	6	2020-11-09	-8.00	COMPRA CARTAO MAESTRO              08/11 MP .EDB	14279	\N
1153	4	25	2020-11-10	-55.00	despessas estacionamento	\N	\N
1154	1	13	2020-11-09	-88.00	TARIFA MENSALIDADE PACOTE SERVICOS  OUTUBRO / 2020	61511	\N
1166	3	15	2020-11-11	-20.00	recarga telefone	\N	\N
1169	3	9	2020-11-11	-86.35	compra fone	\N	\N
1175	1	6	2020-11-16	-187.92	COMPRA CARTAO MAESTRO              15/11 CENTER CARNES	381679	\N
1176	1	6	2020-11-16	-36.44	COMPRA CARTAO MAESTRO              15/11 BISTEKAS CASA DE CA	444579	\N
1178	1	6	2020-11-17	-5.66	COMPRA CARTAO MAESTRO              17/11 PHARMACOS EXPRESS P	70079	\N
1177	1	6	2020-11-16	-10.00	COMPRA CARTAO MAESTRO              16/11 CASA D ACARA.CASA D	291679	\N
1118	1	31	2020-11-03	-73.70	COMPRA CARTAO MAESTRO              01/11 ARACAJN PETISCARIA	405179	\N
1141	1	6	2020-11-09	-20.00	COMPRA CARTAO MAESTRO              07/11 MP .MANDUBEB	63979	\N
1179	3	16	2020-11-16	-623.00	mensalidade mes 11/20	\N	\N
1180	3	9	2020-11-16	-339.40	compra armário	\N	\N
1181	6	8	2020-11-16	-181.11	posto	\N	\N
1186	1	6	2020-11-25	-41.43	COMPRA CARTAO MAESTRO              25/11 OMENA ALIMENTOS	521479	\N
1184	1	4	2020-11-24	3021.14	CR COB DINHEIRO CONF RECEBIMENTO   4538/008496463	72511	\N
1182	1	6	2020-11-17	-183.50	COMPRA CARTAO MAESTRO              17/11 UNICOMPRA SUPERMERC	132079	\N
1183	1	6	2020-11-19	-20.00	COMPRA CARTAO MAESTRO              19/11 MERCPAGO    .MANDUB	371879	\N
1185	1	13	2020-11-24	-2.30	TAR LIQ COB COM REG VIA PAGFOR	194631	\N
1191	3	12	2020-11-23	-60.00	RECARGA TELEFONE CELULAR           IB 82 *****5028 TIM BRASI	0	\N
1189	3	10	2020-11-19	-425.61	PAGAMENTO CONTA LUZ EM CANAIS      INTERNET CEAL ALAGOAS	0	\N
1187	3	20	2020-11-19	300.00	TED RECEBIDA DIF TITULARIDADE STR  David Matheus de Gusmao V	0	\N
1190	3	31	2020-11-23	-32.99	COMPRA CARTAO MAESTRO              21/11 RECANTO DA PIZZA	142752	\N
1192	3	6	2020-11-23	6.00	TED RECEBIDA DIF TITULARIDADE STR  David Matheus de Gusmao V	0	\N
1188	3	20	2020-11-19	-195.50	PAGAMENTO DE TITULOS - BCE         27.351.731/0001-38	0	\N
1193	6	31	2020-11-19	-56.00	bigquits	\N	\N
1196	6	31	2020-11-23	-27.00	eletronmaria	\N	\N
1197	6	25	2020-11-23	-14.00	estacionamento	\N	\N
1198	4	20	2020-11-19	-100.00	devolução adriana 	\N	\N
1199	4	6	2020-11-24	-400.00	pagamento venda nilda	\N	\N
1200	4	25	2020-11-25	-22.00	estacionamento	\N	\N
1202	3	15	2020-11-26	-129.96	PGTO CONTA DE TELEFONE EM CANAIS   INTERNET VIVO FIXO NAC	0	\N
1206	3	7	2020-11-30	-20.00	PAGAMENTO DE TITULOS - BCE         08.561.701/0001-01	0	\N
1204	3	7	2020-11-26	-9.12	DEBITO AUT. FAT.CARTAO MASTER CARD FINAL 3705	0	\N
1205	3	6	2020-11-30	-18.00	COMPRA CARTAO MAESTRO              29/11 GALETO NORDESTINO	400475	\N
1203	3	6	2020-11-26	-55.00	COMPRA CARTAO MAESTRO              26/11 BODEGA DO ALAGOANO	451175	\N
1207	3	25	2020-12-02	-14.00	COMPRA CARTAO MAESTRO              02/12 ESTAPAR HSC BOLSAO	121575	\N
1208	1	6	2020-11-26	-20.00	COMPRA CARTAO MAESTRO              26/11 BOMPRECO B21 PAJUCA	174279	\N
1209	1	6	2020-11-27	-22.95	COMPRA CARTAO MAESTRO              27/11 BOMPRECO B21 PAJUCA	465279	\N
1222	1	6	2020-11-30	-44.55	COMPRA CARTAO MAESTRO              30/11 OMENA ALIMENTOS	100279	\N
1223	1	6	2020-11-30	-11.40	COMPRA CARTAO MAESTRO              30/11 OMENA ALIMENTOS	522079	\N
1226	1	6	2020-12-02	-28.14	COMPRA CARTAO MAESTRO              02/12 OMENA ALIMENTOS	340779	\N
1210	1	14	2020-11-27	-44.50	COMPRA CARTAO MAESTRO              27/11 FARM TRAB BOA VISTA	153179	\N
1211	1	6	2020-11-27	-7.50	COMPRA CARTAO MAESTRO              27/11 CASA D ACARA.CASA D	344879	\N
1212	1	6	2020-11-27	-14.00	COMPRA CARTAO MAESTRO              27/11 R C RACOES LTDA ME	141679	\N
1213	1	31	2020-11-27	-33.00	COMPRA CARTAO MAESTRO              27/11 CASA DO SUCO FILIAL	295079	\N
1214	1	31	2020-11-30	-220.00	COMPRA CARTAO MAESTRO              28/11 PAG.GentechServicos	453479	\N
1215	1	31	2020-11-30	-46.95	COMPRA CARTAO MAESTRO              29/11 POSTO MILLENIUM	223979	\N
1216	1	8	2020-11-30	-167.97	COMPRA CARTAO MAESTRO              29/11 MAXI POSTO VI	503979	\N
1217	1	31	2020-11-30	-30.99	COMPRA CARTAO MAESTRO              29/11 MAXI POSTO VI	553479	\N
1218	1	31	2020-11-30	-145.00	COMPRA CARTAO MAESTRO              29/11 GLAUCIANA LEITE GOU	345779	\N
1219	1	31	2020-11-30	-25.00	COMPRA CARTAO MAESTRO              29/11 AVENIDA CAFE CONVEN	380879	\N
1220	1	14	2020-11-30	-31.50	COMPRA CARTAO MAESTRO              29/11 FARM TRAB BOA VISTA	553679	\N
1221	1	31	2020-11-30	-37.00	COMPRA CARTAO MAESTRO              29/11 CASA DO SUCO FILIAL	83779	\N
1224	1	31	2020-12-01	-53.00	COMPRA CARTAO MAESTRO              01/12 GH LAVA JATO EIRELI	35879	\N
1225	1	25	2020-12-01	-14.00	COMPRA CARTAO MAESTRO              01/12 ESTAPAR HSC BOLSAO	125979	\N
1228	4	27	2020-11-30	-250.00	pagamento contadora	\N	\N
1230	4	25	2020-12-02	-128.00	despesas trabalho	\N	\N
1231	6	8	2020-11-27	-50.00	posto	\N	\N
1243	1	31	2020-12-03	-45.00	COMPRA CARTAO MAESTRO              03/12 CASA DO SUCO FILIAL	511679	\N
1252	1	6	2020-12-07	-104.50	COMPRA CARTAO MAESTRO              05/12 MARIA ISABELE C A G	533779	\N
1236	6	7	2020-12-01	-61.00	itau card	\N	\N
1201	3	24	2020-11-26	-1587.26	PAGAMENTO DE TITULOS - BCE         62.307.848/0001-15	0	\N
1233	6	3	2020-11-30	323.00	salario	\N	\N
1235	6	3	2020-11-30	790.69	salario	\N	\N
1238	6	3	2020-12-04	5121.04	renumracao	\N	\N
1247	1	6	2020-12-04	-20.00	COMPRA CARTAO MAESTRO              04/12 MERCPAGO    .MANDUB	400379	\N
1239	6	31	2020-12-07	-44.00	posto mile	\N	\N
1240	6	31	2020-12-07	-117.60	rodizio	\N	\N
1241	6	31	2020-12-07	-46.00	pizza	\N	\N
1242	1	13	2020-12-03	-7.23	MENSALIDADE DE SEGURO              Parc 010/012 AP	157211	\N
1244	1	6	2020-12-03	-16.80	COMPRA CARTAO MAESTRO              04/12 PAG.AndersonCleyton	454779	\N
1246	1	6	2020-12-04	-48.00	COMPRA CARTAO MAESTRO              04/12 GH LAVA JATO EIRELI	330679	\N
1245	1	14	2020-12-04	-11.00	COMPRA CARTAO MAESTRO              04/12 FARM TRAB BOA VISTA	312479	\N
1250	1	31	2020-12-04	-8.00	COMPRA CARTAO MAESTRO              04/12 KRC DOS SANTOS	400779	\N
1248	1	31	2020-12-04	-54.00	COMPRA CARTAO MAESTRO              04/12 KRC DOS SANTOS	155179	\N
1249	1	31	2020-12-04	-22.00	COMPRA CARTAO MAESTRO              04/12 KRC DOS SANTOS	202279	\N
1253	1	6	2020-12-07	-25.45	COMPRA CARTAO MAESTRO              05/12 BOMPRECO B21 PAJUCA	181479	\N
1251	1	31	2020-12-04	-22.56	COMPRA CARTAO MAESTRO              05/12 EXTRA 1363 MACEIO	152879	\N
1254	3	13	2020-12-07	-49.40	tarifa mensalidade	\N	\N
1255	1	13	2020-12-07	-88.00	TARIFA MENSALIDADE PACOTE SERVICOS  NOVEMBRO / 2020	40912	\N
1256	1	6	2020-12-07	-22.23	COMPRA CARTAO MAESTRO              07/12 BOMPRECO B21 PAJUCA	320579	\N
1257	1	6	2020-12-07	-30.00	COMPRA CARTAO MAESTRO              07/12 GALETO SAO LUIZ	112679	\N
1258	1	31	2020-12-08	-114.60	COMPRA CARTAO MAESTRO              08/12 PAG.RaimundoAntonio	521879	\N
1259	1	6	2020-12-08	-13.97	COMPRA CARTAO MAESTRO              08/12 EXTRA 1363 MACEIO	123179	\N
1262	6	6	2020-12-07	-5.08	ELECTRONCASA VIEIRA	20201207003	\N
1263	6	6	2020-12-07	-12.93	ELECTRONCASA VIEIRA	20201207004	\N
1270	6	31	2020-12-07	-15.00	ELECTRONPOSTO JACIN	20201207011	\N
1271	6	31	2020-12-07	-44.00	ELECTRONPOSTO MILLE	20201207012	\N
1272	6	6	2020-12-07	-227.11	ELECTRONSUPERMERCAD	20201207013	\N
1273	6	3	2020-12-07	0.09	REND PAGO APLIC AUT MAIS	20201207014	\N
1260	6	7	2020-12-07	-100.00	ELECTRONBADULAQUE P	20201207001	\N
1261	6	31	2020-12-07	-16.50	ELECTRONCACAU SHOW	20201207002	\N
1265	6	6	2020-12-07	-38.89	ELECTRONEXTRA MACEI	20201207006	\N
1267	6	9	2020-12-07	-68.99	ELECTRONLEROY MACEI	20201207008	\N
1266	6	6	2020-12-07	-40.00	ELECTRONFABIO MORAE	20201207007	\N
1264	6	25	2020-12-07	-14.00	ELECTRONESTAPAR HSC	20201207005	\N
1274	4	6	2020-12-07	-400.00	pagamento nilda	\N	\N
1275	4	31	2020-12-03	-30.00	gastos diversos	\N	\N
1276	4	17	2020-12-05	-70.00	oferta na manancial	\N	\N
1280	1	6	2020-12-10	-1.89	COMPRA CARTAO MAESTRO              10/12 BOMPRECO B21 PAJUCA	45179	\N
1283	1	6	2020-12-10	-15.70	COMPRA CARTAO MAESTRO              10/12 PAG.AndersonCleyton	125779	\N
1284	1	13	2020-12-10	-6.54	TAR REG TIT COB RAPIDA SIMP-ELETR	194631	\N
1285	1	6	2020-12-11	-30.28	COMPRA CARTAO MAESTRO              11/12 BOMPRECO B21 PAJUCA	485979	\N
1287	1	6	2020-12-11	-302.01	COMPRA CARTAO MAESTRO              11/12 BOMPRECO PONTA VERD	373379	\N
1278	1	6	2020-12-09	-3.77	COMPRA CARTAO MAESTRO              09/12 PHARMACOS EXPRESS P	391279	\N
1277	1	8	2020-12-09	-150.78	COMPRA CARTAO MAESTRO              09/12 AUTO POSTO TIGRE JA	550879	\N
1279	1	6	2020-12-09	-25.00	COMPRA CARTAO MAESTRO              09/12 CHURRASCARIA DOIS I	471579	\N
1282	1	6	2020-12-10	-18.22	COMPRA CARTAO MAESTRO              10/12 REIS ALIMENTOS	515679	\N
1281	1	6	2020-12-10	-25.00	COMPRA CARTAO MAESTRO              10/12 CHURRASCARIA DOIS I	364179	\N
1286	1	6	2020-12-11	-16.32	COMPRA CARTAO MAESTRO              11/12 EXTRA 1363 MACEIO	272079	\N
1288	6	35	2020-12-12	-700.00	tansferencia bancária	\N	\N
1289	3	35	2020-12-12	700.00	transferencia bancária	\N	\N
1290	6	25	2020-12-12	-40.00	refeição trabalho	\N	\N
1291	1	6	2020-12-14	-62.00	COMPRA CARTAO MAESTRO 13/12 GALETO SAO LUIZ	\N	\N
1292	1	6	2020-12-14	-29.46	COMPRA CARTAO MAESTRO 14/12 BOMPRECO B21 PAJUCA	\N	\N
1293	\N	6	2020-12-14	-40.69	COMPRA CARTAO MAESTRO 14/12 BOMPRECO B21 PAJUCA	\N	\N
1300	1	6	2020-12-15	-15.00	COMPRA CARTAO MAESTRO              15/12 MERCPAGO    .MANDUB	30579	\N
1294	1	6	2020-12-14	-40.69	COMPRA CARTAO MAESTRO 14/12 BOMPRECO B21 PAJUCA	\N	\N
1296	4	31	2020-12-14	-20.00	igreja	\N	\N
1297	3	7	2020-12-12	-35.98	 parcela 6/60	\N	\N
1298	3	7	2020-12-12	-517.09	 parcela 6/60	\N	\N
1301	1	6	2020-12-16	-52.00	COMPRA CARTAO MAESTRO              16/12 BISTEKAS CASA DE CA	252579	\N
1303	1	6	2020-12-17	-12.94	COMPRA CARTAO MAESTRO              17/12 OMENA ALIMENTOS	574279	\N
1304	1	6	2020-12-21	-35.55	COMPRA CARTAO MAESTRO              19/12 BOMPRECO B21 PAJUCA	272279	\N
1307	1	6	2020-12-21	-21.49	COMPRA CARTAO MAESTRO              20/12 BOMPRECO B21 PAJUCA	80379	\N
1308	1	9	2020-12-21	-102.89	COMPRA CARTAO MAESTRO              20/12 LEROY MACEIO	105179	\N
1299	1	6	2020-12-15	-6.00	COMPRA CARTAO MAESTRO              15/12 ESFERA .MARCIO FRUT	265279	\N
1302	1	6	2020-12-16	-188.58	COMPRA CARTAO MAESTRO              16/12 EXTRA 1363 MACEIO	443579	\N
1306	1	8	2020-12-21	-136.32	COMPRA CARTAO MAESTRO              19/12 POSTO MILLENIUM	312879	\N
1305	1	14	2020-12-21	-30.62	COMPRA CARTAO MAESTRO              19/12 3151DROGASIL	330679	\N
1310	6	31	2020-12-14	-10.00	ELECTRONCASA DO SUC	20201214002	\N
1309	6	31	2020-12-14	-35.00	ELECTRONCASA DO SUC	20201214001	\N
1311	6	25	2020-12-14	-14.00	ELECTRONESTAPAR HSC	20201214003	\N
1312	6	3	2020-12-14	0.01	REND PAGO APLIC AUT MAIS	20201214004	\N
1313	6	16	2020-12-15	-623.00	INT PAG TIT 109001994406	20201215001	\N
1314	6	31	2020-12-15	-57.00	ELECTRONBODEGA DO A	20201215002	\N
1315	6	21	2020-12-15	-112.98	ELECTRONMC1 MAGABEI	20201215003	\N
1316	6	21	2020-12-15	-7.00	ELECTRONP S ADMINIS	20201215004	\N
1317	6	3	2020-12-15	0.13	REND PAGO APLIC AUT MAIS	20201215005	\N
1320	6	27	2020-12-16	-1300.00	PIX TRANSF  EDNEY S16/12	20201216003	\N
1319	6	6	2020-12-16	-92.00	ELECTRONMARIA ISABE	20201216002	\N
1318	6	31	2020-12-16	-29.70	ELECTRONHABIB S	20201216001	\N
1321	6	3	2020-12-16	0.23	REND PAGO APLIC AUT MAIS	20201216004	\N
1322	6	14	2020-12-17	-52.29	ELECTRON3151DROGASI	20201217001	\N
1323	6	3	2020-12-17	0.01	REND PAGO APLIC AUT MAIS	20201217002	\N
1324	6	31	2020-12-18	-94.00	ELECTRONBIGLOSO	20201218001	\N
1325	6	25	2020-12-18	-25.00	ELECTRONUVA DOCE RE	20201218002	\N
1326	6	3	2020-12-18	664.04	REMUNERACAO/SALARIO	20201218003	\N
1327	6	31	2020-12-21	-30.69	ELECTRONPOSTO MILLE	20201221001	\N
1328	4	17	2020-12-15	-250.00	doação a ana maria	\N	\N
1330	4	25	2020-12-15	-15.00	trabalho	\N	\N
1329	4	6	2020-12-15	-15.00	diversos	\N	\N
1331	3	12	2020-12-21	-60.00	recarga celular	\N	\N
1332	3	27	2020-12-16	-70.00	pagamento impostos empresa	\N	\N
\.


--
-- Data for Name: tbnatureplan; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.tbnatureplan (id_natureplan, id_workspace, ds_natureplan, id_natureplan_father, ind_type) FROM stdin;
2	1	Receita	\N	R
3	1	Salário Carteira	2	R
4	1	Salário Jurídica	2	R
5	1	Despesas	\N	D
7	1	Contas a Pagar	5	D
6	1	Alimentação	5	D
8	1	Transporte	5	D
9	1	Manutenção Casa	5	D
10	1	Energia	5	D
11	1	Agua	5	D
12	1	Telefone	5	D
13	1	Tarifa Bancária	5	D
14	1	Despesas Médicas	5	D
15	1	Telefone	5	D
16	1	Escola	5	D
17	1	Igreja	5	D
18	1	Presentes e Doações	5	D
19	1	Financeira	\N	R
20	1	Empréstimos	19	R
21	1	Roupas	5	D
22	1	Manutenção Mecanica	5	D
23	1	licenciamento e Ipva	5	D
24	1	Financiamento	5	D
25	1	Despesas Trabalho	5	D
27	1	Empresa	5	D
28	1	Contadora	27	D
30	1	Outras Receitas	2	R
31	1	Diversao	5	D
35	1	Transferencia Bancaria	5	D
\.


--
-- Data for Name: tbprojeto; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.tbprojeto (id, data_final, data_inicial, nome_projeto, id_workspace) FROM stdin;
\.


--
-- Data for Name: tbprojetonatureza; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.tbprojetonatureza (id, valor_previsto, valor_realizado, id_natureplan, id_projeto) FROM stdin;
\.


--
-- Data for Name: tbuser; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.tbuser (login, password, email) FROM stdin;
edchantal	edchantal	edney.chantal@gmail.com
\.


--
-- Data for Name: tbworkspace; Type: TABLE DATA; Schema: public; Owner: mvtpiaib
--

COPY public.tbworkspace (id_workspace, ds_workspace) FROM stdin;
1	Edney Chantal
\.


--
-- Name: sequence_generator; Type: SEQUENCE SET; Schema: public; Owner: mvtpiaib
--

SELECT pg_catalog.setval('public.sequence_generator', 1050, false);


--
-- Name: tbaccountbank_id_accountbank_seq; Type: SEQUENCE SET; Schema: public; Owner: mvtpiaib
--

SELECT pg_catalog.setval('public.tbaccountbank_id_accountbank_seq', 6, true);


--
-- Name: tbconfigextrato_idconfigextrato_seq; Type: SEQUENCE SET; Schema: public; Owner: mvtpiaib
--

SELECT pg_catalog.setval('public.tbconfigextrato_idconfigextrato_seq', 42, true);


--
-- Name: tbmovementbank_idmovementbank_seq; Type: SEQUENCE SET; Schema: public; Owner: mvtpiaib
--

SELECT pg_catalog.setval('public.tbmovementbank_idmovementbank_seq', 1332, true);


--
-- Name: tbnatureplan_id_natureplan_seq; Type: SEQUENCE SET; Schema: public; Owner: mvtpiaib
--

SELECT pg_catalog.setval('public.tbnatureplan_id_natureplan_seq', 35, true);


--
-- Name: tbworkspace_id_workspace_seq; Type: SEQUENCE SET; Schema: public; Owner: mvtpiaib
--

SELECT pg_catalog.setval('public.tbworkspace_id_workspace_seq', 1, true);


--
-- Name: workspace_seq; Type: SEQUENCE SET; Schema: public; Owner: mvtpiaib
--

SELECT pg_catalog.setval('public.workspace_seq', 1, false);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: jhi_persistent_audit_event jhi_persistent_audit_event_pkey; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.jhi_persistent_audit_event
    ADD CONSTRAINT jhi_persistent_audit_event_pkey PRIMARY KEY (event_id);


--
-- Name: jhi_persistent_audit_evt_data jhi_persistent_audit_evt_data_pkey; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT jhi_persistent_audit_evt_data_pkey PRIMARY KEY (event_id, name);


--
-- Name: tbconfigextrato pkconfigextrato; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbconfigextrato
    ADD CONSTRAINT pkconfigextrato PRIMARY KEY (idconfigextrato);


--
-- Name: tbaccountbank tbaccountbank_pkey; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbaccountbank
    ADD CONSTRAINT tbaccountbank_pkey PRIMARY KEY (id_accountbank);


--
-- Name: tbmovementbank tbmovementbank_pkey; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbmovementbank
    ADD CONSTRAINT tbmovementbank_pkey PRIMARY KEY (idmovementbank);


--
-- Name: tbnatureplan tbnatureplan_pkey; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbnatureplan
    ADD CONSTRAINT tbnatureplan_pkey PRIMARY KEY (id_natureplan);


--
-- Name: tbprojeto tbprojeto_pkey; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbprojeto
    ADD CONSTRAINT tbprojeto_pkey PRIMARY KEY (id);


--
-- Name: tbprojetonatureza tbprojetonatureza_pkey; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbprojetonatureza
    ADD CONSTRAINT tbprojetonatureza_pkey PRIMARY KEY (id);


--
-- Name: tbuser tbuser_pkey; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbuser
    ADD CONSTRAINT tbuser_pkey PRIMARY KEY (login);


--
-- Name: tbworkspace tbworkspace_pkey; Type: CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbworkspace
    ADD CONSTRAINT tbworkspace_pkey PRIMARY KEY (id_workspace);


--
-- Name: idx_persistent_audit_event; Type: INDEX; Schema: public; Owner: mvtpiaib
--

CREATE INDEX idx_persistent_audit_event ON public.jhi_persistent_audit_event USING btree (principal, event_date);


--
-- Name: idx_persistent_audit_evt_data; Type: INDEX; Schema: public; Owner: mvtpiaib
--

CREATE INDEX idx_persistent_audit_evt_data ON public.jhi_persistent_audit_evt_data USING btree (event_id);


--
-- Name: pkworklogin; Type: INDEX; Schema: public; Owner: mvtpiaib
--

CREATE INDEX pkworklogin ON public.tbloginworkspace USING btree (idworklogin);


--
-- Name: tbprojetonatureza fk8b3jme8vg36g1lx1kc5uya00v; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbprojetonatureza
    ADD CONSTRAINT fk8b3jme8vg36g1lx1kc5uya00v FOREIGN KEY (id_natureplan) REFERENCES public.tbnatureplan(id_natureplan);


--
-- Name: tbconfigextrato fk_configext_account; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbconfigextrato
    ADD CONSTRAINT fk_configext_account FOREIGN KEY (id_accountank) REFERENCES public.tbaccountbank(id_accountbank);


--
-- Name: jhi_persistent_audit_evt_data fk_evt_pers_audit_evt_data; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.jhi_persistent_audit_evt_data
    ADD CONSTRAINT fk_evt_pers_audit_evt_data FOREIGN KEY (event_id) REFERENCES public.jhi_persistent_audit_event(event_id);


--
-- Name: tbconfigextrato fkconfigextrato01; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbconfigextrato
    ADD CONSTRAINT fkconfigextrato01 FOREIGN KEY (idworkspace) REFERENCES public.tbworkspace(id_workspace);


--
-- Name: tbconfigextrato fkconfigextrato02; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbconfigextrato
    ADD CONSTRAINT fkconfigextrato02 FOREIGN KEY (idnatureplan) REFERENCES public.tbnatureplan(id_natureplan);


--
-- Name: tbprojetonatureza fkf6tv0pi8y2b8n6vrp4q8jtsss; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbprojetonatureza
    ADD CONSTRAINT fkf6tv0pi8y2b8n6vrp4q8jtsss FOREIGN KEY (id_projeto) REFERENCES public.tbprojeto(id);


--
-- Name: tbprojeto fkj2o8hd0v32dw096bnyfsivhk7; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbprojeto
    ADD CONSTRAINT fkj2o8hd0v32dw096bnyfsivhk7 FOREIGN KEY (id_workspace) REFERENCES public.tbworkspace(id_workspace);


--
-- Name: tbloginworkspace fkloginwork; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbloginworkspace
    ADD CONSTRAINT fkloginwork FOREIGN KEY (idworkspace) REFERENCES public.tbworkspace(id_workspace);


--
-- Name: tbmovementbank fkmovementbank01; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbmovementbank
    ADD CONSTRAINT fkmovementbank01 FOREIGN KEY (id_accountbank) REFERENCES public.tbaccountbank(id_accountbank);


--
-- Name: tbmovementbank fkmovementbank02; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbmovementbank
    ADD CONSTRAINT fkmovementbank02 FOREIGN KEY (id_natureplan) REFERENCES public.tbnatureplan(id_natureplan);


--
-- Name: tbaccountbank fktbaccountbank_work; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbaccountbank
    ADD CONSTRAINT fktbaccountbank_work FOREIGN KEY (id_workspace) REFERENCES public.tbworkspace(id_workspace);


--
-- Name: tbnatureplan fktbnatureplan01; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbnatureplan
    ADD CONSTRAINT fktbnatureplan01 FOREIGN KEY (id_natureplan_father) REFERENCES public.tbnatureplan(id_natureplan);


--
-- Name: tbnatureplan fktbnatureplan02; Type: FK CONSTRAINT; Schema: public; Owner: mvtpiaib
--

ALTER TABLE ONLY public.tbnatureplan
    ADD CONSTRAINT fktbnatureplan02 FOREIGN KEY (id_workspace) REFERENCES public.tbworkspace(id_workspace);


--
-- PostgreSQL database dump complete
--

