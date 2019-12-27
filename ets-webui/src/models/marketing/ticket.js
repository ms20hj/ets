import {
  page,
  save,
  update,
  checkNameExist,
  remove,
  getById,
  getTicketCategory,
  saveCagegory,
  updateCagegory,
  checkCategoryNameExist,
  getTicketSelectParams,
  getTicketScape,
} from '@/services/ticket';

const TicketModel = {
  namespace: 'ticket',
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
    tempTicket: {
      ticketName: '',
      scenicSpotIdList: [],
      ticketCategoryId: '',
      salePrice: 0,
      printPrice: 0,
      networkPrice: 0,
      physical: 0,
      deadline: 1,
      deadlineUnit: 'D',
      printMethod: 0,
      beginDate: '',
      endDate: '',
      status: 0,
      fmStatus: true,
      printTemplate: '',
      sortNum: 0,
      description: '',
    },
    tempTicketCategory: {
      name: '',
      sortNum: 0,
    },
    treeCategory: [],
    scenicSpotList: [],
    physicalList: [],
    deadlineUnitList: [],
    printMethodList: [],
    printTemplateList: [],
    ticketScapeList: [],
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
        type: 'putTempTicket',
        payload: response,
      });
    },

    *getTreeCategory(_, { call, put }) {
      const response = yield call(getTicketCategory);
      yield put({
        type: 'putTreeCategory',
        payload: response,
      });
    },

    *getTypeById({ payload }, { call, put }) {
      const response = yield call(getById, payload);
      yield put({
        type: 'putTempTicketCategory',
        payload: response,
      });
    },

    *saveCategory({ payload }, { call, put }) {
      const response = yield call(saveCagegory, payload);
      yield put({
        type: 'putHandleResult',
        payload: response,
      });
    },

    *updateCategory({ payload }, { call, put }) {
      const response = yield call(updateCagegory, payload);
      yield put({
        type: 'putHandleResult',
        payload: response,
      });
    },

    *checkCategoryNameExist({ payload }, { call, put }) {
      const response = yield call(checkCategoryNameExist, payload);
      yield put({
        type: 'putHandleResult',
        payload: response,
      });
    },

    *initTicketSelectParams(_, { call, put }) {
      const response = yield call(getTicketSelectParams);
      yield put({
        type: 'putInitTicketSelectParams',
        payload: response,
      });
    },

    *getTicketScapeList({payload}, { call, put }) {
      const response = yield call(getTicketScape, payload);
      yield put({
        type: 'putTicketScapeList',
        payload: response
      });
    }
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
        tempTicket: {
          ticketName: '',
          scenicSpotIdList: [],
          ticketCategoryId: '',
          salePrice: 0.0,
          printPrice: 0.0,
          networkPrice: 0.0,
          physical: 0,
          deadline: 1,
          deadlineUnit: '',
          printMethod: 0,
          beginDate: '',
          endDate: '',
          status: 0,
          fmStatus: true,
          printTemplate: '',
          sortNum: 0,
          description: '',
        },
      };
    },

    clearTypeData(state, _) {
      return {
        ...state,
        handleResult: null,
        tempTicketCategory: {
          name: '',
          sortNum: 0,
        },
      };
    },

    putTempTicket(state, { payload }) {
      return {
        ...state,
        tempTicket: {
          ...payload.data,
        },
        handleResult: {
          ...payload,
        },
      };
    },

    putTreeCategory(state, { payload }) {
      return {
        ...state,
        treeCategory: payload.data,
        handleResult: {
          ...payload,
        },
      };
    },

    putTempTicketCategory(state, { payload }) {
      return {
        ...state,
        tempTicketCategory: {
          ...payload.data,
        },
        handleResult: {
          ...payload,
        },
      };
    },

    putInitTicketSelectParams(state, { payload }) {
      const data = payload.data;
      return {
        ...state,
        scenicSpotList: data.scenicSpotList,
        physicalList: data.physicalList,
        deadlineUnitList: data.deadlineUnitList,
        printMethodList: data.printMethodList,
        printTemplateList: data.printTemplateList,
      };
    },

    putTicketScapeList(state, {payload}) {
      return {
        ...state,
        ticketScapeList: payload.data,
        handleResult: {
          ...payload,
        },
      }
    }
  },
};
export default TicketModel;
