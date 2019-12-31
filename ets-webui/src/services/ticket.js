import request from '@/utils/request';

/**
 * 分页查询
 * @param param
 * @returns {Promise<void>}
 */
export async function page(param) {
  return request(
    `/server/api/ticket/page?current=${param.current}&size=${param.size}&parentId=${param.parentId}&name=${
      param.name ? param.name : ''}`,
  );
}

/**
 * 保存
 * @param obj
 * @returns {Promise<void>}
 */
export async function save(obj) {
  return request('/server/api/ticket/save', {
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
  return request('/server/api/ticket/update', {
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
    `/server/api/ticket/checkNameExist?name=${param.name}&id=${param.id ? param.id : ''}`,
  );
}

export async function remove(param) {
  return request('/server/api/ticket/remove', {
    method: 'DELETE',
    data: param,
  });
}

export async function getById(id) {
  return request(`/server/api/ticket/getById?id=${id}`);
}


export async function getTicketCategory() {
  return request(`/server/api/ticketCategory/listTree`);
}

export async function getTicketSelectParams() {
  return request(`/server/api/ticket/getTicketSelectParams`);
}

export async function saveCagegory(obj) {
  return request('/server/api/ticketCategory/save', {
    method: 'POST',
    data: {
      ...obj,
    },
  });
}

export async function updateCagegory(obj) {
  return request('/server/api/ticketCategory/update', {
    method: 'POST',
    data: {
      ...obj,
    },
  });
}

/**
 * 校验票种名称是否已存在
 * @param param
 * @returns {Promise<void>}
 */
export async function checkCategoryNameExist(param) {
  return request(
    `/server/api/ticketCategory/checkNameExist?name=${param.name}&id=${param.id ? param.id : ''}`,
  );
}

export async function getDictionary(prefix) {
  return request(`/server/api/dictionary/getChildrenByPrefix?prefix=${prefix}`)
}

export async function getTicketScape(id) {
  return request(`/server/api/ticketScape/getByTicketId?ticketId=${id}`)
}

export async function updateConfig(obj) {
  return request('/server/api/ticketScape/update',{
    method: 'POST',
    data: {
      ...obj
    }
  });
}
