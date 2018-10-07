package com.example.max.hermes;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;

import static android.content.Context.BLUETOOTH_SERVICE;


public class BlueDude {
    private Context context;           // Context so we can snag the bluetooth system service
    private BluetoothManager manager;  // Connection manager
    private BluetoothAdapter adapter;  // Adapter
    private BluetoothLeScanner scanner; // Scanner
    private static final long SCAN_TIME = 10000; // Set a scan time so we don't fuck the battery
    private boolean scanning;
    private Handler handler;
    private ScanCallback scanCallback;
    private ArrayList<android.bluetooth.BluetoothDevice> devices;  // Place to add known clients during a scan

    public BlueDude(Context context) {
        this.context = context;
        this.manager = (BluetoothManager) context.getSystemService(BLUETOOTH_SERVICE);
        this.adapter = manager.getAdapter();
        this.scanner = adapter.getBluetoothLeScanner();
        this.devices = new ArrayList<>();

        // Device scan callback.
        scanCallback = new Callback(devices);
    }

    public void sendMessage(String message) {
        // TODO
    }

    public void listenForMessages() {
        // TODO
    }

    public ArrayList<android.bluetooth.BluetoothDevice> getClientList() {
        return this.devices;
    }


    public boolean startScanning() {
        try {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    scanner.startScan(scanCallback);
                }
            });
        } catch(NullPointerException e) {
            return false;
        }

        return true;
    }

    public boolean stopScanning() {
        try {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    scanner.stopScan(scanCallback);
                }
            });
        } catch(Exception e) {
            return false;
        }

        return true;
    }


    private class Callback extends ScanCallback {
        /*
         * Desc: This is an extention of the ScanCallback class for handling detected clients
         *       after scan completion, with an extension to append any found clients into an
         *       accessible ArrayList.
         *
         */
        private ArrayList<android.bluetooth.BluetoothDevice> devices;

        Callback(ArrayList<android.bluetooth.BluetoothDevice> devices) {
            super();
            this.devices = devices;
        }

        public void onScanResult(int callbackType, ScanResult result) {
            devices.add(result.getDevice());
        }
    }
}