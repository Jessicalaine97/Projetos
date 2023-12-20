-- Tabela Animal
CREATE TABLE Animal (
    id INT PRIMARY KEY,
    nome VARCHAR(255),
    sexo ENUM('F', 'M'),
    cpfDono VARCHAR(15),
    idade INT,
    peso INT,
    idRaca INT,
    FOREIGN KEY (idRaca) REFERENCES Raca(id)
);

-- Tabela CondicoesEspeciais
CREATE TABLE CondicoesEspeciais (
    id INT PRIMARY KEY,
    idRaca INT,
    alergias VARCHAR(255),
    problemasDeSaude VARCHAR(255),
    FOREIGN KEY (idRaca) REFERENCES Raca(id)
);

-- Tabela Consulta
CREATE TABLE Consulta (
    id INT PRIMARY KEY,
    data DATE,
    exames VARCHAR(255),
    idAnimal INT,
    FOREIGN KEY (idAnimal) REFERENCES Animal(id)
);

-- Tabela Raca
CREATE TABLE Raca (
    id INT PRIMARY KEY,
    tipoAnimal ENUM('Cachorro', 'Gato'),
    nome VARCHAR(255)
);

-- Tabela Tratamento
CREATE TABLE Tratamento (
    id INT PRIMARY KEY,
    idConsulta INT,
    medicamentos VARCHAR(255),
    procedimentos VARCHAR(255),
    FOREIGN KEY (idConsulta) REFERENCES Consulta(id)
);

-- Tabela Usuario
CREATE TABLE Usuario (
    id INT PRIMARY KEY,
    nome VARCHAR(255),
    sexo ENUM('F', 'M'),
    cpf VARCHAR(15),
    email VARCHAR(255),
    senha VARCHAR(255),
    tipoUsuario ENUM('Cliente', 'Funcionario')
);

-- Alimentar a tabela Raca
INSERT INTO Raca (id, tipoAnimal, nome) VALUES
(1, 'Cachorro', 'Labrador'),
(2, 'Cachorro', 'Bulldog'),
(3, 'Gato', 'Siamês'),
(4, 'Gato', 'Persa'),
(5, 'Cachorro', 'Golden Retriever'),
(6, 'Gato', 'Bengal');

-- Alimentar a tabela Animal
INSERT INTO Animal (id, nome, sexo, cpfDono, idade, peso, idRaca) VALUES
(1, 'Rex', 'M', '12345678901', 3, 10, 1),
(2, 'Luna', 'F', '98765432101', 2, 8, 3),
(3, 'Max', 'M', '23456789012', 5, 15, 2),
(4, 'Mia', 'F', '87654321012', 1, 5, 4),
(5, 'Charlie', 'M', '34567890123', 4, 12, 1),
(6, 'Sophie', 'F', '76543210923', 2, 7, 5);

-- Alimentar a tabela CondicoesEspeciais
INSERT INTO CondicoesEspeciais (id, idRaca, alergias, problemasDeSaude) VALUES
(1, 1, 'Nenhuma', 'Nenhum'),
(2, 2, 'Alergia a grama', 'Problemas respiratórios'),
(3, 3, 'Intolerância a certos alimentos', 'Nenhum'),
(4, 4, 'Alergia a pólen', 'Problemas renais'),
(5, 5, 'Nenhuma', 'Artrite'),
(6, 6, 'Alergia a certos alimentos', 'Nenhum');

-- Alimentar a tabela Usuario
INSERT INTO Usuario (id, nome, sexo, cpf, email, senha, tipoUsuario) VALUES
(1, 'admin', 'M', '00000000000', 'admin', 'admin', 'Funcionario'),
(2, 'Maria Silva', 'F', '11122233344', 'maria@email.com', 'senha123', 'Cliente'),
(3, 'João Oliveira', 'M', '22233344455', 'joao@email.com', 'senha456', 'Cliente'),
(4, 'Ana Pereira', 'F', '33344455566', 'ana@email.com', 'senha789', 'Funcionario'),
(5, 'Pedro Santos', 'M', '44455566677', 'pedro@email.com', 'senhaABC', 'Funcionario'),
(6, 'Julia Souza', 'F', '55566677788', 'julia@email.com', 'senhaDEF', 'Cliente'),
(7, 'Lucas Lima', 'M', '66677788899', 'lucas@email.com', 'senhaGHI', 'Cliente');

-- Alimentar a tabela Consulta
INSERT INTO Consulta (id, data, exames, idAnimal) VALUES
(1, '2023-01-15', 'Exame de sangue', 1),
(2, '2023-02-20', 'Raio-X', 3),
(3, '2023-03-25', 'Ultrassom', 2),
(4, '2023-04-10', 'Exame de fezes', 4),
(5, '2023-05-05', 'Exame de urina', 5),
(6, '2023-06-12', 'Radiografia', 6);

-- Alimentar a tabela Tratamento
INSERT INTO Tratamento (id, idConsulta, medicamentos, procedimentos) VALUES
(1, 1, 'Antibiótico', 'Repouso'),
(2, 2, 'Anti-inflamatório', 'Fisioterapia'),
(3, 3, 'Vitaminas', 'Repouso'),
(4, 4, 'Medicação específica', 'Repouso e alimentação especial'),
(5, 5, 'Anti-inflamatório', 'Fisioterapia'),
(6, 6, 'Soro e medicação', 'Repouso');

