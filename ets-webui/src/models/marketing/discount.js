import { page, save, update, checkNameExist, remove, getForEdit, getSelectList } from '@/services/discount';

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
    handleResult: {
      status: false,
    },
    tempDiscount: {
      discName: '',
      limitCount: false,
      limitBegin: 1,
      limitEnd: 1,
      discountWay: '0',
      discountScale: 0.00,
      status: true,
      beginDate: '',
      endDate: '',
    },
    discountWayList: [],
    touristList: [],
    ticketList: [],
    travelAgencyList: [],
    touristIds: [],
    ticketIds: [],
    travelAgencyIds: [],
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

    *getForEdit({ payload }, { call, put }) {
      const response = yield call(getForEdit, payload);
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
          limitCount: false,
          limitBegin: 1,
          limitEnd: 1,
          discountWay: '0',
          discountScale: 0.00,
          status: true,
          beginDate: '',
          endDate: '',
        },
        touristIds: [],
        ticketIds: [],
        travelAgencyIds: [],
      };
    },

    putTempDiscount(state, { payload }) {
      return {
        ...state,
        tempDiscount: {
          ...payload.data,
        },
        touristIds: payload.data.touristIds,
        ticketIds: payload.data.ticketIds,
        travelAgencyIds: payload.data.travelAgencyIds,
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
        touristIds: payload
      }
    },

    changeTicketSelected(state, {payload}) {
      return {
        ...state,
        ticketIds: payload
      }
    },

    changeTravelAgencySelected(state, {payload}){
      return {
        ...state,
        travelAgencyIds: payload
      }
    },
  },
};
export default DiscountModel;
