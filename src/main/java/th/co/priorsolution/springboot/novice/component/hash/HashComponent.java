package th.co.priorsolution.springboot.novice.component.hash;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashComponent {

	@Value("${hash.algorithm}")
	private String tAlgorithm;
	@Value("${hash.salt}")
	private String tSalt;

	private static String algorithm;
	private static String salt;

	@PostConstruct
	private void init() {
		setAlgorithm(this.tAlgorithm);
		setSalt(this.tSalt);
	}
	
	public String hash(Object obj) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(salt.getBytes());
		if(obj != null) {
			byte[] hash = digest.digest(String.valueOf(obj).getBytes(StandardCharsets.UTF_8));
			return Hex.encodeHexString(hash);
		}else {
			return null;
		}
	}

	public static void setAlgorithm(String algorithm) {
		HashComponent.algorithm = algorithm;
	}

	public static void setSalt(String salt) {
		HashComponent.salt = salt;
	}
}
