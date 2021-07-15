package com.mokhs.springplayground.IoC;

public class Encoder implements IEncoder {
    private IEncoder iEncoder;

    public Encoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    @Override
    public String encode(String message) {
        return iEncoder.encode(message);
    }

    public void setiEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }
}
