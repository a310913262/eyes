package com.cmccsi.account.sync.accountsync.utils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;
import org.bouncycastle.crypto.generators.OpenBSDBCrypt;
public class SeachGuardHash {

    public static String hash(final char[] clearTextPassword) {
        final byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        final String hash = OpenBSDBCrypt.generate((Objects.requireNonNull(clearTextPassword)), salt, 12);
        Arrays.fill(salt, (byte)0);
        Arrays.fill(clearTextPassword, '\0');
        return hash;
    }
}
