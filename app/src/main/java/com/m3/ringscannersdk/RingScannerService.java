package com.m3.ringscannersdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.m3.ringscanner.IRingScannerService;
import com.m3.ringscanner.ScannerBatteryCallback;
import com.m3.ringscanner.ScannerIDCallback;
import com.m3.ringscanner.ScannerReceiver;
import com.m3.ringscanner.ScannerVersionCallback;


public class RingScannerService implements ServiceConnection {

    private IRingScannerService iRingScannerService;
    private Context context;
    private boolean isScannerReceiverOpened;

    public RingScannerService(Context context) {
        this.context = context;
        Intent intent = new Intent("com.m3.ringscanner.aidl");
        intent.setPackage("com.m3.ringscanner");
        context.bindService(intent, this, Context.BIND_AUTO_CREATE | Context.BIND_ABOVE_CLIENT);
    }

    private boolean isConnected() {
        return iRingScannerService != null;
    }

    public void disconnect() {
        context.unbindService(this);
    }

    public void setReadable(boolean aBoolean) {
        if (isConnected()) {
            try {
                iRingScannerService.setReadable(aBoolean);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void startScan() {
        if (isConnected()) {
            try {
                iRingScannerService.startScan();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void defaultReset() {
        if (isConnected()) {
            try {
                iRingScannerService.defaultReset();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setBluetoothName(String aString) {
        if (isConnected()) {
            try {
                iRingScannerService.setBluetoothName(aString);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSoundVolume(int anInt) {
        if (isConnected()) {
            try {
                iRingScannerService.setSoundVolume(anInt);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSleepTime(int anInt) {
        if (isConnected()) {
            try {
                iRingScannerService.setSleepTime(anInt);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setEndCharacter(int anInt) {
        if (isConnected()) {
            try {
                iRingScannerService.setEndCharacter(anInt);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPrefix(String aString) {
        if (isConnected()) {
            try {
                iRingScannerService.setPrefix(aString);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSuffix(String aString) {
        if (isConnected()) {
            try {
                iRingScannerService.setSuffix(aString);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCallbackMessage(ScannerIDCallback callback) {
        if (isConnected()) {
            try {
                iRingScannerService.getScannerID(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCallbackMessage(ScannerBatteryCallback callback) {
        if (isConnected()) {
            try {
                iRingScannerService.getScannerBattery(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCallbackMessage(ScannerVersionCallback callback) {
        if (isConnected()) {
            try {
                iRingScannerService.getScannerVersion(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCallbackMessage(ScannerReceiver message) {
        if (isConnected()) {
            try {
                isScannerReceiverOpened = iRingScannerService.openScannerReceiver(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        if (service != null) {
            iRingScannerService = IRingScannerService.Stub.asInterface(service);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        if (isScannerReceiverOpened) {
            try {
                iRingScannerService.closeScannerReceiver();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        iRingScannerService = null;
    }
}