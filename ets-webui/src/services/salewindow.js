import request from '@/utils/request';

/**
 * 分页查询
 * @param param
 * @returns {Promise<void>}
 */
export async function page(param) {
  return request(
    `/server/api/saleWindow/page?current=${param.current}&size=${param.size}&name=${
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
  return request('/server/api/saleWindow/save', {
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
  return request('/server/api/saleWindow/update', {
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
    `/server/api/saleWindow/checkNameExist?name=${param.name}&id=${param.id ? param.id : ''}`,
  );
}

export async function remove(param) {
  return request('/server/api/saleWindow/remove', {
    method: 'DELETE',
    data: param,
  });
}

export async function getById(id) {
  return request(`/server/api/saleWindow/getById?id=${id}`);
}

export async function getSaleSiteList() {
  return request('/server/api/saleSite/getSimpleList');
}

/**
 * 校验Mac地址是否已存在
 * @param param
 * @returns {Promise<void>}
 */
export async function checkMacExist(param) {
  return request(
    `/server/api/saleWindow/checkMacExist?mac=${param.mac}&id=${param.id ? param.id : ''}`,
  );
}
