package com.practice.manage.membermanage.jwt;


import com.nimbusds.jose.*;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import lombok.SneaThrows;
import lombok.experimental.UtilityClass;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@UtilityClass
public class RsaHelper {

    //프로젝트 제공기능
    //1. KeyPair 생성
    //2. DB 에 pub/pri 저장 (base64 format)
    //3. JWT 발행시 pri 불러와 서명
    //3. pub 다운로드 (pem 파일)

    //리소스 기능
    //1. pub 다운로드 및 경로설정
    //2. oauth resource verify 에 사용

    /*
    참고
    - https://connect2id.com/products/nimbus-jose-jwt/examples/jws-with-rsa-signature
    - https://github.com/jwtk/jjwt/issues/131
    - https://m.blog.naver.com/hj_kim97/222720125751
    - https://this-programmer.tistory.com/259
     */

    static final String algorithm = "RSA";

    public static KeyPair generateKeyPair() throws JOSEException {
        RSAKey rsaJWK = new RSAKeyGenerator(2048).generate();
        return rsaJWK.toKeyPair();
    }

    public static void writePem(String description, byte[] keyBytes, Writer someWriter) throws IOException {
        PemWriter pemWriter = new PemWriter(someWriter);
        pemWriter.writeObject(new PemObject(description, keyBytes));
        pemWriter.close();
    }

    @SneakyThrows(NoSuchAlgorithmException.class)
    public static RSAPrivateKey readPrivateKey(byte[] bytes) throws InvalidKeySpecException {
        return (RSAPrivateKey) KeyFactory.getInstance(algorithm).generatePrivate(new PKCS8EncodedKeySpec(bytes));
    }

    @SneakyThrows(NoSuchAlgorithmException.class)
    public static RSAPublicKey readPublicKey(byte[] bytes) throws InvalidKeySpecException {
        return (RSAPublicKey) KeyFactory.getInstance(algorithm).generatePublic(new X509EncodedKeySpec(bytes));
    }

    public static RSAPrivateKey readPrivateKey(String filePath) throws IOException, GeneralSecurityException {
        return readPrivateKey(new File(filePath));
    }

    public static RSAPublicKey readPublicKey(String filePath) throws IOException, GeneralSecurityException {
        return readPublicKey(new File(filePath));
    }

    public static RSAPrivateKey readPrivateKey(File file) throws IOException, GeneralSecurityException {
        KeyFactory factory = KeyFactory.getInstance(algorithm);

        try (FileReader keyReader = new FileReader(file);
             PemReader pemReader = new PemReader(keyReader)) {

            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
            return (RSAPrivateKey) factory.generatePrivate(privKeySpec);
        }
    }

    public static RSAPublicKey readPublicKey(File file) throws IOException, GeneralSecurityException {
        KeyFactory factory = KeyFactory.getInstance(algorithm);

        try (FileReader keyReader = new FileReader(file);
             PemReader pemReader = new PemReader(keyReader)) {

            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
            return (RSAPublicKey) factory.generatePublic(pubKeySpec);
        }
    }

}
