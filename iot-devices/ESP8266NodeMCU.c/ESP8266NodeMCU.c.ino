#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

ESP8266WiFiMulti WiFiMulti;

String AP_NAME = "Mazinho-GVT";
String AP_PASSWORD = "111111";

int device[] = {D0, D1,  D2 , D3 ,D4 , D5, D6, D7};

void setup() {
    Serial.begin(115200);
    delay(10);
    WiFiMulti.addAP(AP_NAME, AP_PASSWORD);
    Serial.print("Wait for WiFi... ");
    delay(100);
    connectWifi();
    delay(500);
}

void connectWifi() {
    while(WiFiMulti.run() != WL_CONNECTED) {
        delay(500);
    }
    Serial.println("");
    Serial.println("WiFi connected");
    Serial.println("IP address: ");
    Serial.println(WiFi.localIP());
}

bool connectServer(WiFiClient client) {
    const uint16_t port = 800;
    const char * host = "192.168.25.6"; // ip or dns
    Serial.print("connecting to ");
    Serial.println(host);
    if (!client.connect(host, port)) {
        Serial.println("connection failed");
        Serial.println("wait 5 sec...");
        delay(5000);
        return false;
    }
    Serial.println("connected");
    return true;
}

void sendState(WiFiClient client) {
  for( int i = 0; i < 8; i++ ) {
    if( deviceState[i] == 1 ) {
      client.print( (char)1 );
    } else {
      client.print( (char)0 );
    }
  }
  client.print('\r');
}

void onDevice(int pos) {
  Serial.print("ON ");
  Serial.println(pos);
  digitalWrite(device[pos], HIGH);
  deviceState[pos] = 1;
}

void offDevice(int pos) {
  Serial.print("OFF ");
  Serial.println(pos);
  digitalWrite(device[pos], LOW);
  deviceState[pos] = 0;
}

void loop() {
    WiFiClient client;
    if( connectServer(client) ) {
      while(1==1) {
        Serial.println("aguardando comando");
        String command = client.readStringUntil('\r');
        char op = command.charAt(0);
        Serial.print("Op: ");
        Serial.println(op);
        if( op == -1 ){
          Serial.print("Waiting");
          delay(500);  
        }
        switch( op ) {
          case '1':
            sendState(client);
            break;
          case '2':
            onDevice(command.charAt(1));
            client.print('0');
            client.print('\r');
            break;
          case '3':
            offDevice(command.charAt(1));
            client.print('0');
            client.print('\r');
            break;
        }
        Serial.println("Finalizando comando");
      }
      Serial.println("closing connection");
      client.stop();    
    }
}

