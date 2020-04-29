# M3 Ring Scanner SDK

[ ![Download](https://api.bintray.com/packages/sjw2554/sdk/ringscannersdk/images/download.svg?version=0.1.1) ](https://bintray.com/sjw2554/sdk/ringscannersdk/0.1.1/link)

## How to use?

### 1. Add build.gradle > dependencies

        implementation 'com.m3.ringscannersdk:ringscannersdk:0.1.1'



### 2. Declare RingScannerService and scan callback message

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


### 3. Bind Service and unbind service

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



### 4. Use you want

#### Readable on / off(true/false)
        

        ringScannerService.setReadable(true);
      
        
        
#### Scan start(scan for 3second)

        ringScannerService.startScan();


#### Default reset

        ringScannerService.defaultReset();


#### Change bluetooth name

        ringScannerService.setBluetoothName("My Bluetooth");


#### Set sound volume(0: off, 1: high, 2: mid, 3: low)

        ringScannerService.setSoundVolume(0);


#### Set sleep time(1-240 minute)

        ringScannerService.setSleepTime(10);


###### - barcode type by index
        
        
        Code128                     0
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


#### Set barcode type setting(0-46(barcode type by index), true/false)

        ringScannerService.setBarcodeType(5, true);


#### Set transmit code id(0: none, 1: aim code id, 2: symbol code id)

        ringScannerService.setTransmitCode(0);


#### Set end character(0: Enter, 1: Space, 2: tab, 3: none)

        ringScannerService.setEndCharacter(0);


#### Set prefix("": none)

        ringScannerService.setPrefix("");


#### Set suffix("": none)

        ringScannerService.setPrefix("");


#### Get scanner id info

        ringScannerService.getCallbackMessage(new ScannerIDCallback.Stub() {
                @Override
                public void callback(final String s) {
                }
        });


#### Get scanner battery info(voltage, ratio)

        ringScannerService.getCallbackMessage(new ScannerBatteryCallback.Stub() {
                @Override
                public void callback(final String s) {
                }
        });


#### Get scanner version and bluetooth version info

        ringScannerService.getCallbackMessage(new ScannerVersionCallback.Stub() {
                @Override
                public void callback(final String s) {
                }
        });





