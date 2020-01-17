package com.m3.ringscanner;


interface ScannerReceiver {
    oneway void message(String aString);
}