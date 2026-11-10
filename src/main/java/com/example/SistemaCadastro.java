
package com.example;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class SistemaCadastro
{
    public static void main(String args[])
    {
        Scanner entrada = new Scanner(System.in);

        int limite;
        int quantidade;
        int opcao;

        System.out.print("Quantas novas pessoas deseja poder cadastrar? ");
        limite = entrada.nextInt();
        entrada.nextLine();

        while (limite <= 0)
        {
            System.out.println("Informe uma quantidade maior que zero.");
            System.out.print("Quantas novas pessoas deseja poder cadastrar? ");
            limite = entrada.nextInt();
            entrada.nextLine();
        }

        // Conta os usuários que já estão salvos no arquivo
        int usuariosExistentes = contarUsuariosArquivo();

        // Cria a matriz com espaço para os usuários existentes e novos
        String usuarios[][] = new String[usuariosExistentes + limite + 1][4];

        usuarios[0][0] = "ID";
        usuarios[0][1] = "Nome";
        usuarios[0][2] = "E-mail";
        usuarios[0][3] = "Telefone";

        // Carrega os dados do arquivo para a matriz
        quantidade = carregarUsuarios(usuarios);

        // Cria o arquivo caso ele ainda não exista
        File arquivo = new File("bancoDeDados.txt");

        if (!arquivo.exists() || arquivo.length() == 0)
        {
            salvarUsuarios(usuarios, quantidade);
        }

        do
        {
            System.out.println("\n===== SISTEMA DE CADASTRO =====");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Consultar usuários");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Deletar usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao)
            {
                case 1:
                    quantidade = cadastrarUsuario(usuarios, quantidade, entrada);
                    salvarUsuarios(usuarios, quantidade);
                    break;

                case 2:
                    consultarUsuarios(usuarios, quantidade);
                    break;

                case 3:
                    atualizarUsuario(usuarios, quantidade, entrada);
                    salvarUsuarios(usuarios, quantidade);
                    break;

                case 4:
                    quantidade = deletarUsuario(usuarios, quantidade, entrada);
                    salvarUsuarios(usuarios, quantidade);
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        entrada.close();
    }

    // Conta quantos usuários já existem no arquivo
    public static int contarUsuariosArquivo()
    {
        int quantidade = 0;

        File arquivo = new File("bancoDeDados.txt");

        if (!arquivo.exists())
        {
            return quantidade;
        }

        BufferedReader leitor = null;

        try
        {
            leitor = new BufferedReader(new FileReader(arquivo));

            String linha;

            while ((linha = leitor.readLine()) != null)
            {
                if (linha.equals("ID:Nome:E-mail:Telefone"))
                {
                    continue;
                }

                if (!linha.trim().isEmpty())
                {
                    String dados[] = linha.split(":", -1);

                    if (dados.length == 4)
                    {
                        quantidade++;
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Erro ao contar usuários: " + e.getMessage());
        }
        finally
        {
            if (leitor != null)
            {
                try
                {
                    leitor.close();
                }
                catch (IOException e)
                {
                    System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
                }
            }
        }

        return quantidade;
    }

    // Carrega os usuários do arquivo para a matriz
    public static int carregarUsuarios(String usuarios[][])
    {
        int quantidade = 0;

        File arquivo = new File("bancoDeDados.txt");

        if (!arquivo.exists())
        {
            return quantidade;
        }

        BufferedReader leitor = null;

        try
        {
            leitor = new BufferedReader(new FileReader(arquivo));

            String linha;

            while ((linha = leitor.readLine()) != null)
            {
                // Ignora o cabeçalho do arquivo
                if (linha.equals("ID:Nome:E-mail:Telefone"))
                {
                    continue;
                }

                if (!linha.trim().isEmpty())
                {
                    String dados[] = linha.split(":", -1);

                    if (dados.length == 4)
                    {
                        if (quantidade < usuarios.length - 1)
                        {
                            quantidade++;

                            for (int j = 0; j < 4; j++)
                            {
                                usuarios[quantidade][j] = dados[j];
                            }
                        }
                    }
                }
            }

            System.out.println("Dados carregados com sucesso!");
        }
        catch (IOException e)
        {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        finally
        {
            if (leitor != null)
            {
                try
                {
                    leitor.close();
                }
                catch (IOException e)
                {
                    System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
                }
            }
        }

        return quantidade;
    }

    // Salva os usuários da matriz no arquivo
    public static void salvarUsuarios(String usuarios[][], int quantidade)
    {
        BufferedWriter escritor = null;

        try
        {
            escritor = new BufferedWriter(new FileWriter("bancoDeDados.txt"));

            // Escreve o cabeçalho
            escritor.write("ID:Nome:E-mail:Telefone");
            escritor.newLine();

            for (int i = 1; i <= quantidade; i++)
            {
                escritor.write(
                    usuarios[i][0] + ":" +
                    usuarios[i][1] + ":" +
                    usuarios[i][2] + ":" +
                    usuarios[i][3]
                );

                escritor.newLine();
            }

            System.out.println("Arquivo atualizado com sucesso!");
        }
        catch (IOException e)
        {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
        finally
        {
            if (escritor != null)
            {
                try
                {
                    escritor.close();
                }
                catch (IOException e)
                {
                    System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
                }
            }
        }
    }

    public static int cadastrarUsuario(String usuarios[][], int quantidade, Scanner entrada)
    {
        if (quantidade >= usuarios.length - 1)
        {
            System.out.println("Limite de usuários atingido!");
            return quantidade;
        }

        System.out.println("\n===== CADASTRAR USUÁRIO =====");

        System.out.print("ID (número): ");
        String id = entrada.nextLine();

        for (int i = 1; i <= quantidade; i++)
        {
            if (usuarios[i][0].equals(id))
            {
                System.out.println("Já existe um usuário com esse ID!");
                return quantidade;
            }
        }

        System.out.print("Nome: ");
        String nome = entrada.nextLine();

        System.out.print("E-mail: ");
        String email = entrada.nextLine();

        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();

        usuarios[quantidade + 1][0] = id;
        usuarios[quantidade + 1][1] = nome;
        usuarios[quantidade + 1][2] = email;
        usuarios[quantidade + 1][3] = telefone;

        quantidade++;

        System.out.println("Usuário cadastrado com sucesso!");

        return quantidade;
    }

    public static void consultarUsuarios(String usuarios[][], int quantidade)
    {
        if (quantidade == 0)
        {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\n===== USUÁRIOS CADASTRADOS =====");

        System.out.println(usuarios[0][0] + " | "
                + usuarios[0][1] + " | "
                + usuarios[0][2] + " | "
                + usuarios[0][3]);

        System.out.println("--------------------------------------------------");

        for (int i = 1; i <= quantidade; i++)
        {
            System.out.println(usuarios[i][0] + " | "
                    + usuarios[i][1] + " | "
                    + usuarios[i][2] + " | "
                    + usuarios[i][3]);
        }
    }

    public static void atualizarUsuario(String usuarios[][], int quantidade, Scanner entrada)
    {
        if (quantidade == 0)
        {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        consultarUsuarios(usuarios, quantidade);

        System.out.print("\nDigite o ID do usuário que deseja atualizar: ");
        String id = entrada.nextLine();

        int indice = 0;

        for (int i = 1; i <= quantidade; i++)
        {
            if (usuarios[i][0].equals(id))
            {
                indice = i;
                break;
            }
        }

        if (indice == 0)
        {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.println("\n===== ATUALIZAR USUÁRIO =====");

        System.out.print("Novo ID (número): ");
        String novoId = entrada.nextLine();

        for (int i = 1; i <= quantidade; i++)
        {
            if (i != indice && usuarios[i][0].equals(novoId))
            {
                System.out.println("Já existe um usuário com esse ID!");
                return;
            }
        }

        System.out.print("Novo nome: ");
        String nome = entrada.nextLine();

        System.out.print("Novo e-mail: ");
        String email = entrada.nextLine();

        System.out.print("Novo telefone: ");
        String telefone = entrada.nextLine();

        usuarios[indice][0] = novoId;
        usuarios[indice][1] = nome;
        usuarios[indice][2] = email;
        usuarios[indice][3] = telefone;

        System.out.println("Usuário atualizado com sucesso!");
    }

    public static int deletarUsuario(String usuarios[][], int quantidade, Scanner entrada)
    {
        if (quantidade == 0)
        {
            System.out.println("Nenhum usuário cadastrado.");
            return quantidade;
        }

        consultarUsuarios(usuarios, quantidade);

        System.out.print("\nDigite o ID do usuário que deseja deletar: ");
        String id = entrada.nextLine();

        int indice = 0;

        for (int i = 1; i <= quantidade; i++)
        {
            if (usuarios[i][0].equals(id))
            {
                indice = i;
                break;
            }
        }

        if (indice == 0)
        {
            System.out.println("Usuário não encontrado!");
            return quantidade;
        }

        for (int i = indice; i < quantidade; i++)
        {
            for (int j = 0; j < usuarios[i].length; j++)
            {
                usuarios[i][j] = usuarios[i + 1][j];
            }
        }

        for (int j = 0; j < usuarios[quantidade].length; j++)
        {
            usuarios[quantidade][j] = null;
        }

        quantidade--;

        System.out.println("Usuário deletado com sucesso!");

        return quantidade;
    }
}
