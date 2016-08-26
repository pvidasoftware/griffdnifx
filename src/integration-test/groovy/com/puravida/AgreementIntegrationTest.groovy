package com.puravida

import griffon.javafx.test.GriffonTestFXRule
import org.junit.Rule
import org.junit.Test

import static org.junit.Assert.assertTrue
import static org.junit.Assert.fail

class AgreementIntegrationTest {
    @Rule
    public GriffonTestFXRule testfx = new GriffonTestFXRule('mainWindow')

    @Test
    void smokeTest() {
        //fail('Not implemented yet!')
        assertTrue(true)
    }
}
