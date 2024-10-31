package com.example.mycafeteria.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mycafeteria.R;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> productos;
    private OnProductoClickListener listener;

    public interface OnProductoClickListener {
        void onProductoClick(Producto producto);
    }

    public ProductoAdapter(List<Producto> productos, OnProductoClickListener listener) {
        this.productos = productos;
        this.listener = listener;
    }

    public void actualizarLista(List<Producto> nuevosProductos) {
        this.productos.clear();
        this.productos.addAll(nuevosProductos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.nombreTextView.setText(producto.getNombre());
        holder.precioTextView.setText("$" + producto.getPrecio());
        holder.descripcionTextView.setText(producto.getDescripcion());

        holder.itemView.setOnClickListener(v -> listener.onProductoClick(producto));
    }

    @Override
    public int getItemCount() { return productos.size(); }

    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, precioTextView, descripcionTextView;

        ProductoViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.tvNombreProducto);
            precioTextView = itemView.findViewById(R.id.tvPrecioProducto);
            descripcionTextView = itemView.findViewById(R.id.tvDescripcionProducto);
        }
    }
}
