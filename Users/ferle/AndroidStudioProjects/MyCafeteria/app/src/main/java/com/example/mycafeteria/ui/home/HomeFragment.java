package com.example.mycafeteria.ui.home;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mycafeteria.R;
import com.example.mycafeteria.ui.dashboard.Producto;
import com.example.mycafeteria.ui.dashboard.ProductoAdapter;
import com.example.mycafeteria.ui.dashboard.ProductoViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private PedidoViewModel pedidoViewModel;
    private ProductoViewModel productoViewModel;
    private TextView mensajeTextView;
    private RecyclerView recyclerViewProductos;
    private ProductoAdapter productoAdapter;
    private String productoSeleccionado; // Para almacenar el producto seleccionado

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inicializa los ViewModels
        pedidoViewModel = new ViewModelProvider(requireActivity()).get(PedidoViewModel.class);
        productoViewModel = new ViewModelProvider(requireActivity()).get(ProductoViewModel.class);

        // Encuentra los elementos de la vista
        EditText nombreClienteEditText = view.findViewById(R.id.etNombreCliente);
        EditText cantidadEditText = view.findViewById(R.id.etCantidad);
        Button btnRegistrarPedido = view.findViewById(R.id.btnRegistrarPedido);
        mensajeTextView = view.findViewById(R.id.tvMensaje);
        recyclerViewProductos = view.findViewById(R.id.recyclerViewProductos);

        // Configura el RecyclerView
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        productoAdapter = new ProductoAdapter(new ArrayList<>(), producto -> {
            productoSeleccionado = producto.getNombre(); // Captura el producto seleccionado
        });
        recyclerViewProductos.setAdapter(productoAdapter);

        // Observa los productos y carga los nombres en el RecyclerView
        productoViewModel.getProductos().observe(getViewLifecycleOwner(), productos -> {
            productoAdapter.actualizarLista(productos);
        });

        // Configura el click listener para el botón
        btnRegistrarPedido.setOnClickListener(v -> {
            String nombreCliente = nombreClienteEditText.getText().toString();
            int cantidad;

            // Manejo de errores para la cantidad
            try {
                cantidad = Integer.parseInt(cantidadEditText.getText().toString());
            } catch (NumberFormatException e) {
                cantidad = 1; // Valor predeterminado
            }

            // Crea un nuevo pedido y lo agrega al ViewModel
            if (productoSeleccionado != null) {
                Pedido nuevoPedido = new Pedido(nombreCliente, productoSeleccionado, cantidad, "En espera");
                pedidoViewModel.agregarPedido(nuevoPedido);

                // Mostrar mensaje de éxito
                mensajeTextView.setText("Pedido registrado con éxito");
                mensajeTextView.setVisibility(View.VISIBLE);

                // Limpia los campos
                nombreClienteEditText.setText("");
                cantidadEditText.setText("");
                productoSeleccionado = null; // Reinicia la selección
            } else {
                mensajeTextView.setText("Por favor selecciona un producto");
                mensajeTextView.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
}
