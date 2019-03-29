import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.jar.JarInputStream;


public class VerifySignature{

    public static void main(String[] args) {
            try {
                FileInputStream keyfis = new FileInputStream("DataPK");
                byte[] encKey = new byte[keyfis.available()];
                keyfis.read(encKey);
                keyfis.close();
                X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
                KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
                PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
                FileInputStream sigfis = new FileInputStream("SignedFile");
                byte[] sigToVerify = new byte[sigfis.available()];
                sigfis.read(sigToVerify);
                sigfis.close();

                Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
                sig.initVerify(pubKey);
                FileInputStream datafis = new FileInputStream("FXMLBrowsing.jar");
                JarInputStream jarInputStream = new JarInputStream(datafis);
                byte[] buffer = new byte[1024];
                int len;
                while (jarInputStream.getNextEntry() != null) {
                    len = jarInputStream.read(buffer);
					if(len>0)	
						sig.update(buffer, 0, len);
                }
                jarInputStream.close();
                boolean verifies = sig.verify(sigToVerify);
                System.out.println("signature verifies: " + verifies);

            } catch (Exception e) {
                System.err.println("Caught exception " + e.toString());
            }
        

    }
}
