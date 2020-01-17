How to use?

Start and scan message receiver
RingScannerService ringScannerService = new RingScannerService(getApplicationContext()) {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        super.onServiceConnected(name, service);
        ringScannerService.getCallbackMessage(new ScannerReceiver.Stub() {
            @Override
            public void message(final String s) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this, "scann message: " + s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
};


Readable on / off
ringScannerService.setReadable(true);
ringScannerService.setReadable(false);

Scan start
ringScannerService.startScan();

Default reset
ringScannerService.defaultReset();

Bluetooth name
ringScannerService.setBluetoothName("My Bluetooth");

Sound volume(0: off, 1: high, 2: mid, 3: low)
ringScannerService.setSoundVolume(0);
ringScannerService.setSoundVolume(1);
ringScannerService.setSoundVolume(2);
ringScannerService.setSoundVolume(3);

Sleep time(0-240)
ringScannerService.setSleepTime(10);

End character(0: Enter, 1: Space, 2: tab, 3: none)
ringScannerService.setEndCharacter(0);
ringScannerService.setEndCharacter(1);
ringScannerService.setEndCharacter(2);
ringScannerService.setEndCharacter(3);

Prefix("": none)
ringScannerService.setPrefix("");

Suffix("": none)
ringScannerService.setPrefix("");

Scanner id
ringScannerService.getCallbackMessage(new ScannerIDCallback.Stub() {
    @Override
    public void callback(final String s) {
    }
});

Scanner battery
ringScannerService.getCallbackMessage(new ScannerBatteryCallback.Stub() {
    @Override
    public void callback(final String s) {
    }
});

Scanner version
ringScannerService.getCallbackMessage(new ScannerVersionCallback.Stub() {
    @Override
    public void callback(final String s) {
    }
});





