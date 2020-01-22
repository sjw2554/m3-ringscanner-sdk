How to use?


1. Add build.gradle > dependencies

        implementation 'com.m3.ringscannersdk:ringscannersdk:0.1.0'



2. Declare RingScannerService and scan callback message

        RingScannerService ringScannerService = new RingScannerService() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                        super.onServiceConnected(name, service);
                        ringScannerService.getCallbackMessage(new ScannerReceiver.Stub() {
                                @Override
                                public void message(final String s) {
                                        // To do
                                }
                        });
                }
        };


3. Bind Service and unbind service

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                ringScannerService.bindService(getApplicationContext());
        }

        @Override
        protected void onDestroy() {
                if (ringScannerService != null) {
                        ringScannerService.unbindService();
                }
                super.onDestroy();
        }



4. Use you want

Readable on / off(true/false)

        ringScannerService.setReadable(true);
      
        
        
Scan start(scan for 3second)

        ringScannerService.startScan();


Default reset

        ringScannerService.defaultReset();


Change bluetooth name

        ringScannerService.setBluetoothName("My Bluetooth");


Set sound volume(0: off, 1: high, 2: mid, 3: low)

        ringScannerService.setSoundVolume(0);


Set sleep time(1-240 minute)

        ringScannerService.setSleepTime(10);


Set end character(0: Enter, 1: Space, 2: tab, 3: none)

        ringScannerService.setEndCharacter(0);


Set prefix("": none)

        ringScannerService.setPrefix("");


Set suffix("": none)

        ringScannerService.setPrefix("");


Get scanner id info

        ringScannerService.getCallbackMessage(new ScannerIDCallback.Stub() {
                @Override
                public void callback(final String s) {
                }
        });


Get scanner battery info(voltage, ratio)

        ringScannerService.getCallbackMessage(new ScannerBatteryCallback.Stub() {
                @Override
                public void callback(final String s) {
                }
        });


Get scanner version and bluetooth version info

        ringScannerService.getCallbackMessage(new ScannerVersionCallback.Stub() {
                @Override
                public void callback(final String s) {
                }
        });





