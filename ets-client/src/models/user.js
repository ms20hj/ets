import { queryCurrent, getUserMenu } from '@/services/user';
const UserModel = {
  namespace: 'user',
  state: {
    currentUser: {},
    menuList: [],
  },
  effects: {

    *fetchCurrent(_, { call, put }) {
      const response = yield call(queryCurrent);
      yield put({
        type: 'saveCurrentUser',
        payload: response,
      });
    },

    *fetchMenu(_, {call, put}) {
      const resp = yield call(getUserMenu);
      yield put({
        type: 'putMenuData',
        payload: resp,
      })
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

    putMenuData(state, {payload}){
      return{
        ...state,
        menuList: payload.data,
      }
    }
  },
};
export default UserModel;
