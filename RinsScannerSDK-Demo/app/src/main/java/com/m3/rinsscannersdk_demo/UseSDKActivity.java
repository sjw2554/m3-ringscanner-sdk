package com.m3.rinsscannersdk_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.m3.ringscanner.ScannerBatteryCallback;
import com.m3.ringscanner.ScannerIDCallback;
import com.m3.ringscanner.ScannerReceiver;
import com.m3.ringscanner.ScannerVersionCallback;
import com.m3.ringscannersdk.RingScannerService;


public class UseSDKActivity extends AppCompatActivity {

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

    RingScannerService ringScannerService = new RingScannerService() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            super.onServiceConnected(name, service);
            ringScannerService.getCallbackMessage(new ScannerReceiver.Stub() {
                @Override
                public void message(final String s) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Message: " + s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    };

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.readable_on:
                    ringScannerService.setReadable(true);
                    break;
                case R.id.readable_off:
                    ringScannerService.setReadable(false);
                    break;
                case R.id.scan_start:
                    ringScannerService.startScan();
                    break;
                case R.id.default_reset:
                    ringScannerService.defaultReset();
                    break;
                case R.id.bluetooth_name:
                    ringScannerService.setBluetoothName(bluetooth_name_edit.getText() + "");
                    bluetooth_name_edit.setText("");
                    bluetooth_name_edit.clearFocus();
                    break;
                case R.id.sound_volume_off:
                    ringScannerService.setSoundVolume(0);
                    break;
                case R.id.sound_volume_high:
                    ringScannerService.setSoundVolume(1);
                    break;
                case R.id.sound_volume_mid:
                    ringScannerService.setSoundVolume(2);
                    break;
                case R.id.sound_volume_low:
                    ringScannerService.setSoundVolume(3);
                    break;
                case R.id.sleep_time:
                    if ("".equals(sleep_time_edit.getText() + "")) {
                        sleep_time_edit.clearFocus();
                        break;
                    }
                    ringScannerService.setSleepTime(Integer.parseInt(sleep_time_edit.getText() + ""));
                    sleep_time_edit.setText("");
                    break;
                case R.id.barcode_type_enable:
                    if ("".equals(barcode_type_edit.getText() + "")) {
                        barcode_type_edit.clearFocus();
                        break;
                    }
                    ringScannerService.setBarcodeType(Integer.parseInt(barcode_type_edit.getText() + ""), true);
                    barcode_type_edit.setText("");
                    break;
                case R.id.barcode_type_disenable:
                    if ("".equals(barcode_type_edit.getText() + "")) {
                        barcode_type_edit.clearFocus();
                        break;
                    }
                    ringScannerService.setBarcodeType(Integer.parseInt(barcode_type_edit.getText() + ""), false);
                    barcode_type_edit.setText("");
                    break;
                case R.id.transmit_id_none:
                    ringScannerService.setTransmitCode(0);
                    break;
                case R.id.transmit_id_aim:
                    ringScannerService.setTransmitCode(1);
                    break;
                case R.id.transmit_id_symbol:
                    ringScannerService.setTransmitCode(2);
                    break;
                case R.id.end_character_enter:
                    ringScannerService.setEndCharacter(0);
                    break;
                case R.id.end_character_space:
                    ringScannerService.setEndCharacter(1);
                    break;
                case R.id.end_character_tab:
                    ringScannerService.setEndCharacter(2);
                    break;
                case R.id.end_character_none:
                    ringScannerService.setEndCharacter(3);
                    break;
                case R.id.prefix:
                    ringScannerService.setPrefix(prefix_edit.getText() + "");
                    prefix_edit.setText("");
                    prefix_edit.clearFocus();
                    break;
                case R.id.suffix:
                    ringScannerService.setSuffix(suffix_edit.getText() + "");
                    suffix_edit.setText("");
                    suffix_edit.clearFocus();
                    break;
                case R.id.scanner_id:
                    ringScannerService.getCallbackMessage(new ScannerIDCallback.Stub() {
                        @Override
                        public void callback(final String s) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "ID: " + s, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    break;
                case R.id.scanner_battery:
                    ringScannerService.getCallbackMessage(new ScannerBatteryCallback.Stub() {
                        @Override
                        public void callback(final String s) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Battery: " + s, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    break;
                case R.id.scanner_version:
                    ringScannerService.getCallbackMessage(new ScannerVersionCallback.Stub() {
                        @Override
                        public void callback(final String s) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Version: " + s, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_sdk);
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
        ringScannerService.bindService(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        if (ringScannerService != null) {
            ringScannerService.unbindService();
        }
        super.onDestroy();
    }
}
