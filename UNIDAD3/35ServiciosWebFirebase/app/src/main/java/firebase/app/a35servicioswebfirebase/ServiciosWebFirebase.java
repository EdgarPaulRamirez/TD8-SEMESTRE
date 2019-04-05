package firebase.app.a35servicioswebfirebase;

import com.google.firebase.database.FirebaseDatabase;

public class ServiciosWebFirebase extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Persistencia de datos (Para en caso de que no tenga internet y agrege un dato, este me lo agregue en cuanto me conecte a internet y agregue a firebase)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
