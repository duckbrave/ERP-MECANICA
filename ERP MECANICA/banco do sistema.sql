PGDMP                  
    |            erp    17.0    17.0 >               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false                       1262    16388    erp    DATABASE     z   CREATE DATABASE erp WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE erp;
                     postgres    false            �            1259    16651    agendamento    TABLE     1  CREATE TABLE public.agendamento (
    id integer NOT NULL,
    id_cliente integer NOT NULL,
    cnpj character varying(18) NOT NULL,
    telefone character varying(15) NOT NULL,
    nome_cliente character varying(100) NOT NULL,
    nome_veiculo character varying(100) NOT NULL,
    marca_veiculo character varying(50) NOT NULL,
    data_entrada date NOT NULL,
    status character(1) NOT NULL,
    defeito text,
    obs_age character varying(240),
    CONSTRAINT agendamento_status_check CHECK ((status = ANY (ARRAY['A'::bpchar, 'P'::bpchar, 'F'::bpchar])))
);
    DROP TABLE public.agendamento;
       public         heap r       postgres    false            �            1259    16650    agendamento_id_seq    SEQUENCE     �   CREATE SEQUENCE public.agendamento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.agendamento_id_seq;
       public               postgres    false    229                       0    0    agendamento_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.agendamento_id_seq OWNED BY public.agendamento.id;
          public               postgres    false    228            �            1259    16397    cliente    TABLE     �  CREATE TABLE public.cliente (
    id integer NOT NULL,
    cpf character varying(11),
    telefone bigint,
    nome character varying(100),
    email character varying(100),
    rua character varying(100),
    bairro character varying(100),
    cidade character varying(100),
    cnpj character varying(20),
    razao_social character varying(60),
    ie_cli character varying(13),
    representante character varying(50),
    estado integer,
    for_cli_trans integer
);
    DROP TABLE public.cliente;
       public         heap r       postgres    false            �            1259    16396    cliente_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.cliente_id_seq;
       public               postgres    false    218                       0    0    cliente_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;
          public               postgres    false    217            �            1259    16462    estoque    TABLE     �  CREATE TABLE public.estoque (
    id_prod integer NOT NULL,
    codigo_pro character varying(50) NOT NULL,
    nome_pro character varying(30) NOT NULL,
    quant_pro integer NOT NULL,
    estado character varying(10) NOT NULL,
    prc_custo numeric(10,2) NOT NULL,
    prc_venda numeric(10,2) NOT NULL,
    tipo_emba character varying(5) NOT NULL,
    data_cad timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    prod_ser character(1) NOT NULL
);
    DROP TABLE public.estoque;
       public         heap r       postgres    false            �            1259    16461    estoque_id_prod_seq    SEQUENCE     �   CREATE SEQUENCE public.estoque_id_prod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.estoque_id_prod_seq;
       public               postgres    false    224                       0    0    estoque_id_prod_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.estoque_id_prod_seq OWNED BY public.estoque.id_prod;
          public               postgres    false    223            �            1259    16406 
   fornecedor    TABLE     �  CREATE TABLE public.fornecedor (
    id integer NOT NULL,
    cnpj character varying(14) NOT NULL,
    nome character varying(100),
    telefone character varying(15),
    email character varying(100),
    rua character varying(40),
    data_cadastro timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    razao_social character varying(60) NOT NULL,
    ie_for character varying(12),
    representante character varying(50),
    bairro character varying(20),
    cidade character varying(60)
);
    DROP TABLE public.fornecedor;
       public         heap r       postgres    false            �            1259    16405    fornecedor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.fornecedor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.fornecedor_id_seq;
       public               postgres    false    220                       0    0    fornecedor_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.fornecedor_id_seq OWNED BY public.fornecedor.id;
          public               postgres    false    219            �            1259    16525 	   permissao    TABLE     �   CREATE TABLE public.permissao (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    descricao character varying(50)
);
    DROP TABLE public.permissao;
       public         heap r       postgres    false            �            1259    16524    permissao_id_seq    SEQUENCE     �   CREATE SEQUENCE public.permissao_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.permissao_id_seq;
       public               postgres    false    226                       0    0    permissao_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.permissao_id_seq OWNED BY public.permissao.id;
          public               postgres    false    225            �            1259    16440    usuario    TABLE     <  CREATE TABLE public.usuario (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    usuario character varying(50) NOT NULL,
    senha character varying(40) NOT NULL,
    perfil character varying(5) NOT NULL,
    estado character varying(20) NOT NULL,
    data_criacao timestamp without time zone
);
    DROP TABLE public.usuario;
       public         heap r       postgres    false            �            1259    16543    usuario_permissao    TABLE     l   CREATE TABLE public.usuario_permissao (
    permissao_id bigint NOT NULL,
    usuario_id bigint NOT NULL
);
 %   DROP TABLE public.usuario_permissao;
       public         heap r       postgres    false            �            1259    16439    usuarios_id_seq    SEQUENCE     �   CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.usuarios_id_seq;
       public               postgres    false    222                       0    0    usuarios_id_seq    SEQUENCE OWNED BY     B   ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuario.id;
          public               postgres    false    221            �            1259    16664 	   venda_nfe    TABLE     Q  CREATE TABLE public.venda_nfe (
    id integer NOT NULL,
    id_cliente integer NOT NULL,
    cnpj character varying(20),
    telefone character varying(15),
    nome_cliente character varying(100),
    nome_veiculo character varying(100),
    marca_veiculo character varying(50),
    forma_pagamento character varying(50),
    peca character varying(100),
    valor numeric(10,2),
    defeito text,
    data_venda timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    cod_peca character varying,
    quantidade integer,
    id_usuario integer,
    nome_usuario character varying(100)
);
    DROP TABLE public.venda_nfe;
       public         heap r       postgres    false            �            1259    16663    venda_nfe_id_seq    SEQUENCE     �   CREATE SEQUENCE public.venda_nfe_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.venda_nfe_id_seq;
       public               postgres    false    231                       0    0    venda_nfe_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.venda_nfe_id_seq OWNED BY public.venda_nfe.id;
          public               postgres    false    230            J           2604    16654    agendamento id    DEFAULT     p   ALTER TABLE ONLY public.agendamento ALTER COLUMN id SET DEFAULT nextval('public.agendamento_id_seq'::regclass);
 =   ALTER TABLE public.agendamento ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    228    229    229            C           2604    16400 
   cliente id    DEFAULT     h   ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);
 9   ALTER TABLE public.cliente ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    217    218    218            G           2604    16465    estoque id_prod    DEFAULT     r   ALTER TABLE ONLY public.estoque ALTER COLUMN id_prod SET DEFAULT nextval('public.estoque_id_prod_seq'::regclass);
 >   ALTER TABLE public.estoque ALTER COLUMN id_prod DROP DEFAULT;
       public               postgres    false    224    223    224            D           2604    16409    fornecedor id    DEFAULT     n   ALTER TABLE ONLY public.fornecedor ALTER COLUMN id SET DEFAULT nextval('public.fornecedor_id_seq'::regclass);
 <   ALTER TABLE public.fornecedor ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    219    220    220            I           2604    16528    permissao id    DEFAULT     l   ALTER TABLE ONLY public.permissao ALTER COLUMN id SET DEFAULT nextval('public.permissao_id_seq'::regclass);
 ;   ALTER TABLE public.permissao ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    225    226    226            F           2604    16443 
   usuario id    DEFAULT     i   ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);
 9   ALTER TABLE public.usuario ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    221    222    222            K           2604    16667    venda_nfe id    DEFAULT     l   ALTER TABLE ONLY public.venda_nfe ALTER COLUMN id SET DEFAULT nextval('public.venda_nfe_id_seq'::regclass);
 ;   ALTER TABLE public.venda_nfe ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    230    231    231                      0    16651    agendamento 
   TABLE DATA           �   COPY public.agendamento (id, id_cliente, cnpj, telefone, nome_cliente, nome_veiculo, marca_veiculo, data_entrada, status, defeito, obs_age) FROM stdin;
    public               postgres    false    229   O       �          0    16397    cliente 
   TABLE DATA           �   COPY public.cliente (id, cpf, telefone, nome, email, rua, bairro, cidade, cnpj, razao_social, ie_cli, representante, estado, for_cli_trans) FROM stdin;
    public               postgres    false    218   �O       �          0    16462    estoque 
   TABLE DATA           �   COPY public.estoque (id_prod, codigo_pro, nome_pro, quant_pro, estado, prc_custo, prc_venda, tipo_emba, data_cad, prod_ser) FROM stdin;
    public               postgres    false    224   �O       �          0    16406 
   fornecedor 
   TABLE DATA           �   COPY public.fornecedor (id, cnpj, nome, telefone, email, rua, data_cadastro, razao_social, ie_for, representante, bairro, cidade) FROM stdin;
    public               postgres    false    220   MP                  0    16525 	   permissao 
   TABLE DATA           8   COPY public.permissao (id, nome, descricao) FROM stdin;
    public               postgres    false    226   �P       �          0    16440    usuario 
   TABLE DATA           Y   COPY public.usuario (id, nome, usuario, senha, perfil, estado, data_criacao) FROM stdin;
    public               postgres    false    222   �P                 0    16543    usuario_permissao 
   TABLE DATA           E   COPY public.usuario_permissao (permissao_id, usuario_id) FROM stdin;
    public               postgres    false    227   =Q                 0    16664 	   venda_nfe 
   TABLE DATA           �   COPY public.venda_nfe (id, id_cliente, cnpj, telefone, nome_cliente, nome_veiculo, marca_veiculo, forma_pagamento, peca, valor, defeito, data_venda, cod_peca, quantidade, id_usuario, nome_usuario) FROM stdin;
    public               postgres    false    231   ZQ                  0    0    agendamento_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.agendamento_id_seq', 3, true);
          public               postgres    false    228                       0    0    cliente_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cliente_id_seq', 9, true);
          public               postgres    false    217                       0    0    estoque_id_prod_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.estoque_id_prod_seq', 2, true);
          public               postgres    false    223                       0    0    fornecedor_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.fornecedor_id_seq', 4, true);
          public               postgres    false    219                       0    0    permissao_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.permissao_id_seq', 1, false);
          public               postgres    false    225                       0    0    usuarios_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.usuarios_id_seq', 12, true);
          public               postgres    false    221                       0    0    venda_nfe_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.venda_nfe_id_seq', 12, true);
          public               postgres    false    230            _           2606    16657    agendamento agendamento_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.agendamento DROP CONSTRAINT agendamento_pkey;
       public                 postgres    false    229            O           2606    16404    cliente cliente_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public                 postgres    false    218            W           2606    16468    estoque estoque_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.estoque
    ADD CONSTRAINT estoque_pkey PRIMARY KEY (id_prod);
 >   ALTER TABLE ONLY public.estoque DROP CONSTRAINT estoque_pkey;
       public                 postgres    false    224            Q           2606    16412    fornecedor fornecedor_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT fornecedor_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT fornecedor_pkey;
       public                 postgres    false    220            Y           2606    16532    permissao permissao_nome_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.permissao
    ADD CONSTRAINT permissao_nome_key UNIQUE (nome);
 F   ALTER TABLE ONLY public.permissao DROP CONSTRAINT permissao_nome_key;
       public                 postgres    false    226            [           2606    16530    permissao permissao_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.permissao DROP CONSTRAINT permissao_pkey;
       public                 postgres    false    226            ]           2606    16547 (   usuario_permissao usuario_permissao_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY public.usuario_permissao
    ADD CONSTRAINT usuario_permissao_pkey PRIMARY KEY (permissao_id, usuario_id);
 R   ALTER TABLE ONLY public.usuario_permissao DROP CONSTRAINT usuario_permissao_pkey;
       public                 postgres    false    227    227            S           2606    16445    usuario usuarios_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuarios_pkey;
       public                 postgres    false    222            U           2606    16447    usuario usuarios_usuario_key 
   CONSTRAINT     Z   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuarios_usuario_key UNIQUE (usuario);
 F   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuarios_usuario_key;
       public                 postgres    false    222            a           2606    16672    venda_nfe venda_nfe_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.venda_nfe
    ADD CONSTRAINT venda_nfe_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.venda_nfe DROP CONSTRAINT venda_nfe_pkey;
       public                 postgres    false    231            d           2606    16658 '   agendamento agendamento_id_cliente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id);
 Q   ALTER TABLE ONLY public.agendamento DROP CONSTRAINT agendamento_id_cliente_fkey;
       public               postgres    false    218    4687    229            b           2606    16548 3   usuario_permissao fk_usuario_permissao_permissao_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_permissao
    ADD CONSTRAINT fk_usuario_permissao_permissao_id FOREIGN KEY (permissao_id) REFERENCES public.permissao(id);
 ]   ALTER TABLE ONLY public.usuario_permissao DROP CONSTRAINT fk_usuario_permissao_permissao_id;
       public               postgres    false    4699    227    226            c           2606    16553 1   usuario_permissao fk_usuario_permissao_usuario_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_permissao
    ADD CONSTRAINT fk_usuario_permissao_usuario_id FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);
 [   ALTER TABLE ONLY public.usuario_permissao DROP CONSTRAINT fk_usuario_permissao_usuario_id;
       public               postgres    false    222    4691    227            e           2606    16673 #   venda_nfe venda_nfe_id_cliente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.venda_nfe
    ADD CONSTRAINT venda_nfe_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.venda_nfe DROP CONSTRAINT venda_nfe_id_cliente_fkey;
       public               postgres    false    218    231    4687               _   x�=��� ��2`�ip휀�$�K������8��.X��� ?����J�$�|�}$n\+��˝`����=��q�&Y>\��Y�8!>      �   R   x�����4��N�Ĥ��ԜD�������LN(C/9?�3%�3%����81_!73�45������������d����� ��H      �   R   x���	�0�s2Eh��EK�p /b#�������)`Լ�w����C����(� ���jD
��� ��2g��7a��),      �   r   x�M�A� ��+������ً��b@����ݓ��T�Pwԛ��xm�<�$C�0����"���|�<Z�="X�V>373q�����{����ʰM��I+��B|��$�             x������ � �      �   A   x�34��,K����FƜ�.��~�%E���FF&����&
FVƦV�&z�Ɔ��\1z\\\ ÑZ            x������ � �         �   x���;
�0D��)t���Ⱦ@����Fހ@a��&��!�"�4��yų&>9�iL1A�>��Z�Jz��{�,Mj%��N��ƹ0l�R= }o\�ЛdZE�>�.���9�9D� ��r}WY��X��I~{󁵓�4c2���b�R/%�R�     