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

public class RouteManagementActivity extends AppCompatActivity {
    private Message msg;
    private boolean createroute = false;

    private static final String[][] DATA_TO_SHOW = {
            {"R001","Marylebone", "45 minutos", "14" },
            {"R002","West Norwood", "45 minutos", "23" },
            {"R003","Crystal palace", "45 minutos", "15" },
            {"R004","Archway Blackfriars", "45 minutos", "21" },
            {"R005","Canning Town", "45 minutos", "17" },
            {"R006","Aldwych Willesden", "45 minutos", "10" }
    };

    //tableview
    private static final String[] TABLE_HEADERS = { "Código", "Nombre de ruta", "Tiempo requerido", "Paradas" };
    private TableView<String[]> tableView;
    private static SimpleTableDataAdapter tableAdapter;
    private static ArrayList<String[]> tableData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_management);

        msg = new Message();

        tableView = (TableView<String[]>) findViewById(R.id.table_routes);
        //cargar header
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, TABLE_HEADERS));
        //cargar datos
        tableAdapter = new SimpleTableDataAdapter(this, DATA_TO_SHOW);
        tableView.setDataAdapter(tableAdapter);
        //inicializando modelo
        TableColumnWeightModel tableColumnModel = new TableColumnWeightModel( 4 );
        tableColumnModel.setColumnWeight(0, 4);
        tableColumnModel.setColumnWeight(1, 7);
        tableColumnModel.setColumnWeight(2, 6);
        tableColumnModel.setColumnWeight(3, 3);
        tableView.setColumnModel(tableColumnModel);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            createroute = extras.getBoolean("createroute");
        }

        if(createroute){
            msg.showMsg(this,"\n¡Registro de ruta completo!", 1);
        }

    }

    public void redirectRouteInsertActivity(View v) {
            Intent i = new Intent(this, RouteInsertActivity.class);
            startActivity(i);
    }

}