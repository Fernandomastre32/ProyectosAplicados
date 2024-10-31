package com.example.mycafeteria.ui.notifications;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycafeteria.R;
import com.example.mycafeteria.ui.home.Pedido;
import com.example.mycafeteria.ui.home.PedidoViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private PedidoViewModel pedidoViewModel;
    private RecyclerView recyclerView;
    private PedidoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        pedidoViewModel = new ViewModelProvider(requireActivity()).get(PedidoViewModel.class);
        recyclerView = view.findViewById(R.id.recyclerViewPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PedidoAdapter(new ArrayList<>(), pedidoViewModel); // Pasar el ViewModel al adaptador
        recyclerView.setAdapter(adapter);

        pedidoViewModel.getListaPedidos().observe(getViewLifecycleOwner(), new Observer<List<Pedido>>() {
            @Override
            public void onChanged(List<Pedido> pedidos) {
                adapter.actualizarLista(pedidos);
            }
        });


        return view;
    }
}
