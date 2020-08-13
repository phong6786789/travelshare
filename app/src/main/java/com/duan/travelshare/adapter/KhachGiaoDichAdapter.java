package com.duan.travelshare.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duan.travelshare.R;
import com.duan.travelshare.firebasedao.PhongDao;
import com.duan.travelshare.fragment.ChiTietPhongHomeFragment;
import com.duan.travelshare.model.ChiTietPhong;
import com.duan.travelshare.model.FullUser;
import com.duan.travelshare.model.GiaoDich;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class KhachGiaoDichAdapter extends RecyclerView.Adapter<KhachGiaoDichAdapter.ViewHolder> {
    List<GiaoDich> list;
    Context context;

    public KhachGiaoDichAdapter(List<GiaoDich> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_danggiaodich, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChiTietPhong chiTietPhong = list.get(position).getChiTietPhong();
        GiaoDich giaoDich = list.get(position);

        holder.tenPhong.setText(chiTietPhong.getTenPhong());
        holder.giaPhong.setText(chiTietPhong.getGiaPhong());
        holder.diachiPhong.setText(chiTietPhong.getDiaChiPhong());
        if (chiTietPhong.getImgPhong().size() != 0) {
            Picasso.with(context).load(chiTietPhong.getImgPhong().get(0)).into(holder.imgPhong);
        }
        switch (giaoDich.getTrangThai()) {
            case "0":
                holder.trangThai.setText("ĐANG XÁC NHẬN");
                break;
            case "1":
                holder.trangThai.setText("ĐÃ XÁC NHẬN");
                break;
            case "2":
                holder.trangThai.setText("ĐÃ HỦY");
                break;
        }

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhong;
        TextView tenPhong, giaPhong, diachiPhong, trangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenPhong = itemView.findViewById(R.id.titleP);
            giaPhong = itemView.findViewById(R.id.priceP);
            diachiPhong = itemView.findViewById(R.id.addressP);
            imgPhong = itemView.findViewById(R.id.imgP);
            trangThai = itemView.findViewById(R.id.tvTrangThaiGG);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            GiaoDich listP = list.get(position);

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.giaodich);
            dialog.setCancelable(true);
            Window window = dialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            ImageView phong;
            TextView tenP, giaP, hten, cmnd, tu, den, ghichu, trangThai;
            LinearLayout erro, lnButton;
            Button ok, huy;

            phong = dialog.findViewById(R.id.ivimgPhongGG);
            tenP = dialog.findViewById(R.id.tvTenPhongGG);
            giaP = dialog.findViewById(R.id.tvGiaphongGG);
            trangThai = dialog.findViewById(R.id.tvTrangThai);
            hten = dialog.findViewById(R.id.tvHoTenGG);
            cmnd = dialog.findViewById(R.id.tvCmndGG);
            tu = dialog.findViewById(R.id.tvTuNgayGG);
            den = dialog.findViewById(R.id.tvDenNgayGG);
            ghichu = dialog.findViewById(R.id.tvGhiChuGG);
            erro = dialog.findViewById(R.id.lnErro);
            lnButton = dialog.findViewById(R.id.lnXacNhan);
            ok = dialog.findViewById(R.id.btnOkGG);
            huy = dialog.findViewById(R.id.btnCancleGG);

            if (!listP.getChiTietPhong().getImgPhong().isEmpty()) {
                Picasso.with(context).load(listP.getChiTietPhong().getImgPhong().get(0)).into(phong);
            }
            tenP.setText(listP.getChiTietPhong().getTenPhong());
            giaP.setText(listP.getChiTietPhong().getGiaPhong());
            hten.setText(listP.getHoTen());
            cmnd.setText(listP.getCmnd());
            tu.setText(listP.getTuTime()+" " +listP.getTuNgay());
            den.setText(listP.getDenTime()+" " +listP.getDenNgay());
            ghichu.setText(listP.getGhiChu());
            switch (listP.getTrangThai()) {
                case "0":
                    trangThai.setText("ĐANG XÁC NHẬN");
                    break;
                case "1":
                    trangThai.setText("ĐÃ XÁC NHẬN");
                    break;
                case "2":
                    trangThai.setText("ĐÃ HỦY");
                    break;
            }
            ok.setVisibility(View.GONE);
            huy.setText("ĐÓNG");
            huy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

}
