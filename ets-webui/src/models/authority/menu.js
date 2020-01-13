import { getWebMenuTree } from '@/services/menu';

const MenuModel = {
  namespace: 'menu',
  state: {
    menuData: [],
  },
  effects: {
    *getMenuData({ _ }, { call, put }) {
      const resp = yield call(getWebMenuTree);
      yield put({
        type: 'putMenuData',
        payload: resp,
      });
    },
  },

  reducers: {
    putMenuData(state, { payload }) {
      return {
        ...state,
        menuData: payload.data,
      };
    },
  },
};
export default MenuModel;
