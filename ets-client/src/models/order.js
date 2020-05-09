/**
 * 构建订单js
  */
import { createOrder, getEmptySaleOrderType, } from '@/services/sale';
const OrderModel = {
  namespace: 'order',
  state: {
    saleOrder: {},
    saleOrderTypeList: [],
    tempSaleOrderType: {},
  },

  effects: {
    *create(_, {call, put}) {
      const response = yield call(createOrder);
      yield put({
        type: 'putOrder',
        payload: response
      });
    },

    *getSaleOrderType(_, {call, put}) {
      const response = yield call(getEmptySaleOrderType);
      yield put({
        type: 'putSaleOrderType',
        payload: response
      });
    }
  },


  reducers: {
    putOrder(state, {payload}) {
      const saleOrderTypeList = payload.data.saleOrderTypeList.map((item, i) => {
        item.key = i.toString();
        return item;
      });
      return {
        ...state,
        saleOrder: payload.data,
        saleOrderTypeList: saleOrderTypeList,
      }
    },

    refreshOrderTypeList(state, {payload}) {
      return {
        ...state,
        saleOrderTypeList: payload,
      }
    },

    putSaleOrderType(state, {payload}) {
      return {
        ...state,
        tempSaleOrderType: payload.data,
      }
    },

  },
};
export default OrderModel;
