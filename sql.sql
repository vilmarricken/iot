  DROP TABLE PLACA;
CREATE TABLE PLACA (
	ID VARCHAR(36) PRIMARY KEY NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	DESCRICAO VARCHAR(512) NOT NULL,
	VERSAO VARCHAR(10) NOT NULL,
	IP VARCHAR(20) NOT NULL
);

  DROP TABLE COMPONENTE;
CREATE TABLE COMPONENTE (
	ID VARCHAR(36) PRIMARY KEY NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	TIPO INTEGER NOT NULL,
	PORTA INTEGER NOT NULL
);

  DROP TABLE MONITOR;
CREATE TABLE MONITOR (
	ID VARCHAR(36) PRIMARY KEY NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	DESCRICAO VARCHAR(512) NOT NULL,
	ALVO NUMERIC(6,2) NOT NULL,
	LIMITE NUMERIC(6,2) NOT NULL,
	-- 0 - QUEDA - Controlador é ligado para baixar   a leitura, liga quando a leitura for maior ou igual a ALVO + LIMITE e desliga quando a leitura for menor ou igual ao ALVO
	-- 1 - ALTA  - Controlador é ligado para aumentar a leitura, liga quando a leitura for menor ou igual a ALBO - LIMITE e desliga quando a leitura for maior ou igual ao ALVO
	TIPO INTEGER,
	IDLEITOR VARCHAR(36) NOT NULL,
	IDCONTROLADOR VARCHAR(36) NOT NULL
);

  DROP TABLE TEMPORIZADOR;
CREATE TABLE TEMPORIZADOR (
	ID VARCHAR(36) PRIMARY KEY NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	DESCRICAO VARCHAR(512) NOT NULL,
	-- 0 - Controlador - Liga/Desliga
	-- 1 - Leitor      - Faz a leitura de um valor
	TIPO INTEGER NOT NULL,
	-- Valor inicial do Controlador, quando o TIPO for 0
	-- 0 - Desligado (Padrão)
	-- 1 - Ligado
	INICIAL INTEGER,
	-- -1 - Inicializa imediatamente (Padrão)
	-- 00 - Minutos a partir da hora atual que o sistema vai Ligar
	INICIAR INTEGER,
	-- TIPO = 0 - Tempo em minutos que o controlador ficará ligado
	-- TIPO = 1 - Tempo em minutos entre cada leitura
	LIGADO INTEGER,
	-- TIPO = 0 - Tempo em minutos que o controlador ficará desligado
	-- TIPO = 1 - Ignorado
	DESLIGADO INTEGER,
)

  DROP TABLE HISTORICO;
CREATE TABLE HISTORICO (
	ID VARCHAR(36) PRIMARY KEY NOT NULL,
	CRIADO NUMERIC(18),
	INICIO NUMERIC(18),
	FIM NUMERIC(18),
	-- 0 - Aguardando
	-- 1 - Executando
	-- 2 - Finalizado
	-- 3 - Erro
	SITUACAO INTEGER,
	ERRO VARCHAR(2000),
	IDTEMPORIZADOR VARCHAR(36),
	IDMONITOR VARCHAR(36),
	IDCOMPONENTE VARCHAR(36),
	-- TIPO = 0 - 1/0 - Ligado/Desligado
	-- TIPO = 1 - Valor da leitura
	VALOR NUMERIC(6,2)
);