-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 10/06/2024 às 22:21
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `gerenciamentoescolar`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `comunicados`
--

CREATE TABLE `comunicados` (
  `id_comunicados` int(11) NOT NULL,
  `texto_comunicado` longtext NOT NULL,
  `data_comunicado` date NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `comunicados`
--

INSERT INTO `comunicados` (`id_comunicados`, `texto_comunicado`, `data_comunicado`, `id_usuario`) VALUES
(2, 'Prova de Matemática na próxima segunda-feira.', '2024-06-11', 5),
(3, 'Entrega do trabalho de Ciências foi adiada.', '2024-06-12', 4),
(4, 'Reunião de pais na sexta-feira.', '2024-06-13', 1),
(5, 'Excursão de História no próximo mês.', '2024-06-14', 6),
(6, 'Novo material de Geografia disponível.', '2024-06-15', 3),
(7, 'esse é um teste de comunicado', '2024-06-10', 1),
(11, 'comunicado teste', '2024-06-10', 1),
(12, 'qual foi', '2024-06-10', 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `disciplina`
--

CREATE TABLE `disciplina` (
  `id_disciplina` int(11) NOT NULL,
  `nome` varchar(70) NOT NULL,
  `descricao` varchar(200) NOT NULL,
  `nota` varchar(45) DEFAULT NULL,
  `id_aluno` int(11) DEFAULT NULL,
  `id_professor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `disciplina`
--

INSERT INTO `disciplina` (`id_disciplina`, `nome`, `descricao`, `nota`, `id_aluno`, `id_professor`) VALUES
(1, 'Matemática', 'Curso de Matemática Básica', NULL, 2, 5),
(2, 'Português', 'Curso de Português Intermediário', NULL, 3, 6),
(3, 'Ciências', 'Curso de Ciências Naturais', NULL, 4, 5),
(4, 'História', 'Curso de História do Brasil', NULL, 2, 6),
(5, 'Geografia', 'Curso de Geografia Física', NULL, 3, 5),
(6, 'Ingles', 'Curso de Língua Inglesa', NULL, 3, 6);

--
-- Acionadores `disciplina`
--
DELIMITER $$
CREATE TRIGGER `insere_relacionamento_professor_disciplina` AFTER INSERT ON `disciplina` FOR EACH ROW BEGIN
    DECLARE new_codigo INT;
    IF NEW.id_professor IS NOT NULL THEN
        -- Seleciona o maior valor de 'codigo' e incrementa em 1
        SELECT COALESCE(MAX(codigo), 0) + 1 INTO new_codigo FROM professor_disciplina;
        -- Insere o novo relacionamento na tabela 'professor_disciplina'
        INSERT INTO professor_disciplina (codigo, id_professor, id_disciplina) 
        VALUES (new_codigo, NEW.id_professor, NEW.id_disciplina);
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `disciplina_aluno`
--

CREATE TABLE `disciplina_aluno` (
  `id_disciplina_aluno` int(11) NOT NULL,
  `id_disciplina` int(11) NOT NULL,
  `id_aluno` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `professor_disciplina`
--

CREATE TABLE `professor_disciplina` (
  `id_professor` int(11) NOT NULL,
  `id_disciplina` int(11) NOT NULL,
  `codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `professor_disciplina`
--

INSERT INTO `professor_disciplina` (`id_professor`, `id_disciplina`, `codigo`) VALUES
(5, 1, 1),
(6, 2, 2),
(5, 3, 3),
(6, 4, 4),
(5, 5, 5),
(6, 6, 6);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nome`, `email`, `senha`, `tipo`) VALUES
(1, 'Rogerio', 'rogerinho@gmail.com', '123', 'professor'),
(2, 'Ana Silva', 'ana.silva@gmail.com', 'senha123', 'aluno'),
(3, 'Carlos Souza', 'carlos.souza@gmail.com', 'senha123', 'aluno'),
(4, 'Maria Oliveira', 'maria.oliveira@gmail.com', 'senha123', 'aluno'),
(5, 'João Pedro', 'joao.pedro@gmail.com', 'senha123', 'professor'),
(6, 'Fernanda Lima', 'fernanda.lima@gmail.com', 'senha123', 'professor');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `comunicados`
--
ALTER TABLE `comunicados`
  ADD PRIMARY KEY (`id_comunicados`),
  ADD KEY `fk_comunicados_usuario` (`id_usuario`);

--
-- Índices de tabela `disciplina`
--
ALTER TABLE `disciplina`
  ADD PRIMARY KEY (`id_disciplina`),
  ADD KEY `fk_disciplina_aluno` (`id_aluno`),
  ADD KEY `fk_disciplina_professor` (`id_professor`);

--
-- Índices de tabela `disciplina_aluno`
--
ALTER TABLE `disciplina_aluno`
  ADD PRIMARY KEY (`id_disciplina_aluno`),
  ADD KEY `fk_disciplina_aluno_disciplina` (`id_disciplina`),
  ADD KEY `fk_disciplina_aluno_aluno` (`id_aluno`);

--
-- Índices de tabela `professor_disciplina`
--
ALTER TABLE `professor_disciplina`
  ADD PRIMARY KEY (`codigo`,`id_professor`,`id_disciplina`),
  ADD KEY `id_professor` (`id_professor`),
  ADD KEY `id_disciplina` (`id_disciplina`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `comunicados`
--
ALTER TABLE `comunicados`
  MODIFY `id_comunicados` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `disciplina`
--
ALTER TABLE `disciplina`
  MODIFY `id_disciplina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `disciplina_aluno`
--
ALTER TABLE `disciplina_aluno`
  MODIFY `id_disciplina_aluno` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `comunicados`
--
ALTER TABLE `comunicados`
  ADD CONSTRAINT `fk_comunicados_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Restrições para tabelas `disciplina`
--
ALTER TABLE `disciplina`
  ADD CONSTRAINT `fk_disciplina_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `fk_disciplina_professor` FOREIGN KEY (`id_professor`) REFERENCES `usuario` (`id_usuario`);

--
-- Restrições para tabelas `disciplina_aluno`
--
ALTER TABLE `disciplina_aluno`
  ADD CONSTRAINT `fk_disciplina_aluno_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `fk_disciplina_aluno_disciplina` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`);

--
-- Restrições para tabelas `professor_disciplina`
--
ALTER TABLE `professor_disciplina`
  ADD CONSTRAINT `professor_disciplina_ibfk_1` FOREIGN KEY (`id_professor`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `professor_disciplina_ibfk_2` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
