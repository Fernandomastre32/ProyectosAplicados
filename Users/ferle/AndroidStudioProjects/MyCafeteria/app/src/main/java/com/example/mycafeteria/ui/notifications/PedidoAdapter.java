package com.example.mycafeteria.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mycafeteria.R;
import com.example.mycafeteria.ui.home.Pedido;
import com.example.mycafeteria.ui.home.PedidoViewModel;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private List<Pedido> pedidos;
    private PedidoViewModel pedidoViewModel;

    public PedidoAdapter(List<Pedido> pedidos, PedidoViewModel pedidoViewModel) {
        this.pedidos = pedidos;
        this.pedidoViewModel = pedidoViewModel;
    }

    public void actualizarLista(List<Pedido> nuevosPedidos) {
        this.pedidos.clear();
        this.pedidos.addAll(nuevosPedidos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pedido_item, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = pedidos.get(position);
        holder.nombreClienteTextView.setText(pedido.getNombreCliente());
        holder.productoTextView.setText(pedido.getProducto());
        holder.cantidadTextView.setText("Cantidad: " + pedido.getCantidad());
        holder.statusTextView.setText("Estado: " + pedido.getStatus());

        // Manejar el clic en el botón "Atender"
        holder.btnAtender.setOnClickListener(v -> {
            // Eliminar el pedido a través del ViewModel
            pedidoViewModel.eliminarPedido(pedido);
        });
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreClienteTextView, productoTextView, cantidadTextView, statusTextView;
        Button btnAtender;

        PedidoViewHolder(View itemView) {
            super(itemView);
            nombreClienteTextView = itemView.findViewById(R.id.tvNombreCliente);
            productoTextView = itemView.findViewById(R.id.tvProducto);
            cantidadTextView = itemView.findViewById(R.id.tvCantidad);
            statusTextView = itemView.findViewById(R.id.tvStatus);
            btnAtender = itemView.findViewById(R.id.btnAtender);
        }
    }
}
