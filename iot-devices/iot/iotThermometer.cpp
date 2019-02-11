#include <Arduino.h>
#include "iotThermometer.h"
#include "error.h"

DeviceThermometer::DeviceThermometer(int _index, int _port) : Device(_index, _port, INPUT) {
    sensor = new OneWire(_port);
    Serial.println("Instanciado DeviceThermometer");
}

String DeviceThermometer::execute(String command) {
    Serial.println("Thermomether::execute command: " + command);
    byte i;
    byte present = 0;
    byte type_s;
    byte data[12];
    byte addr[8];
    float celsius, fahrenheit;
    Serial.println("Thermomether::execute 1");
    if ( !sensor->search(addr)) {
        sensor->reset_search();
		delay(250);
        if ( !sensor->search(addr)) {
            sensor->reset_search();
            Serial.println("ERROR:DeviceThermometer: No more addresses.");
            return IOT_ERROR_TERMOMETHER_NO_MORE_ADDRESS;
		}
    }
    Serial.println("Thermomether::execute 2");
    if (OneWire::crc8(addr, 7) != addr[7]) {
        Serial.println("ERROR:DeviceThermometer: CRC is not valid!");
        return IOT_ERROR_TERMOMETHER_INVALID_CRC;
    }
    Serial.println("Thermomether::execute 3");
    switch (addr[0]) {
        case 0x10:
            type_s = 1;
            break;
        case 0x28:
            type_s = 0;
            break;
        case 0x22:
            type_s = 0;
            break;
        default:
            Serial.println("ERROR:DeviceThermometer: Device is not a DS18x20 family device!");
            return IOT_ERROR_TERMOMETHER_INVALID_FAMILY_DEVICE;
    }
    Serial.println("Thermomether::execute 4");
    sensor->reset();
    sensor->select(addr);
    sensor->write(0x44);
    delay(1000);
    present = sensor->reset();
    sensor->select(addr);
    sensor->write(0xBE);
    for ( i = 0; i < 9; i++) {
        data[i] = sensor->read();
    }
    int16_t raw = (data[1] << 8) | data[0];
    if (type_s) {
        raw = raw << 3;
        if (data[7] == 0x10) {
            raw = (raw & 0xFFF0) + 12 - data[6];
        }
    } else {
        byte cfg = (data[4] & 0x60);
        if (cfg == 0x00) raw = raw & ~7;
        else if (cfg == 0x20) raw = raw & ~3;
        else if (cfg == 0x40) raw = raw & ~1;
    }
    celsius = (float)raw / 16.0;
    String s = "OK:" + String(celsius);
    Serial.println(celsius);
    Serial.println(s);
    return s;
}
