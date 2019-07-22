package com.arthurbatista.kevy.model;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ProdutoRepository {

    private ProdutoDAO produtoDAO;
    private LiveData<List<Produto>> allProdutos;

    public ProdutoRepository(Application application){
        ProdutoDataBase dataBase = ProdutoDataBase.getInstance(application);
        produtoDAO = dataBase.produtoDAO();
        allProdutos = produtoDAO.getAllProdutos();
    }

    public void insert(Produto produto){
        new InsertProdutoAsyncTask(produtoDAO).execute(produto);
    }

    public void update(Produto produto){
        new UpdateProdutoAsyncTask(produtoDAO).execute(produto);
    }

    public void delete(Produto produto){
        new DeleteProdutoAsyncTask(produtoDAO).execute(produto);
    }

    public LiveData<List<Produto>> getAllProdutos(){
        return allProdutos;
    }


    private static class InsertProdutoAsyncTask extends AsyncTask<Produto, Void, Void>{

        private ProdutoDAO produtoDAO;

        private InsertProdutoAsyncTask(ProdutoDAO produtoDAO){
            this.produtoDAO = produtoDAO;
        }

        @Override
        protected Void doInBackground(Produto... produtos) {
            produtoDAO.insert(produtos[0]);
            return null;
        }
    }

    private static class DeleteProdutoAsyncTask extends AsyncTask<Produto, Void, Void>{

        private ProdutoDAO produtoDAO;

        private DeleteProdutoAsyncTask(ProdutoDAO produtoDAO){
            this.produtoDAO = produtoDAO;
        }

        @Override
        protected Void doInBackground(Produto... produtos) {
            produtoDAO.delete(produtos[0]);
            return null;
        }
    }

    private static class UpdateProdutoAsyncTask extends AsyncTask<Produto, Void, Void>{

        private ProdutoDAO produtoDAO;

        private UpdateProdutoAsyncTask(ProdutoDAO produtoDAO){
            this.produtoDAO = produtoDAO;
        }

        @Override
        protected Void doInBackground(Produto... produtos) {
            produtoDAO.update(produtos[0]);
            return null;
        }
    }



}
