package com.produto.api.contoller;

import com.produto.api.entidade.Produto;
import com.produto.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    // Listar todos - GET
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> lista = service.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    // Buscar por ID - GET
    //@GetMapping("/{id}")
    //public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
   //     return service.buscarPorId(id)
   //           .map(produto -> ResponseEntity.ok().body(produto))
   //             .orElse(ResponseEntity.notFound().build());
  //  }

    // Criar novo - POST
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto novoProduto = service.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    // Atualizar - PUT
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            Produto atualizado = service.atualizar(id, produto);
            return ResponseEntity.ok().body(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
