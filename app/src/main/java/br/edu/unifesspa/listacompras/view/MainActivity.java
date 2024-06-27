package br.edu.unifesspa.listacompras.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.Locale;

import br.edu.unifesspa.listacompras.R;
import br.edu.unifesspa.listacompras.controller.ProductDAO;
import br.edu.unifesspa.listacompras.model.Product;

public class MainActivity extends AppCompatActivity {

    SpeedDialView speedDial;
    ListView productListView;
    TextView textTotal;
    ProductDAO productDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de compras");

        initComponents();
        initListeners();
        updateList();
//        loadSampleItems();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void initComponents() {
        speedDial = findViewById(R.id.activity_main_speedDial);
        productListView = findViewById(R.id.activity_main_list_produtos);
        textTotal = findViewById(R.id.activity_main_texto_total);
        productDAO = new ProductDAO(this);

        speedDial.setMainFabClosedDrawable(getDrawable(R.drawable.ic_menu));
        speedDial.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_add_item, R.drawable.ic_add)
                        .setLabel("Adicionar produto")
                        .setLabelColor(Color.WHITE)
                        .setLabelBackgroundColor(Color.BLACK)
                        .setFabBackgroundColor(getColor(R.color.fab_blue))
                        .create()
        );

        speedDial.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_sort, R.drawable.ic_sort)
                        .setLabel("Ordenar lista")
                        .setLabelColor(Color.WHITE)
                        .setLabelBackgroundColor(Color.BLACK)
                        .setFabBackgroundColor(getColor(R.color.fab_blue))
                        .create()
        );

        speedDial.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_clear, R.drawable.ic_delete)
                        .setLabel("Apagar lista")
                        .setLabelColor(Color.WHITE)
                        .setLabelBackgroundColor(Color.BLACK)
                        .setFabBackgroundColor(getColor(R.color.fab_blue))
                        .create()
        );
    }

    private void initListeners() {
        speedDial.setOnActionSelectedListener(actionItem -> {
            int id = actionItem.getId();
            if (id == R.id.fab_add_item) {
                startAddActivity();
                return false;
            } else if (id == R.id.fab_sort) {
                productDAO.sortList();
                updateList();
                return false;
            } else if (id == R.id.fab_clear) {
                productDAO.clearList();
                updateList();
                return false;
            } else {
                return false;
            }
        });

        productListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });

        productListView.setOnItemLongClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(this)
                    .setTitle("Remoção de item")
                    .setMessage("Confirma a exclusão de " + productDAO.getProduct(position).getName() + "?")
                    .setIcon(R.drawable.ic_delete)
                    .setPositiveButtonIcon(getDrawable(R.drawable.ic_positive))
                    .setNegativeButtonIcon(getDrawable(R.drawable.ic_negative))
                    .setPositiveButton("Sim", (dialog, which) -> {
                        productDAO.removeItem(position);
                        productDAO.saveList();
                        updateList();
                        Toast.makeText(this, "Item removido!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Não", (dialog, which) -> {
                        Toast.makeText(this, "Item não removido!", Toast.LENGTH_SHORT).show();
                    })
                    .create()
                    .show();

            return true;
        });
    }

    private void startAddActivity() {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivity(intent);
    }

    private void updateList() {
        productDAO.loadList();
        productListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                productDAO.getProductList()));

        double total = productDAO.getTotal();
        textTotal.setText(
                String.format(new Locale("pt", "br"), "R$ %.2f", total));
    }

    private void loadSampleItems() {
        productDAO.addItem(new Product("Maçã", 5.99, 4));
        productDAO.addItem(new Product("Laranja", 3.99, 8));
        productDAO.addItem(new Product("Papel Higiênico", 10.99, 1));
        productDAO.addItem(new Product("Condicionador", 9.99, 1));
        productDAO.addItem(new Product("Detergente", 2.49, 3));
        productDAO.addItem(new Product("Esponja", 1.99, 2));
        productDAO.saveList();
    }
}










