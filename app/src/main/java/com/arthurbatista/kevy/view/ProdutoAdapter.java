package com.arthurbatista.kevy.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arthurbatista.kevy.R;
import com.arthurbatista.kevy.model.Produto;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoHolder> {

    private List<Produto> produtos = new ArrayList<>();

    @NonNull
    @Override
    public ProdutoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.produto_item, parent, false);
        return new ProdutoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoHolder holder, int position) {
        Produto produtoAtual = produtos.get(position);

        byte[] produtoImage = produtoAtual.getImagemProduto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(produtoImage, 0, produtoImage.length);
        //holder.imageViewImagemProduto.setImageBitmap(bitmap);

        holder.textViewNome.setText(produtoAtual.getNomeProduto());
        holder.textViewPreco.setText(String.valueOf(produtoAtual.getPrecoProduto()));
        holder.textViewQuantidade.setText(String.valueOf(produtoAtual.getQuantidadeProduto()));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void setProdutos(List<Produto> produtos){
        this.produtos = produtos;
        notifyDataSetChanged();
    }

    class ProdutoHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewImagemProduto;
        private TextView textViewNome;
        private TextView textViewPreco;
        private TextView textViewQuantidade;

        public ProdutoHolder(@NonNull View itemView) {
            super(itemView);
            imageViewImagemProduto = itemView.findViewById(R.id.imgProduto);
            textViewNome = itemView.findViewById(R.id.txtNomeProduto);
            textViewPreco = itemView.findViewById(R.id.txtPrecoProduto);
            textViewQuantidade = itemView.findViewById(R.id.txtQuantidadeProduto);
        }
    }


}
