package com.tsofen.agsenceapp.dataAdapters;

public class SettingsDataAdapter {
    private static SettingsDataAdapter instance;
    private SettingsDataAdapter() {}
    public static SettingsDataAdapter getInstance() {
        if (instance == null)
            instance = new SettingsDataAdapter();
        return instance;
    }
}
