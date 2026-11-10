# Desafio - Exibir Hosts de Arquivo CSV

Atividade desenvolvida em Java para a disciplina de Lógica de Programação e Algoritmos.

## Sobre a atividade

O programa realiza a leitura de um arquivo CSV chamado `ativos.csv`, utilizando BufferedReader e manipulação de Strings para exibir somente os nomes dos hosts cadastrados.

Durante a execução, o sistema:

- Abre o arquivo `ativos.csv` para leitura;
- Ignora a primeira linha do arquivo, correspondente ao cabeçalho;
- Percorre os registros do arquivo CSV;
- Separa as informações de cada registro utilizando o método `split()`;
- Acessa e exibe somente os nomes dos hosts cadastrados;
- Trata possíveis erros durante a leitura do arquivo.

## Conteúdos utilizados

- Manipulação de arquivos;
- Classe `FileReader`;
- Classe `BufferedReader`;
- Método `readLine()`;
- Manipulação de Strings;
- Método `split()`;
- Vetores de `String`;
- Estrutura de repetição `while`;
- Estrutura de decisão `if`;
- Tratamento de exceções com `try-catch`;
- Fechamento de fluxo com `finally`;
- Exceção `IOException`.

## Tecnologia utilizada

- Java

## Autora

Mariana Youn