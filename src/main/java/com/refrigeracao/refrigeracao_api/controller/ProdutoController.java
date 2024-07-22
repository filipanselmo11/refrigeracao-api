package com.refrigeracao.refrigeracao_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refrigeracao.refrigeracao_api.model.Produto;
import com.refrigeracao.refrigeracao_api.repository.ProdutoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Produto Controller")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @ApiOperation(value = "Listar todos os produtos", response = List.class)
    @GetMapping
    public List<Produto> listarProds() {
        return produtoRepository.findAll();
    }

    @ApiOperation(value = "Criar um novo produto", response = Produto.class)
    @PostMapping
    public Produto criarProd(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdId(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProd(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoRepository.findById(id)
                .map(prodExistente -> {
                    prodExistente.setNome(produto.getNome());
                    Produto atualizado = produtoRepository.save(prodExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deletarProd(@PathVariable Long id) {
    //     return produtoRepository.findById(id)
    //             .map(produto -> {
    //                 produtoRepository.delete(produto);
    //                 return ResponseEntity.noContent().build();
    //             })
    //             .orElse(ResponseEntity.notFound().build());
    // }
}
