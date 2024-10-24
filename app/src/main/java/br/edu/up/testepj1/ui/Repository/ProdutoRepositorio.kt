package com.exemplo.gerenciadormercado.repositorio

import com.exemplo.gerenciadormercado.modelo.Produto

class ProdutoRepositorio {

    private val listaProdutos = mutableListOf<Produto>()
    private var idAtual = 1

    fun obterTodosProdutos(): List<Produto> {
        return listaProdutos
    }

    fun adicionarProduto(nome: String, preco: Double, quantidade: Int): Produto {
        val produto = Produto(id = idAtual++, nome = nome, preco = preco, quantidade = quantidade)
        listaProdutos.add(produto)
        return produto
    }

    fun atualizarProduto(produtoAtualizado: Produto) {
        listaProdutos.replaceAll { produto ->
            if (produto.id == produtoAtualizado.id) produtoAtualizado else produto
        }
    }

    fun deletarProduto(produtoId: Int) {
        listaProdutos.removeIf { it.id == produtoId }
    }
}
