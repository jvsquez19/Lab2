package com.example.jafethvasquez.labmoviles2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    public static final String DB_NAME = "JuanRa.db";
    public static final String TABLE_NAME = "Sedes";
    public static final String NAME = "name";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String DESCRIPTION = "description";

    public DBManager(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME + "(" + NAME + " text primary key, " + LATITUDE + " text, " + LONGITUDE + " text," +
                DESCRIPTION + " text" + ")");
        // Carga los datos
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addSede(SedeDTO sd){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues reg=new ContentValues();

            reg.put(NAME,sd.getName());
            reg.put(LATITUDE,sd.getLatitude());
            reg.put(LONGITUDE,sd.getLongitude());
            reg.put(DESCRIPTION,sd.getDescription());

            db.insert(TABLE_NAME,null,reg);
            db.close();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public ArrayList<SedeDTO> getListData(){
        String query="SELECT * FROM "+TABLE_NAME;


        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery(query, null);
        ArrayList<SedeDTO> data=new ArrayList<SedeDTO>();

        if(cursor.moveToFirst()){
            do{
                SedeDTO element= new SedeDTO();
                element.setName(cursor.getString(0));
                element.setLatitude(cursor.getDouble(1));
                element.setLongitude(cursor.getDouble(2));
                element.setDescription(cursor.getString(3));
                data.add(element);


            }while(cursor.moveToNext());

        }
        System.out.println(data.size());
        return data;
    }

    public void chargeData(){

        //SAN CARLOS
        addSede(new SedeDTO("Instituto Tecnológico de Costa Rica Sede San Carlos",
                10.362197,-84.509911,
                "Esta sede, ubicada en Santa Clara de San Carlos, en la región tropical húmeda, " +
                        "se encuentra a 105 kilómetros de San José y a 170 metros" +
                        "sobre el nivel del mar, con una temperatura media anual de 26ºC."));

        //CARTAGO
        addSede(new SedeDTO("Instituto Tecnológico de Costa Rica Sede Central",
                9.859636,-83.911884,
                "La Sede Central del Tecnológico de Costa Rica se encuentra en Cartago, " +
                        "una ciudad que se ubica 24 kilómetros al sureste de San José, a una altitud de 1,435 metros " +
                        "sobre el nivel del mar y con un clima tropical húmedo, aunque suele ser más templado debido a su " +
                        "ubicación geográfica y altura, " +
                        "con lluvias moderadas y temperaturas frescas que varían entre 15 y 26 grados centígrados la mayor parte del año.."));

        //LIMON
        addSede(new SedeDTO("Centro Academico Limon",
                9.998576,-83.033637,
                "La provincia de Limón se encuentra en un momento de expansión producto de la inversión pública y privada. " +
                        "La región Huetar Caribe contará con obras de infraestructura que apuntan hacia un desarrollo social, entre ellas:" +
                        " la Megaterminal APM, la nueva refinadora de Recope, la Planta Hidroeléctrica del Reventazón y la ampliación de la ruta 32."));

        //ALAJUELA

        addSede(new SedeDTO("Centro Academico Alajuela",
                10.019891,-84.197260,
                "El Centro Académico se ubica en Desamparados de Alajuela, " +
                        "a 21 kilómetros de San José. Se trata de una localidad en la región intertropical, " +
                        "a 960 metros sobre el nivel del mar y con una temperatura media anual de 22,3ºC. " +
                        "La precipitación pluvial media es de 2.069 mm. anuales, con una estación seca de enero a " +
                        "mediados de marzo y una estación lluviosa de mediados de marzo a diciembre."));
        //SAN JOSE
        addSede(new SedeDTO("Centro Academico San Jose",
                9.938130,-84.075369,
                "El Centro Académico de San José se ubica en Barrio Amón, " +
                        "entre calles 5 y 7 y entre avenidas 9 y 11.  Sus aulas, oficinas " +
                        "administrativas, biblioteca y áreas recreativas se distribuyen entre edificios " +
                        "nuevos y casas antiguas con el valor histórico propio del barrio."));




    }
}
