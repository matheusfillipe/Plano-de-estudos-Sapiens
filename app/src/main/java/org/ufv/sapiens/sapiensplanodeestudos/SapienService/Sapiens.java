package org.ufv.sapiens.sapiensplanodeestudos.SapienService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Sapiens extends Service {
    public Sapiens() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
