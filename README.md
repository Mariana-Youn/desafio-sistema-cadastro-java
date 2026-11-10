# Desafio - Sistema de Cadastro com Manipulação de Arquivos

Atividade desenvolvida em Java para a disciplina de Lógica de Programação e Algoritmos.

## Sobre a atividade

O projeto consiste em um sistema de cadastro de usuários desenvolvido em Java, integrado à manipulação de arquivos de texto para armazenar e recuperar informações.

O sistema utiliza uma matriz para organizar os dados dos usuários e um arquivo chamado `bancoDeDados.txt` para manter os registros salvos mesmo após o encerramento do programa.

Durante a execução, o sistema permite:

- Cadastrar novos usuários;
- Consultar os usuários cadastrados;
- Atualizar informações dos usuários;
- Excluir usuários;
- Carregar os registros salvos no arquivo ao iniciar o programa;
- Salvar as alterações realizadas no arquivo `bancoDeDados.txt`;
- Tratar possíveis erros durante a leitura e gravação dos arquivos.

## Estrutura dos dados

Os registros são armazenados no arquivo `bancoDeDados.txt`, utilizando dois-pontos (`:`) como separador entre os campos.

Formato utilizado:

`ID:Nome:E-mail:Telefone`

## Conteúdos utilizados

- Linguagem Java;
- Matrizes de Strings;
- Manipulação de arquivos;
- Classe `File`;
- Classe `FileReader`;
- Classe `BufferedReader`;
- Classe `FileWriter`;
- Classe `BufferedWriter`;
- Método `readLine()`;
- Método `split()`;
- Estruturas de repetição;
- Estruturas condicionais;
- Tratamento de exceções com `try-catch`;
- Fechamento de fluxos com `finally`;
- Exceção `IOException`.

## Tecnologia utilizada

- Java
- Maven

## Autora

Mariana Youn