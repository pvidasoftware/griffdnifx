package com.puravida

import griffon.core.test.GriffonUnitRule
import griffon.core.test.TestFor
import org.junit.Rule
import org.junit.Test

import static org.junit.Assert.assertTrue
import static org.junit.Assert.fail

@TestFor(LoginController)
class LoginControllerTest {
    static {
        // force initialization JavaFX Toolkit
        new javafx.embed.swing.JFXPanel()
    }

    private LoginController controller

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule()

    @Test
    void smokeTest() {
        //fail('Not yet implemented!')
        assertTrue(true)
    }
}