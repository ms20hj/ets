package com.cms.ets.common.constant;

import java.util.HashMap;
import java.util.Map;

public class DictionaryConstant {

    public static Map<String, String> DICTIONARY_CAHCE_PREFIX_KEY;

    static {
        DICTIONARY_CAHCE_PREFIX_KEY = new HashMap<>();
        DICTIONARY_CAHCE_PREFIX_KEY.put("physical", "门票介质");
        DICTIONARY_CAHCE_PREFIX_KEY.put("deadlineUnit", "门票期限单位");
        DICTIONARY_CAHCE_PREFIX_KEY.put("printMethod", "出票方式");
        DICTIONARY_CAHCE_PREFIX_KEY.put("printTemplate", "打印模板");
        DICTIONARY_CAHCE_PREFIX_KEY.put("discountWay", "优惠方式");
    }
}
