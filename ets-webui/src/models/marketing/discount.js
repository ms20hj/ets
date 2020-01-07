import { page, save, update, checkNameExist, remove, getById, getSelectList } from '@/services/discount';

const DiscountModel = {
  namespace: 'discount',
  state: {
    pageData: {
      list: [],
      pagination: {
        current: 1,
        size: 10,
        total: 0,
      },
    },
    handleResult: null,
    tempDiscount: {
      discName: '',
      limitCount: 0,
      limitBegin: 1,
      limitEnd: 1,
      discountWay: '0',
      discountScale: 0.00,
      touristIds: [],
      ticketIds: [],
      travelAgencyIds: [],
    },
    discountWayList: [],
    touristList: [],
    ticketList: [],
    travelAgencyList: [],
  },

  effects: {
    *page({ payload }, { call, put }) {
      const response = yield call(page, payload);
      yield put({
        type: 'putTableData',
        payload: response,
      });
    },

    *save({ payload }, { call, put }) {
      const response = yield call(save, payload);
      yield put({
        type: 'putHandleResult',
        payload: response,
      });
    },

    *update({ payload }, { call, put }) {
      const response = yield call(update, payload);
      yield put({
        type: 'putHandleResult',
        payload: response,
      });
    },

    *checkNameExist({ payload }, { call, put }) {
      const response = yield call(checkNameExist, payload);
      yield put({
        type: 'putHandleResult',
        payload: response,
      });
    },

    *remove({ payload }, { call, put }) {
      const response = yield call(remove, payload);
      yield put({
        type: 'putHandleResult',
        payload: response,
      });
    },

    *getById({ payload }, { call, put }) {
      const response = yield call(getById, payload);
      yield put({
        type: 'putTempDiscount',
        payload: response,
      });
    },

    *getSelectList({_}, {call, put}) {
      const resp = yield call(getSelectList);
      yield put({
        type: 'putSelectList',
        payload: resp,
      });
    },
  },

  reducers: {
    putTableData(state, { payload }) {
      return {
        ...state,
        pageData: {
          list: payload.data.records,
          pagination: {
            current: payload.data.current,
            size: payload.data.size,
            total: payload.data.total,
          },
        },
      };
    },

    putHandleResult(state, { payload }) {
      return {
        ...state,
        handleResult: {
          ...payload,
        },
      };
    },

    clearData(state, _) {
      return {
        ...state,
        handleResult: null,
        tempDiscount: {
          discName: '',
          limitCount: 0,
          limitBegin: 1,
          limitEnd: 1,
          discountWay: '0',
          discountScale: 0.00,
          touristIds: [],
          ticketIds: [],
          travelAgencyIds: [],
        },
      };
    },

    putTempDiscount(state, { payload }) {
      return {
        ...state,
        tempDiscount: {
          ...payload.data,
        },
        handleResult: {
          ...payload,
        },
      };
    },

    putSelectList(state, {payload}){
      return {
        ...state,
        discountWayList: payload.data.discountWayList,
        ticketList: payload.data.ticketList,
        touristList: payload.data.touristList,
        travelAgencyList: payload.data.travelAgencyList,
      };
    },
    /**
     * 改变选择游客类型key
     * @param state
     * @param payload
     * @returns {{tempDiscount: {touristIds: *}}}
     */
    changeTouristSelected(state, {payload}) {
      return {
        ...state,
        tempDiscount: {
          touristIds: payload
        }
      }
    },

    changeTicketSelected(state, {payload}) {
      return {
        ...state,
        tempDiscount: {
          ticketIds: payload
        }
      }
    },

    changeTravelAgencySelected(state, {payload}){
      return {
        ...state,
        tempDiscount: {
          travelAgencyIds: payload
        }
      }
    },
  },
};
export default DiscountModel;
