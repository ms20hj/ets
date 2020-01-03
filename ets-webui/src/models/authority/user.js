import {
  queryCurrent,
  query as queryUsers,
  page,
  save,
  update,
  checkNameExist,
  remove,
  getById,
  getTicketsAndChecked,
  authUserTicket,
} from '@/services/user';
const UserModel = {
  namespace: 'user',
  state: {
    currentUser: {},
    pageData: {
      list: [],
      pagination: {
        current: 1,
        size: 10,
        total: 0,
      },
    },
    handleResult: null,
    tempUser: {
      userName: '',
      realName: '',
      password: '',
      gender: '男',
      phone: '',
      status: 0,
    },
    ticketList: [],
    userTicketList: [],
  },
  effects: {
    *fetch(_, { call, put }) {
      const response = yield call(queryUsers);
      yield put({
        type: 'save',
        payload: response,
      });
    },

    *fetchCurrent(_, { call, put }) {
      const response = yield call(queryCurrent);
      yield put({
        type: 'saveCurrentUser',
        payload: response.data,
      });
    },

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
        type: 'putTempUser',
        payload: response,
      });
    },

    *getTicketsAndChecked({ payload }, { call, put }){
      const resp = yield call(getTicketsAndChecked, payload);
      yield put({
        type: 'putTicketsAndChecked',
        payload: resp,
      });
    },

    *authTicketUser({payload}, {call, put}) {
      const resp = yield call(authUserTicket, payload);
      yield put({
        type: 'putHandleResult',
        payload: resp,
      });
    }

  },

  reducers: {
    saveCurrentUser(state, action) {
      return { ...state, currentUser: action.payload || {} };
    },

    changeNotifyCount(
      state = {
        currentUser: {},
      },
      action,
    ) {
      return {
        ...state,
        currentUser: {
          ...state.currentUser,
          notifyCount: action.payload.totalCount,
          unreadCount: action.payload.unreadCount,
        },
      };
    },

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

    clearData(state, { _ }) {
      return {
        ...state,
        handleResult: null,
        tempUser: {
          userName: '',
          realName: '',
          password: '',
          gender: '男',
          phone: '',
          status: 0,
        },
      };
    },

    putTempUser(state, { payload }) {
      return {
        ...state,
        handleResult: {
          ...payload,
        },
        tempUser: {
          ...payload.data,
        },
      };
    },

    putTicketsAndChecked(state, {payload}) {
      return {
        ...state,
        ticketList: payload.data.ticketList,
        userTicketList: payload.data.userTicketList,
        handleResult: {
          code: payload.code,
          status: payload.status
        }
      };
    },

    changeUserTicketList(state, {payload}) {
      return{
        ...state,
        userTicketList: payload
      }
    },

  },
};
export default UserModel;
