package com.mhsenpc.v2raybot.xui.services;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class TextReader {
    public String readAllText(String filePath) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded);
    }
}
