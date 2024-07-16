package com.vilaka.tabela_fipe_carro.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") Integer anoModelo,
        @JsonAlias("Combustivel") String combustivel) {

    @Override
    public String toString() {
        return "Ano: " + anoModelo + " (" + combustivel + ")" +
                "\n" + marca + " | " + modelo +
                "\n" +  valor + "\n";
    }
}
