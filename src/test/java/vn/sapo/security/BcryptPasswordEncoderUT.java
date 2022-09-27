package vn.sapo.security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertTrue;

public class BcryptPasswordEncoderUT {
    @Test
    public void generateHashedPassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("spmk@654#12@");
        assertTrue(true);
    }
}
