package com.hienqp.listfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CityListFragment extends ListFragment {
    String[] cityList = {"Hà Nội","Hải Phòng","Nha Trang","Khánh Hòa","Hồ Chí Minh","Cần Thơ"};
    ArrayAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, cityList);
        setListAdapter(adapter);

        return inflater.inflate(R.layout.fragment_citylist, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), cityList[position], Toast.LENGTH_SHORT).show();

        super.onListItemClick(l, v, position, id);
    }
}
