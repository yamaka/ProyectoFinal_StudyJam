package com.androidbolivia.proyectofinal_studyjam;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ModifyPresentActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nombreRegaloText;
    private Button update_presentBtn, delete_presentBtn;
    private CheckBox state_regaloCheck;
    private long _id_regalo;
    private boolean _state_regalo;
    private long _id_foreign_person;
    private DBManager dbManager;

    private String titlePerson, descPerson, id_person_foreign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_present);
        dbManager = new DBManager(this);
        dbManager.open();


        nombreRegaloText = (EditText) findViewById(R.id.et_edit_regalo);
        state_regaloCheck = (CheckBox) findViewById(R.id.id_cb_regalo_modify);

        update_presentBtn = (Button) findViewById(R.id.btn_update_regalo);
        delete_presentBtn = (Button) findViewById(R.id.btn_delete_regalo);

        Intent intent = getIntent();
        String id_regalo = intent.getStringExtra("id_regalo");
        String id_foreign_person = intent.getStringExtra("id_foreign_person");
        String state_regalo = intent.getStringExtra("state_regalo");
        String nombre_regalo = intent.getStringExtra("nombre_regalo");


        titlePerson= intent.getStringExtra("title");
        descPerson= intent.getStringExtra("desc");
        id_person_foreign =id_foreign_person;

        _id_regalo= Long.parseLong(id_regalo);
        _id_foreign_person= Long.parseLong(id_foreign_person);
        nombreRegaloText.setText(nombre_regalo);

        _state_regalo= Boolean.parseBoolean(state_regalo);
        state_regaloCheck.setActivated(_state_regalo);

        update_presentBtn.setOnClickListener(this);
        delete_presentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_regalo:
                String nombre_regalo = nombreRegaloText.getText().toString();
                int state_regalo_int=0;
                if(_state_regalo)
                    state_regalo_int=1;
                dbManager.updatePresent(_id_regalo, nombre_regalo,state_regalo_int, (int) _id_foreign_person);
                this.returnHome();
                break;

            case R.id.btn_delete_regalo:
                dbManager.deletePresent(_id_regalo);
                this.returnHome();
                break;
        }
    }
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ModifyPersonActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home_intent.putExtra("id", id_person_foreign);
        home_intent.putExtra("title",titlePerson);
        home_intent.putExtra("desc", descPerson);
        startActivity(home_intent);
    }
}
