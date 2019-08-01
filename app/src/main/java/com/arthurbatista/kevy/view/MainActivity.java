package com.arthurbatista.kevy.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.arthurbatista.kevy.R;
import com.arthurbatista.kevy.model.Produto;
import com.arthurbatista.kevy.viewmodel.ProdutoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_PRODUTO_REQUEST = 1;

    private ProdutoViewModel produtoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ProdutoAdapter produtoAdapter = new ProdutoAdapter();

        recyclerView.setAdapter(produtoAdapter);

        produtoViewModel = ViewModelProviders.of(this).get(ProdutoViewModel.class);
        produtoViewModel.getAllProdutos().observe(this, produtoAdapter::setProdutos);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Adicionar um novo Produto", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            Intent intent = new Intent(MainActivity.this, AddProdutoActivity.class);
            startActivityForResult(intent, ADD_PRODUTO_REQUEST);
        });

        produtoAdapter.setOnItemClickListener(produto -> { //TODO: NÃO FUNCIONOU O CLICK
            Log.i("TAG MAIN", "onCreate: TESTE ");
            Intent intent = new Intent(MainActivity.this, AddProdutoActivity.class);
            intent.putExtra(AddProdutoActivity.EXTRA_ID, (Parcelable) produto);
            startActivity(intent);
            //TODO: Buscar o produto Parceable na outra tela para editar ou excluir
            //produtoViewModel.getAllProdutos().getValue(); ??
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_PRODUTO_REQUEST && resultCode == RESULT_OK){
            assert data != null;
            Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();

            //TODO: inserir no sqlite com o produtoViewModel OBS: estou fazendo direto na outra activity
        }
        else{
            //TODO: produto não salvo mensagem
        }
    }

 }
