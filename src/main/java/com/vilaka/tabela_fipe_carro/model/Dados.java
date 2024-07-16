package com.vilaka.tabela_fipe_carro.model;

public record Dados(
        //@JsonAlias("codigo") Integer id,
        String codigo,
        String nome) {

    @Override
    public String toString() {
        return "Código: " + codigo + " | Dados: " + nome + "\n";
    }
}
