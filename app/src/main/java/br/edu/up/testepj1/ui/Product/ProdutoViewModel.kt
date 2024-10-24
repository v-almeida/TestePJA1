package com.exemplo.gerenciadormercado.ui.produto

import androidx.lifecycle.ViewModel
import com.exemplo.gerenciadormercado.modelo.Produto
import com.exemplo.gerenciadormercado.repositorio.ProdutoRepositorio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProdutoViewModel(private val repositorio: ProdutoRepositorio) : ViewModel() {

    private val _produtos = MutableStateFlow<List<Produto>>(emptyList())
    val produtos: StateFlow<List<Produto>> = _produtos.asStateFlow()

    init {
        carregarProdutos()
    }

    fun carregarProdutos() {
        _produtos.value = repositorio.obterTodosProdutos()
    }

    fun adicionarProduto(nome: String, preco: Double, quantidade: Int) {
        repositorio.adicionarProduto(nome, preco, quantidade)
        carregarProdutos()
    }

    fun atualizarProduto(produto: Produto) {
        repositorio.atualizarProduto(produto)
        carregarProdutos()
    }

    fun deletarProduto(produtoId: Int) {
        repositorio.deletarProduto(produtoId)
        carregarProdutos()
    }
}
