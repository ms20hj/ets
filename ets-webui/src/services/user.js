import request from '@/utils/request';
export async function query() {
  return request('/api/users');
}
export async function queryCurrent() {
  return request('/api/currentUser');
}
export async function queryNotices() {
  return request('/api/notices');
}
export async function page(param) {
  return request(
    `/server/api/user/page?current=${param.current}&size=${param.size}&name=${
      param.name ? param.name : ''
    }`,
  );
}
