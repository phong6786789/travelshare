package com.duan.travelshare.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duan.travelshare.MainActivity;
import com.duan.travelshare.R;
import com.duan.travelshare.adapter.ThongBaoAdapter;
import com.duan.travelshare.firebasedao.ThongBaoDao;
import com.duan.travelshare.model.ThongBao;

import java.util.ArrayList;

public class ThongBaoFragment extends Fragment {

    ThongBaoDao thongBaoDao;
    public static ThongBaoAdapter thongBaoAdapter;
    ArrayList<ThongBao> list;
    RecyclerView recThongBao;
    String email = MainActivity.emailUser;
    private View view;

    public ThongBaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_thong_bao, container, false);
        //Toolbar
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.tbTitle);
        ImageView back = toolbar.findViewById(R.id.tbBack);
        title.setText("THÔNG BÁO");
        back.setVisibility(View.INVISIBLE);
        recThongBao = view.findViewById(R.id.recThongBao);

        thongBaoDao = new ThongBaoDao(getActivity());
        list = thongBaoDao.getAll(email);
        thongBaoAdapter = new ThongBaoAdapter(list, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recThongBao.setLayoutManager(linearLayoutManager);
        recThongBao.setAdapter(thongBaoAdapter);

        return view;
    }
}