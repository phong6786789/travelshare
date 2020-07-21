package com.duan.travelshare.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.duan.travelshare.MainActivity;
import com.duan.travelshare.R;
import com.duan.travelshare.model.FullUser;
import com.duan.travelshare.model.HinhPhong;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ManegerPhongThueFragment extends Fragment {

    public ManegerPhongThueFragment(){

    }
    FloatingActionButton btnAddPhongThue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maneger_phong_thue, container, false);
        btnAddPhongThue = view.findViewById(R.id.btnAddMngPhongThue);
        btnAddPhongThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_room);
                dialog.setCancelable(true);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                final EditText tenPhong, giaPhong, diaChi, moTa;
                Button btnThem, btnNhapLai;
                btnThem = dialog.findViewById(R.id.btnTPThem);
                btnNhapLai = dialog.findViewById(R.id.btnTPNhapLai);
                tenPhong = dialog.findViewById(R.id.edtTPTieuDe);
                giaPhong = dialog.findViewById(R.id.edtTPGiaPhong);
                diaChi = dialog.findViewById(R.id.edtTPDiaChi);
                moTa = dialog.findViewById(R.id.edtTPMoTaChiTietPhong);

                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FullUser user = ShowUserFragment.fullUserOne;
                        HinhPhong hinhPhong = null;
                        String ten = tenPhong.getText().toString();
                        String gia = giaPhong.getText().toString();
                        String dc = diaChi.getText().toString();
                        String mot = moTa.getText().toString();

                    }
                });

                dialog.show();
            }
        });
        return view;
    }
}