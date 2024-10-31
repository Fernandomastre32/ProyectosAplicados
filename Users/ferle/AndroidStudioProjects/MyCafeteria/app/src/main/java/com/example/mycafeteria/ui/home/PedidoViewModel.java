package com.example.mycafeteria.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PedidoViewModel extends ViewModel {
    private final MutableLiveData<List<Pedido>> listaPedidos;

    public PedidoViewModel() {
        listaPedidos = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<Pedido>> getListaPedidos() {
        return listaPedidos;
    }

    public void agregarPedido(Pedido pedido) {
        List<Pedido> currentList = listaPedidos.getValue();
        if (currentList != null) {
            currentList.add(pedido);
            listaPedidos.setValue(currentList);
        }
    }

    public void eliminarPedido(Pedido pedido) {
        List<Pedido> currentList = listaPedidos.getValue();
        if (currentList != null) {
            currentList.remove(pedido);
            listaPedidos.setValue(currentList); // Notifica el cambio en la lista
        }
    }
}
