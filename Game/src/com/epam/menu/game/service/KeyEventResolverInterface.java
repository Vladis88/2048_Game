package com.epam.menu.game.service;

import com.epam.menu.game.Models.KeyEventResolvedModel;

public interface KeyEventResolverInterface {

    /**
     * This method will resolve key event and return result in KeyEventResolvedModel instance
     * @return KeyEventResolvedModel
     */
    public KeyEventResolvedModel resolve();
}
