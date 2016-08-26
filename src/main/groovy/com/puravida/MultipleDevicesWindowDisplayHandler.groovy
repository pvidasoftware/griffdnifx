package com.puravida

import org.codehaus.griffon.runtime.javafx.DefaultJavaFXWindowDisplayHandler

import javax.annotation.Nonnull
import java.awt.*

import static java.util.Objects.requireNonNull

/**
 * Created by jorge on 9/06/16.
 */
class MultipleDevicesWindowDisplayHandler extends DefaultJavaFXWindowDisplayHandler {

    private static final String ERROR_WINDOW_NULL = "Argument 'window' must not be null";

    @Override
    public void show(@Nonnull String name, @Nonnull javafx.stage.Window window) {
        try {
            centerOnScreen(window)
            super.show(name, window)
        }catch(e){
            println e
        }
    }

    protected static void centerOnScreen(@Nonnull javafx.stage.Window window) {
        requireNonNull(window, ERROR_WINDOW_NULL);

        GraphicsEnvironment ge = GraphicsEnvironment.localGraphicsEnvironment
        GraphicsDevice[] devices = ge.screenDevices
        GraphicsDevice selected = devices.first()
        devices.each { GraphicsDevice gd ->
            if (gd.displayMode.width > selected.displayMode.width) {
                selected = gd
            }
        }

        double w = selected.defaultConfiguration.bounds.width
        double h = selected.defaultConfiguration.bounds.height
        int x = (int) (selected.defaultConfiguration.bounds.x + (w / 2))
        int y = (int) (selected.defaultConfiguration.bounds.y + (h / 2))

        Point corner = new Point(
                (x >= 0 ? x : 0),
                (y >= 0 ? y : 0)
        )

        window.x = corner.x
        window.y = corner.y
    }
}
