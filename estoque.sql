CREATE TABLE usuario(
	id_usuario SERIAL NOT NULL,
	nome VARCHAR(30) NOT NULL,
	email VARCHAR(50) UNIQUE NOT NULL,
	senha VARCHAR(50) NOT NULL,
	criado_em TIMESTAMP,
	atualizado_em TIMESTAMP,
	PRIMARY KEY(id_usuario)
);

CREATE TABLE categoria(
	id_categoria SERIAL NOT NULL,
	nome VARCHAR(30) UNIQUE NOT NULL,
	criado_em TIMESTAMP,
	PRIMARY KEY(id_categoria)
);

CREATE TABLE produto(
	id_produto SERIAL NOT NULL,
	id_categoria INT NOT NULL,
	marca VARCHAR(30) NOT NULL,
	cor VARCHAR(30) NOT NULL,
	tamanho VARCHAR(5) NOT NULL,
	descricao VARCHAR(30),
	codigoBarras VARCHAR(15) UNIQUE,
	preco DECIMAL(5,2) NOT NULL,
	caminhoImg VARCHAR(50),
	visivelCliente boolean NOT NULL,
	criado_em TIMESTAMP,
	atualizado_em TIMESTAMP,
	PRIMARY KEY(id_produto),
	FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE estoque(
	id_estoque SERIAL NOT NULL,
	id_produto INT NOT NULL,
	qtdprod INT,
	atualizado_em TIMESTAMP,
	PRIMARY KEY(id_estoque),
	FOREIGN KEY(id_produto) REFERENCES produto(id_produto)
);

CREATE TABLE entrada(
	id_entrada SERIAL NOT NULL,
	id_estoque INT NOT NULL,
	quantidade INT NOT NULL,
	criado_em TIMESTAMP,
	PRIMARY KEY(id_entrada),
	FOREIGN KEY(id_estoque) REFERENCES estoque(id_estoque)
);

CREATE TABLE devolucao(
	id_devolucao SERIAL NOT NULL,
	id_estoque INT NOT NULL,
	quantidade INT NOT NULL,
	criado_em TIMESTAMP,
	PRIMARY KEY(id_devolucao),
	FOREIGN KEY(id_estoque) REFERENCES estoque(id_estoque)
);

CREATE TABLE venda(
	id_venda SERIAL NOT NULL,
	id_usuario INT NOT NULL,
	valortotal DECIMAL(6,2), -- Max 9999.99
	desconto DECIMAL(4,2), -- Max 99.99
	criado_em TIMESTAMP,
	PRIMARY KEY(id_venda),
	FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE produtovenda(
	id_produtovenda SERIAL NOT NULL,
	id_estoque INT NOT NULL,
	id_venda INT NOT NULL,    
	quantidade INT NOT NULL,
	PRIMARY KEY(id_produtovenda),
	FOREIGN KEY(id_estoque) REFERENCES estoque(id_estoque),
	FOREIGN KEY(id_venda) REFERENCES venda(id_venda)
);