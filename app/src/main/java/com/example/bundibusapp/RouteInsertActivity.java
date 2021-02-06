package com.example.bundibusapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.model.TableColumnWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import com.example.bundibusapp.adapters.SimpleTableHeaderAdapter;
import com.example.bundibusapp.components.Message;

import java.util.ArrayList;

public class RouteInsertActivity extends AppCompatActivity {
    private Message msg;

    private static final String[][] DATA_TO_SHOW = {
            {"P01","White marsh park & ride" },
            {"P02","Ride bay" },
            {"P03","White marsh mall" },
            {"P04","Sandpiper sir" },
            {"P05","Corporate Dr & Comcast" }
    };

    private EditText edit_code;
    private EditText edit_route_name;
    private EditText edit_time;

    //tableview
    private static final String[] TABLE_HEADERS = { "Id", "Nombre de parada" };
    private TableView<String[]> tableView;
    private static SimpleTableDataAdapter tableAdapter;
    private static ArrayList<String[]> tableData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_insert);

        msg = new Message();

        edit_code = (EditText)findViewById(R.id.edit_code);
        edit_route_name = (EditText)findViewById(R.id.edit_route_name);
        edit_time = (EditText)findViewById(R.id.edit_time);

        tableView = (TableView<String[]>) findViewById(R.id.table_parades);
        //cargar header
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS));
        //cargar datos
        tableAdapter = new SimpleTableDataAdapter(this, DATA_TO_SHOW);
        tableView.setDataAdapter(tableAdapter);
        //inicializando modelo
        TableColumnWeightModel tableColumnModel = new TableColumnWeightModel( 2 );
        tableColumnModel.setColumnWeight(0, 1);
        tableColumnModel.setColumnWeight(1, 4);
        tableView.setColumnModel(tableColumnModel);


        edit_code.setText("RT003");
        edit_route_name.setText("White marsh");
        edit_time.setText("45 mins.");
    }

    public void redirectRouteManagementActivity(View v) {
        if(isEmpty(edit_code) || isEmpty(edit_route_name) || isEmpty(edit_time)){
            msg.showMsg(this,"\nNo es posible completar el registro de la ruta. Verifique si alguno de los campos obligatorios (*) esta vacio.", 2);
        }
        else{
            Intent i = new Intent(this, RouteManagementActivity.class);
            boolean response = true;
            i.putExtra("createroute", response);
            startActivity(i);
        }

    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }
}