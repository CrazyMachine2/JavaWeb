package services.base;

import services.HashService;

public class HashServiceImpl implements HashService {
    @Override
    public String hash(String password) {
        return "@" + password + "!";
    }
}
