package dunghtph30405.example.nhom6_md18401.frg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dunghtph30405.example.nhom6_md18401.R;
import dunghtph30405.example.nhom6_md18401.adapter.HoaDonAdapter;
import dunghtph30405.example.nhom6_md18401.dao.HoaDonDAO;
import dunghtph30405.example.nhom6_md18401.model.hoadon;

public class frg_lichsu extends Fragment {


    HoaDonDAO hoaDonDAO;
    ArrayList<hoadon> list;
    ArrayList<hoadon> list1;

    HoaDonAdapter hoaDonAdapter;
    RecyclerView recyclerLichSu;
    public frg_lichsu() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frg_lichsu, container, false);

        recyclerLichSu = view.findViewById(R.id.recyclerView_lichsu);
        SearchView searchView = view.findViewById(R.id.search_hoadon_lichsu);

        loadData();

        //tìm kiếm
        list1 = hoaDonDAO.selectHoaDon();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list.clear();
                for (hoadon hd : list1 ) {
                    if (String.valueOf(hd.getMahd()).contains(newText) || String.valueOf(hd.getTensp()).contains(newText) ){
                        list.add(hd);
                    }
                    hoaDonAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });


        return view;
    }

    public void loadData(){
        hoaDonDAO = new HoaDonDAO(getContext());
        list = hoaDonDAO.selectHoaDon();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLichSu.setLayoutManager(linearLayoutManager);
        hoaDonAdapter = new HoaDonAdapter(getContext(), list, hoaDonDAO);
        recyclerLichSu.setAdapter(hoaDonAdapter);
    }
}
