package com.herokuapp.cristcc2.Repository;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Entidades.Produtos;

public interface ProdutoRepository extends CrudRepository<Produtos, String>{
	Produtos findByNomeProduto(String nome);
}
