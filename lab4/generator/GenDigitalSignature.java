import java.io.*;
import java.security.*;
import java.util.jar.JarInputStream;

public class GenDigitalSignature {

    public static void main(String[] args) {
			try{
                KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
                keyGen.initialize(1024, random);
                KeyPair pair = keyGen.generateKeyPair();
                PrivateKey priv = pair.getPrivate();
                PublicKey pub = pair.getPublic();
                Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
                dsa.initSign(priv);
                FileInputStream fis = new FileInputStream("FXMLBrowsing.jar");
                JarInputStream jarInputStream = new JarInputStream(fis);
                byte[] buffer = new byte[1024];
                int len;
                while (jarInputStream.getNextJarEntry() != null) {
                    len = jarInputStream.read(buffer);
					if(len>0)
						dsa.update(buffer, 0, len);
                }
                jarInputStream.close();
                byte[] realSig = dsa.sign();
                File signedFile = new File("TFolder");
                signedFile.mkdir();
                FileOutputStream sigfos =
                        new FileOutputStream("TFolder/SignedFile");
                sigfos.write(realSig);
                sigfos.close();

                byte[] key = pub.getEncoded();
                FileOutputStream keyfos = new FileOutputStream("TFolder/DataPK");
                keyfos.write(key);
                keyfos.close();
            } catch (Exception e) {
                System.err.println("Caught exception " + e.toString());
            }
        

    }
}

