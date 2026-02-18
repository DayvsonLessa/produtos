package com.produto.api.service;

import com.produto.api.entidade.Produto;
import com.produto.api.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {


    @Autowired
    private ProdutoRepository repository;


    // Listar todos os produtos
    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    // Buscar por ID
    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Salvar ou Criar novo produto
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    // Deletar produto
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // Atualizar produto (Lógica básica)
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return repository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            return repository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }


}
