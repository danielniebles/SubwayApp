package com.danielniebles.subwayapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Daniel on 12/10/2016.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private String DATA_BASE_NAME = "AgendaBD";
    private int DATA_VERSION = 1;

    String sqlCreate = "CREATE TABLE Usuarios(" +
            "id             INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "usuario        TEXT," +
            "contraseña     TEXT," +
            "email          TEXT," +
            "sexo           TEXT," +
            "fecha          TEXT)";

    String sqlCreate2 = "CREATE TABLE Productos(" +
            "idProducto     INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre         TEXT," +
            "descripcion    TEXT," +
            "precio         TEXT," +
            "imagen         INT)";

    String sqlCreate3 = "CREATE TABLE MisFavoritos(" +
            "idFavorito      INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idUsuario       INT," +
            "idProducto      INT)";


    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL(sqlCreate3);

        db.execSQL(sqlCreate2);
        db.execSQL("INSERT INTO Productos VALUES(null, 'Los ligeros', 'Sánduches con 6 gramos de grasa, o menos', " +
                "'$7000', '"+R.drawable.publicidad2+"')");
        db.execSQL("INSERT INTO Productos VALUES(null, 'Diarios', 'Todos los días un sub diferente!', " +
                "'Desde $7000', '"+R.drawable.publicidad3+"')");
        db.execSQL("INSERT INTO Productos VALUES(null, 'Nueva adición', 'Prueba YA la nueva adición de guacamole!', " +
                "'$2000', '"+R.drawable.promocion3+"')");
        db.execSQL("INSERT INTO Productos VALUES(null, 'Nuevo Baratísimo', 'Sub de pollo apanado', " +
                "'$4700', '"+R.drawable.promocion2+"')");
        db.execSQL("INSERT INTO Productos VALUES(null, 'Nuevos combos!', 'Sorteos semanales', " +
                "'Desde $4000', '"+R.drawable.promocion5+"')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL(sqlCreate);
        db.execSQL("DROP TABLE IF EXISTS Productos");
        db.execSQL(sqlCreate2);
        db.execSQL("DROP TABLE IF EXISTS MisFavoritos");
        db.execSQL(sqlCreate3);
    }
}
