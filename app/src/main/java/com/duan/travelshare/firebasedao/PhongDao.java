package com.duan.travelshare.firebasedao;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.duan.travelshare.MainActivity;
import com.duan.travelshare.fragment.HomeFragment;
import com.duan.travelshare.fragment.ManegerPhongThueFragment;
import com.duan.travelshare.fragment.ShowDialog;
import com.duan.travelshare.fragment.ShowUserFragment;
import com.duan.travelshare.model.ChiTietPhong;
import com.duan.travelshare.model.FullUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class PhongDao {
    Context context;
    DatabaseReference reference;
    String key = "";
    Boolean check;
    ShowDialog showDialog;

    public PhongDao() {
    }

    public PhongDao(Context context) {
        this.context = context;
        reference = FirebaseDatabase.getInstance().getReference("Phong");
        showDialog = new ShowDialog((Activity) context);
    }

    //Lấy toàn bộ phòng
    public ArrayList<ChiTietPhong> getAllPhong() {
        final ArrayList<ChiTietPhong> list = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear();
                    Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = dataSnapshotIterable.iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot next = (DataSnapshot) iterator.next();
                        ChiTietPhong nd = next.getValue(ChiTietPhong.class);
                        list.add(nd);
                    }
                    ManegerPhongThueFragment.chiTietPhongAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Lấy thông tin thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
        return list;
    }

    //Lấy toàn bộ phòng
    public ArrayList<ChiTietPhong> getAllPhongHome() {
        final ArrayList<ChiTietPhong> list = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list.clear();
                    Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = dataSnapshotIterable.iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot next = (DataSnapshot) iterator.next();
                        ChiTietPhong nd = next.getValue(ChiTietPhong.class);
                        list.add(nd);
                    }
                    HomeFragment.phongAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Lấy thông tin thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
        return list;
    }

    //Tự sinh key có sẵn trước
    public String creatKey() {
        return reference.push().getKey();
    }

    //Thêm Phòng mới
    public void insertPhong(final ChiTietPhong chiTietPhong) {
//        final String key = reference.push().getKey();
        reference.push().setValue(chiTietPhong).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()) {
//                    reference.child(key).child("idPhong").setValue(key);
//                    showDialog.toastInfo("Thêm phòng thành công!");
                    ManegerPhongThueFragment.chiTietPhongAdapter.notifyDataSetChanged();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                showDialog.toastInfo("Thêm phòng thất bại!");
            }
        });
    }
    //Cập nhật Phòng

    public void updatePhong(final ChiTietPhong chiTietPhong) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("idPhong").getValue(String.class).equalsIgnoreCase(chiTietPhong.getIdPhong())) {
                        reference.child(key).setValue(chiTietPhong);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    //Cập nhật ảnh
    public void upImage(final String id, final String link) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("imgPhong").child("idHinh").getValue(String.class).equalsIgnoreCase(id)) {
                        key = data.getKey();
                        reference.child("imgPhong").child("linkHinh").setValue(link);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
