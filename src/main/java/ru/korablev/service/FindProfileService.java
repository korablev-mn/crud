package ru.korablev.service;

import ru.korablev.model.User;

public interface FindProfileService {
    User findProfile(String login);
}
