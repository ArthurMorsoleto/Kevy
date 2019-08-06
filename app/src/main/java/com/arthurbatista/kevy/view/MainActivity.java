package com.arthurbatista.kevy.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.arthurbatista.kevy.R;
import com.arthurbatista.kevy.viewmodel.ProdutoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_PRODUTO_REQUEST = 1;

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

        ProdutoViewModel produtoViewModel = ViewModelProviders.of(this).get(ProdutoViewModel.class);
        produtoViewModel.getAllProdutos().observe(this, produtoAdapter::setProdutos);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddProdutoActivity.class);
            startActivityForResult(intent, ADD_PRODUTO_REQUEST);
        });

        produtoAdapter.setOnItemClickListener(produto -> {
            Log.i("TAG MAIN", "onCreate: TESTE " + produto);
            Intent intent = new Intent(MainActivity.this, AddProdutoActivity.class);
            intent.putExtra(AddProdutoActivity.EXTRA_PRODUTO, produto);
            startActivity(intent);
        });
    }


}
