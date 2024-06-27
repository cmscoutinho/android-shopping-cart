package br.edu.unifesspa.listacompras.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.unifesspa.listacompras.R;
import br.edu.unifesspa.listacompras.controller.ProductDAO;
import br.edu.unifesspa.listacompras.model.Product;

public class AddItemActivity extends AppCompatActivity {

    Button buttonSave;
    Button buttonClear;
    EditText fieldName;
    EditText fieldPrice;
    EditText fieldQuantity;

    ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        setTitle("Adicionar item");

        initComponents();
        initListeners();
    }

    private void initComponents() {
        productDAO = new ProductDAO(this);
        fieldName = findViewById(R.id.activity_add_item_field_name);
        fieldPrice = findViewById(R.id.activity_add_item_field_price);
        fieldQuantity = findViewById(R.id.activity_add_item_field_quantity);

        buttonSave = findViewById(R.id.activity_add_item_button_save);
        buttonClear = findViewById(R.id.activity_add_item_button_clear);
    }

    private void initListeners() {
        buttonClear.setOnClickListener(v -> {
            fieldName.setText("");
            fieldPrice.setText("");
            fieldQuantity.setText("");
        });

        buttonSave.setOnClickListener(v -> {
            String nome = fieldName.getText().toString();
            double preco = Double.parseDouble(fieldPrice.getText().toString());
            int quantidade = Integer.parseInt(fieldQuantity.getText().toString());

            productDAO.loadList();
            productDAO.addItem(new Product(nome, preco, quantidade));
            productDAO.saveList();
            Toast.makeText(this, nome, Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}


;