package org.example;

import org.example.dao.ClienteDAO;
import org.example.dao.PedidoDAO;
import org.example.model.Cliente;
import org.example.model.Pedido;
import org.example.util.ViaCepService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        int opcao = 0;

        while (opcao != 8) {
            System.out.println("\n=== SISTEMA DE CLIENTES E PEDIDOS ===");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Atualizar Cliente");
            System.out.println("4 - Deletar Cliente");
            System.out.println("5 - Cadastrar Pedido");
            System.out.println("6 - Listar Pedidos");
            System.out.println("7 - Cancelar Pedido");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    if (nome.trim().isEmpty()) {
                        System.out.println("Nome não pode ser vazio!");
                        break;
                    }
                    System.out.print("CPF (11 dígitos): ");
                    String cpf = scanner.nextLine();
                    if (cpf.length() != 11) {
                        System.out.println("CPF inválido! Digite 11 dígitos.");
                        break;
                    }
                    System.out.print("CEP (8 dígitos): ");
                    String cep = scanner.nextLine();
                    if (cep.length() != 8) {
                        System.out.println("CEP inválido! Digite 8 dígitos.");
                        break;
                    }
                    System.out.println("Buscando endereço...");
                    String[] endereco = ViaCepService.buscarEndereco(cep);
                    Cliente cliente = new Cliente();
                    cliente.setNome(nome);
                    cliente.setCpf(cpf);
                    cliente.setCep(cep);
                    cliente.setCidade(endereco[0]);
                    cliente.setEstado(endereco[1]);
                    clienteDAO.salvar(cliente);
                    System.out.println("Cliente cadastrado! Cidade: " + endereco[0] + "/" + endereco[1]);
                    break;

                case 2:
                    List<Cliente> clientes = clienteDAO.listarTodos();
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado!");
                    } else {
                        for (Cliente c : clientes) {
                            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() + " | CPF: " + c.getCpf() + " | Cidade: " + c.getCidade() + "/" + c.getEstado());
                        }
                    }
                    break;

                case 3:
                    System.out.print("ID do cliente a atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    Cliente clienteAtualizar = clienteDAO.buscarPorId(idAtualizar);
                    if (clienteAtualizar == null) {
                        System.out.println("Cliente não encontrado!");
                    } else {
                        System.out.print("Novo nome: ");
                        clienteAtualizar.setNome(scanner.nextLine());
                        System.out.print("Novo CPF: ");
                        clienteAtualizar.setCpf(scanner.nextLine());
                        System.out.print("Novo CEP: ");
                        String novoCep = scanner.nextLine();
                        String[] novoEndereco = ViaCepService.buscarEndereco(novoCep);
                        clienteAtualizar.setCep(novoCep);
                        clienteAtualizar.setCidade(novoEndereco[0]);
                        clienteAtualizar.setEstado(novoEndereco[1]);
                        clienteDAO.atualizar(clienteAtualizar);
                        System.out.println("Cliente atualizado!");
                    }
                    break;

                case 4:
                    System.out.print("ID do cliente a deletar: ");
                    int idDeletar = scanner.nextInt();
                    Cliente clienteDeletar = clienteDAO.buscarPorId(idDeletar);
                    if (clienteDeletar == null) {
                        System.out.println("Cliente não encontrado!");
                    } else {
                        clienteDAO.deletar(idDeletar);
                        System.out.println("Cliente deletado!");
                    }
                    break;

                case 5:
                    System.out.print("ID do cliente: ");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();
                    Cliente clientePedido = clienteDAO.buscarPorId(idCliente);
                    if (clientePedido == null) {
                        System.out.println("Cliente não encontrado!");
                    } else {
                        System.out.print("Descrição do pedido: ");
                        String descricao = scanner.nextLine();
                        System.out.print("Valor: ");
                        double valor = scanner.nextDouble();
                        Pedido pedido = new Pedido();
                        pedido.setDescricao(descricao);
                        pedido.setValor(valor);
                        pedido.setCliente(clientePedido);
                        pedidoDAO.salvar(pedido);
                        System.out.println("Pedido cadastrado!");
                    }
                    break;

                case 6:
                    List<Pedido> pedidos = pedidoDAO.listarTodos();
                    if (pedidos.isEmpty()) {
                        System.out.println("Nenhum pedido cadastrado!");
                    } else {
                        for (Pedido p : pedidos) {
                            System.out.println("ID: " + p.getId() + " | Descrição: " + p.getDescricao() + " | Valor: R$" + p.getValor() + " | Cliente: " + p.getCliente().getNome());
                        }
                    }
                    break;

                case 7:
                    System.out.print("ID do pedido a cancelar: ");
                    int idCancelar = scanner.nextInt();
                    Pedido pedidoCancelar = pedidoDAO.buscarPorId(idCancelar);
                    if (pedidoCancelar == null) {
                        System.out.println("Pedido não encontrado!");
                    } else {
                        pedidoDAO.deletar(idCancelar);
                        System.out.println("Pedido cancelado com sucesso!");
                    }
                    break;

                case 8:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}