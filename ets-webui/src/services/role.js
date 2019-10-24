import request from '@/utils/request';

/**
 * 分页查询
 * @param param
 * @returns {Promise<void>}
 */
export async function page(param) {
  return request(
    `/server/api/role/page?current=${param.current}&size=${param.size}&name=${
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
  return request('/server/api/role/save', {
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
  return request('/server/api/role/update', {
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
    `/server/api/role/checkNameExist?roleName=${param.roleName}&id=${param.id ? param.id : ''}`,
  );
}

export async function remove(param) {
  return request('/server/api/role/remove', {
    method: 'DELETE',
    data: param,
  });
}

export async function getById(id) {
  return request(`/server/api/role/getById?id=${id}`);
}

/**
 * 获取用户和菜单集合
 * @returns {Promise<void>}
 */
export async function getUserAndMenu() {
  return request('/server/api/role/getUserAndMenu');
}

export async function getAuthDataById(id) {
  return request(`/server/api/role/getAuthDataById?id=${id}`);
}
