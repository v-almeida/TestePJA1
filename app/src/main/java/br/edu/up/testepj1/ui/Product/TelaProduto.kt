package com.exemplo.gerenciadormercado.ui.produto

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exemplo.gerenciadormercado.modelo.Produto

@Composable
fun TelaProduto(produtoViewModel: ProdutoViewModel = viewModel()) {
    val produtos by produtoViewModel.produtos.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        // Adicionar Produto
        var nome by remember { mutableStateOf("") }
        var preco by remember { mutableStateOf("") }
        var quantidade by remember { mutableStateOf("") }

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = preco,
            onValueChange = { preco = it },
            label = { Text("Preço") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val precoDouble = preco.toDoubleOrNull() ?: 0.0
                val quantidadeInt = quantidade.toIntOrNull() ?: 0
                produtoViewModel.adicionarProduto(nome, precoDouble, quantidadeInt)
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Adicionar Produto")
        }

        // Lista de Produtos
        LazyColumn {
            items(produtos.size) { index ->
                val produto = produtos[index]
                ProdutoCard(
                    produto = produto,
                    onDeletar = { produtoViewModel.deletarProduto(produto.id) },
                    onAtualizar = {
                        val produtoAtualizado = produto.copy(
                            nome = "Produto Atualizado",
                            preco = produto.preco + 1
                        )
                        produtoViewModel.atualizarProduto(produtoAtualizado)
                    }
                )
            }
        }
    }
}

@Composable
fun ProdutoCard(produto: Produto, onDeletar: () -> Unit, onAtualizar: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nome: ${produto.nome}")
            Text(text = "Preço: R$ ${produto.preco}")
            Text(text = "Quantidade: ${produto.quantidade}")

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = onAtualizar) {
                    Text("Atualizar")
                }
                Button(onClick = onDeletar) {
                    Text("Deletar")
                }
            }
        }
    }
}
