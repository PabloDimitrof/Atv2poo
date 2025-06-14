package poo.banco;

import java.util.List;
import java.util.Scanner;

public class Terminal {

    private Scanner scanner;
    private Banco banco;

    public void run() {
        try {
            this.scanner = new Scanner(System.in);
            this.banco = new Banco("Mogi International Bank");
            System.out.println();
            System.out.println(banco.getName());
            System.out.println();
            Cliente atualCliente = null;
            Conta atualConta = null;
            while (true) {

                try {

                    String prompt = "";
                    prompt +=
                        atualCliente == null
                        ? ""
                        : atualCliente.getName();
                    prompt +=
                        atualConta == null
                        ? ""
                        : String.format("|%s [%s]", atualConta.getId(), atualConta.getSaldo());

                    System.out.print(prompt + "> ");
                    String line = scanner.nextLine().trim();
                    if (line.equals("exit")) {
                        break;
                    } else if (line.equals("help")) {
                        printHelp();
                    } else if (line.equals("1")) {
                        // cria cliente
                        atualCliente = createCustomer();
                        banco.addCliente(atualCliente);
                        atualConta = null;
                    } else if (line.equals("2")) {
                        listCustomers();
                    } else if (line.equals("3")) {
                        System.out.print("codigo do cliente: ");
                        String id = scanner.nextLine();
                        atualCliente = banco.getCliente(id);
                        atualConta = null;
                    } else if (line.equals("4")) {
                        if (atualCliente == null) {
                            throw new BancoException("D05", "cliente não selecionado");
                        }
                        Conta conta = createAccount(atualCliente);
                        atualCliente.addConta(conta);
                        atualConta = conta;
                        // adicionar conta no banco
                        banco.addConta(atualConta);

                    } else if (line.equals("5")) {
                        if (atualCliente == null) {
                            throw new BancoException("D05", "cliente não selecionado");
                        }
                        listAccounts(atualCliente.getContas());

                    } else if (line.equals("6")) {
                        if (atualCliente == null) {
                            throw new BancoException("D05", "cliente não selecionado");
                        }
                        System.out.print("codigo da conta: ");
                        String id = scanner.nextLine();
                        atualConta = atualCliente.getConta(id);

                    } else if (line.equals("7")) {

                        // depositar
                        if (atualConta == null) {
                            throw new BancoException("D06", "conta não selecionada");
                        }
                        double valor = inputValue();
                        atualConta.depositar(valor);

                    } else if (line.equals("8")) {

                        // sacar
                        if (atualConta == null) {
                            throw new BancoException("D06", "conta não selecionada");
                        }
                        double valor = inputValue();
                        atualConta.sacar(valor);

                    } else if (line.equals("9")) {

                        listAccounts(banco.getContas());

                    } else if (line.equals("r")) {

                        banco.getContas().forEach(c -> {
                            if (c instanceof Rendimento) {
                                ((Rendimento) c).render();
                            }
                        });

                    } else if (line.length() == 0) {
                    } else {
                        throw new UnsupportedOperationException("invalid command");
                    }
                } catch (UnsupportedOperationException e) {
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            System.out.println("bye bye!");    
        }
    }

    private void listCustomers() {
        // for (Cliente c: banco.getClientes()) {
        //     System.out.println(c);
        // }
        banco.getClientes().stream().forEach(c -> {
            System.out.println(c);
        });
    }

    private void listAccounts(List<Conta> contas) {
        contas.stream().forEach(c -> {
            System.out.println((
                c instanceof ContaCorrente ? "CC" :
                c instanceof ContaPoupanca ? "CP" :
                "CI") + " " + c
            );
        });
    }

    private Cliente createCustomer() {

        Cliente cliente;

        System.out.print("Nome: ");
        String name = scanner.nextLine().trim();

        System.out.print("Tipo Fisica|Juridica [f|j]: ");
        String tipo = scanner.nextLine().trim();
        if (tipo.trim().toLowerCase().equals("f")) {
            String cpf = null;
            while (true) {
                System.out.print("CPF: ");
                cpf = scanner.nextLine().trim();
                if (Util.isCpf(cpf)) break;
                System.out.println("CPF invalido");
            }
            cliente = new PessoaFisica(name, cpf);
        } else {
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine().trim();
            cliente = new PessoaJuridica(name, cnpj);
        }

        // nao eh possivel, pois a classe cliente
        // eh abstrata
        // Cliente cliente = new Cliente(name);

        return cliente;
    }
    
    private void printHelp() {
        String help = "";
        help += "\n  1. criar cliente";
        help += "\n  2. listar clientes";
        help += "\n  3. selectionar cliente";
        help += "\n  4. criar conta";
        help += "\n  5. listar contas";
        help += "\n  6. selecionar conta";
        help += "\n  7. depositar";
        help += "\n  8. sacar";
        help += "\n  9. lista todas as contas";
        help += "\n  r. render";
        System.out.println(help);
    }

    private Conta createAccount(Cliente cliente) {
        if (cliente == null) {
            throw new RuntimeException("Cliente nao definido");
        }
        Conta conta;
        System.out.print("Tipo [(P)oupanca|(C)orrente|(I)nvestimento]: ");
        String tipo = scanner.nextLine().trim().toLowerCase();

        if (tipo.equals("p")) {
            conta = new ContaPoupanca(cliente);
        } else if (tipo.equals("c")) {
            conta = new ContaCorrente(cliente);
        } else {
            conta = new ContaInvestimento(cliente);
        }

        return conta;
    }

    private double inputValue() {
        while (true) {
            try {
                System.out.print("valor: ");
                String s = scanner.nextLine();
                return Double.parseDouble(s);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
