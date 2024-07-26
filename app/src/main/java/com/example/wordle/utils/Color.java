package com.example.wordle.utils;

public enum Color {
    GRAY(0xFFA4AEC4), RED(0xFFF73131),
    YELLOW(0xFFF3C237), GREEN(0xFF79B851),
    WHITE(0xFFFFFFFF);

    private final int rgbCode;

    Color(int rgbCode) {
        this.rgbCode = rgbCode;
    }

    public int getRgbCode() {
        return rgbCode;
    }
}
