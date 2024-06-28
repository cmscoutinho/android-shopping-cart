package br.edu.unifesspa.listacompras.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import br.edu.unifesspa.listacompras.R;
import br.edu.unifesspa.listacompras.controller.ProductDAO;
import br.edu.unifesspa.listacompras.model.Product;

public class EditActivity extends AppCompatActivity {

    private Button buttonSave;
    private Button buttonClear;
    private Button buttonBack;
    private EditText fieldName;
    private EditText fieldPrice;
    private EditText fieldQuantity;

    private ProductDAO productDAO;
    private Product productAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Editar item");

        initComponents();
        initFields();
        initListeners(productAux);
    }

    private void initComponents() {
        productDAO = new ProductDAO(this);
        fieldName = findViewById(R.id.activity_edit_field_name);
        fieldPrice = findViewById(R.id.activity_edit_field_price);
        fieldQuantity = findViewById(R.id.activity_edit_field_quantity);

        buttonSave = findViewById(R.id.activity_edit_button_save);
        buttonClear = findViewById(R.id.activity_edit_button_clear);
        buttonBack = findViewById(R.id.activity_edit_button_back);

    }

    @NonNull
    private void initFields() {
        productDAO.loadList();
        int position = getIntent().getIntExtra("position", -1);
        productAux = productDAO.getProduct(position);

        fieldName.setText(productAux.getName());
        fieldPrice.setText(Double.toString(productAux.getPrice()));
        fieldQuantity.setText(Integer.toString(productAux.getQuantity()));
    }

    private void initListeners(Product productAux) {
        buttonClear.setOnClickListener(v -> {
            fieldName.setText("");
            fieldPrice.setText("");
            fieldQuantity.setText("");
        });

        buttonSave.setOnClickListener(v -> {
            String name = fieldName.getText().toString();
            double price = Double.parseDouble(fieldPrice.getText().toString());
            int quantity = Integer.parseInt(fieldQuantity.getText().toString());

            productAux.setName(name);
            productAux.setPrice(price);
            productAux.setQuantity(quantity);

            productDAO.saveList();
            finish();
        });

        buttonBack.setOnClickListener(v -> finish());
    }
}


;