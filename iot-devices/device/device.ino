#include <EEPROM.h>
#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

ESP8266WiFiMulti WiFiMulti;

WiFiServer server(1000);

short connectionWasAlive = -1;
short connectionWasRegistred = 0;

int addr = 0;

struct IOT {
  String ssid = "Mazinho-GVT";
  String pass = "12345678";
  String server = "192.168.25.20";
  String id;
};

byte version = byte(1);

IOT iot;

void setup()
{
  Serial.begin(115200);
  Serial.println();
  EEPROM.begin(128);
  readConfigurations();
}

void loop()
{
  if( !connectWiFi() ){
    return;
  }
  if ( !registryDevice() ) {
	  return;
  }
}

void readConfigurations() {
  addr = 0;
  Serial.print("Lendo EEPROM: ");
  iot.ssid = readString(iot.ssid);
  iot.pass = readString(iot.pass);
  iot.server = readString(iot.server);
  iot.id = readString(iot.id);
  Serial.print("  SSID: ");
  Serial.println(iot.ssid);
  Serial.print("  PASS: ");
  Serial.println(iot.pass);
  Serial.print("SERVER: ");
  Serial.println(iot.server);
  Serial.print("    ID: ");
  Serial.println(iot.id);
}

void writeConfigurations() {
  addr = 0;
  Serial.println("Gravando EEPROM");
  writeString(iot.ssid);
  writeString(iot.pass);
  writeString(iot.server);
  writeString(iot.id);
}

void clearConfigurations() {
  Serial.println("Limpando EEPROM");
  for (int i = 0 ; i < 128 ; i++) {
    EEPROM.write(i, 0);
  }
  readConfigurations();
}

bool connectWiFi() {
  while(WiFiMulti.run() == WL_CONNECTED) {
    if( connectionWasAlive != 1 ) {
      connectionWasAlive = 1;
      Serial.print("IP Address: ");
      Serial.print(WiFi.localIP());
      Serial.print(" gateway: ");
      Serial.print(WiFi.gatewayIP());
      Serial.print(" subnet: ");
      Serial.print(WiFi.subnetMask());
      Serial.println("");
    }
    return true; 
  }
  if( connectionWasAlive == -1 ) {
    WiFiMulti.addAP(iot.ssid, iot.pass);
  }
  connectionWasAlive = 0;
  Serial.println("Wait for WiFi... ");
  delay(500);
  return false;
}

bool registryDevice() {
  if( connectionWasRegistred != 1 ) {
    const uint16_t port = 800;
    const char *host = "iot-server"; // ip or dns
    Serial.print("connecting to ");
    Serial.println(host);
    WiFiClient client = new WiFiClient();
    if (!client.connect(host, port)) {
        Serial.println("connection failed");
        Serial.println("wait 1 sec...");
        delay(1000);
        return false;
    }
	connectionWasRegistred = 1;
    Serial.println("connected");
	delay(500);
	client.close();
    return true;
  }
}

void writeString( String text ) {
  int t = text.length();
  Serial.print("Length: ");
  Serial.println(t);
  EEPROM.write(addr++, t);
  for( int i = 0; i < t; i++ ) {
    EEPROM.write(addr++, text.charAt(i));
  }
}

String readString(String defaultValue) {
  byte t;
  String s;
  t = EEPROM.read(addr++);
  if( t != 0 && t != 255 ) {
    Serial.print("Length: ");
    Serial.println(t);
    byte value;
    for( int i = 0; i < t; i++ ) {
      value = EEPROM.read(addr++);
      boolean b = s.concat((char)value);
      Serial.print("Add: ");
      Serial.print((char)value);
      Serial.print(" - ");
      Serial.println(b);
    }
    Serial.print("Returning value: ");
    Serial.println(s);
    return s;
  }
  Serial.print("Returning default value: ");
  Serial.println(defaultValue);
  return defaultValue;
}

