package com.example.mycafeteria.ui.dashboard;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;
import com.example.mycafeteria.R;
import java.util.List;

public class DashboardFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> adapter; // Cambia a un adaptador compatible con ListView
    private ProductoViewModel productoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        listView = view.findViewById(R.id.listViewProductos);

        // Inicializa el ViewModel
        productoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);

        // Observa los cambios en la lista de productos
        productoViewModel.getNombresProductos().observe(getViewLifecycleOwner(), nombresProductos -> {
            // Configura el adaptador para ListView
            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nombresProductos);
            listView.setAdapter(adapter);
        });

        return view;
    }
}
