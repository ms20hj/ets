import {
  page,
  save,
  update,
  checkNameExist,
  remove,
  getById,
  getUserAndMenu,
  getAuthDataById,
} from '@/services/role';

const RoleModel = {
  namespace: 'role',
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
    tempRole: {
      roleName: '',
      status: 0,
      userIdList: [],
      menuIdList: [],
    },
    userList: [],
    menuList: [],
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
        type: 'putTempRole',
        payload: response,
      });
    },

    *getUserAndMenu(_, { call, put }) {
      const resp = yield call(getUserAndMenu);
      yield put({
        type: 'putUserAndMenuList',
        payload: resp,
      });
    },

    *getAuthRole({ payload }, { call, put }) {
      const resp = yield call(getAuthDataById, payload);
      console.log('resp', resp);
      yield put({
        type: 'putTempRole',
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

    clearData(state, { _ }) {
      return {
        ...state,
        handleResult: null,
        tempRole: {
          roleName: '',
          status: 0,
          userIdList: [],
          menuIdList: [],
        },
      };
    },

    putTempRole(state, { payload }) {
      return {
        ...state,
        tempRole: {
          ...payload.data,
        },
        handleResult: {
          ...payload,
        },
      };
    },

    putUserAndMenuList(state, { payload }) {
      return {
        ...state,
        userList: payload.data.userList,
        menuList: payload.data.menuList,
      };
    },

    changeAuthRole(state, { payload }) {
      return {
        ...state,
        tempRole: payload,
      };
    },
  },
};
export default RoleModel;
