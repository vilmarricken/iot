#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

ESP8266WiFiMulti WiFiMulti;

WiFiServer server(1001);

int deviceState[] = {0, 0, 0, 0, 0, 0, 0, 0};

int device[] = {D0, D1,  D2 , D3 ,D4 , D5, D6, D7};

int connected = 0;

int counter = 1;

void setup() {
  Serial.begin(115200);
  for( int i = 0; i < 8; i++ ) {
    pinMode(device[i], OUTPUT);
  }
  delay(10);
  WiFiMulti.addAP("Mazinho-GVT", "12345678");
  Serial.println();
  Serial.print("Wait for WiFi... ");
  while(WiFiMulti.run() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  server.begin();
  Serial.println("Server started");
  delay(500);
}

void loop() {
  Serial.print("Connected ");
  Serial.println(connected);
  if( connected == 0 ) {
    const uint16_t port = 1000;
    const char * host = "192.168.25.20"; // ip or dns
    Serial.print("connecting to ");
    Serial.print(host);
    Serial.print(" on port ");
    Serial.println(port);
    WiFiClient client;
    while (!client.connect(host, port)) {
      Serial.println("connection failed");
      Serial.println("wait 5 sec...");
      delay(5000);
      return;
    }
    connected = 1;
    for( int i = 0; i < 8; i++ ) {
      if(deviceState[i] == 1) {
        onDevice(i);
      } else {
        offDevice(i);
      }
    }
    delay(500);
    client.stop();
  }

  WiFiClient conn = server.available();
  if (!conn) {
    //Serial.println("##############################");
    //Serial.println("Aguardando conexao");
    //Serial.println("wait 5 sec...");
    delay(5000);
    return;
  }
  Serial.println("Conexão recebida");
  delay(100);
  String command = conn.readStringUntil('\r');
  int timeout = 0;
  while(command.length() == 0){
    delay(200);
    Serial.println("Tentando ler novamente");
    command = conn.readStringUntil('\r');
    if(timeout++ > 10) {
      break;
    }
  }
  if(command.length() == 0){
    conn.stop();
    return;
  }
  char op = command.charAt(0);
  switch( op ) {
    case '1':
      sendState(conn);
      break;
    case '2':
      onDevice(command.charAt(1) - 48);
      conn.print('0');
      conn.print('\r');
      break;
    case '3':
      offDevice((int)command.charAt(1) - 48);
      conn.print('0');
      conn.print('\r');
      break;
  }
  conn.stop();
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
