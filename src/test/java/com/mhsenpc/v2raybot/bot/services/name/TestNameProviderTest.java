package com.mhsenpc.v2raybot.bot.services.name;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestNameProviderTest {

    @Autowired
    private TestNameProvider testNameProvider;

    private List<String> disallowedCharacters;


    @BeforeEach
    void setUp() {
        disallowedCharacters = List.of("[", "{", ".", "/", "@");
    }

    @Test
    void getName() {
        String name = testNameProvider.getName();

        assertTrue(name.startsWith("test_"));

        assertEquals(11, name.length());

        for (String word : disallowedCharacters){
            assertFalse(name.contains(word));
        }
    }
}