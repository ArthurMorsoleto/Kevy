package com.arthurbatista.kevy.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "produto_table") //annotation do Room
public class Produto {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nomeProduto;

    private int quantidadeProduto;

    private double precoProduto;

    private String descricaoProduto;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] imagemProduto;

    public Produto(
            String nomeProduto,
            int quantidadeProduto,
            double precoProduto,
            String descricaoProduto,
            byte[] imagemProduto
    ) {
        this.nomeProduto = nomeProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.precoProduto = precoProduto;
        this.descricaoProduto = descricaoProduto;
        this.imagemProduto = imagemProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public byte[] getImagemProduto() {
        return imagemProduto;
    }

}
