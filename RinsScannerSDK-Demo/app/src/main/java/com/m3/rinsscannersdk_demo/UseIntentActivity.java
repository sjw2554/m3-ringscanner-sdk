package com.m3.rinsscannersdk_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class UseIntentActivity extends AppCompatActivity {

    /*
    barcodeType,            index
    Code39                      1
    Code93                      2
    UPC-A                       3
    UPC-E                       4
    UPC-E1 Composite            5
    EAN-8                       6
    EAN-13                      7
    EAN-13 Bookland EAN         8
    EAN-13 ISSN EAN             9
    GS1-128 Composite           10
    ISBT 128                    11
    Trioptic Code39             12
    Code39 Prefix               13
    Code39 Full ASCII           14
    Code11                      15
    Interleaved 2of5            16
    Interleaved 2of5 Reduced    17
    Discrete 2of5               18
    Codabar                     19
    CLSI Editing                20
    NOTIS Editing               21
    MSI                         22
    Chinese 2of5                23
    Matrix 2of5                 24
    GS1 DataBar                 25
    GS1 DataBar Limited         26
    GS1 DataBar Expanded        27
    GS1-128 CC-C                28
    GS1-128 CC-A, B             29
    TLC39                       30
    PDF417                      31
    Micro PDF417                32
    Code128 Emulation           33
    Data Matrix                 34
    Maxi Code                   35
    QR Code                     36
    Micro QR                    37
    Aztec                       38
    HanXin                      39
    US Postnet                  40
    US Planet                   41
    UK Postal                   42
    Japan Postal                43
    Australia                   44
    Kix Code                    45
    UPU FICS Postal             46
    */

    EditText bluetooth_name_edit, sleep_time_edit, barcode_type_edit, prefix_edit, suffix_edit;

    BroadcastReceiver ringScannerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ConstantValues.RING_SCANNER_ACTION_BARCODE.equals(intent.getAction()) && intent.getExtras() != null) {
                Toast.makeText(getApplicationContext(), "Message: " + intent.getExtras().getString(ConstantValues.RING_SCANNER_EXTRA_BARCODE_DATA), Toast.LENGTH_SHORT).show();
            } else if (ConstantValues.RING_SCANNER_ACTION_ID_RESPONSE.equals(intent.getAction()) && intent.getExtras() != null) {
                Toast.makeText(getApplicationContext(), "ID: " + intent.getExtras().getString(ConstantValues.RING_SCANNER_EXTRA_GET_ID), Toast.LENGTH_SHORT).show();
            } else if (ConstantValues.RING_SCANNER_ACTION_BATTERY_RESPONSE.equals(intent.getAction()) && intent.getExtras() != null) {
                Toast.makeText(getApplicationContext(), "Battery: " + intent.getExtras().getString(ConstantValues.RING_SCANNER_EXTRA_GET_BATTERY), Toast.LENGTH_SHORT).show();
            } else if (ConstantValues.RING_SCANNER_ACTION_VERSION_RESPONSE.equals(intent.getAction()) && intent.getExtras() != null) {
                Toast.makeText(getApplicationContext(), "Version: " + intent.getExtras().getString(ConstantValues.RING_SCANNER_EXTRA_GET_VERSION), Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener clickListener = new View.OnClickListener() {

        Intent settingIntent = new Intent(ConstantValues.RING_SCANNER_ACTION_SETTING_CHANGE);

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.readable_on:
                    settingIntent.putExtra("setting", "setReadable");
                    settingIntent.putExtra("readable", true);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.readable_off:
                    settingIntent.putExtra("setting", "setReadable");
                    settingIntent.putExtra("readable", false);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.scan_start:
                    settingIntent.putExtra("setting", "startScan");
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.default_reset:
                    settingIntent.putExtra("setting", "defaultReset");
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.bluetooth_name:
                    settingIntent.putExtra("setting", "setBluetoothName");
                    settingIntent.putExtra("bluetoothName", bluetooth_name_edit.getText() + "");
                    sendOrderedBroadcast(settingIntent, null);
                    bluetooth_name_edit.setText("");
                    bluetooth_name_edit.clearFocus();
                    break;
                case R.id.sound_volume_off:
                    settingIntent.putExtra("setting", "setSoundVolume");
                    settingIntent.putExtra("soundVolume", 0);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.sound_volume_high:
                    settingIntent.putExtra("setting", "setSoundVolume");
                    settingIntent.putExtra("soundVolume", 1);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.sound_volume_mid:
                    settingIntent.putExtra("setting", "setSoundVolume");
                    settingIntent.putExtra("soundVolume", 2);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.sound_volume_low:
                    settingIntent.putExtra("setting", "setSoundVolume");
                    settingIntent.putExtra("soundVolume", 3);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.sleep_time:
                    if ("".equals(sleep_time_edit.getText() + "")) {
                        sleep_time_edit.clearFocus();
                        break;
                    }
                    settingIntent.putExtra("setting", "setSleepTime");
                    settingIntent.putExtra("sleepTime", Integer.parseInt(sleep_time_edit.getText() + ""));
                    sendOrderedBroadcast(settingIntent, null);
                    sleep_time_edit.setText("");
                    break;
                case R.id.barcode_type_enable:
                    if ("".equals(barcode_type_edit.getText() + "")) {
                        barcode_type_edit.clearFocus();
                        break;
                    }
                    settingIntent.putExtra("setting", "setBarcodeType");
                    settingIntent.putExtra("barcodeTypeIndex", Integer.parseInt(barcode_type_edit.getText() + ""));
                    settingIntent.putExtra("barcodeTypeValue", true);
                    barcode_type_edit.setText("");
                    break;
                case R.id.barcode_type_disenable:
                    if ("".equals(barcode_type_edit.getText() + "")) {
                        barcode_type_edit.clearFocus();
                        break;
                    }
                    settingIntent.putExtra("setting", "setBarcodeType");
                    settingIntent.putExtra("barcodeTypeIndex", Integer.parseInt(barcode_type_edit.getText() + ""));
                    settingIntent.putExtra("barcodeTypeValue", false);
                    sendOrderedBroadcast(settingIntent, null);
                    barcode_type_edit.setText("");
                    break;
                case R.id.transmit_id_none:
                    settingIntent.putExtra("setting", "setTransmitCode");
                    settingIntent.putExtra("transmitCode", 0);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.transmit_id_aim:
                    settingIntent.putExtra("setting", "setTransmitCode");
                    settingIntent.putExtra("transmitCode", 1);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.transmit_id_symbol:
                    settingIntent.putExtra("setting", "setTransmitCode");
                    settingIntent.putExtra("transmitCode", 2);
                    sendOrderedBroadcast(settingIntent, null);
                    break;

                case R.id.end_character_enter:
                    settingIntent.putExtra("setting", "setEndCharacter");
                    settingIntent.putExtra("endCharacter", 0);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.end_character_space:
                    settingIntent.putExtra("setting", "setEndCharacter");
                    settingIntent.putExtra("endCharacter", 1);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.end_character_tab:
                    settingIntent.putExtra("setting", "setEndCharacter");
                    settingIntent.putExtra("endCharacter", 2);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.end_character_none:
                    settingIntent.putExtra("setting", "setEndCharacter");
                    settingIntent.putExtra("endCharacter", 3);
                    sendOrderedBroadcast(settingIntent, null);
                    break;
                case R.id.prefix:
                    settingIntent.putExtra("setting", "setPrefix");
                    settingIntent.putExtra("prefix", prefix_edit.getText() + "");
                    sendOrderedBroadcast(settingIntent, null);
                    prefix_edit.setText("");
                    prefix_edit.clearFocus();
                    break;
                case R.id.suffix:
                    settingIntent.putExtra("setting", "setSuffix");
                    settingIntent.putExtra("suffix", suffix_edit.getText() + "");
                    sendOrderedBroadcast(settingIntent, null);
                    suffix_edit.setText("");
                    suffix_edit.clearFocus();
                    break;
                case R.id.scanner_id:
                    sendOrderedBroadcast(new Intent(ConstantValues.RING_SCANNER_ACTION_ID), null);
                    break;
                case R.id.scanner_battery:
                    sendOrderedBroadcast(new Intent(ConstantValues.RING_SCANNER_ACTION_BATTERY), null);
                    break;
                case R.id.scanner_version:
                    sendOrderedBroadcast(new Intent(ConstantValues.RING_SCANNER_ACTION_VERSION), null);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_intent);
        bluetooth_name_edit = findViewById(R.id.bluetooth_name_edit);
        sleep_time_edit = findViewById(R.id.sleep_time_edit);
        barcode_type_edit = findViewById(R.id.barcode_type_edit);
        prefix_edit = findViewById(R.id.prefix_edit);
        suffix_edit = findViewById(R.id.suffix_edit);
        findViewById(R.id.readable_on).setOnClickListener(clickListener);
        findViewById(R.id.readable_off).setOnClickListener(clickListener);
        findViewById(R.id.scan_start).setOnClickListener(clickListener);
        findViewById(R.id.default_reset).setOnClickListener(clickListener);
        findViewById(R.id.bluetooth_name).setOnClickListener(clickListener);
        findViewById(R.id.sound_volume_off).setOnClickListener(clickListener);
        findViewById(R.id.sound_volume_high).setOnClickListener(clickListener);
        findViewById(R.id.sound_volume_mid).setOnClickListener(clickListener);
        findViewById(R.id.sound_volume_low).setOnClickListener(clickListener);
        findViewById(R.id.sleep_time).setOnClickListener(clickListener);
        findViewById(R.id.barcode_type_enable).setOnClickListener(clickListener);
        findViewById(R.id.barcode_type_disenable).setOnClickListener(clickListener);
        findViewById(R.id.transmit_id_none).setOnClickListener(clickListener);
        findViewById(R.id.transmit_id_aim).setOnClickListener(clickListener);
        findViewById(R.id.transmit_id_symbol).setOnClickListener(clickListener);
        findViewById(R.id.end_character_enter).setOnClickListener(clickListener);
        findViewById(R.id.end_character_space).setOnClickListener(clickListener);
        findViewById(R.id.end_character_tab).setOnClickListener(clickListener);
        findViewById(R.id.end_character_none).setOnClickListener(clickListener);
        findViewById(R.id.prefix).setOnClickListener(clickListener);
        findViewById(R.id.suffix).setOnClickListener(clickListener);
        findViewById(R.id.scanner_id).setOnClickListener(clickListener);
        findViewById(R.id.scanner_battery).setOnClickListener(clickListener);
        findViewById(R.id.scanner_version).setOnClickListener(clickListener);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConstantValues.RING_SCANNER_ACTION_BARCODE);
        intentFilter.addAction(ConstantValues.RING_SCANNER_ACTION_ID_RESPONSE);
        intentFilter.addAction(ConstantValues.RING_SCANNER_ACTION_BATTERY_RESPONSE);
        intentFilter.addAction(ConstantValues.RING_SCANNER_ACTION_VERSION_RESPONSE);
        registerReceiver(ringScannerReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(ringScannerReceiver);
        super.onDestroy();
    }
}
