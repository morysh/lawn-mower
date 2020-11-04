package com.morysh.lawnmower.services;

public class SimpleMowersService implements MowersService {
    private SimpleMowersService instance = new SimpleMowersService();

    private SimpleMowersService() {
    }

    // Singleton implementation could have been done with a framework (eg. Spring) but it seems overkill for a small project like this
    public SimpleMowersService getInstance() {
        if (instance == null) {
            instance = new SimpleMowersService();
        }
        return instance;
    }
}
