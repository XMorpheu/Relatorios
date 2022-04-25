create database bdMedico

use bdMedico

-- CRIANDO TABELA DE CLIENTE

create table tbCliente
(

    codCliente int AUTO_INCREMENT
    ,nome varchar (80)
    ,sexo varchar (10)
    ,cpf varchar (20)
    ,rg varchar (20)
    ,estadoCivil varchar (30)
    ,dtNasc datetime
    ,tel varchar (20)
    ,email varchar (60)
    ,endereco varchar (60)
    ,numero int
    ,cep varchar (20)
    ,bairro varchar (50)
    ,cidade varchar (30)
    ,estado varchar (30)
    ,primary key (codCliente)
    
);

-- CRIANDO TABELA DE CLINICA PARA TESTES

create table tbClinica
(
    codMedico int AUTO_INCREMENT
    ,nomeMedico varchar (45)
    ,dtNascMedico date
    ,cpfMedico varchar (45)
    ,primary key (codMedico)
);

delimiter //

create procedure p_nome(in nomep varchar (80))
begin
SELECT * from tbCliente where nome = nomep;
end //

delimiter;

call p_nome('uda')



delimiter //

create procedure p_cpf(in pcpf varchar (20))
begin
SELECT * from tbCliente where cpf = pcpf;
end //

delimiter;


call p_cpf('uda')
