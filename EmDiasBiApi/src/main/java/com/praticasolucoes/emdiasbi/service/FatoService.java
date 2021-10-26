package com.praticasolucoes.emdiasbi.service;

import java.util.List;
import java.util.Optional;

public abstract class  FatoService<T,L> {
    void processarLista(List<T> lista){
        lista.stream().forEach(this::carregarFato);
    };
    L carregarFato(T item) {
        if (buscaFato(item).isEmpty()) insereFato(item);
        return  buscaFato(item).get();
    };
    protected abstract void insereFato(T item);
    protected abstract Optional<L> buscaFato(T item);
}
