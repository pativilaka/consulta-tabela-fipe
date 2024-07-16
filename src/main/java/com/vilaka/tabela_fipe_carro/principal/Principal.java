package com.vilaka.tabela_fipe_carro.principal;

import com.vilaka.tabela_fipe_carro.model.Dados;
import com.vilaka.tabela_fipe_carro.model.Modelos;
import com.vilaka.tabela_fipe_carro.model.Veiculo;
import com.vilaka.tabela_fipe_carro.service.ConsumoAPI;
import com.vilaka.tabela_fipe_carro.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {


    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConverteDados converteDados = new ConverteDados();
    private final String ENDERECOFIXO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Motos");
        System.out.println("2 - Carros");
        System.out.println("3 - Caminhões");
        System.out.print("Digite o que deseja pesquisar: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        String opcaoTratada = "";
        switch (opcao) {
            case 1:
                opcaoTratada = "motos";
                break;
            case 2:
                opcaoTratada = "carros";
                break;
            case 3:
                opcaoTratada = "caminhoes";
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        String endereco = ENDERECOFIXO + opcaoTratada + "/marcas";
        String json = consumoAPI.obterDadosApi(endereco);

        System.out.println("\n Listagem das marcas \n");
        List<Dados> marcas = converteDados.converteLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::nome))
                .map(m-> m.nome().toUpperCase());
        marcas.forEach(System.out::println);

        System.out.print("\nDigite o Id/Código da marca desejada? ");
        int idDesejado = sc.nextInt();
        sc.nextLine();

        //https://parallelum.com.br/api/v1/carros/marcas/6/modelos
        endereco = endereco + "/" + idDesejado + "/modelos";
        json = consumoAPI.obterDadosApi(endereco);

        Modelos modelos = converteDados.converteDadosJson(json, Modelos.class);
        modelos.modelos().stream()
                .sorted(Comparator.comparing(Dados::nome))
                .map(m-> m.nome().toUpperCase());
        modelos.modelos().forEach(System.out::println);

        System.out.print("\nDigite o trecho do modelo desejado: ");
        String modeloDesejado = sc.nextLine();

        List<Dados> modelosFiltrados = modelos.modelos().stream()
                .filter(m-> m.nome().toLowerCase().contains(modeloDesejado.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\n**** Modelos Filtrados ****\n");
        modelosFiltrados.forEach(System.out::println);

        System.out.print("\nDigite o código do modelo escolhido: ");
        int codDesejado = sc.nextInt();
        sc.nextLine();

        //https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos
        endereco = endereco + "/" + codDesejado + "/anos";
        json = consumoAPI.obterDadosApi(endereco);

        List<Dados> anos = converteDados.converteLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        //https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos/2014-3
        for(int i = 0; i < anos.size(); i++){
            String novoEndereco = endereco + "/" + anos.get(i).codigo();
            json = consumoAPI.obterDadosApi(novoEndereco);
            Veiculo veiculo = converteDados.converteDadosJson(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        veiculos.forEach(System.out::println);







    }


}
