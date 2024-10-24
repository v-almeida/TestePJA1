package com.exemplo.gerenciadormercado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.exemplo.gerenciadormercado.repositorio.ProdutoRepositorio
import com.exemplo.gerenciadormercado.ui.produto.ProdutoViewModel
import com.exemplo.gerenciadormercado.ui.produto.TelaProduto

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repositorio = ProdutoRepositorio()

        setContent {
            MaterialTheme {
                TelaProduto(produtoViewModel = ProdutoViewModel(repositorio))
            }
        }
    }
}
