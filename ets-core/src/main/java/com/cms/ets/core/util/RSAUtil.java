package com.cms.ets.core.util;

import com.cms.ets.common.constant.RsaConstant;
import com.cms.ets.common.exception.SystemException;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * http://blog.csdn.net/wangqiuyun/article/details/42143957
 *
 * 前端的配合： https://github.com/rzcoder/node-rsa/issues/92，用es6实现rsa，仅支持chrome,
 * FireFox or Internet Explorer 11
 * https://github.com/travist/jsencrypt，用js实现rsa，支持ie6及以上
 *
 *
 */
public class RSAUtil {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private static RSAPublicKey rsaPublicKey;

	private static RSAPrivateKey rsaPrivateKey;

	/**
	 * 随机生成密钥对
	 *
	 * @param filePath 密钥对要存储的路径
	 */
	public static void genKeyPair(String filePath) {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(1024, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 得到私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 得到公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		FileWriter pubfw = null;
		FileWriter prifw = null;
		BufferedWriter pubbw = null;
		BufferedWriter pribw = null;
		try {
			// 得到公钥字符串
			String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
			// 得到私钥字符串
			String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
			// 将密钥对写入到文件
			pubfw = new FileWriter(filePath + "/publicKey.keystore");
			prifw = new FileWriter(filePath + "/privateKey.keystore");
			pubbw = new BufferedWriter(pubfw);
			pribw = new BufferedWriter(prifw);
			pubbw.write(publicKeyString);
			pribw.write(privateKeyString);
			pubbw.flush();
			pribw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pubbw != null) {
					pubbw.close();
				}
				if (pubfw != null) {
					pubfw.close();
				}
				if (pribw != null) {
					pribw.close();
				}
				if (prifw != null) {
					prifw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 去除密钥的多余信息
	 *
	 * @param key 公钥或私钥字符串
	 * @return Base64的密钥
	 */
	public static String getBase64Key(String key) {
		key = key.replaceAll("-----BEGIN (.*)-----", "");
		key = key.replaceAll("-----END (.*)----", "");
		key = key.replaceAll("\r\n", "");
		key = key.replaceAll("\n", "");
		return key;
	}

	/**
	 * 从字符串中加载公钥
	 *
	 * @param publicKeyStr 公钥数据字符串
	 * @return 公钥
	 */
	public static void loadPublicKeyByStr(String publicKeyStr) {
		try {
			byte[] buffer = Base64.getDecoder().decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			//
			setRsaPublicKey((RSAPublicKey) keyFactory.generatePublic(keySpec));
		} catch (NoSuchAlgorithmException e) {
			throw new SystemException("无此算法", e);
		} catch (InvalidKeySpecException e) {
			throw new SystemException("公钥非法", e);
		} catch (NullPointerException e) {
			throw new SystemException("公钥数据为空", e);
		} catch (NoSuchProviderException e) {
			throw new SystemException("no such provider: RSA, BC", e);
		}
	}

	/**
	 * 从文件中加载密钥
	 *
	 * @param fileName 公钥或私钥的文件名
	 *
	 * @return 字符串形式的密钥，去除-----BEGIN (.*)-----等多余信息
	 */
	public static String loadKeyFromFile(String fileName) {
		byte[] keyBytes = loadRawKeyFromFile(fileName);

		// convert to der format
		return getBase64Key(new String(keyBytes));
	}

	/**
	 * 从文件中加载密钥
	 *
	 * @param fileName 公钥或私钥的文件名
	 *
	 * @return 字符串形式的密钥
	 */
	public static byte[] loadRawKeyFromFile(String fileName) {
		InputStream resourceAsStream = RSAUtil.class.getClassLoader().getResourceAsStream(fileName);
		DataInputStream dis = new DataInputStream(resourceAsStream);
		byte[] keyBytes = null;
		try {
			keyBytes = new byte[resourceAsStream.available()];
			dis.readFully(keyBytes);
			dis.close();
		} catch (IOException e) {
			throw new SystemException("Failed to load public key from file '" + fileName + "'", e);
		}
		return keyBytes;
	}

	/**
	 * 从流中加载密钥
	 *
	 * @param inputSteam 文件流
	 * @return 密钥字符串
	 */
	@SuppressWarnings("deprecation")
	public static String loadRawKey(InputStream inputSteam) {
		try {
			return IOUtils.toString(inputSteam);
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public static void loadPrivateKeyByStr(String privateKeyStr) {
		try {
			byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
			setRsaPrivateKey((RSAPrivateKey) keyFactory.generatePrivate(keySpec));
		} catch (NoSuchAlgorithmException e) {
			throw new SystemException("无此算法", e);
		} catch (InvalidKeySpecException e) {
			throw new SystemException("私钥非法", e);
		} catch (NullPointerException e) {
			throw new SystemException("私钥数据为空", e);
		} catch (NoSuchProviderException e) {
			throw new SystemException("no such provider: RSA, BC", e);
		}
	}

	/**
	 * 公钥加密过程
	 *
	 * @param publicKey 公钥
	 * @param plainTextData 明文数据
	 * @return 密文
	 */
	public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) {
		if (publicKey == null) {
			throw new SystemException("加密公钥为空");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new SystemException("无此加密算法", e);
		} catch (NoSuchPaddingException e) {
			throw new SystemException("无此加密算法", e);
		} catch (InvalidKeyException e) {
			throw new SystemException("加密公钥非法,请检查", e);
		} catch (IllegalBlockSizeException e) {
			throw new SystemException("明文长度非法", e);
		} catch (BadPaddingException e) {
			throw new SystemException("明文数据已损坏", e);
		}
	}

	public static String encryptByPublicKey(String data) {
		byte[] bytes = encrypt(getRsaPublicKey(), data.getBytes());
		return Base64.getEncoder().encodeToString(bytes);
	}

	/**
	 * 私钥加密过程
	 *
	 * @param privateKey 私钥
	 * @param plainTextData 明文数据
	 * @return 密文
	 * @throws Exception 加密过程中的异常信息
	 */
	public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception {
		if (privateKey == null) {
			throw new SystemException("加密私钥为空");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA Cipher.getInstance("RSA");
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new SystemException("无此加密算法", e);
		} catch (NoSuchPaddingException e) {
			throw new SystemException("系统中无此填充机制", e);
		} catch (InvalidKeyException e) {
			throw new SystemException("加密私钥非法,请检查", e);
		} catch (IllegalBlockSizeException e) {
			throw new SystemException("明文长度非法", e);
		} catch (BadPaddingException e) {
			throw new SystemException("明文数据已损坏", e);
		}
	}

	/**
	 * 私钥解密过程
	 *
	 * @param privateKey 私钥
	 * @param cipherData 密文数据
	 * @return 明文
	 */
	public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) {
		if (privateKey == null) {
			throw new SystemException("解密私钥为空");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new SystemException("无此解密算法", e);
		} catch (NoSuchPaddingException e) {
			throw new SystemException("系统中无此填充机制", e);
		} catch (InvalidKeyException e) {
			throw new SystemException("解密私钥非法", e);
		} catch (IllegalBlockSizeException e) {
			throw new SystemException("密文长度非法");
		} catch (BadPaddingException e) {
			throw new SystemException("密文数据已损坏", e);
		}
	}

	/**
	 * 公钥解密过程
	 *
	 * @param publicKey 公钥
	 * @param cipherData 密文数据
	 * @return 明文
	 * @throws Exception 解密过程中的异常信息
	 */
	public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData) throws Exception {
		if (publicKey == null) {
			throw new SystemException("解密公钥为空");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new SystemException("无此解密算法", e);
		} catch (NoSuchPaddingException e) {
			throw new SystemException("系统中无此填充机制", e);
		} catch (InvalidKeyException e) {
			throw new SystemException("解密公钥非法", e);
		} catch (IllegalBlockSizeException e) {
			throw new SystemException("密文长度非法");
		} catch (BadPaddingException e) {
			throw new SystemException("密文数据已损坏");
		}
	}

	/**
	 * 解密Rsa加密的密码
	 * @param rsaPwd 经过公钥加密的密码
	 * @return String
	 */
	public static String decrypt(String rsaPwd) {
		// rsa解密
		byte[] cipherData = Base64.getDecoder().decode(rsaPwd);
		byte[] xpwd = RSAUtil.decrypt(getRsaPrivateKey(), cipherData);
		return new String(xpwd);
	}

	public static RSAPublicKey getRsaPublicKey() {
		return rsaPublicKey;
	}

	public static void setRsaPublicKey(RSAPublicKey rsaPublicKey) {
		RSAUtil.rsaPublicKey = rsaPublicKey;
	}

	public static RSAPrivateKey getRsaPrivateKey() {
		return rsaPrivateKey;
	}

	public static void setRsaPrivateKey(RSAPrivateKey rsaPrivateKey) {
		RSAUtil.rsaPrivateKey = rsaPrivateKey;
	}
}
