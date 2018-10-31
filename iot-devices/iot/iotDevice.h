#ifndef __IOT_DEVICE_H__
#define __IOT_DEVICE_H__

class Device {
    
    private:
        int port;
        
    public:
        Device();
        char* execute(char command);
        void setPort(int _port);
};

#endif
