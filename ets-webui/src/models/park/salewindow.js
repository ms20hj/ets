import {
  page,
  save,
  update,
  checkNameExist,
  remove,
  getById,
  getSaleSiteList,
  checkMacExist,
} from '@/services/salewindow';

const SaleWindowModel = {
  namespace: 'saleWindow',
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
    tempSaleWindow: {
      windowName: '',
      mac: '',
      saleSiteId: '',
    },
    saleSiteList: [],
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
        type: 'putTempSaleWindow',
        payload: response,
      });
    },

    *getSaleSiteList(_, { call, put }) {
      const response = yield call(getSaleSiteList);
      yield put({
        type: 'putSaleSiteList',
        payload: response,
      });
    },

    *checkMacExist({payload}, { call, put }) {
      const response = yield call(checkMacExist, payload);
      yield put({
        type: 'putHandleResult',
        payload: response,
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
        tempSaleWindow: {
          windowName: '',
          mac: '',
          saleSiteId: '',
        },
      };
    },

    putTempSaleWindow(state, { payload }) {
      return {
        ...state,
        tempSaleWindow: {
          ...payload.data,
        },
        handleResult: {
          ...payload,
        },
      };
    },

    putSaleSiteList(state, { payload }) {
      return {
        ...state,
        saleSiteList: payload.data,
      };
    },
  },
};
export default SaleWindowModel;
