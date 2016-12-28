package com.androidbolivia.proyectofinal_studyjam;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddPresentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addTodoBtnPresent;
    private EditText nombre_regaloEditText;
    private CheckBox state_regalo;
    private DBManager dbManager;
    private int _id_foreign_person;
    private String titleEditText, descEditText, id_person_foreign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_present);
        nombre_regaloEditText = (EditText) findViewById(R.id.add_regalo_edittext);
        state_regalo = (CheckBox) findViewById(R.id.id_cb_regalo_add);
        addTodoBtnPresent = (Button) findViewById(R.id.add_regalo_btn);
        dbManager = new DBManager(this);
        dbManager.open();

        Intent intent = getIntent();
        id_person_foreign= intent.getStringExtra("_id_person");
        titleEditText  = intent.getStringExtra("title");
        descEditText = intent.getStringExtra("desc");
        _id_foreign_person = Integer.parseInt(id_person_foreign);
        addTodoBtnPresent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_regalo_btn:

                final String name = nombre_regaloEditText.getText().toString();
                final boolean state_regalo_bool = state_regalo.isActivated();
                int state_regalo_int=0;
                if(state_regalo_bool)
                    state_regalo_int=1;

                dbManager.insertPresent(name, state_regalo_int,_id_foreign_person);

                Intent main = new Intent(AddPresentActivity.this, ModifyPersonActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                main.putExtra("id", id_person_foreign);
                main.putExtra("title", titleEditText);
                main.putExtra("desc", descEditText);

                startActivity(main);
                break;
        }
    }
}
