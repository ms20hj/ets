import request from '@/utils/request';
export async function query() {
  return request('/server/api/users');
}
export async function queryCurrent() {
  return request('/server/api/user/queryUser');
}
export async function queryNotices() {
  return request('/server/api/notices');
}
