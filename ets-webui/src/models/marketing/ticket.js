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
  getScenicSpotList,
  getDictionary,
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
      scenicSpotId: '',
      ticketCategoryId: '',
      salePrice: 0.00,
      printPrice: 0.00,
      networkPrice: 0.00,
      physical: 0,
      deadline: 1,
      deadlineUnit: '',
      printMethod: 0,
      beginDate: '',
      endDate: '',
      status: 0,
      printTemplate: '',
      sortNum: 0,
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

    *getScenicSpotList(_, { call, put}) {
      const response = yield call(getScenicSpotList);
      yield put({
        type: 'putScenicSpotList',
        payload: response,
      });
    },

    *getPhysicalDictionary(_, { call, put }) {
      const response = yield call(getDictionary, TICKET_PHYSICAL);
      yield put({
        type: 'putPhysicalList',
        payload: response,
      });
    },

    *getDeadlineUnitDictionary(_, { call, put}) {
      const response = yield call(getDictionary, TICKET_DEADLINEUNIT);
      yield put({
        type: 'putDeadlineUnitList',
        payload: response
      });
    },

    *getPrintMethodDictionary(_, { call, put}) {
      const response = yield call(getDictionary, TICKET_PRINTMETHOD);
      yield put({
        type: 'putPrintMethodList',
        payload: response
      });
    },

    *getPrintTemplateDictionary(_, { call, put}) {
      const response = yield call(getDictionary, TICKET_PRINTTEMPLATE);
      yield put({
        type: 'putPrintTemplateList',
        payload: response
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
        tempTicket: {
          ticketName: '',
          scenicSpotId: '',
          ticketCategoryId: '',
          salePrice: 0.00,
          printPrice: 0.00,
          networkPrice: 0.00,
          physical: 0,
          deadline: 1,
          deadlineUnit: '',
          printMethod: 0,
          beginDate: '',
          endDate: '',
          status: 0,
          printTemplate: '',
          sortNum: 0,
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

    putScenicSpotList(state, { payload }){
      return {
        ...state,
        scenicSpotList: payload.data,
      };
    },

    putPhysicalList(state, {payload}) {
      return {
        ...state,
        physicalList: payload.data,
      }
    },

    putDeadlineUnitList(state, {payload}) {
      return {
        ...state,
        deadlineUnitList: payload.data,
      }
    },

    putPrintMethodList(state, {payload}) {
      return {
        ...state,
        printMethodList: payload.data,
      }
    },

    putPrintTemplateList(state, {payload}) {
      return {
        ...state,
        printTemplateList: payload.data,
      }
    },

  },
};
export default TicketModel;
