package com.vilaka.tabela_fipe_carro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<Dados> modelos) {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Modelos: \n");
        for (Dados m : modelos) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }

}
