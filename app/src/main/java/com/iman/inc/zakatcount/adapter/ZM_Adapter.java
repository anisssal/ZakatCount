package com.iman.inc.zakatcount.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iman.inc.zakatcount.List_ZM;
import com.iman.inc.zakatcount.R;
import com.iman.inc.zakatcount.model.Mdl_ZM;

import java.util.ArrayList;

/**
 * Created by z on 14/12/17.
 */


public class ZM_Adapter extends RecyclerView.Adapter <ZM_Adapter.ViewHolder>{
    Context context;
    ArrayList<Mdl_ZM> list;

    public ZM_Adapter(Context context, ArrayList<Mdl_ZM> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlistzmal, null );
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tjKaya.setText(list.get(position).getJmlHarta());
        holder.tHargaEmas.setText(list.get(position).getHrgEmas());
        holder.tnama_pemilik.setText(list.get(position).getNamaPemilik());
        holder.tTotalZakatMal.setText(list.get(position).gettZakat());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tnama_pemilik;
        TextView tjKaya;
        TextView tHargaEmas;
        TextView tTotalZakatMal;
        ImageView delete;
        public ViewHolder(View itemView) {
            super(itemView);
            tnama_pemilik = itemView.findViewById(R.id.txtNamaPem);
            tjKaya = itemView.findViewById(R.id.txtJmlKaya);
            tHargaEmas = itemView.findViewById(R.id.txtHargaEmas);
            tTotalZakatMal = itemView.findViewById(R.id.text_totalZakat);
            delete = itemView.findViewById(R.id.imgDelete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Delete");
                    builder.setMessage("Yakin menghapus data ini dari database");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Mdl_ZM clickedZmal = getItem(getAdapterPosition());
                            List_ZM.activity.deleteZmal(clickedZmal);
                        }
                    });
                    builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.show();
                }
            });
        }

    }

    public Mdl_ZM getItem (int position){
        return list.get(position);
    }




}
