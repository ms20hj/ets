import {
  page,
  save,
  update,
  checkNameExist,
  remove,
  getById,
  getTreeTravel,
} from '@/services/travelagency';

const TravelAgencyModel = {
  namespace: 'travelAgency',
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
    tempTravelAgency: {
      name: '',
      phone: '',
      contact: '',
      address: '',
    },
    tempTravelType: {
      name: '',
    },
    treeTravel: [],
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
        type: 'putTempTravelAgency',
        payload: response,
      });
    },

    *getTreeTravel(_, { call, put }) {
      const response = yield call(getTreeTravel);
      yield put({
        type: 'putTreeTravel',
        payload: response,
      });
    },

    *getTypeById({ payload }, { call, put }) {
      const response = yield call(getById, payload);
      yield put({
        type: 'putTempTravelType',
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
        tempTravelAgency: {
          name: '',
          phone: '',
          contact: '',
          address: '',
        },
      };
    },

    clearTypeData(state, _) {
      return {
        ...state,
        handleResult: null,
        tempTravelType: {
          name: '',
        },
      };
    },

    putTempTravelAgency(state, { payload }) {
      return {
        ...state,
        tempTravelAgency: {
          ...payload.data,
        },
        handleResult: {
          ...payload,
        },
      };
    },

    putTreeTravel(state, { payload }) {
      return {
        ...state,
        treeTravel: payload.data,
        handleResult: {
          ...payload,
        },
      };
    },

    putTempTravelType(state, { payload }) {
      return {
        ...state,
        tempTravelType: {
          ...payload.data,
        },
        handleResult: {
          ...payload,
        },
      };
    },


  },
};
export default TravelAgencyModel;
