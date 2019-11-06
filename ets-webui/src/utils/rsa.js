import {JSEncrypt} from 'jsencrypt';

/**
 * RSA 前端加密
 * @param data
 * @returns {PromiseLike<ArrayBuffer>}
 */
export function encrypt(data) {
  const jsEncrypt = new JSEncrypt();
  const rsaPublicKey = localStorage.getItem("rsaKey");
  jsEncrypt.setPublicKey(rsaPublicKey);
  return jsEncrypt.encrypt(data);
}
