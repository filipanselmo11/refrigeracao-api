package com.refrigeracao.refrigeracao_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.refrigeracao.refrigeracao_api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
