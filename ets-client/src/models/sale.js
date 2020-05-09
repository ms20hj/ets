import { queryTravelAgency, getBaseSaleData} from '@/services/sale';
const SaleModel = {
  namespace: 'sale',
  state: {
    travelAgencyTree: [],
    touristList: [],
    ticketList: [],
    discountList: [],
  },
  effects: {
    *getTravelAgencyTree(_, {call, put}) {
      const response = yield call(queryTravelAgency);
      yield put({
        type: 'putTravelAgency',
        payload: response
      });
    },

    *initBaseSaleData(_, {call, put}) {
      const response = yield call(getBaseSaleData);
      yield put({
        type: 'putBaseSaleData',
        payload: response,
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

    putBaseSaleData(state, {payload}) {
      return {
        ...state,
        touristList: payload.data.touristList,
        ticketList: payload.data.ticketList,
        discountList: payload.data.discountList,
      }
    },


  },
};
export default SaleModel;
