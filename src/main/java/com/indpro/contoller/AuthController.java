package com.indpro.contoller;

import com.indpro.dto.AuthenticationRequest;
import com.indpro.dto.AuthenticationResponse;
import com.indpro.service.IndproUserDetailsService;
import com.indpro.service.UserService;
import com.indpro.util.JwtUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

@CrossOrigin
@RestController
public class AuthController {

    private static final String SECRET_KEY = "123456789";
    private static final String SALTVALUE = "abcdefg";

    private JSONObject jsonObject = null;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IndproUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            jsonObject = getAuthJson(authenticationRequest);
            if (jsonObject != null)
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jsonObject.getString("username"), jsonObject.getString("password")));
            else
                throw new Exception("Failed to authenticate");
            } catch (BadCredentialsException e) {
                throw new BadCredentialsException("Invalid username or password", e);
            } catch (Exception e) {
                System.out.println(e);
            }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jsonObject.getString("username"));
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, userService.findByUsername(userDetails.getUsername()).get()));
    }

    public JSONObject getAuthJson(AuthenticationRequest authenticationRequest) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            String str = new String(cipher.doFinal(Base64.getDecoder().decode(authenticationRequest.getAuthInput())));
            jsonObject = new JSONObject(str);
        } catch (Exception e) {
            System.out.println("Error parsing the auth request " + e);
        }
        return jsonObject;
    }
}
