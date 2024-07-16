package com.vilaka.tabela_fipe_carro.service;

import java.util.List;

public interface iConverteDados {
    <T> T converteDadosJson(String json, Class<T> classe);

    <T> List<T> converteLista(String json, Class<T> classe);
}
