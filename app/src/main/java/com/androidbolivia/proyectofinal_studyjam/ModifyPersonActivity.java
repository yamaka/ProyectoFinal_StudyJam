package com.androidbolivia.proyectofinal_studyjam;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ModifyPersonActivity extends Activity implements OnClickListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn, addPresentBtn;
    private EditText descText;
    private Long _id;
    private String _id_person;

    private DBManager dbManager;
    private ListView listView;

    private SimpleCursorAdapter adapter;
    private String titlePerson, descPerson;

    final String[] fromPresent = new String[] { DatabaseHelper._ID_PRESENT,
            DatabaseHelper.NAME_PRESENT, DatabaseHelper.STATE_PRESENT, DatabaseHelper._ID_FOREIGN_PERSON};

    final int[] toPresent = new int[] { R.id.id_regalo, R.id.nombre_regalo, R.id.iv_regalo };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Person");

        setContentView(R.layout.activity_modify_person);

        dbManager = new DBManager(this);
        dbManager.open();


        titleText = (EditText) findViewById(R.id.subject_edittext);
        descText = (EditText) findViewById(R.id.description_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);
        addPresentBtn = (Button) findViewById(R.id.add_regalo_btn_Modify );

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        _id_person=id;
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");
        titlePerson=name;
        descPerson=desc;

        _id = Long.parseLong(id);


        titleText.setText(name);
        descText.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        addPresentBtn.setOnClickListener(this);


        Cursor cursor = dbManager.fetchPresents(_id);

        listView = (ListView) findViewById(R.id.list_view_present);


        adapter = new SimpleCursorAdapter(this, R.layout.item_list_regalo, cursor, fromPresent, toPresent, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id_regalo);
                TextView nombreRegaloTextView = (TextView) view.findViewById(R.id.nombre_regalo);
                ImageView img_sate_present= (ImageView) view.findViewById(R.id.iv_regalo);
                //  TextView descTextView = (TextView) view.findViewById(R.id.desc);

                String _id_regalo = idTextView.getText().toString();
                String nombre_regalo = nombreRegaloTextView.getText().toString();
                boolean state_regalo = img_sate_present.isShown();

                // String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyPresentActivity.class);
                modify_intent.putExtra("nombre_regalo", nombre_regalo);
                modify_intent.putExtra("state_regalo", state_regalo);
                modify_intent.putExtra("id_regalo", _id_regalo);
                modify_intent.putExtra("id_foreign_person", _id_person);
                modify_intent.putExtra("title", titlePerson);
                modify_intent.putExtra("desc", descPerson);
                startActivity(modify_intent);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String title = titleText.getText().toString();
                String desc = descText.getText().toString();

                dbManager.update(_id, title, desc);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
            case R.id.add_regalo_btn_Modify:
                Intent add_mem = new Intent(this, AddPresentActivity.class);
                add_mem.putExtra("_id_person", _id_person);
                add_mem.putExtra("title", titleText.getText().toString());
                add_mem.putExtra("desc", descText.getText().toString());

                startActivity(add_mem);
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), PersonListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
