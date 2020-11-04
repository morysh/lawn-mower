package com.morysh.lawnmower.services;

public class PlainTextInputFileService implements InputFileService {
    private PlainTextInputFileService instance;

    private PlainTextInputFileService() {
    }

    // Singleton implementation could have been done with a framework (eg. Spring) but it seems overkill for a small project like this
    public PlainTextInputFileService getInstance() {
        if (instance == null) {
            instance = new PlainTextInputFileService();
        }

        return instance;
    }
}
