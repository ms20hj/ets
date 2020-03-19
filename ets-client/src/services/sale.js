import request from '@/utils/request';

export async function queryTravelAgency() {
  return request('/server/api/travelAgency/getTreeDataExceptRoot')
}
