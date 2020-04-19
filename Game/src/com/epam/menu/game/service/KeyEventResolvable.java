package com.epam.menu.game.service;

public interface KeyEventResolvable {

    /**
     * This method will resolve key event and return result in KeyEventResolvedModel instance
     * @return KeyEventResolvedModel
     */
    public KeyEventResolvedModel resolve();
}
