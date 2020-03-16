import { stringify } from 'querystring';
import { router } from 'umi';
import { userLogin, getPublicKey } from '@/services/login';
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
            window.location.href = '/';
            return;
          }
        }

        router.replace(redirect || '/');
      }
    },

    logout() {
      const { redirect } = getPageQuery(); // Note: There may be security issues, please note

      if (window.location.pathname !== '/user/login' && !redirect) {
        router.replace({
          pathname: '/user/login',
          search: stringify({
            redirect: window.location.href,
          }),
        });
      }
    },

    *getPublicKey(_, { call, put }) {
      const rsaKey = localStorage.getItem('rsaKey');
      if (!rsaKey) {
        const response = yield call(getPublicKey);
        if (response.status) {
          localStorage.setItem('rsaKey', response.data);
        }
      }
    },
  },
  reducers: {
    changeLoginStatus(state, { payload }) {
      setLoginData(payload.data);
      return {
        ...state,
        loginResult: payload.data
      };
    },
  },
};
export default Model;
