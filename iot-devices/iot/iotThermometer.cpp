#include <Arduino.h>
#include "iotThermometer.h"

DeviceThermometer::DeviceThermometer(int _index, int _port) : Device(_index, _port, INPUT) {
    sensor = new OneWire(_port);
}

char* DeviceThermometer::execute(char* command) {
    Serial.print("Thermomether comand: ");
    byte i;
    byte present = 0;
    byte type_s;
    byte data[12];
    byte addr[8];
    float celsius, fahrenheit;
    if ( !sensor->search(addr)) {
      sensor->reset_search();
      delay(250);
      return "ERROR:DeviceThermometer: No more addresses.";
    }
    if (OneWire::crc8(addr, 7) != addr[7]) {
        return "ERROR:DeviceThermometer: CRC is not valid!";
    }
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
            return "ERROR:DeviceThermometer: Device is not a DS18x20 family device!";
    }
    sensor->reset();
    sensor->select(addr);
    sensor->write(0x44);
    delay(1000);
    present = sensor->reset();
    sensor->select(addr);
    sensor->write(0xBE);
    for ( i = 0; i < 9; i++) {
        data[i] = ds.read();
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
    return "OK:" + celsius;
}
