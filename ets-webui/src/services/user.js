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

/**
 * 分页查询
 * @param param
 * @returns {Promise<void>}
 */
export async function page(param) {
  return request(
    `/server/api/user/page?current=${param.current}&size=${param.size}&name=${
      param.name ? param.name : ''
    }`,
  );
}

/**
 * 保存
 * @param user
 * @returns {Promise<void>}
 */
export async function save(user) {
  return request('/server/api/user/save', {
    method: 'POST',
    data: {
      ...user,
    },
  });
}
