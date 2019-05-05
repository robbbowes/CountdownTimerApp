package com.example.countdowntimerapp;

import android.widget.SeekBar;

import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends MainActivity{
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void time_isDisplayedCorrectly() {
        int i = 367;
        String expectedString = "06:07";
        String actualString = displayTimeCorrectly(i);
        assertEquals("Expected " + expectedString + ", but actually " + actualString, expectedString, actualString);
    }

}