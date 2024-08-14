INSERT INTO TB_TECNICO (nome, telefone, email, senha) VALUES ('Jon Snow', '86876876', 'jon@gmail.com', '1234');
INSERT INTO TB_TECNICO (nome, telefone, email, senha) VALUES ('Jamie Lanister', '86876876', 'jamie@gmail.com', '1234');
INSERT INTO TB_TECNICO (nome, telefone, email, senha) VALUES ('Rob Stark', '86876876', 'rob@gmail.com', '1234');
INSERT INTO TB_SETOR (sigla, nome, email, telefone, coordenador) VALUES ('CEREL', 'Central de Relacionamentos', 'cerel.nv@ifms.edu.br', '(67) 98888-5555', 'Daniel Colman'); 
INSERT INTO TB_SETOR (sigla, nome, email, telefone, coordenador) VALUES ('DIREN', 'Direção de Ensino', 'diren.nv@ifms.edu.br', '(67) 97878-4545', 'Vagner Antuniassi'); 
INSERT INTO TB_EQUIPAMENTO (equipamento, patrimonio, id_setor_fk) VALUES ('Notebook Lenovo', '12345', 1);
INSERT INTO TB_EQUIPAMENTO (equipamento, patrimonio, id_setor_fk) VALUES ('Daten AllInOne', '54321', 2);
INSERT INTO TB_ORDEM_DE_SERVICO (descricao_problema, descricao_solucao, data_cadastro, prioridade, status, id_tecnico_fk, id_equipamento_fk) VALUES ('Muito lento e não imprime', '', '2022-06-10', 'ALTA', 'PENDENTE', 1, 1);