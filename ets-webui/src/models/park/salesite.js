import {
  page,
  save,
  update,
  checkNameExist,
  remove,
  getById,
  getScenicSpotList,
} from '@/services/salesite';

const SaleSiteModel = {
  namespace: 'saleSite',
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
    tempSaleSite: {
      siteName: '',
      description: '',
      scenicSpotId: '',
    },
    scenicSpotList: [],
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
        type: 'putTempSaleSite',
        payload: response,
      });
    },

    *getScenicSpotList(_, { call, put }) {
      const response = yield call(getScenicSpotList);
      yield put({
        type: 'putScenicSpotList',
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
        tempSaleSite: {
          siteName: '',
          description: '',
          scenicSpotId: '',
        },
      };
    },

    putTempSaleSite(state, { payload }) {
      return {
        ...state,
        tempSaleSite: {
          ...payload.data,
        },
        handleResult: {
          ...payload,
        },
      };
    },

    putScenicSpotList(state, { payload }) {
      return {
        ...state,
        scenicSpotList: payload.data,
      };
    },
  },
};
export default SaleSiteModel;
