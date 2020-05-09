import request from '@/utils/request';

export async function queryTravelAgency() {
  return request('/server/api/travelAgency/getTreeDataExceptRoot');
}

export async function createOrder() {
  return request('/server/api/saleOrder/createOrder?size=5');
}

export async function getBaseSaleData() {
  return request('/server/api/saleOrder/getBaseSaleData');
}

export async function getEmptySaleOrderType() {
  return request('/server/api/saleOrder/getEmptySaleOrderType');
}
