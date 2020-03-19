import { queryTravelAgency, } from '@/services/sale';
const SaleModel = {
  namespace: 'sale',
  state: {
    travelAgencyTree: [],
  },
  effects: {
    *getTravelAgencyTree(_, {call, put}) {
      const response = yield call(queryTravelAgency);
      yield put({
        type: 'putTravelAgency',
        payload: response
      });
    }
  },


  reducers: {
    putTravelAgency(state, {payload}) {
      return {
        ...state,
        travelAgencyTree: payload.data,
      }
    },
  },
};
export default SaleModel;
