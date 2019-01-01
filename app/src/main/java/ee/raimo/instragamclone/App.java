package ee.raimo.instragamclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("PAifNrNuCBR6r23gwoHOyg4HO1NVA3AgB4ecQQRs")
                // if defined
                .clientKey("l2zCn01w2cmC04fz8u02ii5hxv9eHST4Gl3HdvQs")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    };
}
