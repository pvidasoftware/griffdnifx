package com.puravida

import griffon.javafx.test.GriffonTestFXRule
import org.junit.Rule
import org.junit.Test

import static org.junit.Assert.fail
import static org.junit.Assert.assertTrue

class RestIntegrationTest {
    @Rule
    public GriffonTestFXRule testfx = new GriffonTestFXRule('mainWindow')

    @Test
    void smokeTest() {
        //fail('Not implemented yet!')
        assertTrue(true)
    }
}
