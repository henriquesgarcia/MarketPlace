package com.example.henrique.vendas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

import io.objectbox.Box;

public class AnunciosRVAdapter extends RecyclerView.Adapter<AnunciosRVAdapter.ViewHolder> {

    private Box<Usuario> user;
    private Usuario dono;
    private List<Anuncio> anuncios;
    private Context context;
    private Box<Anuncio> box;

    public AnunciosRVAdapter(List<Anuncio> anuncios, Context context, Box<Anuncio> box, Box<Usuario> user, Usuario dono){
        this.anuncios = anuncios;
        this.context = context;
        this.box = box;
        this.user = user;
        this.dono = dono;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final LayoutInflater inflater = LayoutInflater.from(context);

        View linha = inflater.inflate(R.layout.rv_item_anuncio, parent, false);

        return new ViewHolder(linha);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Anuncio anuncio = this.anuncios.get(position);

        holder.tvTitulo.setText(anuncio.getTitulo());
        holder.tvPreco.setText("R$ " + anuncio.getValor());
        holder.tvLocalizacao.setText(anuncio.getLocalizacao());

        holder.itemView.setOnClickListener( view -> {
            if(dono.getId() == anuncio.getIdDono()){
                final Intent intent = new Intent(context, FormularioAnuncioActivity.class);
                intent.putExtra("anuncioId", anuncio.getId());
                context.startActivity(intent);}
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Dados para Contato:");
                builder.setMessage("Vendedor: "+ user.get(anuncio.getIdDono()).getNome() + "\nE-mail: " + user.get(anuncio.getIdDono()).getEmail() +
                        "\nTelefone: " + user.get(anuncio.getIdDono()).getTel() + "\nEndereço: " + user.get(anuncio.getIdDono()).getEnde());
                builder.setPositiveButton("FECHAR", null);
                builder.show();
            }
        }
        );

        holder.itemView.setOnLongClickListener( view -> {
            if(dono.getId() == anuncio.getIdDono()){
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener( menuItem -> {
                    if (menuItem.getItemId() == R.id.op_remover){

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        builder.setTitle("Confirmação");
                        builder.setMessage("Desejar mesmo remover o anúncio: " + anuncio.getTitulo());
                        builder.setPositiveButton("SIM", (dialogInterface, i) -> {
                            this.anuncios.remove(anuncio);
                            box.remove(anuncio);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, this.getItemCount());
                        });
                        builder.setNegativeButton("NÃO", null);
                        builder.show();

                    }
                    return false;
                });

            popupMenu.show();
            return false;
        }
        return false;});
    }

    @Override
    public int getItemCount() {
        return this.anuncios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitulo;
        private TextView tvPreco;
        private TextView tvLocalizacao;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.tv_titulo);
            tvPreco = itemView.findViewById(R.id.tv_preco);
            tvLocalizacao = itemView.findViewById(R.id.tv_localizacao);
        }
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

}
