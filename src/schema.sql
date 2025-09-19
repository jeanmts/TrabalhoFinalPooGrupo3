create database empresaPoo; 
create table Funcionario (
id_funcionario serial primary key,
nome_funcionario varchar(100) not null,
cpf_funcionario varchar(11) unique not null,
data_de_nascimento_funcionario date not null,
salarioBruto numeric(10,2) not null,
descontoINSS numeric(10,2),
descontoIR numeric(10,2)
);

create table Dependentes (
id_dependente serial primary key,
nome_dependente varchar(100) not null,
cpf_dependente varchar(11) not null unique,
data_de_nascimento_dependente date not null,
parentesco_dependente varchar(50) not null,
id_funcionario_func int not null,
foreign key (id_funcionario_func) references funcionario (id_funcionario) on delete cascade
);

create table FolhaPagamento (
id_folha serial primary key,
id_funcionario int not null,
data_pagamento date not null,
desconto_INSS numeric(10,2) not null,
desconto_IR numeric(10,2) not null,
id_funcionario_func int not null,
foreign key (id_funcionario_func) references funcionario (id_funcionario) on delete cascade
);