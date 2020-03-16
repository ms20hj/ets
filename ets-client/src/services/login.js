import request from '@/utils/request';
import { encrypt } from '@/utils/rsa';
export async function userLogin(params) {
  const pwd = encrypt(params.password);
  return request('/server/api/login/userLogin', {
    method: 'POST',
    data: {
      userName: params.userName,
      password: pwd
    },
  });
}
export async function getFakeCaptcha(mobile) {
  return request(`/server/api/login/captcha?mobile=${mobile}`);
}

export async function getPublicKey() {
  return request('/server/api/login/getRsaPublicKey');
}
