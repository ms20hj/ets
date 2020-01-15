import { routerRedux } from 'dva/router';
import { stringify } from 'querystring';
import { userLogin, getFakeCaptcha, getPublicKey, logout } from '@/services/login';
import { setLoginData } from '@/utils/authority';
import { getPageQuery } from '@/utils/utils';
const Model = {
  namespace: 'login',
  state: {
    loginResult: {
      status: true,
    },
  },
  effects: {
    *getPublicKey(_, { call, put }) {
      const rsaKey = localStorage.getItem('rsaKey');
      if (!rsaKey) {
        const response = yield call(getPublicKey);
        if (response.status) {
          localStorage.setItem('rsaKey', response.data);
        }
      }
    },

    *login({ payload }, { call, put }) {
      const response = yield call(userLogin, payload);
      yield put({
        type: 'changeLoginStatus',
        payload: response,
      }); // Login successfully
      if (response.status) {
        const urlParams = new URL(window.location.href);
        const params = getPageQuery();
        let { redirect } = params;

        if (redirect) {
          const redirectUrlParams = new URL(redirect);

          if (redirectUrlParams.origin === urlParams.origin) {
            redirect = redirect.substr(urlParams.origin.length);

            if (redirect.match(/^\/.*#/)) {
              redirect = redirect.substr(redirect.indexOf('#') + 1);
            }
          } else {
            window.location.href = redirect;
            return;
          }
        }
        yield put(routerRedux.replace(redirect || '/'));
      } else {
      }
    },

    *getCaptcha({ payload }, { call }) {
      yield call(getFakeCaptcha, payload);
    },

    *logout(_, { call, put }) {
      yield call(logout);

      const { redirect } = getPageQuery(); // redirect
      if (window.location.pathname !== '/user/login' && !redirect) {
        yield put(
          routerRedux.replace({
            pathname: '/user/login',
            search: stringify({
              redirect: 'http://' + window.location.host,
            }),
          }),
        );
      }
    },
  },
  reducers: {
    changeLoginStatus(state, { payload }) {
      if (payload.status) {
        setLoginData(payload.data);
      }
      return { ...state, loginResult: payload };
    },
  },
};
export default Model;
