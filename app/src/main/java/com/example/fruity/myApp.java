package com.example.fruity;

import android.app.Application;

public class myApp extends Application {

    public NetworkingService getNetworkingService() {
        return networkingService;
    }

    DatabaseService dbService = new DatabaseService();

    public DatabaseService getDbService() {
        return dbService;
    }
    private NetworkingService networkingService = new NetworkingService();
    private JsonService jsonService = new JsonService();

    public JsonService getJsonService() {
        return jsonService;
    }
}
