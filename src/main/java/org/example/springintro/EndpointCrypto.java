
//Uppgift
//Implementera två endpoints:
//
/// Encrypt Tar emot en text eller ord och returnerar en krypterad version.
//
///Decrypt Tar emot den krypterade texten och återställer den till originalform.
//
//Metod: Välj själv en algoritm, till exempel Caesar-chiffer, Rövarspråket eller en egen påhittad logik.




package org.example.springintro; // Paketnamn för projektet

import org.springframework.web.bind.annotation.*; // Importerar Spring annotations för REST API
import org.springframework.http.HttpStatus; // För att kunna returnera rätt HTTP-status
import org.springframework.web.server.ResponseStatusException; // För snygg felhantering

import java.util.Base64; // För Base64 encoding/decoding
import java.nio.charset.StandardCharsets; // För att säkerställa rätt teckenkodning

@RestController // Markerar att detta är en REST-controller
@RequestMapping("/") // Bas-URL för alla endpoints i denna klass
public class EndpointCrypto {

    private static final String SECRET = "mySecretKey"; // "hemlig" nyckel som läggs till
    // (OBS: inte säker i verkligheten)

    @PostMapping("/encrypt") // Hanterar POST requests till /encrypt
    public String encrypt(@RequestBody String input) { // Tar emot data i request body
        String combined = input + SECRET; // Lägger till secret i slutet av input
        // Kodar resultatet till Base64 och returnerar det
        return Base64.getEncoder().encodeToString(combined.getBytes(StandardCharsets.UTF_8));
    }

    @GetMapping("/encrypt") // Gör det möjligt att testa via webbläsare (GET)
    public String encryptGet(@RequestParam String input) { // Tar input som query parameter
        return encrypt(input); // Återanvänder POST-metoden
    }

    @PostMapping("/decrypt") // Hanterar POST requests till /decrypt
    public String decrypt(@RequestBody String encrypted) { // Tar emot Base64-sträng

        byte[] decodedBytes = Base64.getDecoder().decode(encrypted); // Avkodar Base64 till bytes
        String decoded = new String(decodedBytes, StandardCharsets.UTF_8); // Gör om bytes till String

        // Kontrollerar att strängen slutar med vår secret
        if (decoded.endsWith(SECRET)) {
            // Tar bort secret och returnerar originaltexten
            return decoded.substring(0, decoded.length() - SECRET.length());
        } else {
            // Om datan inte är giltig returneras ett tydligt HTTP-fel (400 Bad Request)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid encrypted data");
        }
    }

    @GetMapping("/decrypt") // Gör det möjligt att testa via webbläsare (GET)
    public String decryptGet(@RequestParam String data) { // Tar Base64-sträng som query parameter
        return decrypt(data); // Återanvänder POST-metoden
    }
}