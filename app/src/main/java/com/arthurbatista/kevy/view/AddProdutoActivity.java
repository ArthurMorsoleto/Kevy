package com.arthurbatista.kevy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.arthurbatista.kevy.R;
import com.arthurbatista.kevy.model.Produto;

import java.io.ByteArrayOutputStream;

public class AddProdutoActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtPreco;
    private NumberPicker nbmQuantidade;
    private ImageView imgProduto;
    private EditText edtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);

        edtNome = findViewById(R.id.edtNomeProduto);
        edtPreco = findViewById(R.id.edtPrecoProduto);
        nbmQuantidade = findViewById(R.id.nbmQuantidade);
        imgProduto = findViewById(R.id.imgProduto);
        edtDescricao = findViewById(R.id.edtDescricaoProduto);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        setTitle("Adicionar Produto");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_produto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_produto:
                salvarProduto();
                return true;
            case R.id.delete_produto:
                //TODO: metodo para deletar o produto

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void salvarProduto() {
        String nomeProduto = edtNome.getText().toString();
        double precoProduto = Double.valueOf(edtPreco.getText().toString());
        int quantidadeProduto = nbmQuantidade.getValue();
        String descProduto = edtDescricao.getText().toString();
        if(nomeProduto.trim().isEmpty()){
            Toast.makeText(this, "Insira o nome do produto", Toast.LENGTH_SHORT).show();
            return;
        }
        //TODO: finalizar validação dos campos antes de salvar no sqlite
        //TODO: método para buscar a imagem: galeria??camera??

        Produto produto = (new Produto(
                nomeProduto,
                quantidadeProduto,
                precoProduto,
                descProduto,
                imageViewToByte(imgProduto) //TODO: img não pode ser nula
        ));

        Intent intentData = new Intent();
        intentData.putExtra("PRODUTO", (Parcelable) produto);
        setResult(RESULT_OK, intentData);
        finish();
    }


    //metodo para transformar imagem em byteArray
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
