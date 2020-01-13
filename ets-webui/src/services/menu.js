import request from '@/utils/request';

export async function getWebMenuTree() {
  return request(`/server/api/menu/getWebMenuRoute`);
}
