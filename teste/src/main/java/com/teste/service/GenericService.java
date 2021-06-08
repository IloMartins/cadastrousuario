package com.teste.service;

import java.util.List;

public interface GenericService<T> {

    void salvar(T t);

    void excluir(Long id);

    T buscarPorId(Long id);

    List<T> buscarTodos();


}
