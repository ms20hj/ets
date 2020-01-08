import request from '@/utils/request';

/**
 * 分页查询
 * @param param
 * @returns {Promise<void>}
 */
export async function page(param) {
  return request(
    `/server/api/discount/page?current=${param.current}&size=${param.size}&name=${
      param.name ? param.name : ''
    }`,
  );
}

/**
 * 保存
 * @param obj
 * @returns {Promise<void>}
 */
export async function save(obj) {
  return request('/server/api/discount/save', {
    method: 'POST',
    data: {
      ...obj,
    },
  });
}

/**
 * 更新
 * @param obj
 * @returns {Promise<void>}
 */
export async function update(obj) {
  return request('/server/api/discount/update', {
    method: 'POST',
    data: {
      ...obj,
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
    `/server/api/discount/checkNameExist?name=${param.name}&id=${param.id ? param.id : ''}`,
  );
}

export async function remove(param) {
  return request('/server/api/discount/remove', {
    method: 'DELETE',
    data: param,
  });
}

export async function getForEdit(id) {
  return request(`/server/api/discount/getForEdit?id=${id}`);
}
export async function getSelectList() {
  return request('/server/api/discount/getSelectList');
}
