package com.example.fruity;

import android.app.Application;

public class myApp extends Application {

    public NetworkingService getNetworkingService() {
        return networkingService;
    }
    private final NetworkingService networkingService = new NetworkingService();

    public DatabaseService getDbService() {
        return dbService;
    }
    private final DatabaseService dbService = new DatabaseService();

    public JsonService getJsonService() {
        return jsonService;
    }
    private final JsonService jsonService = new JsonService();
}

