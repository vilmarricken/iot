#ifndef __IOT_DEVICE_H__
#define __IOT_DEVICE_H__

class Device {
    
    protected:
        int port;
        
    public:
        Device(int port);
        char* execute(char* command);
        void setPort(int _port);
        int getPort();
};

#endif
