package com.m3.ringscanner;

import com.m3.ringscanner.ScannerIDCallback;
import com.m3.ringscanner.ScannerBatteryCallback;
import com.m3.ringscanner.ScannerVersionCallback;
import com.m3.ringscanner.ScannerReceiver;

interface IRingScannerService {
    void setReadable(boolean aBoolean);
    void startScan();
    void defaultReset();
    void setBluetoothName(String aString);
    void setSoundVolume(int anInt);
    void setSleepTime(int anInt);
    void setEndCharacter(int anInt);
    void setPrefix(String aString);
    void setSuffix(String aString);
    void getScannerID(ScannerIDCallback callback);
    void getScannerBattery(ScannerBatteryCallback callback);
    void getScannerVersion(ScannerVersionCallback callback);
    boolean openScannerReceiver(ScannerReceiver message);
    void closeScannerReceiver();
    void setTransmitCode(int anInt);
    void setBarcodeType(int anInt, boolean aBoolean);
}
