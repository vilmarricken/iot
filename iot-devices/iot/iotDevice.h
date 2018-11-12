#ifndef __IOT_DEVICE_H__
#define __IOT_DEVICE_H__

class Device {
    
    protected:
        int port;
        int index;
        
    public:
        Device(int indice, int port, int mode);
        char* execute(char* command);
};

#endif
