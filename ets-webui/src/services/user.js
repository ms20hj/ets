import request from '@/utils/request';
export async function query() {
  return request('/api/users');
}
export async function queryCurrent() {
  return request('/server/api/user/queryUser');
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

/**
 * 更新
 * @param user
 * @returns {Promise<void>}
 */
export async function update(user) {
  return request('/server/api/user/update', {
    method: 'POST',
    data: {
      ...user,
    },
  });
}

/**
 * 校验名称是否已存在
 * @param param
 * @returns {Promise<void>}
 */
export async function checkNameExist(param) {
  return request(
    `/server/api/user/checkNameExist?userName=${param.userName}&id=${param.id ? param.id : ''}`,
  );
}

export async function remove(param) {
  return request('/server/api/user/remove', {
    method: 'DELETE',
    data: param,
  });
}

export async function getById(id) {
  return request(`/server/api/user/getById?id=${id}`);
}

export async function getTicketsAndChecked(userId) {
  return request(`/server/api/userTicket/getTicketsAndChecked?userId=${userId}`);
}

export async function authUserTicket(obj) {
  return request(`/server/api/userTicket/authUserTicket`, {
    method: 'POST',
    data: obj,
  });
}
