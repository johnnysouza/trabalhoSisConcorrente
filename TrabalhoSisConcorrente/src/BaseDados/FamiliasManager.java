package BaseDados;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import pkg.Familia;

public class FamiliasManager {
	
	private static final byte[]	s		= new byte[]{25, 32, 87, 110, 87, 34, 76, 29};
	private static final String	PATH	= "./familais.bin";
	
	private static byte[] internalSaveFamilys(final Familia[] familias) throws InvalidKeyException, InvalidKeySpecException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException {
		
		final SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(s));
		
		final Cipher encrypter = Cipher.getInstance("DES/ECB/PKCS5Padding");
		encrypter.init(Cipher.ENCRYPT_MODE, secretKey);
		
		// Seal it, storing it in a SealedObject
		final SealedObject sealed = new SealedObject(familias, encrypter);
		
		final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(buffer);
		oos.writeObject(sealed);
		oos.close();
		
		return buffer.toByteArray();
	}
	
	public static void saveFamilys(final Familia[] familias) {
		try {
			final FileOutputStream fos = new FileOutputStream(PATH);
			fos.write(internalSaveFamilys(familias));
			fos.close();
		} catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Familia[] loadFamilys() {
		final File file = new File(PATH);
		if (file.exists()) {
			try {
				final byte[] buffer = new byte[(int) file.length()];
				final FileInputStream fis = new FileInputStream(file);
				fis.read(buffer);
				fis.close();
				return internalLoadFamilys(buffer);
			} catch (IOException | InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException
					| NoSuchPaddingException | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private static Familia[] internalLoadFamilys(final byte[] buffer) throws InvalidKeyException, InvalidKeySpecException,
			NoSuchAlgorithmException, NoSuchPaddingException, IOException, ClassNotFoundException, IllegalBlockSizeException,
			BadPaddingException {
		final SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(s));
		
		final Cipher decrypter = Cipher.getInstance("DES/ECB/PKCS5Padding");
		decrypter.init(Cipher.DECRYPT_MODE, secretKey);
		
		final SealedObject sealed = (SealedObject) new ObjectInputStream(new ByteArrayInputStream(buffer)).readObject();
		
		final Familia[] familias = (Familia[]) sealed.getObject(decrypter);
		
		return familias;
	}
	
}
