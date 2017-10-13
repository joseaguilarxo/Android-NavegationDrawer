package com.joseaguilar.navegationdrawer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.joseaguilar.navegationdrawer.Fragments.P1_FragmentDos;
import com.joseaguilar.navegationdrawer.Fragments.P1_FragmentTres;
import com.joseaguilar.navegationdrawer.Fragments.P1_FragmentUno;
import com.ncapdevi.fragnav.FragNavController;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class P0_NavegationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //--------------------------------------------------------------------
    //EN ESTE APARTADO CONSTRUIREMOS TODA LA LOGICA DONDE SE PODRA HACER USO DE LOS FRAGMENTOS

    //Paso 1 -Fragment: Declaramos la dependencia en build.gradle
    //" compile 'com.ncapdevi:frag-nav:1.0.3' "

    //Paso 2 -Fragment: Declaramos el uso de FragNavController;
    private FragNavController fragNavController;

    //Paso 3 -Fragment: Definir los indices de los fragmentos -- en este ejemplo 3
    private final int TAB_1 = FragNavController.TAB1;
    private final int TAB_2 = FragNavController.TAB2;
    private final int TAB_3 = FragNavController.TAB3;
    private View view;

    //--------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p0__navegation_drawer);
        //Paso 4 -Fragment: crear una arraylist que contendran el indice de fragmentos
        List<android.support.v4.app.Fragment> fragments = new ArrayList<>(3);

        //Paso 5 -Fragment: agregamos los fragmentos a la lista
        fragments.add(P1_FragmentUno.newInstance(0));
        fragments.add(P1_FragmentDos.newInstance(1));
        fragments.add(P1_FragmentTres.newInstance(2));

        //Paso 6 -Fragment: El paso 6 lo realizaremos en los fragmentos y sera crear las nuevas instancias

        //Paso 7 -Fragment: Una vez hecho el paso 6, creamos los links que lanzaran a los fragment al precionarlos
        //En caso les salga error en " Contenedor " es por que deben ir al xml de su actividad y cambiar su ID por Contenedor
        fragNavController = new FragNavController(getSupportFragmentManager(), R.id.Contenedor, fragments);

        //Paso 8 -- Bajar hasta onNavigationItemSelected


        //----------------------------------------------------------
        //Apartado del menu toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //------------------------------------------------------------
        //------------------------------------------------------------------------------
        // Este segmento es la utilizacion del menu boton flotante que estara en la actividad, se le puede brindar acciones
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Esto realizara una accion", Snackbar.LENGTH_LONG)
                     .setAction("Action", null).show();

            }
        });

        //----------------------------------------------------------------------------------
        //EN caso queramos establecer que un fragment se muestre por defecto apenas lancemos el aplicativo
        // se debe de realizar lo siguiente
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.Contenedor, new P1_FragmentUno()).commit();
        //----------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------
        //Esta es la logica de funcion del navegation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //---------------------------------------------------------------------------------------------------

        //----------------------------------------------------------------------------------
        // Este apartado es donde se programa el header del navigation drawer, aca es donde se colocara
        // mediante un sharedpreferences los nombres e imagen del usuario
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //----------------------------------------------------------------------------------
    }//--- fin oncreate

    //----------------------------------------------------------------------------------
    //Este apartado es para saber que hacer cuando presionemos el boton fisico del celular " RETROCEDER"
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //----------------------------------------------------------------------------------
    //Apartado del menu, consume el " p0_navegation_drawer"
    //Con OnCreateOptionMenu  estamos anexando la actividad con el menu
    // Y Con onOptionsItemsSelected brindamos las acciones de los items del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.p0__navegation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

      Toast.makeText(getApplicationContext(),"Settings activo",Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
    //----------------------------------------------------------------------------------

    //Este segmento pertenece a la seleccion de los fragmentos que contenga el proyecto
    //Trabajaran con el menu "activity_p0_navegation_drawer_drawer.xml" , esto de acuerdo al nombre del proyecto

    //En este caso tenemos 3 fragmentos y le daremos una opcion de salir

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Paso 8: Ahora solo nos toca vincular las acciones
        int id = item.getItemId();

        if (id == R.id.nav_fragment1) {
            fragNavController.switchTab(TAB_1);

        } else if (id == R.id.nav_fragment2) {
            fragNavController.switchTab(TAB_2);

        } else if (id == R.id.nav_fragment3) {
            fragNavController.switchTab(TAB_3);

        } else if (id == R.id.nav_opcion1) {
            try {

                final AlertDialog.Builder buil =
                        new AlertDialog.Builder(this);
                buil.setTitle("¿Desea activar notificacion?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(getApplicationContext(), "HOLA MUNDO", Toast.LENGTH_LONG).show();


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                buil.show();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //----------------------------------------------------------------------------------

}
