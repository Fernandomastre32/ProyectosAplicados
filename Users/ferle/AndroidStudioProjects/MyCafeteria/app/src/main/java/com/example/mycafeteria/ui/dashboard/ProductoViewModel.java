package com.example.mycafeteria.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductoViewModel extends ViewModel {
    private final MutableLiveData<List<Producto>> productosLiveData;
    private final MutableLiveData<List<String>> nombresProductosLiveData;

    public ProductoViewModel() {
        productosLiveData = new MutableLiveData<>(new ArrayList<>());
        nombresProductosLiveData = new MutableLiveData<>(new ArrayList<>());
        inicializarProductos();
    }

    private void inicializarProductos() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Café Americano", 20.0, "Café negro sin azúcar"));
        productos.add(new Producto("Café con Leche", 25.0, "Café con leche y azúcar opcional"));
        productos.add(new Producto("Capuchino", 30.0, "Café con espuma de leche"));

        productosLiveData.setValue(productos);

        // Genera y actualiza la lista de nombres de productos
        actualizarNombresProductos(productos);
    }

    private void actualizarNombresProductos(List<Producto> productos) {
        List<String> nombres = new ArrayList<>();
        for (Producto producto : productos) {
            nombres.add(producto.getNombre());
        }
        nombresProductosLiveData.setValue(nombres);
    }

    public LiveData<List<Producto>> getProductos() {
        return productosLiveData;
    }

    public LiveData<List<String>> getNombresProductos() {
        return nombresProductosLiveData;
    }
}


