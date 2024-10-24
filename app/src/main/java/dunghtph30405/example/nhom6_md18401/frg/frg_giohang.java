package dunghtph30405.example.nhom6_md18401.frg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dunghtph30405.example.nhom6_md18401.R;
import dunghtph30405.example.nhom6_md18401.adapter.GioHangAdapter;
import dunghtph30405.example.nhom6_md18401.dao.GioHangDAO;
import dunghtph30405.example.nhom6_md18401.inteface.TotalUpdateListener;
import dunghtph30405.example.nhom6_md18401.model.giohang;
import dunghtph30405.example.nhom6_md18401.util.Amount;

public class frg_giohang extends Fragment implements TotalUpdateListener {

    GioHangDAO gioHangDAO;
    ArrayList<giohang> list;

    GioHangAdapter gioHangAdapter;

    RecyclerView recyclerGH;
    TextView txt_tongtien;



    public frg_giohang() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frg_giohang, container, false);
        txt_tongtien = view.findViewById(R.id.txt_tongtien_gh);
        recyclerGH = view.findViewById(R.id.rec_giohang);



        loadData();
        updateTotal();












        return view;
    }

    public void loadData(){
        gioHangDAO = new GioHangDAO(getContext());
        list = gioHangDAO.listGH();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerGH.setLayoutManager(linearLayoutManager);
        gioHangAdapter = new GioHangAdapter(getContext(), list, gioHangDAO, frg_giohang.this );
        recyclerGH.setAdapter(gioHangAdapter);
    }


    public void updateTotal() {
        double tongTien = gioHangAdapter.calculateTotal();
        txt_tongtien.setText("Tổng tiền: " + Amount.moneyFormat((int) tongTien));
        gioHangAdapter.notifyDataSetChanged();

    }

    public void setSumPrice(double sumPrice){
        txt_tongtien.setText("Tổng tiền: " + Amount.moneyFormat((int) sumPrice));
        gioHangAdapter.notifyDataSetChanged();//chay lai
    }

    @Override
    public void onTotalUpdated(double total) {
        txt_tongtien.setText("Tổng tiền: " + Amount.moneyFormat((int) total) + " VND");
    }



}